package com.example.bai2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class cau4 extends AppCompatActivity {
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
                ArrayList<Integer> a = new ArrayList<>();
                for (int i = 0; i < num; i++) {
                    a.add(rd.nextInt(10));
                }
                
                String ans = "ArrayList Square : ";
                for (int i = 0; i < num; i++) {
                    if (isSquare(a.get(i))) {
                        ans += a.get(i)
                                .toString() + " ";
                        Toast.makeText(
                                     getApplicationContext(),
                                     "This is square : " + a.get(i),
                                     Toast.LENGTH_LONG
                             )
                             .show();
                    }
                }
                txt.setText(ans);
            }
        });
    }

    public boolean isSquare(Integer x) {
        Double ans = Math.sqrt(x);
        return ans * ans == x;
    }

}