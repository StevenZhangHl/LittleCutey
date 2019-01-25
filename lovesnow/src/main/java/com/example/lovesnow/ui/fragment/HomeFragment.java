package com.example.lovesnow.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.lovesnow.R;
import com.example.lovesnow.SnowVideoListAdapter;
import com.example.lovesnow.entity.VideoEntity;
import com.example.lovesnow.mvp.contract.HomePageContract;
import com.example.lovesnow.mvp.model.HomePageModel;
import com.example.lovesnow.mvp.presenter.HomePagePresenter;
import com.jaeger.library.StatusBarUtil;
import com.steven.base.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @user steven
 * @createDate 2019/1/24 09:55
 * @description 自定义
 */
public class HomeFragment extends BaseFragment<HomePagePresenter, HomePageModel> implements HomePageContract.View {
    private RecyclerView recyclerview;
    private SnowVideoListAdapter videoListAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_homepage;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        StatusBarUtil.setColor(_mActivity, getResources().getColor(R.color.color_1296db), 0);
        recyclerview = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));
        videoListAdapter = new SnowVideoListAdapter(R.layout.snow_item_video_list, new ArrayList<VideoEntity>());
        recyclerview.setAdapter(videoListAdapter);
        mPresenter.getHomeData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            _mActivity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            StatusBarUtil.setColor(_mActivity, getResources().getColor(R.color.color_1296db), 0);
        }
    }

    @Override
    public void onError(String msg) {

    }

    @Override
    protected void setFitsSystemWindows() {

    }

    @Override
    public void setHomeData(List<VideoEntity> videoLists) {
        videoListAdapter.setNewData(videoLists);
    }
}
