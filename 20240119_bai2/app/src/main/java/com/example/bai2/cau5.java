package com.example.bai2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class cau5 extends AppCompatActivity {
    TextView txt;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau4);

        init();
        process();
    }

    public void init() {
        txt = (TextView) findViewById(R.id.textView2);
        btn = (Button) findViewById(R.id.button2);
    }

    public void process() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rd = new Random();
                int num = rd.nextInt(10);
                txt.setText(num + " ");
            }
        });
    }
}