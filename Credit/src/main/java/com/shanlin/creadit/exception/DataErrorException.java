package com.shanlin.creadit.exception;

import com.android.volley.VolleyError;

/**
 * 数据错误，code 不为0
 */
public class DataErrorException extends VolleyError {

    public DataErrorException(String message) {
        super(message);
    }
}