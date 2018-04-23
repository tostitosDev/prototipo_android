package com.tesis.arduinoconnection;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.tesis.arduinoconnection.models.Fingerprint;
import com.tesis.arduinoconnection.models.Result;

import java.io.IOException;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InscriptionStepTwo extends AppCompatActivity  {
    ImageView back;
    Button registerF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_step_two);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        back = findViewById(R.id.button_back);

        registerF = findViewById(R.id.finish_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        registerF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fingerprint newFinger = new Fingerprint("HXOS", "6.627.031-9", 1);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://b71bed9c.ngrok.io/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                LindiAPI api = retrofit.create(LindiAPI.class);
                Call<Result> response = api.createFingerPrint(newFinger);

                response.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        try {
                            if(response.body() == null) {
                                new AlertDialog.Builder(InscriptionStepTwo.this).setTitle("Error").setMessage("No hemos podido conectar").setNeutralButton("Close", null).show();
                            }else{
                                Result result = response.body();

                                if(result.getResult().equals("not exist")){
                                    new AlertDialog.Builder(InscriptionStepTwo.this).setTitle("Error").setMessage("Trabajador no existe").setNeutralButton("Close", null).show();
                                }else{
                                    if(result.getResult().equals("error")){
                                        new AlertDialog.Builder(InscriptionStepTwo.this).setTitle("Error").setMessage("Error inesperado en el servidor ").setNeutralButton("Close", null).show();
                                    }else{
                                        if(result.getResult().equals("ok")){
                                            Toast toast1 = Toast.makeText(getApplicationContext(),
                                                            "¡Ingresado correctamente!", Toast.LENGTH_SHORT);
                                            toast1.show();
                                            Intent main = new Intent(InscriptionStepTwo.this, MainActivity.class );
                                            startActivity(main);
                                            finish();
                                        }else{
                                            if(result.getResult().equals("exist finger")){
                                                new AlertDialog.Builder(InscriptionStepTwo.this).setTitle("Error").setMessage("Trabajador ya registrado").setNeutralButton("Close", null).show();
                                            }
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {

                    }
                });
            }
        });
    }
    @Override
    public void onBackPressed(){
        Intent step1 = new Intent(InscriptionStepTwo.this, Inscription.class );
        startActivity(step1);
        finish();
    }
}
