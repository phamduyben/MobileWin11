package com.example.myapplication.api;

import com.example.myapplication.model.Category;
import com.example.myapplication.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("categories.php")
    Call<List<Category>> getCategoriesAll();

    @GET("getcategory.php")
    Call<List<Product>> getCategoryProducts(@Query("idcategory") String categoryId);

}
