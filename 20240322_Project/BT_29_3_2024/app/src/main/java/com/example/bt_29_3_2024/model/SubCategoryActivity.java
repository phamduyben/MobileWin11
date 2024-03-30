package com.example.bt_29_3_2024.model;

import static com.example.bt_29_3_2024.model.AllSubCategories.subCategories;
import static com.example.bt_29_3_2024.model.CategoryAdapter.ans;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bt_29_3_2024.R;

import java.util.List;

public class SubCategoryActivity extends AppCompatActivity {

    public static List<SubCategory> subCategoryList;
    RecyclerView rcvCategory;
    SubCategoryAdapter subCategoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        AllSubCategories all = new AllSubCategories(ans.getId());
        all.getAllSubCategories();
        subCategoryAdapter = new SubCategoryAdapter(this, subCategories);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rcvCategory.setLayoutManager(gridLayoutManager);
        rcvCategory.setAdapter(subCategoryAdapter);
    }
}