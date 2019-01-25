package com.example.lovesnow.mvp.contract;

import com.example.lovesnow.entity.VideoEntity;
import com.steven.base.mvp.BaseModel;
import com.steven.base.mvp.BasePresenter;
import com.steven.base.mvp.BaseView;

import java.util.List;

import cn.jzvd.JZDataSource;

/**
 * @user steven
 * @createDate 2019/1/24 10:23
 * @description 自定义
 */
public interface HomePageContract {
    interface View extends BaseView {
        void setHomeData(List<VideoEntity> videoLists);
    }

    interface Model extends BaseModel {

    }

    public abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getHomeData();
    }
}
