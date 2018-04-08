package com.liyi.flow.adapter;

import android.view.View;
import android.view.ViewGroup;

/**
 * FlowView 的适配器的基类
 */
public abstract class BaseFlowAdapter {

    public abstract View getView(int position, View convertView, ViewGroup parent);

    public abstract int getCount();
}
