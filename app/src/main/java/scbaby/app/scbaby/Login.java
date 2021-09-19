package scbaby.app.scbaby;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.app.scbaby.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    FirebaseAuth Auth;
    DatabaseReference Database;

    EditText edtLogin_email;
    EditText edtLogin_Password;
    FirebaseAuth.AuthStateListener AuthListener;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        Auth = FirebaseAuth.getInstance();
        Database = FirebaseDatabase.getInstance().getReference().child("Users");

        edtLogin_email = findViewById(R.id.edtLogin_email);
        edtLogin_Password =findViewById(R.id.edtLogin_Password);


        Auth = FirebaseAuth.getInstance();
        AuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null){

                }else{
                    Intent intent = new Intent(Login.this, Record.class);
                    startActivity(intent);
                }
            }
        };

    }

    public void progressDialog(){
        progressDialog = new ProgressDialog(Login.this);
        progressDialog.setMessage("Signing In... Please Wait");
        progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Auth.addAuthStateListener(AuthListener);
    }

    public void onLogin(View view) {

        String Login_name = edtLogin_email.getText().toString().trim();
        String Login_Pass = edtLogin_Password.getText().toString().trim();

        if (TextUtils.isEmpty(Login_name)) {
            Toast.makeText(Login.this, "Child's Name Field is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Login_Pass)){
            Toast.makeText(Login.this, "Password Field is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            progressDialog();
            Auth.signInWithEmailAndPassword(Login_name, Login_Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){

                        String user_id = Auth.getCurrentUser().getUid();
                        Database.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.hasChild(user_id)){
                                    Intent intent = new Intent(Login.this, Disclaimer.class);
                                    startActivity(intent);

                                }else{
                                    Toast.makeText(Login.this, "You are not registered yet", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                    }else{
                        Toast.makeText(Login.this, "Error Login", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            });
        }

    }

    public void onRegister(View view) {
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
    }

}