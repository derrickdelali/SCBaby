package com.example.scbaby;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import pl.bclogic.pulsator4droid.library.PulsatorLayout;

public class Record extends AppCompatActivity {

    PulsatorLayout pulsate;

    TextView tvText;


    private final int SPLASH_DISPLAY_LENGTH = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

         pulsate = findViewById(R.id.pulsator);
         tvText = findViewById(R.id.tvText);

    }

    public void onClick(View view) {

        StartRecording();

    }

    private void StartRecording(){
        tvText.setVisibility(View.INVISIBLE);
        pulsate.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =new Intent(Record.this, Cry_Recognition.class);
                Record.this.startActivity(intent);
                Record.this.finish();
            }
        },SPLASH_DISPLAY_LENGTH);
    }

    }