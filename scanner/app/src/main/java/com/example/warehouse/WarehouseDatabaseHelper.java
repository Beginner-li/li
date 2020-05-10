package com.example.warehouse;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class WarehouseDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="warehouse.db";
    private static final int DB_VERSION=1;

    public WarehouseDatabaseHelper(Context context){ super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        long time=System.currentTimeMillis();
        Date date=new Date(time);
        TimeZone timeZone=TimeZone.getTimeZone("Asia/Beijing");
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        dateFormat.setTimeZone(timeZone);
        String now=dateFormat.format(date);
        db.execSQL(String.format("CREATE TABLE user(number varchar PRIMARY KEY ,password varchar,operator varchar,phone number);"));
        db.execSQL(String.format("CREATE TABLE goods(goods_id int PRIMARY KEY AUTOINCREMENT, FOREIGN KEY(goods_id)REFERENCES user,name varchar,amount number,category text,source text);"));
        db.execSQL(String.format("CREATE TABLE record(goods_id int PRIMARY KEY,number varchar,in_or_out text,amount int,time text);"));
        insertGoods(db,001,"图书",100,"学习用书","新华书店");
        insertUser(db,123,"abc","KK",1234567890);
        insertRecord(db,001,50,"in",now );

    }
    private  static void insertGoods(SQLiteDatabase db,int goods_id ,String name ,int amount ,String category ,String source){
        ContentValues goodsValues=new ContentValues();
        goodsValues.put("goods_id",goods_id);
        goodsValues.put("name",name);
        goodsValues.put("amount",amount);
        goodsValues.put("category",category);
        goodsValues.put("source",source);
        Long result=db.insert("goods",null,goodsValues);
        Log.d("sqlite","insert"+name+"_id"+result);
    }
    private static void insertUser(SQLiteDatabase db,int number,String password,String operator,int phone){
        ContentValues userValues=new ContentValues();
        userValues.put("number",  number);
        userValues.put("password",password);
        userValues.put("operator",operator);
        userValues.put("phone",phone);
        Long result=db.insert("user",null,userValues);
        Log.d("sqlite","insert"+number+"_id"+result);
    }
    private  static  void insertRecord(SQLiteDatabase db,int goods_id ,int number,String in_or_out ,String time){
        ContentValues recordValues=new ContentValues();
        recordValues.put("good_id",goods_id);
        recordValues.put("number",number);
        recordValues.put("in_or_out",in_or_out);
        recordValues.put("time",time);
        Long result=db.insert("record",null,recordValues);
        Log.d("sqlite","insert"+goods_id+"_id"+result);
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
