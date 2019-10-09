package com.example.tr.dx;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Index extends AppCompatActivity implements View.OnClickListener {

    SimpleAdapter saImageItems,simpleAdapter;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    ArrayList<View> arrayList=new ArrayList<View>();
    GridView viewgrid;
    Bitmap bitmap=null;
    ListView viewlist;
    Dialog dialog,dialog1;
    Button button;
    Uri imageUri;
    String[] uristring=new String[100];
    Bitmap logoBitmap;
    public static Bitmap[] logoBitmaps=new Bitmap[100];
    Drawable[] drawables=new BitmapDrawable[100];
    static int i=0,k=0;
    String[] curr=new String[100];
    TextView zhanghao;
    File outputImage;
    ViewPager viewPager;
    private List<View> viewList = new ArrayList<View>();
    boolean flag=true;
    ImageView imageView;
    String s = null;
    PagerAdapter viewAdapter;
    LayoutInflater inflater;
    FrameLayout frameLayout = null;
    View grid = null;
    View list = null,kong=null;
    CircleImageView head;

    //dialog
    CircleImageView touxiang;
    EditText name,other;
    String name_g,other_g;
    Button save;
    final int cha=1500;
    long first=0,second=0;
    long count=0;
    RadioGroup sex;
    RadioButton boy,girl;
    boolean b=true,g;

    ArrayList<Map<String,Object>> listitem;
    ArrayList<HashMap<String, Object>> lstImageItem;
    public List<? extends Map<String,?>> generateDataList(){
        listitem=new ArrayList<Map<String,Object>>();
        for(int j=0;j<=i;++j){
            HashMap<String,Object> htm=new HashMap<String,Object>();
            htm.put("text",curr[j]);
            htm.put("image",logoBitmaps[j]);
            listitem.add(htm);
        }
        return listitem;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_index);
        frameLayout = findViewById(R.id.frame);
        grid=getLayoutInflater().inflate(R.layout.view_grid,null);
        list = getLayoutInflater().inflate(R.layout.view_list,null);
        kong=getLayoutInflater().inflate(R.layout.kong,null);

        viewgrid=grid.findViewById(R.id.viewgrid);
        viewlist=list.findViewById(R.id.viewlist);

        head=(CircleImageView)findViewById(R.id.yuan);

        Intent intent=getIntent();
        if (intent  != null &&  intent.getParcelableExtra("BITMAP") != null) {
            bitmap = (Bitmap)getIntent().getParcelableExtra("BITMAP");
            head.setImageBitmap(bitmap);
            s=intent.getStringExtra("zhanghao");
        }

        if("123".equals(s)){
            logoBitmaps[0]=BitmapFactory.decodeResource(getResources(),R.drawable.bb1);
            logoBitmaps[1]=BitmapFactory.decodeResource(getResources(),R.drawable.bb2);
            logoBitmaps[2]=BitmapFactory.decodeResource(getResources(),R.drawable.bb3);
            logoBitmaps[3]=BitmapFactory.decodeResource(getResources(),R.drawable.bb4);
            logoBitmaps[4]=BitmapFactory.decodeResource(getResources(),R.drawable.bb5);
            logoBitmaps[5]=BitmapFactory.decodeResource(getResources(),R.drawable.bb6);
            logoBitmaps[6]=BitmapFactory.decodeResource(getResources(),R.drawable.bb7);
            logoBitmaps[7]=BitmapFactory.decodeResource(getResources(),R.drawable.bb8);
            logoBitmaps[8]=BitmapFactory.decodeResource(getResources(),R.drawable.bb91);
            curr[0]="2018.12.22 15.14";
            curr[1]="2018.12.22 15.14";
            curr[2]="2018.12.22 15.15";
            curr[3]="2018.12.22 15.15";
            curr[4]="2018.12.22 15.15";
            curr[5]="2018.12.22 15.15";
            curr[6]="2018.12.22 15.16";
            curr[7]="2018.12.22 15.19";
            curr[8]="2018.12.22 15.21";
            i=9;
            setimageitem();
            frameLayout.removeAllViews();
            frameLayout.addView(grid);
        }

//        }
        for(int j=0;j<i;++j){
                Log.i("abcde", String.valueOf(logoBitmaps[j]));
            }
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

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
                    imageUri = FileProvider.getUriForFile(Index.this, "com.example.xch.generateqrcode.fileprovider", outputImage);
                }
                // 启动相机程序
                Intent it1;
                it1=new Intent("android.media.action.IMAGE_CAPTURE");
                it1.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(it1,1);
                frameLayout.removeAllViews();
                if(flag){
                    frameLayout.addView(grid);
                }else{
                    frameLayout.addView(list);
                }

            }
        });

        if(i==0){

            frameLayout.addView(kong);
        }


        imageView=findViewById(R.id.switchchang);
        imageView.setOnClickListener(this);

        //头像点击
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inforDialog();
            }
        });


    }
    @SuppressLint("ClickableViewAccessibility")
    protected void inforDialog(){
        dialog = new Dialog(Index.this);
        dialog.setTitle("身份证");
        dialog = new AlertDialog
                .Builder(Index.this)
                .setView(R.layout.infordialoga)
                .create();
        dialog.show();
        final WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = 400;
        params.height = 600;
        dialog.getWindow().setAttributes(params);
        name = dialog.getWindow().findViewById(R.id.name_a);
        other = dialog.getWindow().findViewById(R.id.other);
        save = dialog.getWindow().findViewById(R.id.succed);
        touxiang=dialog.getWindow().findViewById(R.id.touxiang_a);
        sex=dialog.getWindow().findViewById(R.id.radiogroup);
        girl=dialog.getWindow().findViewById(R.id.nv_a);
        boy=dialog.getWindow().findViewById(R.id.nan_a);
        zhanghao=dialog.getWindow().findViewById(R.id.zhanghao);
        zhanghao.setText(s);

        girl.setChecked(g);
        boy.setChecked(b);
        sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.nv_a){
                    girl.setChecked(true);
                }else if (checkedId==R.id.nan_a){
                    boy.setChecked(true);
                }
            }
        });

