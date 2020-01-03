package edu.hope.biz.common.exception;

import edu.hope.biz.common.constants.RStatus;
import edu.hope.biz.common.resonse.R;
import edu.hope.biz.common.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * 全局的的异常拦截器
 *
 * @author lujing
 * @date 2018/05/22
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    /**
     * 全局异常.
     *
     * @param e the e
     * @return R
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public R exception(Exception e) {
        String profile = SpringUtil.getActiveProfile();
        
        if (log.isDebugEnabled()) {
            e.printStackTrace();
        } else {
            if ("dev".equals(profile) || "local".equals(profile)) {
                log.error(profile, e);
            } else {
                log.error("内部异常：{}", e.getMessage());
            }
        }

        return R.fail(RStatus.INTERNAL_SERVER_ERROR,e.getMessage());
    }
    
    /**
     * 全局异常.
     *
     * @param e the e
     * @return R
     */
    @ExceptionHandler(BindException.class)
    public R bindException(BindException e) {
        log.info("参数异常:{}", e.getMessage());
        List<ObjectError> allErrors = e.getAllErrors();
        String defaultMessage = allErrors.get(0).getDefaultMessage();
        return R.fail(RStatus.INTERNAL_SERVER_ERROR,defaultMessage);
    }
    

    /***
     * 校验错误
     * @param ex
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R validationErrorHandler(MethodArgumentNotValidException ex){
        //错误
        BindingResult bindingResult = ex.getBindingResult();
        StringBuffer error = new StringBuffer("校验错误:");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            error.append("参数[").append(fieldError.getField()).append("]").append(fieldError.getDefaultMessage()).append("。");
        }
        log.error("参数异常:" + error.toString());
        return R.fail(RStatus.PARAM_ERROR,error.toString());
    }

    /***
     * 校验错误
     * @param ex
     * @return
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public R validationErrorHandler(ConstraintViolationException ex){
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation item: ex.getConstraintViolations()) {
            sb.append(item.getMessageTemplate()+"["+((PathImpl)item.getPropertyPath()).getLeafNode()+"]");
        }
        log.error("参数异常:" + sb.toString());
        return R.fail(RStatus.PARAM_ERROR,sb.toString());
    }
    /**
     * 业务异常.
     *
     * @param e the e
     * @return R
     */
    @ExceptionHandler({ServiceException.class})
    @ResponseStatus(HttpStatus.OK)
    public R serviceException(ServiceException e) {
        log.info("业务异常：{}", e.getMessage());
        return new R(e.getMessage(), e.getCode(), null);
    }


}
