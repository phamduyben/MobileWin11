package com.example.bai1;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.bai1.databinding.ActivityHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private final ObservableField<String> title = new ObservableField<>(
            "Ví dụ về DataBinding cho RecyclerView"); // Preset title for clarity
    private ListUserAdapter listUserAdapter;
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        binding.setHome(this); // Set this Activity instance for potential future interaction within layout

        // Generate sample user data (consider using a more realistic data source)
        List<User> userList = generateSampleUserList();

        // Initialize the adapter with the user data
        listUserAdapter = new ListUserAdapter(userList);

        // Set the layout manager and adapter for the RecyclerView
        binding.rcView.setLayoutManager(new LinearLayoutManager(this));
        binding.rcView.setAdapter(listUserAdapter);
        listUserAdapter.setOnItemClickListener(this::itemClick);
    }

    public void itemClick(User user) {
        Toast.makeText(this, "bạnvừaclick", Toast.LENGTH_SHORT)
             .show();
    }

    private List<User> generateSampleUserList() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) { // Generate 10 sample users
            User user = new User();
            user.setFirstName("Pham" + i);
            user.setLastName("Chien" + i);
            userList.add(user);
        }
        return userList;
    }
}