//        if(right){
            name.setText(name_g);
            other.setText(other_g);
//            right=true;
//        }
            other.setFocusable(false);
            other.setCursorVisible(false);
            name.setFocusable(false);
            name.setCursorVisible(false);
            if(bitmap!=null){
                touxiang.setImageBitmap(bitmap);
            }

            name.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (MotionEvent.ACTION_DOWN == event.getAction()) {
                        count++;
                        if (count == 1) {
                            second = System.currentTimeMillis();
                        } else if (count == 2) {
                            if (second - first <= cha) {
                                name.requestFocus();
                                name.setCursorVisible(true);
                                name.setFocusable(true);
                                name.setFocusableInTouchMode(true);
                                count = 0;
                                first = 0;
                            } else {
                                first = second;
                                count = 1;
                            }
                            second = 0;
                        }
                    }
                    return true;
                }
            });
            other.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (MotionEvent.ACTION_DOWN == event.getAction()) {
                        count++;
                        if (count == 1) {
                            second = System.currentTimeMillis();
                        } else if (count == 2) {
                            if (second - first <= cha) {
                                other.requestFocus();
                                other.setCursorVisible(true);
                                other.setFocusable(true);
                                other.setFocusableInTouchMode(true);
                                count = 0;
                                first = 0;
                            } else {
                                first = second;
                                count = 1;
                            }
                            second = 0;
                        }
                    }
                    return true;
                }
            });
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name.setFocusable(false);
                    other.setFocusable(false);

                    name_g=name.getText().toString();
                    other_g=other.getText().toString();
                    g=girl.isChecked();
                    b=boy.isChecked();

                    Toast.makeText(Index.this, "保存成功！", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });


    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1){
            try {
                // 读取拍照结果
                logoBitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));

                int mYear,mMonth,mDay,mWay,mHour,mMinute;
                String currdata;
                Calendar c = Calendar.getInstance();//
                mYear = c.get(Calendar.YEAR); // 获取当前年份
                mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
                mDay = c.get(Calendar.DAY_OF_MONTH);// 获取当日期
                mHour = c.get(Calendar.HOUR_OF_DAY);//时
                mMinute = c.get(Calendar.MINUTE);//分
                currdata=mYear+"."+mMonth+"."+mDay+" "+mHour+":"+mMinute;

                logoBitmaps[i]=logoBitmap;
                curr[i]=currdata;

                setimageitem();
                i++;
                Toast.makeText(Index.this,"上传成功！",Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                Toast.makeText(Index.this,"上传失败！",Toast.LENGTH_SHORT).show();
            }
        }
    }
    //点击跳转到详情页面
    class  ItemClickListener implements AdapterView.OnItemClickListener
    {
        public void onItemClick(AdapterView<?> arg0,//The AdapterView where the click happened
                                View arg1,//The view within the AdapterView that was clicked
                                int arg2,//The position of the view in the adapter
                                long arg3){//The row id of the item that was clicked
            //在本例中arg2=arg3
            @SuppressWarnings("unchecked")
            HashMap<String, Object> item=(HashMap<String, Object>) arg0.getItemAtPosition(arg2);

            Intent intent=new Intent(Index.this,Itemgridview.class);

//            ByteArrayOutputStream baos=new ByteArrayOutputStream();
//            for(int j=0;j<i;++j){
//                logoBitmaps[j].compress(Bitmap.CompressFormat.PNG, 100, baos);
//            }
//            byte [] bitmapByte =baos.toByteArray();
//            intent.putExtra("bitmap", bitmapByte);

//            for(int j=0;j<i;++j){
//                Log.i("abcdee",uristring[j]);
//            }


//            Bundle bundle=new Bundle();
//            bundle.putStringArray("uri", uristring);
//            intent.putExtras(bundle);

            intent.putExtra("num",i);
            intent.putExtra("pos",arg2);

            Log.i("abcde","input ??");
            startActivity(intent);
            Log.i("abcde","chenggongmei?");
        }
    }





    public void setimageitem(){
        arrayList=new ArrayList<>();
        arrayList.add(list);
        arrayList.add(grid);

        //生成动态数组，并且转入数据
        lstImageItem = new ArrayList<HashMap<String, Object>>();
        for(int j=0;j<=i;++j){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage",logoBitmaps[j]);
            map.put("ItemText", curr[j]);
            lstImageItem.add(map);
        }

        //生成适配器的ImageItem <====> 动态数组的元素，两者一一对应
        saImageItems = new SimpleAdapter(this, //没什么解释
                lstImageItem,//数据来源
                R.layout.item_grid,//night_item的XML实现
                //动态数组与ImageItem对应的子项
                new String[]{"ItemImage", "ItemText"},
                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[]{R.id.ItemImage, R.id.ItemText});

        //实现接口
        saImageItems.setViewBinder(new SimpleAdapter.ViewBinder() {
            public boolean setViewValue(View view, Object data, String textRepresentation) {
                if ((view instanceof ImageView) & (data instanceof Bitmap)) {
                    ImageView iv = (ImageView) view;
                    Bitmap bm = (Bitmap) data;
                    iv.setImageBitmap(bm);
                    return true;
                }
                return false;
            }
        });
        viewgrid.setAdapter(saImageItems);
        viewgrid.setOnItemClickListener(new ItemClickListener());
        viewgrid.setOnItemLongClickListener(new ItemLongClickListener());


//        //List


        simpleAdapter = new SimpleAdapter(
                this,
                generateDataList(),
                R.layout.item_list,
                new String[]{"image","text"},
                new int[]{R.id.listimage,R.id.listtext}
        );
        simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            public boolean setViewValue(View view, Object data, String textRepresentation) {
                if ((view instanceof ImageView) & (data instanceof Bitmap)) {
                    ImageView iv = (ImageView) view;
                    Bitmap bm = (Bitmap) data;
                    iv.setImageBitmap(bm);
                    return true;
                }
                return false;
            }
        });
        viewlist.setAdapter(simpleAdapter);
        viewlist.setOnItemClickListener(new ItemClickListener());
        viewlist.setOnItemLongClickListener(new ItemLongClickListener2());

        viewgrid.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                    if ((firstVisibleItem==totalItemCount-8)||(firstVisibleItem==totalItemCount-7)){
                        View l=viewgrid.getChildAt(viewgrid.getChildCount()-1);
                        if(l!=null&&l.getBottom()==viewgrid.getHeight()) {
//                            Toast.makeText(Index.this, "共" + i + "张照片", Toast.LENGTH_SHORT).show();
                            Snackbar.make(view, "共" + i + "张照片", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                        }
                    }
            }
        });
        viewlist.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem==totalItemCount-7){
                    View l=viewlist.getChildAt(viewlist.getChildCount()-1);
                    if(l!=null&&l.getBottom()==viewlist.getHeight()) {
//                        Toast.makeText(Index.this, "共" + i + "张照片", Toast.LENGTH_SHORT).show();
                        Snackbar.make(view, "共" + i + "张照片", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
            }
        });
