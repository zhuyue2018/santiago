package com.santiago.commons.dto.resp;

public class SimpleResponse extends BaseResponse {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public SimpleResponse(String code, String msg) {
        super(code, msg);
    }

    public SimpleResponse(String code, String msg, String content) {
        super(code, msg);
        this.content = content;
    }

    @Override
    public String toString() {
        return "SimpleResponse{" +
                "content='" + content + '\'' +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}