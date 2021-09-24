package scbaby.app.scbaby;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioFormat;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.app.scbaby.CryRecognition;
import com.app.scbaby.R;

import java.io.File;

import pl.bclogic.pulsator4droid.library.PulsatorLayout;

public class Record extends AppCompatActivity {

    PulsatorLayout pulsate;
    TextView tvText;
    MediaRecorder mediaRecorder;

    Dialog dialog;

    private final int SPLASH_DISPLAY_LENGTH = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        pulsate = findViewById(R.id.pulsator);
        tvText = findViewById(R.id.tvBegin_Recording);

        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(3000);
        animation.setStartOffset(10);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(Animation.INFINITE);
        tvText.startAnimation(animation);

        dialog = new Dialog(this);
        openDialog();


    }

    public void onClick(View view) {
        try {
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(AudioFormat.ENCODING_PCM_16BIT);
            mediaRecorder.setOutputFile(RecFilePath());
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.prepare();
            mediaRecorder.start();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        StartRecording();
        RecFilePath();
    }

    private void StartRecording(){
        tvText.setText("Listening...");
        pulsate.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mediaRecorder.stop();
                mediaRecorder.release();
                mediaRecorder = null;
                Intent intent =new Intent(Record.this, CryRecognition.class);
                Record.this.startActivity(intent);
                Record.this.finish();
            }
        },SPLASH_DISPLAY_LENGTH);
    }

    private String RecFilePath(){
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File cryDirectory = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(cryDirectory, "scb" + ".wav");
        return file.getPath();
    }

    public void openDialog(){
        dialog.setContentView(R.layout.new_child_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btnYes=dialog.findViewById(R.id.btnYes);
        Button btnNo = dialog.findViewById(R.id.btnNo);

        btnYes.setOnClickListener(view -> {
            dialog.dismiss();
            Intent intent = new Intent(Record.this, New_Child.class);
            startActivity(intent);
        });

        btnNo.setOnClickListener(view -> {
            dialog.dismiss();

        });

        dialog.show();
    }
}