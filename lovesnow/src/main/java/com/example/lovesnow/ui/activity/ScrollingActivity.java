package com.example.lovesnow.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.lovesnow.R;
import com.steven.base.base.BaseActivity;

public class ScrollingActivity extends BaseActivity {
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ScrollingActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_scrolling;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }
}
