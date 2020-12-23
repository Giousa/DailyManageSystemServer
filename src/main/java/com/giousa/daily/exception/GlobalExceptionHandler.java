package com.giousa.daily.exception;

import com.giousa.daily.bean.ResultVO;
import com.giousa.daily.enums.ResultEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2019/7/10
 * Email:65489469@qq.com
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResultVO exception(Exception ex) {

        ex.printStackTrace();

        String message = ex.getMessage();
        if(message.contains("TooManyResultsException")){
            return ResultVO.error(ResultEnum.SQL_TOO_MANY_RESULTS);
        }else if(message.contains("MySQLIntegrityConstraintViolationException")){
            return ResultVO.error(ResultEnum.SQL_ID_HAS_EXIST);
        }

        return ResultVO.error(ResultEnum.SERVE_EXCEPTION);

    }

}