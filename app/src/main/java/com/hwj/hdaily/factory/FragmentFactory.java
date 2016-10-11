package com.hwj.hdaily.factory;

import android.support.v4.util.SparseArrayCompat;

import com.hwj.hdaily.base.BaseFragment;
import com.hwj.hdaily.conf.Constants;
import com.hwj.hdaily.view.fragment.ColumnFragment;
import com.hwj.hdaily.view.fragment.DailyFragment;
import com.hwj.hdaily.view.fragment.HotFragment;
import com.hwj.hdaily.view.fragment.SubjectFragment;

/**
 * 作者: huangwenjian
 * <p>
 * 描述: Fragment工厂类
 * <p>
 * 时间: 16/9/18
 */
public class FragmentFactory {
    public static SparseArrayCompat<BaseFragment> cachesFragment = new SparseArrayCompat<>();

    public static BaseFragment getFragment(int position) {

        BaseFragment fragment = null;
        // 如果缓存里面有对应的fragment,就直接取出返回

        BaseFragment tmpFragment = cachesFragment.get(position);
        if (tmpFragment != null) {
            fragment = tmpFragment;
            return fragment;
        }
        switch (position) {
            case Constants.DAILY:
                fragment = new DailyFragment();
                break;
            case Constants.SUBJECT:
                fragment = new SubjectFragment();
                break;
            case Constants.COLUMN:
                fragment = new ColumnFragment();
                break;
            case Constants.HOT:
                fragment = new HotFragment();
                break;
            default:
                break;
        }
        // 保存对应的fragment
        cachesFragment.put(position, fragment);
        return fragment;
    }
}
