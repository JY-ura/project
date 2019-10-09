package com.example.tr.dx;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Weclome extends AppCompatActivity {

    private MediaPlayer m;
    Runnable runnable;
    Handler handler;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weclome);
        getSupportActionBar().hide();
        tv=findViewById(R.id.time);
        m=MediaPlayer.create(this,R.raw.mingjing);
        m.start();
        new CountDownTimer(4*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub
                tv.setText(millisUntilFinished/1000+" 跳过");
            }

            @Override
            public void onFinish() {
            }
        }.start();

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t= new Intent(Weclome.this,login.class);
                startActivity(t);
                m.stop();
                finish();
                if (runnable!=null){
                    handler.removeCallbacks(runnable);
                }

            }
        });

            handler=new Handler();
            handler.postDelayed((runnable=new Runnable() {
                @Override
                public void run() {
                        Intent t = new Intent(Weclome.this, login.class);
                        startActivity(t);
                        m.stop();
                        finish();
                }
            }),3000);



    }
}
