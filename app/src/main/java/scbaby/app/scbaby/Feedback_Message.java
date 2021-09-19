package scbaby.app.scbaby;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app.scbaby.R;
import com.google.firebase.auth.FirebaseAuth;

public class Feedback_Message extends AppCompatActivity {

    FirebaseAuth Auth;
    FirebaseAuth.AuthStateListener AuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_message);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        Auth = FirebaseAuth.getInstance();
        AuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null){
                    Intent intent = new Intent(Feedback_Message.this, Login.class);
                    startActivity(intent);
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        Auth.addAuthStateListener(AuthListener);
    }

    public void onClick(View view) {
        Intent intent = new Intent(Feedback_Message.this, Record.class);
        startActivity(intent);
    }

    public void onExit(View view) {
        Auth.signOut();

    }
}