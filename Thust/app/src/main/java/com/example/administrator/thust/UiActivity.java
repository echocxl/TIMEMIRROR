package com.example.administrator.thust;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.BufferedReader;
//用户界面
public class UiActivity extends Activity {
    private ImageButton btcount;//查看aqpp运行次数
    private ImageButton bttime;//查看app使用时间
    private ImageButton btuse;//查看aoo使用说明
    private ImageButton btappabout;//查看app　作者信息。
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        btcount=(ImageButton)findViewById(R.id.imageButton9);
        bttime=(ImageButton)findViewById(R.id.imageButton10);
        btuse=(ImageButton)findViewById(R.id.imageButton7);
        btappabout=(ImageButton)findViewById(R.id.imageButton8);

        btcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UiActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        bttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(UiActivity.this,TimeActivity.class);
                startActivity(intent);

            }
        });

        btuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UiActivity.this,Explain_activity.class);
                startActivity(intent);

            }
        });

        btappabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(UiActivity.this,About_activity.class);
                startActivity(intent);

            }
        });

    }


}
