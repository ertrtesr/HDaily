package com.hwj.hdaily.model.entity;

import com.hwj.hdaily.model.entity.base.HttpResult;

import java.io.Serializable;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/9
 */

public class SplashInfo extends HttpResult implements Serializable {
    private String text;
    private String img;

    public SplashInfo() {
    }

    public SplashInfo(String text, String img) {
        this.text = text;
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
