package springboot.exception;

/**
 * Description: TODO
 * CreateTime: 2022/8/9 17:37
 *
 * @author: dxz
 */

import lombok.Getter;
import springboot.enu.ExceptionCodeEnum;

/**
 * 业务异常
 * biz是business的缩写
 * @author dxz
 * @see ExceptionCodeEnum
 */
@Getter
public class BizException extends RuntimeException {

    private ExceptionCodeEnum error;

    /**
     * 构造器，有时我们需要将第三方异常转为自定义异常抛出，同时又不想丢失原来的异常信息，此时可以传入cause
     * @param error
     * @param cause
     */
    public BizException(ExceptionCodeEnum error, Throwable cause) {
        super(cause);
        this.error = error;
    }

    /**
     * 构造器，只传入通用错误枚举
     * @param error
     */
    public BizException(ExceptionCodeEnum error) {
        this.error = error;
    }
}
