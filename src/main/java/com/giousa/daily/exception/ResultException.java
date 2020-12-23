package com.giousa.daily.exception;


import com.giousa.daily.enums.ResultEnum;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2019/7/10
 * Email:65489469@qq.com
 */
public class ResultException extends RuntimeException{

    private Integer code;

    public ResultException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }


    public ResultException(Integer code, String message){
        super(message);
        this.code = code;
    }

}
