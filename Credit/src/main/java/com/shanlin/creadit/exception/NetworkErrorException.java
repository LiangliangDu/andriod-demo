package com.shanlin.creadit.exception;

import com.android.volley.VolleyError;
import com.shanlin.creadit.R;
import com.shanlin.creadit.ShanlinApp;

/**
 *
 */
public class NetworkErrorException extends VolleyError {

    public NetworkErrorException() {
        super(ShanlinApp.genInstance().getResources().getString(R.string.exception_netword_error));
    }
}