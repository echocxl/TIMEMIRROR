package com.example.administrator.thust;


import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


import static android.content.Context.USAGE_STATS_SERVICE;

class Appinfo{
     String applables = null;//app标签
     Drawable appicon = null;//app图标
     String lastusetime = null;//app最后一次使用时间
     String totaltime = null;//app运行时间
     String lunchtimes = null;//app运行次数

 }

public class UserStatics {
    Appinfo appinfo;
    List<Appinfo> appset=new ArrayList<Appinfo>();//app列表信息
    List<String> applabels=new ArrayList<String>();
    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Appinfo> getApp(Context context,int days) {
        long time;
        time = System.currentTimeMillis() - days*1 * 24 * 60 * 60 * 1000;//输入天数转换成需要的时间
        UsageStatsManager manager = (UsageStatsManager) context.getSystemService(USAGE_STATS_SERVICE);//获取UsageStatsManager

        List<UsageStats> stats = manager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time, System.currentTimeMillis());//通过 UsageStatsManager获取app列表
        for (UsageStats us : stats) {//遍历stats中的每个ａｐｐ以获取它们的信息。
            appinfo=new Appinfo();
            try {
                PackageManager pm = context.getPackageManager();
                ApplicationInfo applicationInfo = pm.getApplicationInfo(us.getPackageName(), PackageManager.GET_META_DATA);
                if ((applicationInfo.flags & applicationInfo.FLAG_SYSTEM) <= 0) {
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                    String t = format.format(new Date(us.getLastTimeUsed()));
                    Field field = us.getClass().getDeclaredField("mLaunchCount");

                   // sb.append(pm.getApplicationLabel(applicationInfo)+"\t"+t+"\t"+(us.getTotalTimeInForeground()/60000)+"\t"+field.getInt(us)+"\n");
                    if(applabels.contains(pm.getApplicationLabel(applicationInfo).toString())){
                        for(Appinfo a:appset){
                            if (a.applables.equals(pm.getApplicationLabel(applicationInfo).toString())){

                                a.lastusetime=t;
                                a.totaltime=String.valueOf(Integer.parseInt(a.totaltime)+us.getTotalTimeInForeground() / 60000);
                                a.lunchtimes=String.valueOf(Integer.parseInt(a.lunchtimes)+field.getInt(us));
                            }

                        }


                    }
                    else{
                    appinfo.applables = pm.getApplicationLabel(applicationInfo).toString();//获取app标签
                    applabels.add(appinfo.applables);
                    appinfo.appicon=pm.getApplicationIcon(us.getPackageName());//获取pp图标
                    appinfo.lastusetime = t;//app最后一次使用时间
                    appinfo.totaltime = String.valueOf(us.getTotalTimeInForeground() / 60000);//app使用时长
                    appinfo.lunchtimes = String.valueOf(field.getInt(us));//app使用次数
                    appset.add(appinfo);//加入到appset里。

                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return appset;
    }


}