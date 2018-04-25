package com.tesis.arduinoconnection;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.tesis.arduinoconnection.models.Fingerprint;
import com.tesis.arduinoconnection.models.Mark;
import com.tesis.arduinoconnection.models.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MarkStepTwo extends AppCompatActivity {
    Button send;
    int type_mark_id;
    double latitude;
    double longitude;
    GPSTracker gpsTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_step_two);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        send = findViewById(R.id.finish_button);
        type_mark_id = getIntent().getIntExtra("type_mark_id", 0);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Mark mark = new Mark((float)1.9, (float)1.9, 1, type_mark_id);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://56a745d6.ngrok.io/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                LindiAPI api = retrofit.create(LindiAPI.class);
                Call<Result> response = api.createMark(mark);
                response.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        try {
                            if(response.body() == null) {
                                new AlertDialog.Builder(MarkStepTwo.this).setTitle("Error").setMessage("No es posible conectar con el servidor").setNeutralButton("Cerrar", null).show();
                            }else{
                                Result result = response.body();
                                Log.d("event",result.getResult());
                                if(result.getResult().equals("not exist")){
                                    new AlertDialog.Builder(MarkStepTwo.this).setTitle("Error").setMessage("Trabajador no registrado en la plataforma").setNeutralButton("Cerrar", null).show();
                                }else{
                                    if(result.getResult().equals("mark exist")){
                                        new AlertDialog.Builder(MarkStepTwo.this).setTitle("Error").setMessage("Trabajador ya marcó ese evento.").setNeutralButton("Cerrar", null).show();
                                    }else{
                                        if(result.getResult().equals(("ok"))){
                                            Log.d("inside","inside");
                                            Toast toast1 = Toast.makeText(getApplicationContext(),
                                                    "¡Marcación correcta! Recibirás un correo como comprobante", Toast.LENGTH_SHORT);
                                            toast1.show();
                                            Intent main = new Intent(MarkStepTwo.this, MainActivity.class );
                                            startActivity(main);
                                            finish();
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
}
