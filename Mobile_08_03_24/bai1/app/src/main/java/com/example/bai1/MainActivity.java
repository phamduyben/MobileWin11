package com.example.bai1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //khai báo
    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter adapter;
    EditText editText1;
    Button btnNhap, btnUpdate;
    int idx = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

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

        setupUI();
        setupProcess();
    }

    @SuppressLint("WrongViewCast")
    public void setupUI() {
        //ánh xạ
        listView = findViewById(R.id.listview1);
        btnNhap = findViewById(R.id.button_add);
        editText1 = findViewById(R.id.editText_add_subject);
        btnUpdate = findViewById(R.id.button_update);
        //Thêm dữ liệu vào List
        arrayList = new ArrayList<>();
        arrayList.add("Java");
        arrayList.add("C#");
        arrayList.add("PHP");
        arrayList.add("Kotlin");
        arrayList.add("Dart");
        adapter = new ArrayAdapter(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                arrayList
        );
        //truyền dữ liệu từ adapter ra listview
        listView.setAdapter(adapter);
    }

    public void setupProcess() {
        batSuKien();
        addSubject();
        updateSubject();
    }

    public void addSubject() {
        btnNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText1.getText()
                                       .toString();
                arrayList.add(name);
                adapter.notifyDataSetChanged();
            }
        });

    }

    public void updateSubject() {

        //bắt sự kiện trên từng dòng của Listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(
                    AdapterView<?> adapterView, View view, int i, long l
            ) {
//lấy nội dung đua lên edittext
                editText1.setText(arrayList.get(i));
                idx = i;
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.set(idx, editText1.getText()
                                            .toString());
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void batSuKien() {
        //bắt sự kiện click nhanh trên từng dòng của
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(
                    AdapterView<?> adapterView, View view, int i, long l
            ) {
                //code yêu cầu
                //i: trả về vị trí click chuột trên ListView -> i ban đầu =0
                Toast.makeText(
                             MainActivity.this,
                             "" + i,
                             Toast.LENGTH_SHORT
                     )
                     .show();
            }
        });

        //bắt sự kiện click giữ trên từng dòng của Listview
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(
                    AdapterView<?> adapterView, View view, int i, long l
            ) {
                //code yêu cầu
                //i: trả về vị trí click chuột trên ListView -> i ban đầu =0
                Toast.makeText(
                             MainActivity.this,
                             "Bạn đang " + "nhấn giữ " + i + "-" + arrayList.get(i),
                             Toast.LENGTH_SHORT
                     )
                     .show();
                return false;
            }
        });
    }
}