package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.api.APIService;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.CategoryAdapter;
import com.example.myapplication.model.Product;
import com.example.myapplication.model.ProductAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private APIService apiService;
    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;

    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rc_category);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        categoryAdapter = new CategoryAdapter(MainActivity.this, new ArrayList<>());
        recyclerView.setAdapter(categoryAdapter);

        // Khởi tạo Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://app.iotstar.vn/appfoods/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Tạo một instance của ApiService từ Retrofit
        apiService = retrofit.create(APIService.class);

        // Gửi yêu cầu API khi cần
        getCategoryProducts();
    }

    private void getCategoryProducts() {
        // Gọi phương thức API và thực hiện luồng Request
        apiService.getCategoriesAll().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    List<Category> categoryList = response.body(); // Nhận dữ liệu mảng

                    // Khởi tạo Adapter
                    categoryAdapter = new CategoryAdapter(MainActivity.this, categoryList);
                    recyclerView.setHasFixedSize(true);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                            LinearLayoutManager.HORIZONTAL, false);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(categoryAdapter);
                    categoryAdapter.setOnCategoryClickListener(new CategoryAdapter.OnCategoryClickListener() {
                        @Override
                        public void onCategoryClick(Category category) {
                            String categoryId = String.valueOf(category.getId());
                            getCategoryProducts(categoryId);
                        }

                    });
                } else {
                    int statusCode = response.code();
                    // Xử lý lỗi yêu cầu tùy thuộc vào mã trạng thái
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d("logg", t.getMessage());
            }
        });
    }

    private void getCategoryProducts(String categoryId) {
        // Gọi phương thức API và thực hiện luồng Request
        apiService.getCategoryProducts(categoryId).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    List<Product> productList = response.body(); // Nhận dữ liệu mảng sản phẩm

                    // Khởi tạo Adapter cho danh sách sản phẩm và hiển thị
                    productAdapter = new ProductAdapter(MainActivity.this, productList);
                    recyclerView.setAdapter(productAdapter);
                } else {
                    int statusCode = response.code();
                    // Xử lý lỗi yêu cầu tùy thuộc vào mã trạng thái
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("logg", t.getMessage());
            }
        });
    }
}
