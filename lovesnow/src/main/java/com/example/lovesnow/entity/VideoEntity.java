package com.example.lovesnow.entity;

/**
 * @user steven
 * @createDate 2019/1/25 09:52
 * @description 自定义
 */
public class VideoEntity {
    private String videoUrl;
    private String videoThumb;

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoThumb() {
        return videoThumb;
    }

    public void setVideoThumb(String videoThumb) {
        this.videoThumb = videoThumb;
    }

    public VideoEntity(String videoUrl, String videoThumb) {
        this.videoUrl = videoUrl;
        this.videoThumb = videoThumb;
    }
}
