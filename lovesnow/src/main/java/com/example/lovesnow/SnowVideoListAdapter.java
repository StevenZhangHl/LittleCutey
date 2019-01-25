package com.example.lovesnow;

import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lovesnow.entity.VideoEntity;

import java.util.List;

import cn.jzvd.JZDataSource;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

/**
 * @user steven
 * @createDate 2019/1/23 15:42
 * @description 自定义
 */
public class SnowVideoListAdapter extends BaseQuickAdapter<VideoEntity, BaseViewHolder> {
    public SnowVideoListAdapter(int layoutResId, @Nullable List<VideoEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoEntity item) {
        JzvdStd jzvdStd = helper.getView(R.id.item_jcvideoplayer);
        jzvdStd.setUp(item.getVideoUrl(), "", Jzvd.SCREEN_WINDOW_LIST);
        Glide.with(mContext)
                .load(item.getVideoThumb())
                .into(jzvdStd.thumbImageView);
    }
}
