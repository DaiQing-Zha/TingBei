package com.jxnu.zha.tingbei.model;

/**
 * Created by DaiQing.Zha on 2017/3/30.
 * email:13767191284@163.com
 * description:
 */
public class Entity extends BaseBean {

    private int code;
    private String msg;
    private String cacheKey;
    private boolean isHaveCache = true;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    public boolean isHaveCache() {
        return isHaveCache;
    }

    public void setHaveCache(boolean haveCache) {
        isHaveCache = haveCache;
    }
}
