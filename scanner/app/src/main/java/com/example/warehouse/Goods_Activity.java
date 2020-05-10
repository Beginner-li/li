package com.example.warehouse;

import android.app.Application;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Goods_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle saveIntanceState){
        super.onCreate(saveIntanceState);
        setContentView(R.layout.activity_goods);

        SQLiteOpenHelper WarehouseDatabaseHelper    =new WarehouseDatabaseHelper(this);
        try(SQLiteDatabase db=WarehouseDatabaseHelper.getReadableDatabase()){

        }catch (SQLException e){
            Log.e("sqlite",e.getMessage());
            Toast toast=Toast.makeText(this,"unavailable",Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}
