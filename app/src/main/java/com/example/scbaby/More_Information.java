package com.example.scbaby;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class More_Information extends AppCompatActivity {

    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_information);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        dialog = new Dialog(this);
    }

    public void onClick(View view) {
        openDialog();
    }

    public void openDialog(){
        dialog.setContentView(R.layout.popup_input_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btnYes=dialog.findViewById(R.id.btnYes);
        Button btnNo = dialog.findViewById(R.id.btnNo);

        btnYes.setOnClickListener(view -> {
            dialog.dismiss();
            Toast.makeText(More_Information.this, "Thank You for your feedback", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(More_Information.this, Feedback_Message.class);
            startActivity(intent);
        });

        btnNo.setOnClickListener(view -> {
            dialog.dismiss();
            Toast.makeText(More_Information.this, "Thank You for your feedback", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(More_Information.this, Feedback_Message.class);
            startActivity(intent);

        });

        dialog.show();
    }
}