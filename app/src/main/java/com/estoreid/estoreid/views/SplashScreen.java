package com.estoreid.estoreid.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.estoreid.estoreid.R;

public class SplashScreen extends AppCompatActivity {

    ProgressBar progressBar;
    private int pStatus = 0;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init();
    }

    private void init() {

        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        handler = new Handler();
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (pStatus < 100) {

                    pStatus++;

                    if(pStatus == 99){
                        Intent intent = new Intent(SplashScreen.this,Login.class);
                        startActivity(intent);
                        finish();
                    }
                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            progressBar.setProgress(pStatus);
                            progressBar.setSecondaryProgress(pStatus+25);
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        // Just to display the progress slowly
                        Thread.sleep(50); //thread will take approx 3 seconds to finish
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
