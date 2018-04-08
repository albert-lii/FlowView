package com.liyi.flow.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liyi.view.R;

import java.util.List;

/**
 * 简单的适配器
 */
public class SimpleFlowAdapter<T> extends BaseFlowAdapter {
    private List<T> mTagList;
    private LoadData mLoadData;

    public void setData(List<T> list) {
        this.mTagList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.flow_view_item_simple_text, null);
        TextView tv_content = (TextView) convertView.findViewById(R.id.tv_flow_view_simple_text);
        if (mLoadData != null) {
            mLoadData.onLoadData(position, mTagList.get(position), tv_content);
        }
        return convertView;
    }

    @Override
    public int getCount() {
        return mTagList != null ? mTagList.size() : 0;
    }

    public void setLoadData(LoadData loadData) {
        this.mLoadData = loadData;
    }

    public interface LoadData {
        void onLoadData(int position, Object data, TextView view);
    }
}
