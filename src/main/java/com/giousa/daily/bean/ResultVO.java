package com.giousa.daily.bean;


import com.giousa.daily.enums.ResultEnum;

import java.io.Serializable;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2019/7/10
 * Email:65489469@qq.com
 */
public class ResultVO implements Serializable{

    // 响应业务状态
    private Integer code;

    // 响应消息
    private String message;

    // 响应中的数据
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResultVO() {

    }

    public ResultVO(Object data) {
        this.code = 0;
        this.message = "OK";
        this.data = data;
    }

    public ResultVO(Integer status, String msg, Object data) {
        this.code = status;
        this.message = msg;
        this.data = data;
    }


    public static ResultVO ok() {
        return new ResultVO(null);
    }

    public static ResultVO ok(Object data) {
        return new ResultVO(data);
    }

    public static ResultVO build(Integer status, String msg) {
        return new ResultVO(status, msg, null);
    }

    public static ResultVO build(Integer status, String msg, Object data) {
        return new ResultVO(status, msg, data);
    }


    public static ResultVO error(ResultEnum resultEnum){

        return new ResultVO(resultEnum.getCode(),resultEnum.getMessage(),null);
    }
    public static ResultVO error(Integer status, String msg){
        return new ResultVO(status, msg,null);
    }



        @Override
	public String toString() {
		return "ResultVO [code=" + code + ", message=" + message + ", data=" + data + "]";
	}
    
    

}
