package com.example.tr.dx;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class login extends AppCompatActivity {

//    public static final String[][] np=new String[][];
    //注册好的账号密码

    File outputImage;
    AlertDialog.Builder dialog;
    AlertDialog ad = null;
    Uri imageUri;
    public String Rname,Rpass;
    private ImageView he;
    Handler handler = null;
    //记住密码
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private CheckBox box;
    private static final int takephoto=1,chocephoto=2;

    //输入的账号密码
    private EditText editname,editpass;
    private Bitmap logoBitmap;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        he=(ImageView)findViewById(R.id.yuan);
        editname=(EditText)findViewById(R.id.edit1);
        editpass=(EditText)findViewById(R.id.edit2);
        box=findViewById(R.id.remmber);

        //接受注册好的账号和密码
        Intent ing=getIntent();
        Rname=ing.getStringExtra("name");
        Rpass=ing.getStringExtra("pass");
        editname.setText(Rname);
        editpass.setText(Rpass);


        preferences=PreferenceManager.getDefaultSharedPreferences(this);
        Boolean f=preferences.getBoolean("remember",false);
        if(f){
            editname.setText(preferences.getString("name",""));
            editpass.setText(preferences.getString("pass",""));
            box.setChecked(true);
            Rname=preferences.getString("name","");
            Rpass=preferences.getString("pass","");

        }

         handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what==11)
                {
                    ad.dismiss();
                }
            }
        };

        Intent intent=getIntent();
        if (intent  != null &&  intent.getParcelableExtra("BITMAP") != null) {
            Bitmap bitmap = (Bitmap)getIntent().getParcelableExtra("BITMAP");
            he.setImageBitmap(bitmap);
        }

    }

    //注册登录
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.re: {
                Intent t1 = new Intent(login.this, register.class);
                he.setDrawingCacheEnabled(Boolean.TRUE);
                t1.putExtra("BITMAP", he.getDrawingCache());
                login.this.startActivity(t1);
                finish();
                break;
            }
            case R.id.lo: {
                String name = editname.getText().toString();
                String pass = editpass.getText().toString();
                if (name.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(login.this, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ((name.equals(Rname) && pass.equals(Rpass))||(name.equals("123")&&pass.equals("123456"))) {
                    editor = preferences.edit();
                    if (box.isChecked()) {
                        editor.putString("name", name);
                        editor.putString("pass", pass);
                        editor.putBoolean("remember", true);
                    } else {
                        editor.clear();
                    }
                    editor.apply();
                    Toast.makeText(login.this, "登录成功！", Toast.LENGTH_SHORT).show();
                    Intent t2 = new Intent(login.this, Index.class);
                    he.setDrawingCacheEnabled(Boolean.TRUE);
                    t2.putExtra("BITMAP", he.getDrawingCache());
                    t2.putExtra("zhanghao",name);

                    login.this.startActivity(t2);
                    finish();
                    break;
                } else {
                    Toast.makeText(login.this, "账号或密码有误！", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }
    }


    //菜单
    public void press(View v){
        switch (v.getId()){
            case R.id.yuan:  setdialog();break;
            case R.id.camera:
                Toast.makeText(this,"正在打开相机",Toast.LENGTH_SHORT).show();
                takephoto();
                break;
            case R.id.photo:
                Toast.makeText(this,"正在打开相册",Toast.LENGTH_SHORT).show();
                photos();
                break;

        }
    }

    //相册
    private void photos() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, chocephoto);
    }
    // 创建File对象，用于存储拍照后的图片
    private void takephoto() {

        outputImage = new File(getExternalCacheDir(), "output_image.jpg");
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Build.VERSION.SDK_INT < 24) {
            imageUri = Uri.fromFile(outputImage);
        } else {
            imageUri = FileProvider.getUriForFile(login.this, "com.example.xch.generateqrcode.fileprovider", outputImage);
        }
        // 启动相机程序
        Intent it1;
        it1=new Intent("android.media.action.IMAGE_CAPTURE");
        it1.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(it1,takephoto);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case takephoto:
                if (resultCode == RESULT_OK) {
                    try {
                        // 读取拍照结果
                        handler.sendEmptyMessage(11);
                        logoBitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        he.setImageBitmap(logoBitmap);
                        Toast.makeText(login.this,"上传成功！",Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(login.this,"上传失败！",Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            case chocephoto:
                    if (resultCode == RESULT_OK) {
                        handleImageBeforeKitKat(data);
                    }
                    break;
        }
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        //显示图片
//        Log.i("1231231232113321312312", imagePath);
        if (imagePath != null) {
//            logoBitmap = BitmapFactory.decodeFile(imagePath);
            handler.sendEmptyMessage(11);
            ContentResolver resolver = getContentResolver();
            try {
                logoBitmap = MediaStore.Images.Media.getBitmap(resolver, uri);
                he.setImageBitmap(logoBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Toast.makeText(this, "获取图片失败", Toast.LENGTH_SHORT).show();
        }

    }

    private String getImagePath(Uri uri, String o) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, o, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    //dialog
    private void setdialog(){
//        Toast.makeText(this,"slasljkdlsafjlk",Toast.LENGTH_LONG).show();
        dialog=new AlertDialog.Builder(this);
        dialog.setIcon(R.drawable.dialogtouxiang);
        dialog.setTitle("你想从哪换头像呢？");
        dialog.setView(R.layout.bottom_dialog);
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        ad = dialog.create();
        ad.setCanceledOnTouchOutside(false);
        ad.setCancelable(false);
        ad.show();
//        LinearLayout ro=(LinearLayout) LayoutInflater.from(this).inflate(R.layout.bottom_dialog,null);
//        mc=new Dialog(this,R.style.AppTheme);
//        ro.findViewById(R.id.camera);
//        ro.findViewById(R.id.photo);
//        ro.findViewById(R.id.cancel);
//        mc.setContentView(ro);
//        Window dia=mc.getWindow();
//        dia.setGravity(Gravity.BOTTOM);
//        WindowManager.LayoutParams lp=dia.getAttributes();
//        lp.x=0;
//        lp.y=0;
//        lp.width=(int)getResources().getDisplayMetrics().widthPixels;
//        lp.height=ro.getMeasuredHeight();
//        lp.alpha=9f;
//        dia.setAttributes(lp);
//        mc.show();
    }

}
