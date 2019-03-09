package com.example.administrator.thust;

import android.app.Activity;
import android.app.usage.UsageStats;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.thust.R;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
//获取运行次数的页面
public class MainActivity extends Activity {
    SharedPreferences myShare;
    private ListView listView;
    private MyAdapter mAdapter;//运行次数的listview的适配器。
    private Button bt; //查询按钮
    private EditText ed; //输入天数
    List<Appinfo> appset; //获取到的app信息的集合。

    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        bt=(Button)findViewById(R.id.button);
        ed=(EditText)findViewById(R.id.editText);


             //这段代码通过调用　getApp获取相应的信息，然后通过适配器作用于listview展示给用户。
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int days = Integer.parseInt(ed.getText().toString());
                if(days<=2 && days>=1){//判断输入的数字是否合理
                appset = new ArrayList<Appinfo>();
                UserStatics us = new UserStatics();
                myShare = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                String flag = myShare.getString("flag", "false");
                if (flag.equals("false")) {
                    SharedPreferences.Editor editor = myShare.edit();
                    editor.putString("flag", "true");
                    editor.commit();
                    startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
                }
                appset = us.getApp(context, days);

               //appsethalf.addAll(appset.subList(appset.size() / 2, appset.size()));
                //   Appinfo[] appinfo=new Appinfo[appset.size()];
                // appinfo=appset.toArray(appinfo);
                Collections.sort(appset, new Comparator<Appinfo>() {
                    @Override
                    public int compare(Appinfo o1, Appinfo o2) {

                        if (Integer.parseInt(o1.lunchtimes) > Integer.parseInt(o2.lunchtimes)) {
                            return -1;
                        }
                        if (Integer.parseInt(o1.lunchtimes) > Integer.parseInt(o2.lunchtimes)) {
                            return 0;
                        }
                        return 1;
                    }

                    /*
                     * int compare(Person p1, Person p2) 返回一个基本类型的整型，
                     * 返回负数表示：p1 小于p2，
                     * 返回0 表示：p1和p2相等，
                     * 返回正数表示：p1大于p2
                     */

                });
                mAdapter = new MyAdapter(context,appset);
                listView.setAdapter(mAdapter);
            }
            else{//输入数字不合理时提醒用户。
                    Toast.makeText(MainActivity.this,"请输入合理的数字！（1-2）",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
