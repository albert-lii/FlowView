package com.liyi.example;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainAdapter extends BaseAdapter {
    private ArrayList<String> mList;
    private boolean showImg;

    public MainAdapter() {

    }

    public void setData(ArrayList<String> list, boolean showImg) {
        this.mList = list;
        this.showImg = showImg;
    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
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
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flow_text, null);
        ItemHolder holder = new ItemHolder();
        holder.iv_left = (ImageView) convertView.findViewById(R.id.iv_item_flow_left);
        holder.iv_right = (ImageView) convertView.findViewById(R.id.iv_item_flow_right);
        holder.tv_text = (TextView) convertView.findViewById(R.id.tv_item_flow_text);
        holder.tv_text.setText(mList.get(position));
        if (showImg) {
            holder.iv_left.setVisibility(View.VISIBLE);
            holder.iv_right.setVisibility(View.VISIBLE);
        } else {
            holder.iv_left.setVisibility(View.GONE);
            holder.iv_right.setVisibility(View.GONE);
        }
        return convertView;
    }

    private class ItemHolder {
        private ImageView iv_left, iv_right;
        private TextView tv_text;
    }
}
