package com.hwj.hdaily.manager;

import android.app.Activity;

import com.hwj.hdaily.base.BaseActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: huangwenjian
 * <p/>
 * Des:Activity的栈管理类,用于添加和结束Activity
 * <p/>
 * Date: 2016-07-07
 */
public class ActivityCollection {
    public static HashMap<String, BaseActivity> map = new HashMap<>();

    public static void addActivity(BaseActivity activity) {
        map.put(activity.getClass().getSimpleName(), activity);
    }

    /**
     * 从map集合中移除指定的activity
     *
     * @param activity
     */
    public static void removeActivity(BaseActivity activity) {
        String name = activity.getClass().getSimpleName();
        if (map.containsKey(name)) {
            map.remove(name);         //从map中移除对应name的activity
        }
    }

    /**
     * 从map集合中移除指定名称的activity
     *
     * @param name
     */
    public static void removeActivity(String name) {
        if (map.containsKey(name)) {
            map.remove(name);
        }
    }

    /**
     * 结束当前的activity
     *
     * @param activity
     */
    public static void finishActivity(BaseActivity activity) {
        activity.finish();
    }

    /**
     * 根据名称结束任意指定的activity
     *
     * @param name
     */
    public static void finishActivity(String name) {
        if (map.containsKey(name) && map.get(name) != null) { //如果对应的activity不为空
            map.get(name).finish();
        }
    }

    /**
     * finish所有的activity
     */
    public static void finishAllActivities() {
        for (Map.Entry<String, BaseActivity> entry : map.entrySet()) {
            //            String name = entry.getKey();
            Activity activity = entry.getValue();   //得到每一个Activity
            if (activity != null) {
                activity.finish();
            }
        }
    }

    /**
     * 清空map集合中所有的activity
     */
    public static void removeAll() {
        map.clear();
    }

    public static BaseActivity getActivity(Class<? extends BaseActivity> clazz) {
        return map.get(clazz.getSimpleName());
    }

    public static BaseActivity getActivity(String name) {
        return map.get(name);
    }
}
