package com.shanlin.creadit.api;


import com.shanlin.creadit.bean.LoginBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Description:
 * User:         沈亮
 * Date:         2016-05-10  13{MINUTE}
 */
public interface ApiServer {
    /**
     * 测试
     * @param
     */
    @POST("auth/login")
    Call<LoginBean> getLoginMessage(@Body Map<String,Object> params);
}
