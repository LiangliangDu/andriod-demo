package com.shanlin.creadit.exception;

import com.android.volley.VolleyError;
import com.shanlin.creadit.R;
import com.shanlin.creadit.ShanlinApp;

/**
 * 服务器异常 或 Json解析错误
 */
public class ServerErrorException extends VolleyError {

    public ServerErrorException() {
        super(ShanlinApp.genInstance().getResources().getString(R.string.exception_json_error));
    }
}