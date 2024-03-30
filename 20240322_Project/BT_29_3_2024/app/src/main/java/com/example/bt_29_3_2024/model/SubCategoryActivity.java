package com.example.bt_29_3_2024.model;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bt_29_3_2024.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class SubCategoryActivity extends AppCompatActivity {

    public static List<SubCategory> subCategoryList;
    RecyclerView rcvCategory;
    SubCategoryAdapter subCategoryAdapter;
    ImageButton imageButton;
    ArrayList<SubCategory> temp = new ArrayList<>();
    Executor executor;
    private int cnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        rcvCategory = findViewById(R.id.rcv_category);
        imageButton = findViewById(R.id.imgBtn_syc);

        temp.add(new SubCategory(
                "1",
                "SubCategory 1",
                "https://www.themealdb.com/images/media/meals/020z181619788503.jpg",
                "1",
                "1"
        ));
        subCategoryAdapter = new SubCategoryAdapter(this, temp);
        rcvCategory.setHasFixedSize(true);

//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(
                        getApplicationContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                );

        rcvCategory.setLayoutManager(layoutManager);
        rcvCategory.setAdapter(subCategoryAdapter);

        imageButton.setOnClickListener(v -> getData());
        subCategoryAdapter.notifyDataSetChanged();
    }

    public void getData() {
        new AllSubCategories(1).getAllSubCategories();
        temp.clear();
        temp.addAll(AllSubCategories.ans);
        if (cnt != 0) {
            subCategoryAdapter.notifyDataSetChanged();
        }
        cnt++;
    }
}