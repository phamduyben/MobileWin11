package com.example.bt_29_3_2024.model;

import static com.example.bt_29_3_2024.MainActivity.NEXT_SCREEN;
import static com.example.bt_29_3_2024.MainActivity.cnt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bt_29_3_2024.R;

import java.util.ArrayList;

public class SubCategoryActivity extends AppCompatActivity {
    RecyclerView rcvCategory;
    SubCategoryAdapter subCategoryAdapter;
    ImageButton imageButton;
    ArrayList<SubCategory> temp = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        setUpUI();
        imageButton.setOnClickListener(v -> getData());
    }

    @SuppressLint("NotifyDataSetChanged")
    public void getData() {
        Intent intent = getIntent();
        int idCategory = intent.getIntExtra(NEXT_SCREEN, 1);
        new AllSubCategories(idCategory).getAllSubCategories();
        temp.clear();
        temp.addAll(AllSubCategories.ans);

        if (cnt != 0) {
            subCategoryAdapter.notifyDataSetChanged();
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setUpUI() {
        rcvCategory = findViewById(R.id.rcv_category);
        imageButton = findViewById(R.id.imgBtn_syc);

        temp.add(new SubCategory(
                "1",
                "SubCategory 1",
                "https://www.themealdb.com/images/media/meals/020z181619788503.jpg",
                "1",
                "1"
        ));
        temp.add(new SubCategory(
                "1",
                "SubCategory 1",
                "https://i.pinimg.com/originals/f6/5c/a1/f65ca179444200f380144b3ad432c800.jpg",
                "1",
                "1"
        ));

        subCategoryAdapter = new SubCategoryAdapter(this, temp);
        rcvCategory.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager =
                new GridLayoutManager(getApplicationContext(), 2);

        rcvCategory.setLayoutManager(layoutManager);
        rcvCategory.setAdapter(subCategoryAdapter);

        subCategoryAdapter.notifyDataSetChanged();
    }
}