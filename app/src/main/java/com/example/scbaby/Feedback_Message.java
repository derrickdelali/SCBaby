package com.example.scbaby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Feedback_Message extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_message);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    public void onClick(View view) {
        Intent intent = new Intent(Feedback_Message.this, Record.class);
        startActivity(intent);
    }

    public void onExit(View view) {
        Intent intent = new Intent(Feedback_Message.this, Record.class);
        startActivity(intent);

    }
}