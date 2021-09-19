package scbaby.app.scbaby;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.scbaby.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Register extends AppCompatActivity {

    EditText edtName;
    EditText edtAge;
    EditText edtEmail;
    EditText edtPassword;
    Button btnSignup;

    CheckBox chbox;

    EditText edtcountry;

    FirebaseAuth Auth;
    DatabaseReference Database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        Auth = FirebaseAuth.getInstance();
        Database = FirebaseDatabase.getInstance().getReference().child("Users");

        edtcountry = findViewById(R.id.country);
        chbox = findViewById(R.id.chBox);

        edtName = findViewById(R.id.edtName);
        edtAge = findViewById(R.id.edtAge);
        edtPassword = findViewById(R.id.edtPassword);
        edtEmail = findViewById(R.id.edtEmail);
        btnSignup = findViewById(R.id.btnSignup);

    }

    public void onClick(View view) {
        String Name = edtName.getText().toString().trim();
        String Age = edtAge.getText().toString().trim();
        String Pass = edtPassword.getText().toString().trim();
        String Country = edtcountry.getText().toString().trim();
        String Email = edtEmail.getText().toString().trim();


        if (TextUtils.isEmpty(Name)) {
            Toast.makeText(Register.this, "Child's Name Field is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Email)){
            Toast.makeText(Register.this, "Email Field is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Age)){
            Toast.makeText(Register.this, "Age Field is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Pass)){
            Toast.makeText(Register.this, "Password Field is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Pass.length() < 6){
            Toast.makeText(Register.this, "Password should be at least 6 characters long", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Country)){
            Toast.makeText(Register.this, "Country Field is Empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Auth.createUserWithEmailAndPassword(Email, Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    String user_id = Auth.getCurrentUser().getUid();
                    DatabaseReference current_user_db = Database.child(user_id);
                    current_user_db.child("Name").setValue(Name);
                    current_user_db.child("Date of Birth").setValue(Age);
                    current_user_db.child("Country").setValue(Country);

                    Intent intent = new Intent(Register.this, Disclaimer.class);
                    startActivity(intent);
                }
            }
        });


    }

    public void onLogin(View view) {
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
    }

}