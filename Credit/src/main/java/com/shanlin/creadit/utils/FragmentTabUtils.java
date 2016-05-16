package com.shanlin.creadit.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.RadioGroup;

import java.util.ArrayList;

/**
 * Description:  主界面切换fragment工具类
 * User:         沈亮
 * Date:         2016-05-10  13{MINUTE}
 */
public class FragmentTabUtils implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup mTabGroup;
    private FragmentManager mFragmentManager;
    private ArrayList<Fragment> mFragmentList;
    private int containerId;

    public FragmentTabUtils(RadioGroup mTabGroup, FragmentManager mFragmentManager, ArrayList<Fragment> mFragmentList, int containerId) {
        this.mTabGroup = mTabGroup;
        this.mFragmentManager = mFragmentManager;
        this.mFragmentList = mFragmentList;
        this.containerId=containerId;
        mTabGroup.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        for (int i = 0; i < group.getChildCount(); i++) {
            Fragment fragment = mFragmentList.get(i);
            if (group.getChildAt(i).getId()==checkedId) {
                if (fragment.isAdded()) {
                    mFragmentManager.beginTransaction().show(fragment).commit();
                }else{
                    mFragmentManager.beginTransaction().add(containerId, fragment).show(fragment).commit();
                }
            }else{
                mFragmentManager.beginTransaction().hide(fragment).commit();
            }
        }
    }
}
