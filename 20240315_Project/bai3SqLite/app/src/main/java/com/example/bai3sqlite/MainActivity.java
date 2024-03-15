package com.example.bai3sqlite;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Declare a global variable
    DatabaseHandler databaseHandler;
    ListView listView;
    ArrayList<NotesModel> arrayList;
    NotesAdapter notesAdapter;
    Dialog dialog, dialogUpdate, dialogDelete;
    EditText edAdd, edtNameTask;
    Button btnAdd, btnCancle, btnConfirm, btnCancleUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpUI();
        // Call method to initialize SQLite database
        InitDatabaseSQLite();
//        createDatabaseSQLite();
        // Call method to interact with the database
        showData();

//        addEvent();
    }

    public void setUpUI() {
        listView = findViewById(R.id.listView1);
        arrayList = new ArrayList<>();
        notesAdapter = new NotesAdapter(this, R.layout.row_item, arrayList);
        listView.setAdapter(notesAdapter);
    }

    private void createDatabaseSQLite() {
        databaseHandler.queryData("INSERT INTO Notes VALUES(null, 'Vi du SQLite 1')");
        databaseHandler.queryData("INSERT INTO Notes VALUES(null, 'Vi du SQLite 2')");
    }

    private void InitDatabaseSQLite() {
        // Initialize the database
        databaseHandler = new DatabaseHandler(this, "NoteDB.sqlite", null, 1);
        // Create the table if it does not exist
        databaseHandler.queryData(
                "CREATE TABLE IF NOT EXISTS Notes(Id INTEGER PRIMARY KEY AUTOINCREMENT, NameNotes VARCHAR(200))");
    }

    private void showData() {
        Cursor cursor = databaseHandler.getData("SELECT * FROM Notes");
        arrayList.clear();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(1);
                int id = cursor.getInt(0);
                arrayList.add(new NotesModel(id, name));
            }
        }
        notesAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_task, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(
            @NonNull MenuItem item
    ) {
        if (item.getItemId() == R.id.menuAdd) {
            addDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    public void addDialog() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_task);

        edAdd = dialog.findViewById(R.id.editText_add_task);
        btnAdd = dialog.findViewById(R.id.button_add);
        btnCancle = dialog.findViewById(R.id.button_cancle);

        solveButton();

        dialog.show();
    }

    public void solveButton() {
        btnCancle.setOnClickListener(v -> {
            dialog.dismiss();
        });

        btnAdd.setOnClickListener(v -> {
            String nameTask = edAdd.getText()
                                   .toString();
            if (nameTask.equals("")) {
                Toast.makeText(this, "Vui long nhap ten cong viec", Toast.LENGTH_SHORT)
                     .show();
            } else {
                databaseHandler.queryData("INSERT INTO Notes VALUES(null, '" + nameTask +
                                                  "')");
                Toast.makeText(this, "da them", Toast.LENGTH_SHORT)
                     .show();
                dialog.dismiss();
                showData();
            }
        });
    }

    public void updateDialog(String nameTask, int idTask) {
        dialogUpdate = new Dialog(this);
        dialogUpdate.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogUpdate.setContentView(R.layout.dialog_update);

        edtNameTask = dialogUpdate.findViewById(R.id.editText_update);
        btnConfirm = dialogUpdate.findViewById(R.id.btn_confirm);
        btnCancleUpdate = dialogUpdate.findViewById(R.id.button_cancle_update);

        edtNameTask.setText(nameTask);

        btnConfirm.setOnClickListener(v -> {
            String name = edtNameTask.getText()
                                     .toString();
            if (name.equals("")) {
                Toast.makeText(this, "Vui long nhap ten cong viec", Toast.LENGTH_SHORT)
                     .show();
            } else {
                databaseHandler.queryData("UPDATE Notes SET NameNotes = '" + name + "'WHERE Id = '" + idTask + "'");
                Toast.makeText(this, "Da cap nhat", Toast.LENGTH_SHORT)
                     .show();
                dialogUpdate.dismiss();
                showData();
            }
        });
        btnCancleUpdate.setOnClickListener(v -> {
            dialogUpdate.dismiss();
        });
        dialogUpdate.show();
    }

    public void deleteTask(int idTask) {
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(this);
        dialogDelete.setMessage("Ban co muon xoa cong viec nay khong");

        dialogDelete.setPositiveButton("Co", (dialog, which) -> {
            databaseHandler.queryData("DELETE FROM Notes WHERE Id = '" + idTask + "'");
            showData();
        });

        dialogDelete.setNegativeButton("Khong", (dialog, which) -> {

        });

        dialogDelete.show();
    }

}