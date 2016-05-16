package com.shanlin.creadit.mvp.model.impl;

import com.android.volley.Request;
import com.shanlin.creadit.Config;
import com.shanlin.creadit.mvp.model.BaseVolleyRequest;

import java.util.List;
import java.util.Map;

/**
 *
 */
public class LoginRequest extends BaseVolleyRequest {
    private String mLoginName, mPassword;
    private long type;

    public LoginRequest(String loginName, String password, long typedata) {
        mLoginName = loginName;
        mPassword = password;
        type = typedata;
    }


    @Override
    protected int toHttpMethod() {
        return Request.Method.POST;
    }

    @Override
    protected String toRequestURL() {
        return Config.PART_URL + "auth/login";
    }

    @Override
    protected void toHttpRequestParams(Map<String, Object> params) {
        params.put("username", mLoginName);
        params.put("password", mPassword);
        params.put("type", type);
    }

    @Override
    protected void toHttpRequestHeader(Map<String, String> header) {
    }

    @Override
    protected List<String> toUploadFilePath() {
        return null;
    }
}
