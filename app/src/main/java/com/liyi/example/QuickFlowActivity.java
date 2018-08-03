package com.liyi.example;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.liyi.flow.FlowView;
import com.liyi.flow.adapter.BaseFlowHolder;
import com.liyi.flow.adapter.QuickFlowAdapter;

public class QuickFlowActivity extends Activity {
    private FlowView flowView;
    private FlowAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_flow);
        flowView = findViewById(R.id.flowVi);
        mAdapter = new FlowAdapter();
        mAdapter.setData(Utils.getData1());
        flowView.setAdapter(mAdapter);
    }

    private class FlowAdapter extends QuickFlowAdapter<String, BaseFlowHolder> {

        public FlowAdapter() {
            super();
            addItemType(0, R.layout.flow_view_item_simple_text);
        }

        @Override
        protected int onHandleViewType(int position) {
            return 0;
        }

        @Override
        protected void onHandleView(int position, BaseFlowHolder holder, String item) {
            holder.getTextView(R.id.tv_flow_view_simple_text).setText(item);
        }
    }
}
