package com.liyi.example;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.liyi.flow.FlowConfig;
import com.liyi.flow.FlowView;
import com.liyi.flow.adapter.BaseFlowHolder;
import com.liyi.flow.adapter.SimpleFlowAdapter;

import java.util.List;

public class SimpleFlowActivity extends Activity implements View.OnClickListener {
    private FlowView flowView;
    private SimpleFlowAdapter<String, BaseFlowHolder> mAdapter;
    private List<String> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_flow);
        initView();
    }

    private void initView() {
        flowView = findViewById(R.id.flowVi);

        mList = Utils.getData1();
        mAdapter = new SimpleFlowAdapter(mList);
        mAdapter.setLoadData(new SimpleFlowAdapter.LoadData<String>() {
            @Override
            public void onLoadData(int position, String item, TextView textView) {
                textView.setText(item);
            }
        });
        flowView.setAdapter(mAdapter);
        flowView.setOnItemClickListener(new FlowView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                Toast.makeText(SimpleFlowActivity.this, "我是" + position + "号", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_hleft:
                flowView.setHorizontalAlign(FlowConfig.FLOW_HORIZONTAL_ALIGN_LEFT);
                flowView.requestLayout();
                break;
            case R.id.btn_hmiddle:
                flowView.setHorizontalAlign(FlowConfig.FLOW_HORIZONTAL_ALIGN_MIDDLE);
                flowView.requestLayout();
                break;
            case R.id.btn_hright:
                flowView.setHorizontalAlign(FlowConfig.FLOW_HORIZONTAL_ALIGN_RIGHT);
                flowView.requestLayout();
                break;
            case R.id.btn_vtop:
                flowView.setVerticalAlign(FlowConfig.FLOW_VERTICAL_ALIGN_TOP);
                flowView.requestLayout();
                break;
            case R.id.btn_vmiddle:
                flowView.setVerticalAlign(FlowConfig.FLOW_VERTICAL_ALIGN_MIDDLE);
                flowView.requestLayout();
                break;
            case R.id.btn_vbottom:
                flowView.setVerticalAlign(FlowConfig.FLOW_VERTICAL_ALIGN_BOTTOM);
                flowView.requestLayout();
                break;
            case R.id.btn_maxr1:
                flowView.setMaxRow(1);
                flowView.requestLayout();
                break;
            case R.id.btn_maxr2:
                flowView.setMaxRow(2);
                flowView.requestLayout();
                break;
            case R.id.btn_maxr3:
                flowView.setMaxRow(FlowConfig.INVALID_VAL);
                flowView.requestLayout();
                break;
            case R.id.btn_update1:
                mList = Utils.getData1();
                mAdapter.setData(mList);
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.btn_update2:
                mList = Utils.getData2();
                mAdapter.setData(mList);
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.btn_update3:
                mList = Utils.getData3();
                mAdapter.setData(mList);
                mAdapter.notifyDataSetChanged();
                break;
        }
    }
}
