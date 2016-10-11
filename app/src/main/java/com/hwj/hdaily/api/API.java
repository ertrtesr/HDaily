package com.hwj.hdaily.api;

import com.hwj.hdaily.base.BaseActivity;
import com.hwj.hdaily.base.BaseFragment;
import com.hwj.hdaily.base.BaseSubscriber;
import com.hwj.hdaily.di.component.DaggerRetrofitComponent;
import com.hwj.hdaily.di.module.RetrofitModule;
import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.android.FragmentEvent;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者: huangwenjian
 * <p>
 * 时间: 16/10/5
 * <p>
 * 描述: API类,访问网络的方法,以及获得ApiService接口的方法
 */

public class API {

    /**
     * 访问网络并进行数据转换的操作,在activity中请求网络时用
     *
     * @param observable service访问接口得到的observable对象
     * @param subscriber 订阅者
     */
    public static void doApi(Observable observable, BaseSubscriber subscriber, BaseActivity activity) {
        observable.subscribeOn(Schedulers.io())                   //这连续几个方法都是RxJava里面的
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())      //AndroidSchedulers是RxAndroid里面的类;
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(subscriber);
    }

    /**
     * 在fragment中请求网络时用
     *
     * @param observable
     * @param subscriber
     * @param fragment
     */
    public static void doApi(Observable observable, BaseSubscriber subscriber, BaseFragment fragment) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(fragment.bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(subscriber);
    }

    public static APIService service() {
        return DaggerRetrofitComponent.builder().retrofitModule(new RetrofitModule()).build().getService();
    }

    public static APIService service(String baseUrl) {
        return DaggerRetrofitComponent.builder().retrofitModule(new RetrofitModule(baseUrl)).build().getServiceByUrl();
    }
}
