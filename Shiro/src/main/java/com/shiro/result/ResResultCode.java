package com.shiro.result;

/**
 * 响应编码枚举
 *
 * @author FANG
 */
public enum ResResultCode {

    /**
     * 成功
     */
    SUCCESS(200, "ok"),

    /**
     * 请求错误
     */
    BAD_REQUEST(400, "请求参数错误"),

    /**
     * 未登录授权
     */
    UNAUTHORIZED(401, "无效令牌"),

    /**
     * 权限不足
     */
    FORBIDDEN(403, "权限不足"),

    /**
     * 服务器错误
     */
    FAILED(500, "server error"),

    /**
     * 业务异常
     */
    BIZ_ERROR(600, "业务异常"),

    /**
     * 用户名或密码错误
     */
    USERNAME_OR_PASSWORD_ERROR(601, "用户名或密码错误");

    private int status;
    private String message;

    ResResultCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

