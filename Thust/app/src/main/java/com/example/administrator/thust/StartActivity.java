package com.example.administrator.thust;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import static android.os.SystemClock.sleep;
import static android.view.WindowManager.*;

//启动界面
public class StartActivity extends Activity{
    private Button bt;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().addFlags(LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        setContentView(R.layout.start_activity);
        Thread my=new Thread() {
            public void run(){
                try {
                    sleep(3000);//让app展示这个界面3秒之后，再打开UI界面。
                    Intent intent = new Intent(StartActivity.this, UiActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
              }
        };
        my.start();







    }



}
