package com.example.bai3;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView imageViewBackgound;
    ConstraintLayout constraintLayout;
    ImageButton imageButtonOn;
    ArrayList<Integer> arrayListImage;
    Switch swWifi, swBackground;
    CheckBox cbBackGround;
    RadioGroup rdBackGround;
    Button btnNav2, btnNav3;
    Intent intentNav1, intentNav2;
    int idx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();
        setupProcess();
    }

    private void setupProcess() {
        demo();
        bai1();
        bai2();
        setNavigaion();
    }

    private void setupUI() {
        imageViewBackgound = findViewById(R.id.imageView_background);
        constraintLayout = findViewById(R.id.constraint_layout);
        imageButtonOn = findViewById(R.id.imageButton_on);
        swWifi = findViewById(R.id.switch_wifi);
        swBackground = findViewById(R.id.switch_background);
        cbBackGround = findViewById(R.id.checkBox_background);
        rdBackGround = findViewById(R.id.radiogroup_bg);

        btnNav2 = findViewById(R.id.button_nav_activity2);
        btnNav3 = findViewById(R.id.button_nav_activity3);
    }

    public void demo() {
        // imageview
        imageViewBackgound.setImageResource(R.drawable.vietnam1);

        //imagebutton
        imageButtonOn.setOnClickListener(v -> {
            imageButtonOn.setImageResource(R.drawable.button_on);
            imageButtonOn.getLayoutParams().width = 50;
            imageButtonOn.getLayoutParams().height = 50;
        });

        // switch
        swWifi.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String temp = "Wifi";
            if (isChecked) {
                temp += " ON";
            } else {
                temp += " OFF";
            }
            Toast.makeText(this, temp, Toast.LENGTH_SHORT)
                    .show();
        });

        // checkbox
        cbBackGround.setOnCheckedChangeListener((buttonView, isChecked) -> {
            int temp1 = R.drawable.vietnam1;
            Drawable temp2 = cbBackGround.getBackground();

            if (isChecked) {
                cbBackGround.setBackgroundResource(temp1);
            } else {
                cbBackGround.setBackground(temp2);
            }
        });

        // radiogroup
        rdBackGround.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radio_button_bg) {
                constraintLayout.setBackgroundResource(R.drawable.vietnam3);
            } else if (checkedId == R.id.radio_button_bg1) {
                constraintLayout.setBackgroundResource(R.drawable.vietnam4);
            }
        });

    }

    public void bai1() {
        //background layout
        constraintLayout.setBackgroundColor(Color.BLUE);
        constraintLayout.setBackgroundResource(R.drawable.ic_launcher_background);

        arrayListImage = new ArrayList<>();
        arrayListImage.add(R.drawable.vietnam);
        arrayListImage.add(R.drawable.vietnam1);
        arrayListImage.add(R.drawable.vietnam2);

        Random rd = new Random();
        idx = rd.nextInt(arrayListImage.size());
        constraintLayout.setBackgroundResource(arrayListImage.get(idx));

    }

    public void bai2() {
        swBackground.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                constraintLayout.setBackgroundResource(R.drawable.vietnam5);
            } else {
                constraintLayout.setBackgroundResource(arrayListImage.get(idx));
            }
        });
    }

    public void setNavigaion() {
        btnNav2.setOnClickListener(v -> {
            intentNav1 = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intentNav1);
        });

        btnNav3.setOnClickListener(v -> {
            intentNav2 = new Intent(MainActivity.this, ThirdActivity.class);
            startActivity(intentNav2);
        });
    }
}