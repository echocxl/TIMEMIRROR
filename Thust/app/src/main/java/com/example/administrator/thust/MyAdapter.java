package com.example.administrator.thust;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
//获取次数列表的适配器
public class MyAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    private List<Appinfo> mDatas;



    public MyAdapter(Context context, List<Appinfo> datas) {

              mInflater = LayoutInflater.from(context);
              mDatas = datas;

           }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
                        convertView = mInflater.inflate(R.layout.layout, parent, false); //加载布局
                        holder = new ViewHolder();

                       holder.appname = (TextView) convertView.findViewById(R.id.textView);
                       holder.counts = (TextView) convertView.findViewById(R.id.textView2);
                       holder.appicon=(ImageView)convertView.findViewById(R.id.imageView);


                        convertView.setTag(holder);
                 } else {   //else里面说明，convertView已经被复用了，说明convertView中已经设置过tag了，即holder
                    holder = (ViewHolder) convertView.getTag();
                 }

                 Appinfo app = mDatas.get(position);
              holder.appname.setText(app.applables);
              holder.counts.setText(app.lunchtimes);
              holder.appicon.setImageDrawable(app.appicon);


         return convertView;


}

    private class ViewHolder {

         TextView appname;
         TextView counts;
        // TextView lasttime;
       //  TextView totaltime;
         ImageView appicon;

    }
}
