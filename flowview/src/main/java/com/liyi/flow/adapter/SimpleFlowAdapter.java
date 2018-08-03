package com.liyi.flow.adapter;

import android.widget.TextView;

import com.liyi.view.R;

import java.util.List;

public class SimpleFlowAdapter<T, K extends BaseFlowHolder> extends QuickFlowAdapter<T, K> {
    private LoadData<T> mLoadData;

    public SimpleFlowAdapter() {

    }

    public SimpleFlowAdapter(List<T> list) {
        setData(list);
        addItemType(0, R.layout.flow_view_item_simple_text);
    }

    public void setLoadData(LoadData<T> loadData) {
        this.mLoadData = loadData;
    }

    @Override
    protected int onHandleViewType(int position) {
        return 0;
    }

    @Override
    protected void onHandleView(int position, K holder, T item) {
        if (mLoadData != null) {
            mLoadData.onLoadData(position, item, holder.getTextView(R.id.tv_flow_view_simple_text));
        }
    }

    public interface LoadData<V> {
        void onLoadData(int position, V item, TextView textView);
    }
}
