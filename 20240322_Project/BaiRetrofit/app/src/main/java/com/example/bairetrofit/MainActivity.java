package com.example.bairetrofit;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bairetrofit.api.APIService;
import com.example.bairetrofit.api.RetrofitClient;
import com.example.bairetrofit.model.Category;
import com.example.bairetrofit.model.CategoryAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView rcCate;
    CategoryAdapter categoryAdapter;
    APIService apiService;
    List<Category> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpUI();
        getCategory();
    }

    private void getCategory() {
        apiService = RetrofitClient.getRetrofit()
                                   .create(APIService.class);
        apiService.getCategoryAll()
                  .enqueue(new Callback<List<Category>>() {
                      @SuppressLint("NotifyDataSetChanged")
                      @Override
                      public void onResponse(
                              @NonNull Call<List<Category>> call,
                              @NonNull Response<List<Category>> response
                      ) {
                          if (response.isSuccessful()) {
                              categoryList = response.body();
                              categoryAdapter = new CategoryAdapter(
                                      MainActivity.this,
                                      categoryList
                              );
                              rcCate.setHasFixedSize(true);

                              RecyclerView.LayoutManager layoutManager =
                                      new LinearLayoutManager(
                                              getApplicationContext(),
                                              LinearLayoutManager.HORIZONTAL,
                                              false
                                      );

                              rcCate.setLayoutManager(layoutManager);
                              rcCate.setAdapter(categoryAdapter);
                              categoryAdapter.notifyDataSetChanged();
                          } else {
                              int statusCode = response.code();
                              Log.e("Error", "onResponse: " + statusCode);
                          }
                      }

                      @Override
                      public void onFailure(
                              @NonNull Call<List<Category>> call,
                              @NonNull Throwable t
                      ) {
                          Log.e("Error", "onFailure: " + t.getMessage());
                      }
                  });
    }

    private void setUpUI() {
        rcCate = findViewById(R.id.rc_category);
    }
}