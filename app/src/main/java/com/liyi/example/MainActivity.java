package com.liyi.example;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_simple:
                startActivity(new Intent(this, SimpleFlowActivity.class));
                break;
            case R.id.btn_quick:
                startActivity(new Intent(this, QuickFlowActivity.class));
                break;
        }
    }
}
