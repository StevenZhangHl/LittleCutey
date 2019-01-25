package com.steven.jcvideoplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.steven.base.ARouterPath;
import com.steven.base.RxHelper;
import com.steven.base.rx.RxManager;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class WelcomeActivity extends AppCompatActivity {
    /**
     * 启动页时间-暂定1s
     */
    public int DURATION = 2;
    private RxManager rxManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rxManager = new RxManager();
        jumpActivity();
    }

    private void jumpActivity() {
        rxManager.add(Observable.timer(DURATION, TimeUnit.SECONDS).compose(RxHelper.<Long>applySchedulers()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                ARouter.getInstance().build(ARouterPath.LOVESNOW_MAIN_ACTIVITY).navigation();
                finish();
            }
        }));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rxManager.clear();
    }
}
