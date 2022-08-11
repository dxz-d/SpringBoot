package springboot.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import springboot.enu.ExceptionCodeEnum;
import springboot.result.Result;

/**
 * 全局异常处理
 * @author dxz
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常
     * @param
     * @return
     */
    @ExceptionHandler(BizException.class)
    public Result<ExceptionCodeEnum> handleBizException(BizException bizException) {
        log.warn("业务异常:{}", bizException.getError().getDesc(), bizException);
        return Result.error(bizException.getError());
    }

    /**
     * 运行时异常
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public Result<ExceptionCodeEnum> handleRunTimeException(RuntimeException e) {
        log.warn("运行时异常: {}", e.getMessage(), e);
        return Result.error(ExceptionCodeEnum.ERROR);
    }

}
