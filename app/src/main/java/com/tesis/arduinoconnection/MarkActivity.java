package com.tesis.arduinoconnection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class MarkActivity extends AppCompatActivity {
    ImageView back;
    Button mark_in;
    Button mark_out;
    Button mark_out_collation;
    Button mark_in_collation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        back = findViewById(R.id.button_back);
        mark_in = findViewById(R.id.button_in_mark);
        mark_out = findViewById(R.id.button_out_mark);
        mark_out_collation = findViewById(R.id.button_out_collation);
        mark_in_collation = findViewById(R.id.button_in_collation);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mark_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mark_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mark_in_collation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mark_out_collation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    @Override
    public void onBackPressed(){
        Intent main = new Intent(MarkActivity.this, MainActivity.class );
        startActivity(main);
        finish();
    }
}
