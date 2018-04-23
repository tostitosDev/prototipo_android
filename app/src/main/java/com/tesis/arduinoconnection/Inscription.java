package com.tesis.arduinoconnection;

import android.app.AlertDialog;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Inscription extends AppCompatActivity {
    ImageView next;
    ImageView back;
    EditText rut;
    String rut_string;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        next = findViewById(R.id.next_button_register);
        back = findViewById(R.id.button_back);
        rut  = findViewById(R.id.input_rut);

        rut.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String valortext = rut.getText().toString().trim();
                if(valortext.equals(""))
                {
                    valortext = "0";
                }
                else
                {
                    valortext = format_rut(valortext);
                }
            }
        });
        next.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v) {
               rut_string = rut.getText().toString();
               Log.d("rut", rut_string);
               if(rut_string.equals("") || rut_string.equals("0") || rut_string.length() <=7 || rut_string.length() >= 10){
                   new AlertDialog.Builder(Inscription.this).setTitle("Error").setMessage("Ingrese rut válido").setNeutralButton("Close", null).show();
               }else{
                   rut_string = format_rut(rut_string);
                   Intent step2 = new Intent(Inscription.this, InscriptionStepTwo.class );
                   step2.putExtra("rut", rut_string);
                   startActivity(step2);
                   finish();
               }
            }
        });
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent main = new Intent(Inscription.this, MainActivity.class );
        startActivity(main);
        finish();
    }

    public String format_rut(String rut) {
        if (rut ==""){
            return "";
        }
        int cont = 0;
        String format;
        rut = rut.replace(".", "");
        rut = rut.replace("-", "");
        format = "-" + rut.substring(rut.length() - 1);
        for (int i = rut.length() - 2; i >= 0; i--) {
            format = rut.substring(i, i + 1) + format;
            cont++;
            if (cont == 3 && i != 0) {
                format = "." + format;
                cont = 0;
            }
        }
        return format;
    }
}
