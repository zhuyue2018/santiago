package com.santiago.commons.dto.resp;

import com.google.gson.GsonBuilder;

public final class UnionResult<T> {
    private int code;
    private String msg;
    private T data;

    public UnionResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public UnionResult(int code, String message, T data) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", message='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public String toJSONString(){
        return new GsonBuilder().create().toJson(this);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public static <T> UnionResult<T> ok(T data) {
        return new UnionResult<>(200, "ok", data);
    }
    public static UnionResult<Void> ok() {
        return new UnionResult(200, "ok", null);
    }

    public static <T> UnionResult<T> ok(String msg, T data) {
        return new UnionResult<>(200, msg, data);
    }

    public static UnionResult fail(String msg) {
        return new UnionResult<String>(500, msg, null);
    }
}