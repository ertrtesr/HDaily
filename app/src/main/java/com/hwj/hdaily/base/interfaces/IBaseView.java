package com.hwj.hdaily.base.interfaces;

/**
 * 作者: huangwenjian
 * -
 * 描述: view层接口基类,提供刷新界面,展示错误界面,展示空界面的三个抽象方法
 * -
 * 日期: 16/8/23
 */
public interface IBaseView {
    void showLoading();         //显示加载进度条

    void showError();           //显示错误页面

    void showEmpty();           //显示数据为空时的页面

    void finishLoading();       //结束加载进度条
}
