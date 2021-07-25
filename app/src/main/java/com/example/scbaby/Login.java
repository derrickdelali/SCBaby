package com.example.scbaby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText edtLogin_name;
    EditText edtLogin_DOB;
    EditText edtLogin_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        edtLogin_name = findViewById(R.id.edtLogin_name);
        edtLogin_Password =findViewById(R.id.edtLogin_Password);

    }

    public void onClick(View view) {

        String Login_name = edtLogin_name.getText().toString().trim();
        String Login_Pass = edtLogin_Password.getText().toString().trim();

        if (TextUtils.isEmpty(Login_name)) {
            Toast.makeText(Login.this, "Child's Name Field is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Login_Pass)){
            Toast.makeText(Login.this, "Password Field is Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(Login.this, Disclaimer.class);
        startActivity(intent);
    }

    public void onRegister(View view) {
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
    }
}