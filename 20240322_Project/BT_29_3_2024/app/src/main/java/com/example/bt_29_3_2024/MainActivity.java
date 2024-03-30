package com.example.bt_29_3_2024;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bt_29_3_2024.api.APIService;
import com.example.bt_29_3_2024.api.RetrofitClient;
import com.example.bt_29_3_2024.model.Category;
import com.example.bt_29_3_2024.model.CategoryAdapter;
import com.example.bt_29_3_2024.model.SubCategory;
import com.example.bt_29_3_2024.model.SubCategoryActivity;
import com.example.bt_29_3_2024.model.SubCategoryAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static final String NEXT_SCREEN = "details_screen";
    public static int ok = -1;
    protected final int idCategory = 1;
    RecyclerView rcCate;
    CategoryAdapter categoryAdapter;
    APIService apiService;
    List<Category> categoryList;
    List<SubCategory> subCategoryList;
    SubCategoryAdapter subCategoryAdapter;
//    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpUI();
        getCategory();

//        getAllSubCategories(1);
//        addEventNavigation();
    }

    private void getCategory() {
        apiService = RetrofitClient.getRetrofit()
                                   .create(APIService.class);
        apiService.getCategoryAll()
                  .enqueue(new Callback<List<Category>>() {
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
                              categoryAdapter.setOnClickListener((position, model) -> {
                                  ok = model.getId();
                                  Log.e("TAG", "onResponse: " + ok);
                                  Intent intent = new Intent(
                                          MainActivity.this,
                                          SubCategoryActivity.class
                                  );
                                  // Passing the data to the
                                  // EmployeeDetails Activity
                                  intent.putExtra(NEXT_SCREEN, ok);
                                  startActivity(intent);
                              });
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
//        bottomNavigationView = findViewById(R.id.btn_navigation);
    }

//    public void addEventNavigation() {
//        bottomNavigationView.setOnClickListener(v -> {
//            int itemId = v.getId();
//            if (itemId == R.id.home) {
//                Log.e("TAG", "addEventNavigation: " + itemId);
//            } else if (itemId == R.id.profile) {
//                Log.e("TAG", "addEventNavigation: " + itemId);
//            } else if (itemId == R.id.cart) {
//                Log.e("TAG", "addEventNavigation: " + itemId);
//            } else if (itemId == R.id.setting) {
//                Log.e("TAG", "addEventNavigation: " + itemId);
//            } else if (itemId == R.id.support) {
//                Log.e("TAG", "addEventNavigation: " + itemId);
//            }
//        });
//    }
}