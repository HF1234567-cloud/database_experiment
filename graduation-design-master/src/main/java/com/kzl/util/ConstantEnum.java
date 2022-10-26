package com.kzl.util;

public enum ConstantEnum {

    SUCCESS(200,"interface request success"),
    FAIL(201,"interface request fail"),
    ADD_DATA_SUCCESS(200,"添加数据成功"),
    ADD_DATA_FAIL(201,"添加数据失败"),
    UPDATE_DATA_SUCCESS(200,"修改数据成功"),
    UPDATE_DATA_FAIL(201,"修改数据失败"),
    DEL_DATA_SUCCESS(200,"删除数据成功"),
    DEL_DATA_FAIL(201,"删除数据失败"),
    MISSING_PARAMETER(201,"缺少参数"),
    SERVER_ERROR(201,"服务异常"),


    ;

    ConstantEnum() {
    }

    ConstantEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    private Integer code;
    private String message;

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

}
