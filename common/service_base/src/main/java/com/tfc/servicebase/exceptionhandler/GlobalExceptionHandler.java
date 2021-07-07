package com.tfc.servicebase.exceptionhandler;





import com.tfc.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 田付成
 * @date 2021/7/6 0:21
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("处理了全局异常。。。。。");
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.error().message("特定异常处理。。。。。");
    }


    @ExceptionHandler(TfcException.class)
    @ResponseBody
    public R error(TfcException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().message("自定义异常。。。。。");
    }

}
