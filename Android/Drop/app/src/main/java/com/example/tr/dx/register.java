package com.example.tr.dx;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class register extends AppCompatActivity {

    private ImageView he;
    private Button ge;

    private EditText EDITNAME,EDITPASS,EDITRELY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);
        ge=findViewById(R.id.reg);
        EDITNAME=(EditText)findViewById(R.id.edit3);
        EDITPASS=(EditText)findViewById(R.id.edit4);
        EDITRELY=(EditText)findViewById(R.id.edit5) ;

        initlistener();
        he=findViewById(R.id.yuan1);
        he.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"请前往登录界面设置！",Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = getIntent();
        if (intent  != null &&  intent.getParcelableExtra("BITMAP") != null) {
            Bitmap bitmap = (Bitmap)getIntent().getParcelableExtra("BITMAP");
            he.setImageBitmap(bitmap);
        }
    }

    private void initlistener() {
        ge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regist();
            }
        });
    }

    private void regist() {
        String registName=EDITNAME.getText().toString();
        String registPass=EDITPASS.getText().toString();
        String registRely=EDITRELY.getText().toString();
        if(registName.isEmpty()||registPass.isEmpty()){
            Toast.makeText(register.this,"输入的用户名或密码不能为空！",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(registPass.length()<6){
            Toast.makeText(register.this, "注册密码小于6位！", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(!registPass.equals(registRely)){
            Toast.makeText(register.this, "两次密码不同！", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            Toast.makeText(getApplicationContext(), "注册成功!", Toast.LENGTH_SHORT).show();
            Intent t=new Intent();
            t.putExtra("name",registName);
            t.putExtra("pass",registPass);
            he.setDrawingCacheEnabled(Boolean.TRUE);
            t.putExtra("BITMAP", he.getDrawingCache()); //这里放一个bitmap
            t.setClass(register.this,login.class);
            register.this.startActivity(t);
            finish();
        }
        //注册账号
    }
}
