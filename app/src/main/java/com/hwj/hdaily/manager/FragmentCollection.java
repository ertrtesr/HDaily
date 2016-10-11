package com.hwj.hdaily.manager;

import com.hwj.hdaily.base.BaseFragment;

import java.util.HashMap;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/10/10
 */

public class FragmentCollection {
    public static HashMap<String, BaseFragment> map = new HashMap<>();

    public static void addFragment(BaseFragment fragment) {
        map.put(fragment.getClass().getSimpleName(), fragment);
    }

    /**
     * 从map集合中移除指定的fragment
     *
     * @param fragment
     */
    public static void remove(BaseFragment fragment) {
        String name = fragment.getClass().getSimpleName();
        if (map.containsKey(name)) {
            map.remove(name);         //从map中移除对应name的fragment
        }
    }

    /**
     * 从map集合中移除指定名称的fragment
     *
     * @param name
     */
    public static void removeFragment(String name) {
        if (map.containsKey(name)) {
            map.remove(name);
        }
    }

    /**
     * 清空map集合中所有的fragment
     */
    public static void removeAll() {
        map.clear();
    }

    public static BaseFragment getFragment(Class<? extends BaseFragment> clazz) {
        return map.get(clazz.getSimpleName());
    }

    public static BaseFragment getFragment(String name) {
        return map.get(name);
    }
}
