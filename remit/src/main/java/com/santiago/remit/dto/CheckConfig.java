package com.santiago.remit.dto;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2020-01-19 16:54
 **/
public class CheckConfig {
    private Integer expire;
    private String check;

    public Integer getExpire() {
        return expire;
    }

    public void setExpire(Integer expire) {
        this.expire = expire;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }
}
