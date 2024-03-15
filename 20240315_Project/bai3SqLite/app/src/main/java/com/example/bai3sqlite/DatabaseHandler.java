package com.example.bai3sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "NoteDB.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Notes";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
//    private static final String KEY_ADDRESS = "address";
//    private static final String KEY_PHONE_NUMBER = "phone_number";

    public DatabaseHandler(
            @Nullable Context context,
            @Nullable String name,
            @Nullable SQLiteDatabase.CursorFactory factory, int version
    ) {
        super(context, name, factory, version);
    }

    public DatabaseHandler(
            @Nullable Context context,
            @Nullable String name,
            @Nullable SQLiteDatabase.CursorFactory factory, int version,
            @Nullable DatabaseErrorHandler errorHandler
    ) {
        super(context, name, factory, version, errorHandler);
    }

    //    public DatabaseHandler(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String create_students_table = String.format(
//                "CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT)",
//                TABLE_NAME,
//                KEY_ID,
////                KEY_NAME,
////                KEY_ADDRESS,
////                KEY_PHONE_NUMBER
//        );
//        db.execSQL(create_students_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        String drop_students_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
//        db.execSQL(drop_students_table);
//        onCreate(db);
    }

    //truy vấn không trả kết quả Create, Insert, update, delete, ..
    public void queryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    //truy vấn có trả kết quả Select
    public Cursor getData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    public void updateTask(int id, String name) {
    }
}
