package com.example.lovesnow.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.lovesnow.R;
import com.example.lovesnow.ui.fragment.HomeFragment;
import com.example.lovesnow.ui.fragment.MeFragment;
import com.jaeger.library.StatusBarUtil;
import com.steven.base.ARouterPath;
import com.steven.base.util.ToastUitl;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;

@Route(path = ARouterPath.LOVESNOW_MAIN_ACTIVITY)
public class MainActivity extends SupportActivity implements View.OnClickListener {
    private HomeFragment homeFragment;
    private MeFragment meFragment;

    /**
     * fragment集合
     */
    private SupportFragment[] mFragments = new SupportFragment[2];
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    private RadioGroup bottom_bar;
    private ImageView img_protruding;
    private RadioButton rb_center;
    /**
     * 当前选中的位置
     */
    private int currentPosition = FIRST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lovesnow_main);
        if (savedInstanceState == null) {
            homeFragment = new HomeFragment();
            meFragment = MeFragment.newInstance("1");
            setmFragmentsData();
            loadMultipleRootFragment(R.id.ll_fragment_container, FIRST, mFragments);
        } else {
            homeFragment = findFragment(HomeFragment.class);
            meFragment = findFragment(MeFragment.class);
            setmFragmentsData();
        }
        bottom_bar = (RadioGroup) findViewById(R.id.bottom_bar);
        img_protruding = (ImageView) findViewById(R.id.img_protruding);
        rb_center = (RadioButton) findViewById(R.id.rb_center);
        img_protruding.setOnClickListener(this);
        initTabLayout();
    }

    private void initTabLayout() {
        bottom_bar.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_home) {
                    currentPosition = 0;
                } else if (checkedId == R.id.rb_my) {
                    currentPosition = 1;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                        StatusBarUtil.setColor(MainActivity.this, getResources().getColor(R.color.white), 0);
                    } else {
                        StatusBarUtil.setColor(MainActivity.this, getResources().getColor(R.color.black), 0);
                    }
                }
                showHideFragment(mFragments[currentPosition]);
            }
        });
    }

    private void setmFragmentsData() {
        mFragments[FIRST] = homeFragment;
        mFragments[SECOND] = meFragment;
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            JzvdStd.releaseAllVideos();
        } catch (Exception e) {
        }
    }

    @Override
    public void onClick(View v) {
        if (v == img_protruding) {
            AddVideoActivity.startActivity(this);
        }
    }

    private long clickTime = 0;

    @Override
    public void onBackPressedSupport() {
        long currentTime = System.currentTimeMillis();
        long time = 2000;
        if ((currentTime - clickTime) > time) {
            ToastUitl.showShort(R.string.hint_exit);
            clickTime = System.currentTimeMillis();
        } else {
            super.onBackPressedSupport();
        }
    }
}
