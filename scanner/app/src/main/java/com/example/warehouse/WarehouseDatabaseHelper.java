package com.example.warehouse;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class WarehouseDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="warehouse.db";
    private static final int DB_VERSION=1;

    public WarehouseDatabaseHelper(Context context){ super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE user(number varchar PRIMARY KEY ,"
                +"password varchar,"
                +"operator varchar,"
                +"phone number);");
        db.execSQL("CREATE TABLE goods(goods_id int PRIMARY KEY AUTOINCREMENT, FOREIGN KEY(goods_id)REFERENCES user,"
                +"name varchar,"
                +"amount number,"
                +"category text,"
                + "source text);");
        db.execSQL("CREATE TABLE record(goods_id PRIMARY KEY,"
                +"number varchar,"
                +"in_or_out boolen,"
                +"amount int,"
                +"time date);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }
}
