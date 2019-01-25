package com.example.lovesnow.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.lovesnow.R;
import com.steven.base.base.BaseActivity;

public class AddVideoActivity extends BaseActivity {
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, AddVideoActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_video;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        showWhiteTitle("展示最美的你", R.color.white);
    }
}
