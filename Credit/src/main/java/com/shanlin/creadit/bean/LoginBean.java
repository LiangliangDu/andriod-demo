package com.shanlin.creadit.bean;

/**
 * Description:
 * User:         沈亮
 * Date:         2016-05-10  13{MINUTE}
 */
public class LoginBean extends BaseVolleyResponse {
    private LoGintail result;

    public LoginBean(){

    }

    public LoginBean(LoGintail result) {
        this.result = result;
    }

    public void setResult(LoGintail result) {
        this.result = result;
    }

    public LoGintail getResult() {
        return result;
    }

    public static class LoGintail {
        private long lastTime;
        private String token;

        public LoGintail(long lastTime, String token) {
            this.lastTime = lastTime;
            this.token = token;
        }

        public LoGintail() {
        }

        public long getLastTime() {
            return lastTime;
        }

        public void setLastTime(long lastTime) {
            this.lastTime = lastTime;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}