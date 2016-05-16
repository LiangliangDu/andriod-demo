package com.shanlin.creadit.exception;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.shanlin.creadit.R;
import com.shanlin.creadit.ShanlinApp;
import com.shanlin.creadit.utils.LogUtils;
import com.shanlin.creadit.utils.SharedPreferencesUtil;


/**
 *
 */
public abstract class BaseErrorListener implements Response.ErrorListener {

    private static final String LOG_TAG = BaseErrorListener.class.getSimpleName();

    @Override
    public void onErrorResponse(VolleyError arg0) {
        if (arg0 instanceof AuthFailureError) {
            LogUtils.error(LOG_TAG, "[HttpResponse error]" + "[AuthFailureError]" + arg0.getMessage());
            ShanlinApp.genInstance().showToast(R.string.exception_no_authority);
            SharedPreferencesUtil.cleanStringValue(ShanlinApp.genInstance(), "token");
            gotoLoginActivityAndFinishAllBefore();
            return;
        } else if (arg0 instanceof TimeoutError) {
            LogUtils.error(LOG_TAG, "[HttpResponse error]" + "[TimeoutError]" + arg0.getMessage());
            ShanlinApp.genInstance().showToast(R.string.exception_netword_error);
        } else if (arg0 instanceof NoConnectionError) {
            LogUtils.error(LOG_TAG, "[HttpResponse error]" + "[NoConnectionError]" + arg0.getMessage());
            ShanlinApp.genInstance().showToast(R.string.exception_netword_error);
        } else if (arg0 instanceof NetworkError) {
            LogUtils.error(LOG_TAG, "[HttpResponse error]" + "[NetworkError]" + arg0.getMessage());
            ShanlinApp.genInstance().showToast(R.string.exception_netword_error);
        }
        if (arg0.getMessage()!=null) {
            ShanlinApp.genInstance().showToast(arg0.getMessage());
        } else {
            ShanlinApp.genInstance().showToast(R.string.exception_netword_error);
        }
        onErrorResponseListener(arg0);
    }

    private void gotoLoginActivityAndFinishAllBefore() {
        //TODO token 过期
//        Intent intent = new Intent(ShanlinApp.genInstance(), LoginActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        ShanlinCreditApp.genInstance().startActivity(intent);
    }

    public abstract void onErrorResponseListener(VolleyError arg0);

}