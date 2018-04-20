package com.tesis.arduinoconnection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.tesis.arduinoconnection.models.Fingerprint;

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
                Fingerprint newFinger = new Fingerprint("HXOS", "18.295.066-1", 1);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://c7340024.ngrok.io/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                LindiAPI api = retrofit.create(LindiAPI.class);
                Call<ResponseBody> response = api.createFingerPrint(newFinger);

                response.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            if(response.body() == null) {
                                Log.d("error", "error");
                            }else{
                                Log.d("test",response.body().string());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

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
