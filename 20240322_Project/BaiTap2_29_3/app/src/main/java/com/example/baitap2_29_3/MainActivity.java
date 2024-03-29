package com.example.baitap2_29_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView rcvUser;
    private UserAdapter userAdapter;
    private TextView tvDs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcvUser = findViewById(R.id.rcv_user);
        tvDs = findViewById(R.id.tv_ds);
        userAdapter = new UserAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rcvUser.setLayoutManager(gridLayoutManager);
        userAdapter.setData(getListUser());
        rcvUser.setAdapter(userAdapter);
    }

    private List<User> getListUser() {
        List<User> users = new ArrayList<>();
        users.add(new User("User name 1", R.drawable.an1));
        users.add(new User("User name 2", R.drawable.an2));
        users.add(new User("User name 3", R.drawable.an3));
        users.add(new User("User name 4", R.drawable.an4));
        users.add(new User("User name 5", R.drawable.an5));
        users.add(new User("User name 6", R.drawable.an6));
        users.add(new User("User name 7", R.drawable.an1));
        users.add(new User("User name 8", R.drawable.an2));
        return users;
    }
}