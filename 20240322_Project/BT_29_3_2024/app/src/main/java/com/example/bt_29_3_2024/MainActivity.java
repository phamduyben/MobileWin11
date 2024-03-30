package com.example.bt_29_3_2024;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bt_29_3_2024.api.APIService;
import com.example.bt_29_3_2024.api.RetrofitClient;
import com.example.bt_29_3_2024.model.Category;
import com.example.bt_29_3_2024.model.CategoryAdapter;
import com.example.bt_29_3_2024.model.SubCategory;
import com.example.bt_29_3_2024.model.SubCategoryAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static int ok = -1;
    RecyclerView rcCate;
    CategoryAdapter categoryAdapter;
    APIService apiService;
    List<Category> categoryList;
    List<SubCategory> subCategoryList;
    SubCategoryAdapter subCategoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpUI();
        getCategory();

//        getAllSubCategories(1);

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
                                              LinearLayoutManager.VERTICAL,
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

//    public void getAllSubCategories(int idCategory) {
//        RequestBody requestBody = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("idcategory", String.valueOf(idCategory))
//                .build();
//
//        apiService = RetrofitClient.getRetrofit()
//                                   .create(APIService.class);
//
//        apiService.getSubCategory(idCategory)
//                  .enqueue(new Callback<ResponseBody>() {
//                      @Override
//                      public void onResponse(
//                              Call<ResponseBody> call, Response<ResponseBody> response
//                      ) {
//                          if (response.isSuccessful()) {
//                              try {
//                                  String json = response.body()
//                                                        .string();
//                                  Log.e("Response", "onResponse: " + response.body()
//                                                                             .string());
//
//
////                                  parseJson(json);
//                              } catch (Exception e) {
//                                  e.printStackTrace();
//                              }
//                          } else {
//                              Log.e("Error", "onResponse: " + response.code());
//                          }
//                      }
//
//                      @Override
//                      public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//                      }
//                  });
//    }

    private void setUpUI() {
        rcCate = findViewById(R.id.rc_category);
    }

    public void setSubCategoryAdapter() {
        subCategoryAdapter = new SubCategoryAdapter(this, subCategoryList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rcCate.setLayoutManager(gridLayoutManager);
        rcCate.setAdapter(subCategoryAdapter);
    }

}