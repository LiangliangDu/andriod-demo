package com.shanlin.creadit.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.RadioGroup;

import com.shanlin.creadit.R;
import com.shanlin.creadit.mvp.presenter.impl.LoginPresenterImpl;
import com.shanlin.creadit.mvp.view.LoginView;
import com.shanlin.creadit.ui.fragment.HomePageFragment;
import com.shanlin.creadit.ui.fragment.MineFragment;
import com.shanlin.creadit.ui.fragment.MoreFragment;
import com.shanlin.creadit.ui.fragment.StrategyFragment;
import com.shanlin.creadit.utils.FragmentTabUtils;
import com.shanlin.creadit.utils.LogUtils;
import com.shanlin.creadit.views.MainTabRadioButton;

import java.util.ArrayList;

/**
 * Description:
 * User:         沈亮
 * Date:         2016-05-10  13{MINUTE}
 */
public class MainActivity extends BaseActivity implements LoginView {

    private LoginPresenterImpl mLoginPresenter;
    private ArrayList<Fragment> mFragmentList;
    private RadioGroup mRgTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        mLoginPresenter = new LoginPresenterImpl(this);
        mLoginPresenter.login("admin", "admin123", 1);
        new FragmentTabUtils(mRgTab, getSupportFragmentManager(), mFragmentList, R.id.fragment_container);
        ((MainTabRadioButton) mRgTab.getChildAt(0)).setChecked(true);
    }

    @Override
    public void initView() {
        super.initView();
        mRgTab = (RadioGroup) findViewById(R.id.main_rg_tab);
    }

    @Override
    public void initData() {
        super.initData();
        mFragmentList = new ArrayList<>();
        mFragmentList.add(HomePageFragment.newInstance());
        mFragmentList.add(StrategyFragment.newInstance());
        mFragmentList.add(MineFragment.newInstance());
        mFragmentList.add(MoreFragment.newInstance());
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showLoginFaile(String msg) {
        LogUtils.error("123","LoginFaile"+msg);
    }

    @Override
    public void loginSuccess(String msg) {
        LogUtils.error("123","loginSuccess"+msg);
    }
}