//        frameLayout.removeAllViews();
//        frameLayout.addView(gr);
    }

    class ItemLongClickListener2 implements AdapterView.OnItemLongClickListener{


        @Override
        public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
            AlertDialog.Builder builder=new AlertDialog.Builder(Index.this);
            builder.setTitle("确认要删除吗？");
            builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    showProgressDialog();
                    Toast.makeText(Index.this,"删除成功",Toast.LENGTH_LONG).show();
                    for(int j=position;j<i;++j){
                        logoBitmaps[j]=logoBitmaps[j+1];
                    }
                    listitem.remove(position);
                    lstImageItem.remove(position);
                    i--;
                    simpleAdapter.notifyDataSetChanged();
                    saImageItems.notifyDataSetChanged();
                    dialog.dismiss();
                    if(viewlist.getCount()==0){
                        frameLayout.removeAllViews();
                        frameLayout.addView(kong);
                    }
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
            return true;
        }
    }
    class  ItemLongClickListener implements AdapterView.OnItemLongClickListener {

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

            AlertDialog.Builder builder=new AlertDialog.Builder(Index.this);
            builder.setTitle("确认要删除吗？");
            builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    showProgressDialog();
                    Toast.makeText(Index.this,"删除成功",Toast.LENGTH_LONG).show();
                    for(int j=position;j<i;++j){
                        logoBitmaps[j]=logoBitmaps[j+1];
                    }
                    lstImageItem.remove(position);
                    listitem.remove(position);
                    i--;
                    saImageItems.notifyDataSetChanged();
                    simpleAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                    if(viewgrid.getCount()==0){
                        frameLayout.removeAllViews();
                        frameLayout.addView(kong);
                    }
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
            return true;
        }
    }
    private void showProgressDialog() {
        final int MAX_PROGRESS = 1;
        final ProgressDialog progressDialog = new ProgressDialog(Index.this);
        progressDialog.setProgress(0);
//        progressDialog.setTitle("正在删除，请稍后！");
        progressDialog.setProgressStyle(ProgressDialog.BUTTON_NEUTRAL);
        progressDialog.setMessage("正在删除，请稍后！");
        progressDialog.setMax(MAX_PROGRESS);
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int progress= 0;
                while (progress < MAX_PROGRESS){
                    try {
                        Thread.sleep(1000);
                        progress++;
                        progressDialog.setProgress(progress);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                // 进度达到最大值后，窗口消失
                progressDialog.cancel();
            }
        }).start();
    }
    @Override
    public void onClick(View v) {
        if (flag){
            imageView.setImageResource(R.drawable.hang);
            if(i==0){
                frameLayout.removeAllViews();
                frameLayout.addView(kong);
            }
            else {
                frameLayout.removeAllViews();
                frameLayout.addView(list);
            }
           // frameLayout.addView(grid);
//            frameLayout.removeAllViews();
//            frameLayout.addView(grid);
//            viewPager.setCurrentItem(1);
            flag=false;
        }else{
            imageView.setImageResource(R.drawable.kuai);
            if(i==0){
                frameLayout.removeAllViews();
                frameLayout.addView(kong);
            }
            else {
                frameLayout.removeAllViews();
                frameLayout.addView(grid);
            }


//            frameLayout.removeAllViews();
//            frameLayout.addView(list);
//            viewPager.setCurrentItem(0);
            flag=true;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            dialog();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void dialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(Index.this);
        builder.setMessage("您确定要退出吗？");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Index.this.finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}
