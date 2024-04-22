package com.vanessa.bai3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ViewFlipperActivity extends AppCompatActivity {
    ViewFlipper viewFlipperMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper);
        viewFlipperMain = findViewById(R.id.viewFlipperMain);
        ActionViewFlipperMain();
    }
    private void ActionViewFlipperMain(){
        List<String>arrayListFlipper = new ArrayList<>();
        arrayListFlipper.add("http://app.iostar.vn/appfoods/flipper/quangcao.png");
        arrayListFlipper.add("http://app.iostar.vn/appfoods/flipper/coffee.jng");
        arrayListFlipper.add("http://app.iostar.vn/appfoods/flipper/companypizza.jpeg");
        arrayListFlipper.add("http://app.iostar.vn/appfoods/flipper/themoingon.jpeg");
        for(int i = 0; i<arrayListFlipper.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(arrayListFlipper.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipperMain.addView(imageView);

        }
        viewFlipperMain.setFlipInterval(3000);
        viewFlipperMain.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipperMain.setAnimation(slide_in);
        viewFlipperMain.setAnimation(slide_out);

    }

}