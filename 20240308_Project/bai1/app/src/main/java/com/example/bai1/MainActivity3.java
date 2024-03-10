package com.example.bai1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {
    GridView gridView;
    ArrayList<Subject> arrayList;
    SubjectAdapter adapter;
    EditText editText1;
    Button btnNhap, btnUpdate;
    int idx = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {
                    Insets systemBars =
                            insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );
                    return insets;
                }
        );
        //ánh xạ
        AnhXa();
        //Tạo Adapter
        adapter = new SubjectAdapter(
                MainActivity3.this,
                R.layout.row1_subject,
                arrayList
        );
        //truyền dữ liệu từ adapter ra listview
        gridView.setAdapter(adapter);
    }

    private void AnhXa() {
        gridView = findViewById(R.id.gridview);
        btnNhap = findViewById(R.id.button_add_gridview);
        editText1 = findViewById(R.id.editText_add_subject_gridview);
        btnUpdate = findViewById(R.id.button_update_gridview);
        //Thêm dữ liệu vào List
        arrayList = new ArrayList<>();
        arrayList.add(new Subject("Java", "Java 1", R.drawable.java1));
        arrayList.add(new Subject("C#", "C# 1", R.drawable.c));
        arrayList.add(new Subject("PHP", "PHP 1", R.drawable.php));
        arrayList.add(new Subject("Kotlin", "Kotlin 1", R.drawable.kotlin));
        arrayList.add(new Subject("Dart", "Dart 1", R.drawable.dart));
    }
}
