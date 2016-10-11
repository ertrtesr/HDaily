package com.hwj.hdaily.api;

import com.hwj.hdaily.model.entity.ColumnInfo;
import com.hwj.hdaily.model.entity.DailyInfo;
import com.hwj.hdaily.model.entity.HotInfo;
import com.hwj.hdaily.model.entity.SplashInfo;
import com.hwj.hdaily.model.entity.SubjectInfo;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 作者: huangwenjian
 * -
 * 描述:
 * -
 * 日期: 16/8/22
 */
public interface APIService {
    public static final String BASE_URL = "http://news-at.zhihu.com/api/4/";
//
//    @GET("data/cityinfo/101010100.html")
//    Observable<WeatherBean> getWeatherInfo();
//
//    @GET("n8/number/dictionary")
//    Observable<UserBean> getUserInfo(@Query("imsi") String name);

    @GET("start-image/{res}")
    Observable<SplashInfo> getSplashInfo(@Path("res") String res);

    @GET("news/latest")
    Observable<DailyInfo> getDailyInfo();           //获取日报页面的信息

    @GET("themes")
    Observable<SubjectInfo> getSubjectInfo();       //获取主题页面的信息

    @GET("sections")
    Observable<ColumnInfo> getColumnInfo();         //获取专栏页面的信息

    @GET("news/hot")
    Observable<HotInfo> getHotInfo();               //获取热门页面的信息
}
