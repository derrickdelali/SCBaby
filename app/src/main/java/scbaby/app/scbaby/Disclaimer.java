package scbaby.app.scbaby;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.app.scbaby.R;

public class Disclaimer extends AppCompatActivity {

    private int Record_Audio = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    public void onClick(View view) {
        if (ContextCompat.checkSelfPermission(Disclaimer.this,
                Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED){

            Intent intent = new Intent(Disclaimer.this, Record.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "You denied microphone permissions", Toast.LENGTH_SHORT).show();
            Request_record_audioPermission();
        }
    }

    private void Request_record_audioPermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)){

            new AlertDialog.Builder(this)
                    .setTitle("MICROPHONE ACCESS NEEDED")
                    .setMessage("This permission is needed to record your baby's cries")
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(Disclaimer.this, new String[]{Manifest.permission.RECORD_AUDIO}, Record_Audio);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            dialogInterface.dismiss();

                        }
                    })
                    .create().show();
        }else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, Record_Audio);
        }
    }
}