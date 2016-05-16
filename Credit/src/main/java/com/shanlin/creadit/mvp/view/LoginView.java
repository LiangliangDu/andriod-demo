package com.shanlin.creadit.mvp.view;

/**
 * Description:  登陆view层
 * User:         沈亮
 * Date:         2016-05-10  13{MINUTE}
 */
public interface LoginView {
    void showLoading();

    void showLoginFaile(String msg);

    void loginSuccess(String msg);
}
