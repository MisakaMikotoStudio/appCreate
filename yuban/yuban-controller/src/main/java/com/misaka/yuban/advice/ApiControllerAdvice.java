package com.misaka.yuban.advice;

import com.misaka.yuban.common.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一拦截处理controller异常返回
 */
@Slf4j
@ControllerAdvice
public class ApiControllerAdvice {
    private final Logger logger = LoggerFactory.getLogger(ApiControllerAdvice.class);

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Object handleAny(HttpServletRequest req, Throwable th) {
        logger.error("后端异常:" + th.getMessage(), th);
        return BaseResponse.fail(th.getMessage());
    }
}
