package com.shanlin.creadit.mvp.presenter.impl;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.shanlin.creadit.bean.BaseEntity;
import com.shanlin.creadit.bean.LoginBean;
import com.shanlin.creadit.exception.BaseErrorListener;
import com.shanlin.creadit.mvp.model.impl.LoginRequest;
import com.shanlin.creadit.mvp.view.LoginView;

/**
 * Description:
 * User:         沈亮
 * Date:         2016-05-10  13{MINUTE}
 */
public class LoginPresenterImpl  {

    LoginView loginView;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
    }

    public void login(String name, String pwd, int type) {
        loginView.showLoading();

        new LoginRequest(name,pwd,type).doRequest("", LoginBean.class, new Response.Listener<BaseEntity>() {
            @Override
            public void onResponse(BaseEntity baseEntity) {
                if (baseEntity != null & baseEntity instanceof LoginBean) {
                    String token = ((LoginBean) baseEntity).getResult().getToken();
                    loginView.loginSuccess(token);
                }
            }
        }, new BaseErrorListener() {
            @Override
            public void onErrorResponseListener(VolleyError arg0) {
                loginView.showLoginFaile(arg0.toString());
            }
        });
    }
}
