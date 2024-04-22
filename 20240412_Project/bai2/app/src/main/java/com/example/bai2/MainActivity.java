package com.example.bai2;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bai2.Helpers.LinePagerIndicatorDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SearchView searchView;
    private RecyclerView rcIcon;
    private IconAdapter iconAdapter;
    private ArrayList<IconModel> arrayList1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void initView() {
        rcIcon = findViewById(R.id.rcIcon);
        arrayList1.add(new IconModel(R.drawable.icon6, "dfdf"));
        arrayList1 = new ArrayList<>();
        arrayList1.add(new IconModel(R.drawable.icon1, "jfdjfdjf djfdh"));
        arrayList1.add(new IconModel(R.drawable.icon2, "sdfdf sfdsf"));
        arrayList1.add(new IconModel(R.drawable.icon3, "sdfdf sfds"));
        arrayList1.add(new IconModel(R.drawable.icon4, "dfgfhyh sxdff"));
        arrayList1.add(new IconModel(R.drawable.icon5, "jfdjfdjf djfdh"));
        arrayList1.add(new IconModel(R.drawable.icon6, "sdfdf sfdsf"));
        arrayList1.add(new IconModel(R.drawable.icon7, "sdfdf sfds"));
        arrayList1.add(new IconModel(R.drawable.icon8, "dfgfhyh sxdff"));
        arrayList1.add(new IconModel(R.drawable.icon9, "dfgfhyh sxdff"));
        arrayList1.add(new IconModel(R.drawable.icon1, "jfdjfdjf djfdh"));
        arrayList1.add(new IconModel(R.drawable.icon2, "sdfdf sfdsf"));
        arrayList1.add(new IconModel(R.drawable.icon3, "sdfdf sfds"));
        arrayList1.add(new IconModel(R.drawable.icon4, "dfgfhyh sxdff"));
        arrayList1.add(new IconModel(R.drawable.icon5, "jfdjfdjf djfdh"));
        arrayList1.add(new IconModel(R.drawable.icon7, "sdfdf sfds"));
        arrayList1.add(new IconModel(R.drawable.icon8, "dfgfhyh sxdff"));
        arrayList1.add(new IconModel(R.drawable.icon9, "dfgfhyh sxdff"));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(
                MainActivity.this,
                2,
                GridLayoutManager.HORIZONTAL,
                false
        );
        rcIcon.setLayoutManager(gridLayoutManager);
        iconAdapter = new IconAdapter(getApplicationContext(), arrayList1);
        rcIcon.setAdapter(iconAdapter);
        rcIcon.addItemDecoration(new LinePagerIndicatorDecoration());
        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterListener(newText);
                return true;
            }
        });
    }

    private void filterListener(String text) {
        List<IconModel> list = new ArrayList<>();
        for (IconModel iconModel : arrayList1) {
            if (iconModel.getDesc()
                         .toLowerCase()
                         .contains(text.toLowerCase())) {
                list.add(iconModel);
            }
        }

        if (list.isEmpty()) {
            Toast.makeText(this, "Khong co do lieu", Toast.LENGTH_SHORT)
                 .show();
        } else {
            iconAdapter.setListenerList(list);
        }
    }
}