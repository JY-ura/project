package com.example.tr.dx;


import android.annotation.SuppressLint;
import android.arch.lifecycle.GeneratedAdapter;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.Objects;


public class Itemgridview extends Index {

    Index index=new Index();
    Bitmap[] bitmaps = new Bitmap[100];
    byte[] img = new byte[100];
    int n, i = 0, pos;
    String[] uri;
    GestureDetector detector;
    String[] array;
    ViewFlipper viewFlipper;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_itemgridview);

        Log.i("abcde", "onCreate: ");
        intent = getIntent();
        if (intent != null) {
            n = intent.getIntExtra("num", 0);
            pos = intent.getIntExtra("pos", 0);
        }

//            Bundle bundle=intent.getExtras();
//            uri = bundle.getStringArray("uri");
//            for (int j = 0; j < n; ++j) {
//                String strings=uri[j].substring(77);
//                Log.i("aaa",strings);
//                bitmaps[j]=getBitmap(strings);
//            }
        @SuppressLint("WrongViewCast")
        DIYGallery gallery = (DIYGallery) findViewById(R.id.diygallery);
        BaseAdapter baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return n;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView imageView = new ImageView(Itemgridview.this);
                imageView.setImageBitmap(Index.logoBitmaps[position]);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new Gallery.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 600));
                return imageView;
            }
        };
        gallery.setAdapter(baseAdapter);
        gallery.setSelection(pos);


    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
//    private Bitmap getBitmap(String path) {
//        //先解析图片边框的大小
//        BitmapFactory.Options ops = new BitmapFactory.Options();
//        ops.inJustDecodeBounds = true;
//        Bitmap bm = BitmapFactory.decodeFile(path, ops);
//        ops.inSampleSize = 1;
//        int oHeight = ops.outHeight;
//        int oWidth = ops.outWidth;
//        //控制压缩比
//        int contentHeight = 0;
//        int contentWidth = 0;
//        contentHeight = 1000; //自定义
//        contentWidth = 600;   //自定义
//        if (((float) oHeight / contentHeight) < ((float) oWidth / contentWidth)) {
//            ops.inSampleSize = (int) Math.ceil((float) oWidth / contentWidth);
//        } else {
//            ops.inSampleSize = (int) Math.ceil((float) oHeight / contentHeight);
//        }
//        ops.inJustDecodeBounds = false;
//        bm = BitmapFactory.decodeFile(path, ops);
//        return bm;
//    }
//
//    private Bitmap getBitmapFromUri(Uri uri)
//    {
//        try
//        {
//            // 读取uri资源中的bitmap
//            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
//            return bitmap;
//        }
//        catch (Exception e)
//        {
//            Log.e("[Android]", e.getMessage());
//            Log.e("[Android]", "目录为：" + uri);
//            e.printStackTrace();
//            return null;
//        }
//    }

//    private Bitmap Uri_to_bitmap() {
//        Intent intent = getIntent();
//        if (intent != null) {
//            Uri uri = intent.getData();
//            if (uri == null) {
//                Log.i("tag", "The uri is not exist.");
//                return null;
//            }
//            intent.setDataAndType(uri, "image/*");
//            Bundle extras = intent.getExtras();
//            if (extras != null) {
//                Bitmap photo = extras.getParcelable("intent");
//                return photo;
//            } else {
//                Log.i("extras", "The extras is null.");
//            }
//        } else {
//            Log.i("intent", "The intent is null.");
//        }
//        return null;
//    }
}
