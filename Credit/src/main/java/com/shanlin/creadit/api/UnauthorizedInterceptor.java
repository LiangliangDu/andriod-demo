package com.shanlin.creadit.api;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Description:  错误code拦截
 * User:         沈亮
 * Date:         2016-05-10  13{MINUTE}
 */
public class UnauthorizedInterceptor implements Interceptor {
    private Callback mCallback;

    public UnauthorizedInterceptor(Callback callback) {
        mCallback = callback;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (originalResponse.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
            mCallback.onUserLoggedOut();
        }
        return originalResponse;
    }

    public interface Callback {
        void onUserLoggedOut();
    }
}
