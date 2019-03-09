package com.example.administrator.thust;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//获取运行时间界面
public class TimeActivity extends Activity {
    SharedPreferences myShare;
    private ListView listView;
    private TimeAdapter timeAdapter;//运行时间的listview的适配器。
    private Button bt;//查询按钮
    private EditText ed;//输入天数
    List<Appinfo> appset;//获取到的app信息的集合。

    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_acticity);


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

                    //   Appinfo[] appinfo=new Appinfo[appset.size()];
                    // appinfo=appset.toArray(appinfo);
                    Collections.sort(appset, new Comparator<Appinfo>() {
                        @Override
                        public int compare(Appinfo o1, Appinfo o2) {

                            if (Integer.parseInt(o1.totaltime) > Integer.parseInt(o2.totaltime)) {
                                return -1;
                            }
                            if (Integer.parseInt(o1.totaltime) > Integer.parseInt(o2.totaltime)) {
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
                    timeAdapter = new TimeAdapter(context, appset);
                    listView.setAdapter(timeAdapter);
                }
                else{//输入数字不合理时提醒用户。
                    Toast.makeText(TimeActivity.this,"请输入合理的数字！（1-2）",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}
