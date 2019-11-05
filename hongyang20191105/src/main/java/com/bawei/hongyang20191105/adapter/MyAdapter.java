package com.bawei.hongyang20191105.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.hongyang20191105.R;
import com.bawei.hongyang20191105.bean.MyData;
import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private Context mContext;
    private List<MyData.GriddataBean> mList;

    public MyAdapter(Context mContext, List<MyData.GriddataBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_layout, null);
            holder.mImage = convertView.findViewById(R.id.MyImage);
            holder.mText = convertView.findViewById(R.id.MyText);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        Glide.with(mContext).load(mList.get(position).getImageurl()).into(holder.mImage);
        holder.mText.setText(mList.get(position).getTitle() + "");
        return convertView;
    }

    class Holder {
        private ImageView mImage;
        private TextView mText;
    }
}
