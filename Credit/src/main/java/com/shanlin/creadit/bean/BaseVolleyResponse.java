package com.shanlin.creadit.bean;

/**
 *
 */
public class BaseVolleyResponse extends BaseEntity {
    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BaseEntity saveInfo(BaseVolleyResponse response) {
        return response;
    }
}
