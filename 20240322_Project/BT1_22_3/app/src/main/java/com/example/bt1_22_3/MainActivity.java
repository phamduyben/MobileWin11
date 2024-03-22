package com.example.bt1_22_3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    ItemAdapter itemAdapter;
    ArrayList<String> rowsArrayList = new ArrayList<>();
    boolean isLoading = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        populateData();
        initAdapter();
        initScrollListener();
    }

    private void initScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading){
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == rowsArrayList.size()-1){
                        loadMore();
                        isLoading = true;
                    }
                }
            }
        });
    }

    private void loadMore() {
        rowsArrayList.add(null);
        itemAdapter.notifyItemInserted(rowsArrayList.size()-1);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                rowsArrayList.remove(rowsArrayList.size() - 1);
                int scrollPosition = rowsArrayList.size();
                itemAdapter.notifyItemRemoved(scrollPosition);
                int currentSize = scrollPosition;
                int nextLimit = currentSize + 10;
                while (currentSize - 1 < nextLimit){
                    rowsArrayList.add("Item " + currentSize);
                    currentSize++;
                }
                itemAdapter.notifyDataSetChanged();
                isLoading = false;
            }
        }, 2000);
    }

    private void initAdapter() {
         itemAdapter = new ItemAdapter(rowsArrayList);
         recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
         recyclerView.setLayoutManager(gridLayoutManager);
         recyclerView.setAdapter(itemAdapter);
    }

    private void populateData() {
        for(int i = 0; i< 20; i++){
            rowsArrayList.add("Item " + i);
        }
    }
}