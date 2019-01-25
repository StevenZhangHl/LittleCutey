package com.example.lovesnow.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.lovesnow.R;
import com.steven.base.base.BaseFragment;
import com.steven.base.util.KeyboardUtil;
import com.steven.base.util.Titanic;
import com.steven.base.util.ToastUitl;
import com.steven.base.util.Typefaces;
import com.steven.base.widget.TitanicTextView;

import java.util.Random;

import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;

/**
 * @user steven
 * @createDate 2019/1/24 10:31
 * @description 自定义
 */
public class MeFragment extends BaseFragment implements View.OnClickListener {
    private DanmakuView danmaku_view;
    private DanmakuContext danmakuContext;
    private boolean showDanmaku;
    private RelativeLayout rl_input_danku;
    private ImageView bt_send_danku;
    private EditText et_danku;
    private TitanicTextView titanic_textview;
    BaseDanmakuParser parser = new BaseDanmakuParser() {
        @Override
        protected IDanmakus parse() {
            return new Danmakus();
        }
    };

    public static MeFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        MeFragment meFragment = new MeFragment();
        meFragment.setArguments(bundle);
        return meFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        danmaku_view = (DanmakuView) rootView.findViewById(R.id.danmaku_view);
        et_danku = (EditText) rootView.findViewById(R.id.et_danku);
        rl_input_danku = (RelativeLayout) rootView.findViewById(R.id.rl_input_danku);
        bt_send_danku = (ImageView) rootView.findViewById(R.id.bt_send_danku);
        titanic_textview = (TitanicTextView) rootView.findViewById(R.id.titanic_textview);
        initDanmakuView();
        titanic_textview.setTypeface(Typefaces.get(_mActivity, "Satisfy-Regular.ttf"));
        new Titanic().start(titanic_textview);
        bt_send_danku.setOnClickListener(this);
    }

    private void initDanmakuView() {
        danmaku_view.setCallback(new DrawHandler.Callback() {
            @Override
            public void prepared() {
                danmaku_view.start();
                showDanmaku = true;
                generateSomeDanmaku();
            }

            @Override
            public void updateTimer(DanmakuTimer timer) {

            }

            @Override
            public void danmakuShown(BaseDanmaku danmaku) {

            }

            @Override
            public void drawingFinished() {

            }
        });
        danmakuContext = DanmakuContext.create();
        danmakuContext.setScaleTextSize(2.0f);
        danmaku_view.prepare(parser, danmakuContext);
    }

    //随机生成一些弹幕内容以供测试
    private void generateSomeDanmaku() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (showDanmaku) {
                    int time = new Random().nextInt(500);
                    String content = "老婆我爱你";
                    addDanmaku(content);
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void addDanmaku(String content) {
        BaseDanmaku danmaku = danmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        danmaku.text = content;
        danmaku.padding = 5;
        danmaku.textSize = 25;
        danmaku.timeOffset = 5;
        if (!content.equals("老婆我爱你")) {
            danmaku.borderColor = Color.BLUE;
        }
        danmaku.textColor = Color.argb(new Random().nextInt(256), new Random().nextInt(256),
                new Random().nextInt(256), new Random().nextInt(256));
        danmaku.setTime(danmaku_view.getCurrentTime());
        danmaku_view.addDanmaku(danmaku);
    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (danmaku_view != null && danmaku_view.isPrepared()) {
                danmaku_view.pause();
            }
        } else {
            if (danmaku_view != null && danmaku_view.isPrepared() && danmaku_view.isPaused()) {
                danmaku_view.resume();
            }
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        showDanmaku = false;
        if (danmaku_view != null) {
            danmaku_view.release();
            danmaku_view = null;
        }
    }

    private boolean isSend = false;

    @Override
    public void onClick(View v) {
        if (v == bt_send_danku) {
            if (isSend) {
                String danku = et_danku.getText().toString().trim();
                if (TextUtils.isEmpty(danku)) {
                    ToastUitl.showShort("内容不能为空");
                    return;
                }
                addDanmaku(danku);
                KeyboardUtil.closeKeybord(et_danku, _mActivity);
                rl_input_danku.setVisibility(View.INVISIBLE);
                isSend = false;
            } else {
                rl_input_danku.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.SlideInRight)
                        .duration(1000)
                        .playOn(et_danku);
                isSend = true;
            }
        }
    }
}
