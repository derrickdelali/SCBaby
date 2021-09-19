package scbaby.app.scbaby;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.app.scbaby.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class New_Child extends AppCompatActivity {

    FirebaseAuth Auth;
    DatabaseReference Database;

    EditText edtName;
    EditText edtDOB;
    EditText edtGender;

    FirebaseAuth.AuthStateListener AuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_child);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        edtGender = findViewById(R.id.sex);
        edtName = findViewById(R.id.edtName);
        edtDOB = findViewById(R.id.edtDOB);

        Auth = FirebaseAuth.getInstance();
        Database = FirebaseDatabase.getInstance().getReference().child("Users");


        Auth = FirebaseAuth.getInstance();
        AuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null){

                    Intent intent = new Intent(New_Child.this, Login.class);
                    startActivity(intent);

                }else{

                }
            }
        };

        edtDOB.addTextChangedListener(new TextWatcher() {

            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]", "");
                    String cleanC = current.replaceAll("[^\\d.]", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        if(mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon-1);

                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    edtDOB.setText(current);
                    edtDOB.setSelection(sel < current.length() ? sel : current.length());

                }
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        Auth.addAuthStateListener(AuthListener);
    }


    public void onCancel(View view) {
        Intent intent = new Intent(New_Child.this, Record.class);
        startActivity(intent);
    }

    public void onContinue(View view) {

        String Name = edtName.getText().toString().trim();
        String DOB = edtDOB.getText().toString().trim();
        String Gender = edtGender.getText().toString().trim();

        if (TextUtils.isEmpty(Name)) {
            Toast.makeText(New_Child.this, "Child's Name Field is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(DOB)){
            Toast.makeText(New_Child.this, "Date of Birth Field is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(Gender)){
            Toast.makeText(New_Child.this, "Gender Field is Empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (Gender.equals("male")){
        }
        else if (Gender.equals("Male")){
        }
        else if (Gender.equals("Female")){
        }
        else if (Gender.equals("female")){
        }
        else{
            Toast.makeText(New_Child.this, "You have entered an Invalid Gender", Toast.LENGTH_SHORT).show();
            return;
        }

        String user_id = Auth.getCurrentUser().getUid();
        DatabaseReference current_user_db = Database.child(user_id);
        current_user_db.child("Name").setValue(Name);
        current_user_db.child("Date of Birth").setValue(DOB);
        current_user_db.child("Gender").setValue(Gender);

        Intent intent = new Intent(New_Child.this, Record.class);
        startActivity(intent);

    }

}