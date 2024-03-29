package com.example.bt_29_3_2024;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.intellij.lang.annotations.Language;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<Beef> beefs;
    BeefAdapter beefAdapter;

    private void AnhXa(){
        gridView = (GridView) findViewById(R.id.grv);

    }

    private void XuLy(){
        beefs = new ArrayList<>();

        beefs.add(new Beef("Sườn",  R.drawable.suon));
        beefs.add(new Beef("Beef",  R.drawable.beef));
        beefs.add(new Beef("Beef_1",  R.drawable.beef_1));
    }

    private void SuKien(){
        beefAdapter = new BeefAdapter(this, R.layout.icon_grv, beefs);
        gridView.setAdapter(beefAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Beef selectedBeef = beefs.get(position);
                String message = "Name: " + selectedBeef.getName();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        XuLy();
        SuKien();
    }
}