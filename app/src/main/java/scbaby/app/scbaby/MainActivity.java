package scbaby.app.scbaby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.app.scbaby.R;

public class MainActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);


    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            Intent mainIntent =new Intent(MainActivity.this, Login.class);
             MainActivity.this.startActivity(mainIntent);
             MainActivity.this.finish();
        }
    },SPLASH_DISPLAY_LENGTH);

    }


}