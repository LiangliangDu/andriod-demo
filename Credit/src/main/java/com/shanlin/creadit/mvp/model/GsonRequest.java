package com.shanlin.creadit.mvp.model;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.lidroid.xutils.http.client.multipart.MultipartEntity;
import com.lidroid.xutils.http.client.multipart.content.FileBody;
import com.lidroid.xutils.http.client.multipart.content.StringBody;
import com.shanlin.creadit.bean.BaseEntity;
import com.shanlin.creadit.bean.BaseVolleyResponse;
import com.shanlin.creadit.exception.DataErrorException;
import com.shanlin.creadit.utils.LogUtils;
import com.shanlin.creadit.utils.SlinUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GsonRequest extends Request<BaseEntity> {
    private static final String LOG_TAG = GsonRequest.class.getSimpleName();

    private final Listener<BaseEntity> mListener;
    private Map<String, Object> mParams;
    private Class<? extends BaseVolleyResponse> mClzz;
    private Map<String, String> mHeaderParams;
    private List<String> mFilePath;

    private MultipartEntity mReqEntity;

    public GsonRequest(String Tag, int method, String url, Map<String, String> headers, Map<String, Object> params,
                       List<String> files, Class<? extends BaseVolleyResponse> clzz,
                       Listener<BaseEntity> listener, ErrorListener errorListener) {
        super(method, url, errorListener);
        setRetryPolicy(new DefaultRetryPolicy(8000, 0, 1.0f));
        mListener = listener;
        mParams = params;
        mHeaderParams = headers;
        mFilePath = files;
        mClzz = clzz;
        mReqEntity = new MultipartEntity();

        setShouldCache(false);
        setTag(Tag);
        if (!SlinUtil.isCollectionEmpty(mFilePath)) {
            // upload files
            buildMultipartEntity();
        }

    }

    @Override
    protected Response<BaseEntity> parseNetworkResponse(NetworkResponse response) {
        // this will be called in child thread. Not in ui thread
        try {
            int status = response.statusCode;
            LogUtils.error(LOG_TAG, "[HttpResponse Status]" + status);
            if (status >= 200 && status < 300) { // 200 ~ 300 are success
                String jsonString = new String(response.data,
                        HttpHeaderParser.parseCharset(response.headers));
                LogUtils.error(LOG_TAG, "[JSON CONTENT]" + jsonString);
                if (TextUtils.isEmpty(jsonString)) {
                    return Response.error(new ParseError(new Exception("Json error")));
                } else {
                    // json to object
                    final BaseVolleyResponse localResponse = JSON
                            .parseObject(jsonString, mClzz);
                    if (localResponse == null) {
                        return Response.error(new ParseError(new Exception("Json error")));
                    } else {
                        // code "0" success, else failure.
                        if (!"000000".equals(localResponse.getCode())) {
                            return Response.error(new DataErrorException(localResponse.getMessage()));
                        } else {
                            // save info local. like save data to database.
                            BaseEntity result = localResponse.saveInfo(localResponse);
                            return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
                        }
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
        return Response.error(new ParseError(new Exception("Json error")));
    }

    @Override
    protected void deliverResponse(BaseEntity response) {
        mListener.onResponse(response);
    }

    @Override
    public String getBodyContentType() {
        if (!SlinUtil.isCollectionEmpty(mFilePath)) {
            return mReqEntity.getContentType().getValue();
        }
        return "application/json";
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        if (!SlinUtil.isCollectionEmpty(mFilePath)) {
            // upload files
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                mReqEntity.writeTo(bos);
            } catch (IOException e) {
                VolleyLog.e("IOException writing to ByteArrayOutputStream");
            }
            return bos.toByteArray();
        }

        // other
        if (mParams != null) {
            String ss = JSON.toJSONString(mParams);
            LogUtils.error(LOG_TAG, ss);
            return ss.getBytes();
        }
        return super.getBody();
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return mHeaderParams;
    }

    private void buildMultipartEntity() {
        try {
            for (String filepath : mFilePath) {
                File file = new File(filepath);
                FileBody filePart = new FileBody(file);
                mReqEntity.addPart(file.getName(), filePart);
            }
            if (mParams != null && !mParams.isEmpty()) {
                Iterator<String> keySet = mParams.keySet().iterator();
                while (keySet.hasNext()) {
                    String key = keySet.next();
                    String ss = JSON.toJSONString(mParams.get(key));
                    mReqEntity.addPart(key, new StringBody(ss));
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
