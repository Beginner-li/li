
package com.example.warehouse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login_Activity extends AppCompatActivity {
    private  Button btn=null;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        btn=(Button)findViewById(R.id.登录);
        btn.setOnClickListener(new MyButtonListener());
    }
    private class MyButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View view){
            Toast.makeText(Login_Activity.this,"登陆成功",Toast.LENGTH_SHORT).show();
        }
    }

}
