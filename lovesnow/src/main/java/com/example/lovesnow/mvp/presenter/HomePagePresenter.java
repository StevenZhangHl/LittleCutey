package com.example.lovesnow.mvp.presenter;

import com.example.lovesnow.VideoConstants;
import com.example.lovesnow.entity.VideoEntity;
import com.example.lovesnow.mvp.contract.HomePageContract;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.jzvd.JZDataSource;

/**
 * @user steven
 * @createDate 2019/1/24 10:21
 * @description 自定义
 */
public class HomePagePresenter extends HomePageContract.Presenter {
    @Override
    public void getHomeData() {
        List<VideoEntity> result = new ArrayList<>();
        for (int i = 0; i < VideoConstants.videoUrls[0].length; i++) {
            VideoEntity videoEntity = new VideoEntity(VideoConstants.videoUrls[0][i], VideoConstants.videoThumbs[i]);
            result.add(videoEntity);
        }
        mView.setHomeData(result);
    }
}
