package com.liyi.example;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.liyi.flow.FlowConfig;
import com.liyi.flow.FlowView;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button btn_hleft, btn_hmiddle, btn_hright;
    private Button btn_vtop, btn_vmiddle, btn_vbottom;
    private Button btn_row1, btn_row2, btn_row_nolimit;
    private Button btn_update1, btn_update2, btn_update3;
    private FlowView flowLayout;

    private MainAdapter mAdapter;
    private ArrayList<String> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        addListeners();
    }

    private void initUI() {
        btn_hleft = (Button) findViewById(R.id.btn_hleft);
        btn_hmiddle = (Button) findViewById(R.id.btn_hmiddle);
        btn_hright = (Button) findViewById(R.id.btn_hright);
        btn_vtop = (Button) findViewById(R.id.btn_vtop);
        btn_vmiddle = (Button) findViewById(R.id.btn_vmiddle);
        btn_vbottom = (Button) findViewById(R.id.btn_vbottom);
        btn_row1 = (Button) findViewById(R.id.btn_maxr1);
        btn_row2 = (Button) findViewById(R.id.btn_maxr2);
        btn_row_nolimit = (Button) findViewById(R.id.btn_maxr3);
        btn_update1 = (Button) findViewById(R.id.btn_update1);
        btn_update2 = (Button) findViewById(R.id.btn_update2);
        btn_update3 = (Button) findViewById(R.id.btn_update3);
        flowLayout = (FlowView) findViewById(R.id.flowLayout);

        mAdapter = new MainAdapter();
        mList = new ArrayList<String>();
        update1();
    }

    private void addListeners() {
        btn_hleft.setOnClickListener(this);
        btn_hmiddle.setOnClickListener(this);
        btn_hright.setOnClickListener(this);
        btn_vtop.setOnClickListener(this);
        btn_vmiddle.setOnClickListener(this);
        btn_vbottom.setOnClickListener(this);
        btn_row1.setOnClickListener(this);
        btn_row2.setOnClickListener(this);
        btn_row_nolimit.setOnClickListener(this);
        btn_update1.setOnClickListener(this);
        btn_update2.setOnClickListener(this);
        btn_update3.setOnClickListener(this);
        flowLayout.setOnItemClickListener(new FlowView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                Toast.makeText(MainActivity.this, "我是" + position + "号", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_hleft:
                flowLayout.setFlowHorAlign(FlowConfig.FLOW_HOR_LEFT);
                break;
            case R.id.btn_hmiddle:
                flowLayout.setFlowHorAlign(FlowConfig.FLOW_HOR_MIDDLE);
                break;
            case R.id.btn_hright:
                flowLayout.setFlowHorAlign(FlowConfig.FLOW_HOR_RIGHT);
                break;
            case R.id.btn_vtop:
                flowLayout.setFlowVertAlign(FlowConfig.FLOW_VERT_TOP);
                break;
            case R.id.btn_vmiddle:
                flowLayout.setFlowVertAlign(FlowConfig.FLOW_VERT_MIDDLE);
                break;
            case R.id.btn_vbottom:
                flowLayout.setFlowVertAlign(FlowConfig.FLOW_VERT_BOTTOM);
                break;
            case R.id.btn_maxr1:
                flowLayout.setFlowMaxRows(1);
                break;
            case R.id.btn_maxr2:
                flowLayout.setFlowMaxRows(2);
                break;
            case R.id.btn_maxr3:
                flowLayout.setFlowMaxRows(FlowConfig.INVALID_VAL);
                break;
            case R.id.btn_update1:
                update1();
                break;
            case R.id.btn_update2:
                update2();
                break;
            case R.id.btn_update3:
                update3();
                break;
        }
    }

    private void update1() {
        mList.clear();
        for (int i = 0; i < 4; i++) {
            mList.add("啦啦啦啦啦啦");
            mList.add("切西瓜，切西瓜，切西瓜，切西瓜，切西瓜，吃西瓜，吃西瓜，红太阳，热热热热热热热热");
            mList.add("嘟嘟嘟嘟嘟嘟\nuuuuu");
            mList.add("ABC ABC");
            mList.add("check it out");
            mList.add("静静");
        }
        mAdapter.setData(mList, false);
        flowLayout.setAdapter(mAdapter);
    }

    private void update2() {
        mList.clear();
        for (int i = 0; i < 4; i++) {
            mList.add("缘分像一道桥");
            mList.add("Get it  on  the  floor");
            mList.add("头文字D");
            mList.add("albert-lii");
            mList.add("be  the  burning fire");
            mList.add("压力背包");
        }
        mAdapter.setData(mList, true);
        flowLayout.setAdapter(mAdapter);
    }

    private void update3() {
        mList.clear();
        for (int i = 0; i < 4; i++) {
            mList.add("巴比隆");
            mList.add("齐格飞");
            mList.add("幻魔大剑\n天魔失坠");
            mList.add("liyi");
            mList.add("亚瑟");
            mList.add("弗兰克斯坦");
        }
        mAdapter.setData(mList, true);
        flowLayout.setAdapter(mAdapter);
    }
}
