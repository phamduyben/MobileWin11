package com.example.bai3;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    ProgressBar pbLoad, pbLoad1;
    ImageView imgVn;
    SeekBar seekBar;
    Button btnNav1, btnNav3;
    Intent intentNav1, intentNav3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        setupUI();
        setupProcess();
    }

    private void setupUI() {
        pbLoad = findViewById(R.id.progressBar_load);
        pbLoad1 = findViewById(R.id.progressBar_load1);
        imgVn = findViewById(R.id.imageView_vn);
        seekBar = findViewById(R.id.seekBar);

        btnNav1 = findViewById(R.id.button_nav_activity1);
        btnNav3 = findViewById(R.id.button_nav_activity3);
    }

    private void setupProcess() {
        demo();
        setNavigaion();
    }

    private void demo() {
        // progressbar
        pbLoad1.setOnClickListener(v -> {
            int pos = pbLoad1.getProgress();
            pbLoad1.setProgress(pos + 10);
        });

        pbLoad.setOnClickListener(v -> {
            int cur = pbLoad.getProgress();
            if (cur >= pbLoad.getMax())
                cur = 0;
            pbLoad.setProgress(cur + 10);
        });

        imgVn.setOnClickListener(v -> {
            CountDownTimer countDownTimer = new CountDownTimer(10000, 100) {

                @Override
                public void onTick(long millisUntilFinished) {
                    int cur = pbLoad1.getProgress();
                    if (cur >= pbLoad1.getMax()) {
                        cur = 0;
                    }
                    pbLoad1.setProgress(cur + 10);
                }

                @Override
                public void onFinish() {
                    Toast.makeText(SecondActivity.this, "Hết giờ", Toast.LENGTH_LONG)
                            .show();
                }
            };
            countDownTimer.start();
        });

        setupSeekbar();
    }

    public void setupSeekbar() {
        // seekbar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //progress: giá trị của seekbar
                Log.d("AAA", "Giá trị:" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d("AAA", "Start");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("AAA", "Stop");
            }
        });
    }

    public void setNavigaion() {
        btnNav1.setOnClickListener(v -> {
            intentNav1 = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(intentNav1);
        });

        btnNav3.setOnClickListener(v -> {
            intentNav3 = new Intent(SecondActivity.this, ThirdActivity.class);
            startActivity(intentNav3);
        });
    }
}