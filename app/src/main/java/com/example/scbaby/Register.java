package com.example.scbaby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText edtName;
    EditText edtDOB;
    EditText edtPassword;

    Spinner country;
    Spinner sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        country = findViewById(R.id.country);
        sex = findViewById(R.id.sex);

        edtName = findViewById(R.id.edtName);
        edtDOB = findViewById(R.id.edtDOB);
        edtPassword = findViewById(R.id.edtPassword);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.country_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        country.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter_sex = ArrayAdapter.createFromResource(this,R.array.sex_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sex.setAdapter(adapter_sex);


    }

    public void onClick(View view) {

        String Name = edtName.getText().toString().trim();
        String DOB = edtDOB.getText().toString().trim();
        String Pass = edtPassword.getText().toString().trim();

        if (TextUtils.isEmpty(Name)) {
            Toast.makeText(Register.this, "Child's Name Field is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(DOB)){
            Toast.makeText(Register.this, "Date of Birth Field is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Pass)){
            Toast.makeText(Register.this, "Password Field is Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);

    }

    public void onLogin(View view) {
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
    }
}