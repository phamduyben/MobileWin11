package com.example.bt_29_3_2024.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.bt_29_3_2024.R;
import com.example.bt_29_3_2024.api.APIService;
import com.example.bt_29_3_2024.api.RetrofitClient;
import com.example.bt_29_3_2024.helpers.SubCategoryAdapter;
import com.example.bt_29_3_2024.models.SubCategory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubCategoryActivity extends AppCompatActivity {
    RecyclerView rcvCategory;
    SubCategoryAdapter subCategoryAdapter;
    TextView tvTitle;
    APIService apiService;
    MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        setUpUI();

        getAllSubCategories();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setUpUI() {
        // Hook UI elements
        tvTitle = findViewById(R.id.txt_category);
        bottomNavigation = findViewById(R.id.me);

        // set navagation bar
        setUpNavagationBar();
    }

    public void setUpNavagationBar() {
        // Set title
        tvTitle.setText(getIntent().getStringExtra("namecategory"));

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.infoprofile));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.shopping));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.blurcircular));
        bottomNavigation.add(new MeowBottomNavigation.Model(5, R.drawable.settings));
        bottomNavigation.setOnClickMenuListener(model -> {
            Toast.makeText(
                         SubCategoryActivity.this,
                         "Item click" + model.getId(),
                         Toast.LENGTH_SHORT
                 )
                 .show();
            return null;
        });

        bottomNavigation.setOnShowListener(model -> {
            String name;
            if (model.getId() == 1) {
                name = "Home";
            } else if (model.getId() == 2) {
                name = "Profile";
            } else if (model.getId() == 3) {
                name = "Shopping";
            } else if (model.getId() == 4) {
                name = "Support";
            } else if (model.getId() == 5) {
                name = "Settings";
            }
            return null;
        });
    }

    public void getAllSubCategories() {
        apiService = RetrofitClient.getRetrofit()
                                   .create(APIService.class);

        apiService.getSubCategory(getIntent().getIntExtra("idcategory", -1))
                  .enqueue(new Callback<ResponseBody>() {
                      @Override
                      public void onResponse(
                              Call<ResponseBody> call, Response<ResponseBody> response
                      ) {
                          if (response.isSuccessful()) {
                              try {
                                  String json = response.body()
                                                        .string();
                                  ArrayList<SubCategory> subCategories = parseJson(json);
                                  setRecyclerView(subCategories);
                              } catch (Exception e) {
                                  e.printStackTrace();
                              }
                          } else {
                              Log.e("Error", "onResponse2: " + response.code());
                          }
                      }

                      @Override
                      public void onFailure(Call<ResponseBody> call, Throwable t) {
                          Log.e("Error", "onFailure: " + t.getMessage());
                      }
                  });

    }

    public void setRecyclerView(ArrayList<SubCategory> subCategories) {
        rcvCategory = findViewById(R.id.rcv_category);

        subCategoryAdapter =
                new SubCategoryAdapter(
                        SubCategoryActivity.this,
                        subCategories
                );

        rcvCategory.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager =
                new GridLayoutManager(getApplicationContext(), 2);

        rcvCategory.setLayoutManager(layoutManager);
        rcvCategory.setAdapter(subCategoryAdapter);
    }

    public ArrayList<SubCategory> parseJson(String json) {
        Type founderListType = new TypeToken<ArrayList<SubCategory>>() {}.getType();
        Gson gson = new Gson();
        return gson.fromJson(json, founderListType);
    }
}
