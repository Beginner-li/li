package com.example.warehouse;


import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.client.android.CaptureActivity;

public class CannerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.scanner);
        if(ContextCompat.checkSelfPermission(CannerActivity.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(CannerActivity.this,new String[]{Manifest.permission.CAMERA},1);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CannerActivity.this, CaptureActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0){
                    if(grantResults[0]!=PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(CannerActivity.this,"必须授权才能进行",Toast.LENGTH_SHORT).show();
                        finish();
                        return;
                    }
                }else {
                    Toast.makeText(CannerActivity.this,"发生未知错误",Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }
}
