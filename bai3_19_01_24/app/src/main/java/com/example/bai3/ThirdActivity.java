package com.example.bai3;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {
    Button btnPopupMenu, btnContext, btnDelete, btnUpdate;
    Dialog dialog;
    EditText edtMssv, edtName;
    TextView tvRecord;
    Button btnNav1, btnNav2;
    Intent intentNav1, intentNav2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        setupUI();
        setupProcess();
    }

    private void setupProcess() {
        showPopupMenu();
        registerForContextMenu(btnContext);
        showDialog();
        dialogLogin();
        setNavigaion();
    }

    private void setupUI() {
        btnPopupMenu = findViewById(R.id.button_popup);
        btnContext = findViewById(R.id.button_context_menu);
        btnDelete = findViewById(R.id.button_confirmDelete);
        tvRecord = findViewById(R.id.textView_record);

        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_custom);
        dialog.setCanceledOnTouchOutside(false);

        edtMssv = dialog.findViewById(R.id.editText_mssv);
        edtName = dialog.findViewById(R.id.editText_name);
        btnUpdate = dialog.findViewById(R.id.button_update);

        btnNav1 = findViewById(R.id.button_navig1);
        btnNav2 = findViewById(R.id.button_navig2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_demo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        selectItemOnMenu(id);
        return super.onOptionsItemSelected(item);
    }

    public void showPopupMenu() {
        btnPopupMenu.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(this, btnPopupMenu);
            popupMenu.getMenuInflater()
                    .inflate(R.menu.menu_demo, popupMenu.getMenu());
            popupMenu.show();

            popupMenu.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();
                selectItemOnMenu(id);
                return false;
            });
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_demo, menu);
        menu.setHeaderIcon(R.mipmap.ic_launcher);
        menu.setHeaderTitle("Context Menu");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        selectItemOnMenu(id);
        return super.onContextItemSelected(item);
    }

    public void selectItemOnMenu(int id) {
        String temp = "";
        if (id == R.id.menu_setting) {
            temp = "Setting";
        } else if (id == R.id.meneu_logout) {
            temp = "logout";
        } else if (id == R.id.menu_share) {
            temp = "share";
        } else if (id == R.id.menu_sub_menu) {
            temp = "menu sub";
        }
        Toast.makeText(this, temp, Toast.LENGTH_LONG)
                .show();
    }

    public void showDialog() {
        btnDelete.setOnClickListener(v -> confirmDelete());
    }

    public void confirmDelete() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Notify");
        alertDialog.setMessage("Do you want to delete ? ");
        alertDialog.setPositiveButton("Yes", (dialog, which) -> {

        });
        alertDialog.setNegativeButton("No", (dialog, which) -> {

        });
        alertDialog.show();
    }

    public void dialogLogin() {
        tvRecord.setOnClickListener(v -> setDialog());
    }

    public void setDialog() {
        btnUpdate.setOnClickListener(v -> {
            String mssv = edtMssv.getText()
                    .toString(),
                    name = edtName.getText()
                            .toString();

            Toast.makeText(this, mssv + "\n" + name, Toast.LENGTH_LONG)
                    .show();
        });

        dialog.show();
    }

    public void setNavigaion() {
        btnNav1.setOnClickListener(v -> {
            intentNav1 = new Intent(ThirdActivity.this, MainActivity.class);
            startActivity(intentNav1);
        });

        btnNav2.setOnClickListener(v -> {
            intentNav2 = new Intent(ThirdActivity.this, SecondActivity.class);
            startActivity(intentNav2);
        });
    }
}