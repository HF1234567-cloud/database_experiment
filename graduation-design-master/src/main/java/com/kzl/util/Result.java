package com.kzl.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Map;

public class Result<T> implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(Result.class);
    private int code;
    private String message;
    private long total;
    private T data;

    public Result() {
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public static Result create(int code, String message,Object data){
        Result result = new Result(code, message, data);
        return result;
    }

    public static Result create(int code, String message){
        Result result = new Result(code, message);
        return result;
    }

    /*没有数据*/
    public static Result createSuccess() {
        return create(ConstantEnum.SUCCESS.getCode(), ConstantEnum.SUCCESS.getMessage());
    }
    /*添加数据成功*/
    public static Result addDataSuccess() {
        return create(ConstantEnum.ADD_DATA_SUCCESS.getCode(), ConstantEnum.ADD_DATA_SUCCESS.getMessage());
    }
    /*添加数据失败*/
    public static Result addDataFail() {
        return create(ConstantEnum.ADD_DATA_FAIL.getCode(), ConstantEnum.ADD_DATA_FAIL.getMessage());
    }
    /*修改数据成功*/
    public static Result updateDataSuccess() {
        return create(ConstantEnum.UPDATE_DATA_SUCCESS.getCode(), ConstantEnum.UPDATE_DATA_SUCCESS.getMessage());
    }
    /*修改数据失败*/
    public static Result updateDataFail() {
        return create(ConstantEnum.UPDATE_DATA_FAIL.getCode(), ConstantEnum.UPDATE_DATA_FAIL.getMessage());
    }
    /*删除数据成功*/
    public static Result delDataSuccess() {
        return create(ConstantEnum.DEL_DATA_SUCCESS.getCode(), ConstantEnum.DEL_DATA_SUCCESS.getMessage());
    }
    /*删除数据失败*/
    public static Result delDataFail() {
        return create(ConstantEnum.DEL_DATA_FAIL.getCode(), ConstantEnum.DEL_DATA_FAIL.getMessage());
    }

    /*有数据返回成功*/
    public static <T> Result<T> createSuccess(T data) {
        logger.info("response success:"+data);
        return createSuccess().setData(data,0);
    }


    public static <T> Result<T> createPageSuccess(Map<String,Object> map) {
        logger.info("response success:"+map);
        return createSuccess().setData(map.get("data"),(long) map.get("total"));
    }

    /*无描述返回失败*/
    public static Result createFail() {
        return create(ConstantEnum.FAIL.getCode(), ConstantEnum.FAIL.getMessage());
    }

    public static Result createFail(String message) {
        return create(ConstantEnum.FAIL.getCode(), message);
    }

    /*自定义返回失败描述*/
    public static Result createFail(int code, String msg) {
        return create(code, msg);
    }

    public Result setData(T data,long total){
        this.data = data;
        this.total = total;
        return this;
    }
}
