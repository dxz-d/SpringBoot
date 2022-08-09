package com.shiro.result;

/**
 * Description: TODO
 * CreateTime: 2022/8/8 10:31
 *
 * @author: dxz
 */
import lombok.Data;


@Data
public class ResResult {

    /**
     * 响应编码
     */
    private int code;
    /**
     * 响应信息
     */
    private String msg;
    /**
     * 响应数据
     */
    private Object data;

    public ResResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResResult success() {
        return new ResResult(ResResultCode.SUCCESS.getStatus(), ResResultCode.SUCCESS.getMessage(), null);
    }

    public static ResResult success(Object data) {
        return new ResResult(ResResultCode.SUCCESS.getStatus(), ResResultCode.SUCCESS.getMessage(), data);
    }

    public static ResResult failure() {
        return new ResResult(ResResultCode.FAILED.getStatus(), ResResultCode.FAILED.getMessage());
    }

    public static ResResult failure(String msg) {
        return new ResResult(ResResultCode.FAILED.getStatus(), msg);
    }

    public static ResResult failure(ResResultCode ResResultCode) {
        return new ResResult(ResResultCode.getStatus(), ResResultCode.getMessage());
    }

}

