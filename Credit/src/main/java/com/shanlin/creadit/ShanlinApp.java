package com.shanlin.creadit;

import android.app.Application;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.shanlin.creadit.utils.CrashHandler;
import com.shanlin.creadit.utils.CrashHandlerTool;

/**
 * Description:
 * User:         沈亮
 * Date:         2016-05-10  13{MINUTE}
 */
public class ShanlinApp extends Application{
    private static ShanlinApp mInstance;
    private static RequestQueue mQueue;
    private Toast toast;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;

        mQueue = Volley.newRequestQueue(this);
        //捕获全局异常
        Thread.setDefaultUncaughtExceptionHandler(new CrashHandlerTool());
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }

    public static ShanlinApp genInstance() {
        return mInstance;
    }

    public static RequestQueue genQueue() {
        return mQueue;
    }

    public void showToast(String message) {
        TextView textView = new TextView(this);
        textView.setText(message);
        textView.setTextColor(getResources().getColor(R.color.white));
        if (toast == null) {
            toast = new Toast(this);
        }
        toast.setView(textView);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }

    public void showToast(int id) {
        showToast(getString(id));
    }
}
