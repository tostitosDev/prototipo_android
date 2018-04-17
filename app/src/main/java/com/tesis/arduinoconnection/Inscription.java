package com.tesis.arduinoconnection;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class Inscription extends AppCompatActivity {
    ImageView next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        next = findViewById(R.id.next_button_register);

       next.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent step2 = new Intent(Inscription.this, InscriptionStepTwo.class );
               startActivity(step2);
               finish();
           }
       });
    }
}