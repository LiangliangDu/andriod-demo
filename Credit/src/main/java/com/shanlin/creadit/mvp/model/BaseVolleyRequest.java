package com.shanlin.creadit.mvp.model;

import android.net.Uri;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.shanlin.creadit.Config;
import com.shanlin.creadit.ShanlinApp;
import com.shanlin.creadit.bean.BaseEntity;
import com.shanlin.creadit.bean.BaseVolleyResponse;
import com.shanlin.creadit.exception.NetworkErrorException;
import com.shanlin.creadit.utils.EncryptUtils;
import com.shanlin.creadit.utils.LogUtils;
import com.shanlin.creadit.utils.NetworkUtil;
import com.shanlin.creadit.utils.SystemUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseVolleyRequest {
    protected abstract int toHttpMethod();

    protected abstract String toRequestURL();

    protected abstract void toHttpRequestParams(Map<String, Object> params);

    protected abstract void toHttpRequestHeader(Map<String, String> header);

    protected abstract List<String> toUploadFilePath();

    private int mHttpMethod;
    private String mRequestUrl;
    private Map<String, Object> mContentParams;
    private Map<String, String> mHeaderParams;
    private List<String> mUploadFilesPath;

    private void initRequest() {
        mHttpMethod = toHttpMethod();
        mRequestUrl = toRequestURL();
        mUploadFilesPath = toUploadFilePath();
        mContentParams = getHttpRequestContentParams();
        if (mHttpMethod == Request.Method.DELETE || mHttpMethod == Request.Method.GET)
            mRequestUrl = toGetUrl(mRequestUrl, mContentParams);
        mHeaderParams = getHttpRequestHeaderParams();
    }

    private Map<String, Object> getHttpRequestContentParams() {
        Map<String, Object> params = new HashMap<String, Object>();
        toHttpRequestParams(params);
        return params;
    }

    private Map<String, String> getHttpRequestHeaderParams() {
        Map<String, String> params = new HashMap<String, String>();
        toHttpRequestHeader(params);
        long time = System.currentTimeMillis();
        params.put("t", "" + time);
        params.put("v", Config.API_VERSION + "");
        params.put("ip", NetworkUtil.getLocalIpAddress(ShanlinApp.genInstance()));
        params.put("cv", String.valueOf(SystemUtils.getDeviceVersion()));
        params.put("di", SystemUtils.getDeviceId(ShanlinApp.genInstance()));
        params.put("pf", Config.pf);
        String body = JSON.toJSONString(mContentParams);
        String sign = EncryptUtils.encryptMD5(EncryptUtils.encryptMD5(body + time) + Config.API_VERSION);
        params.put("sign", sign);
        return params;
    }

    @SuppressWarnings("unchecked")
    public void doRequest(String Tag, final Class<? extends BaseVolleyResponse> clzz,
                          Listener<BaseEntity> listener, ErrorListener errorListener) {
        if (!NetworkUtil.isNetWorkAvailable(ShanlinApp.genInstance())) {
            errorListener.onErrorResponse(new NetworkErrorException());
            return;
        }
        initRequest();
        @SuppressWarnings("rawtypes")
        GsonRequest request = new GsonRequest(Tag, mHttpMethod, mRequestUrl, mHeaderParams, mContentParams,
                mUploadFilesPath, clzz, listener, errorListener);
        ShanlinApp.genQueue().add(request);
    }

    private String toGetUrl(String url, Map<String, Object> entity) {
        StringBuilder urlSB = new StringBuilder();
        if (entity != null && !entity.isEmpty()) {
            for (String key : entity.keySet()) {
                if (urlSB.length() != 0) {
                    urlSB.append("&");
                }
                LogUtils.error("GsonRequest",
                        "request" + JSON.toJSONString(entity.get(key)));
                urlSB.append(key).append("=").append(Uri.encode(JSON.toJSONString(entity.get(key))));
            }
        }
        if ("".equals(urlSB.toString())) {
            return url;
        }
        LogUtils.error("GsonRequest", "[request url]" + urlSB);
        return url + "?" + urlSB.toString();
    }

}
