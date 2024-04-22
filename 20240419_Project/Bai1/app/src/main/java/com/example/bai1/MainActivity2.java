package com.example.bai1;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.bai1.api.APIService;
import com.example.bai1.helpers.VideosAdapter;
import com.example.bai1.model.MessageVideoModel;
import com.example.bai1.model.VideoModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private VideosAdapter videosAdapter;
    private List<VideoModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow()
            .setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
            );
        setContentView(R.layout.activity_main2);
        viewPager2 = findViewById(R.id.vpager);
        list = new ArrayList<>();
        getVideos();
    }

    private void getVideos() {
        APIService.servieapi
                .getVideos()
                .enqueue(new Callback<MessageVideoModel>() {
                    @Override
                    public void onResponse(
                            Call<MessageVideoModel> call,
                            Response<MessageVideoModel> response
                    ) {
                        list = response.body()
                                       .getResult();
                        videosAdapter = new VideosAdapter(
                                getApplicationContext(),
                                list
                        );
                        viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
                        viewPager2.setAdapter(videosAdapter);
                    }

                    @Override
                    public void onFailure(
                            Call<MessageVideoModel> call,
                            Throwable t
                    ) {
                        Log.d("TAG", t.getMessage());
                    }
                });
    }
}