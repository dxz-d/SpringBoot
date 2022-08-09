package com.shiro.result;

/**
 * @author FANG
 * @create 2021-01-26-16:05
 */
public enum GlobalEnum {

    STATUS_NORMAL("0", "正常"),
    STATUS_LOCK("1", "禁用");

    private String code;
    private String msg;

    GlobalEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
