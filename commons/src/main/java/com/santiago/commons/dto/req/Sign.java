package com.santiago.commons.dto.req;

/**
 * @program: sac-fa
 * @description:
 * @author: zhuyue
 * @create: 2019-11-21 10:42
 **/
public class Sign {
    private String signType;
    private String signValue;
    private String signKeyName;

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSignValue() {
        return signValue;
    }

    public void setSignValue(String signValue) {
        this.signValue = signValue;
    }

    public String getSignKeyName() {
        return signKeyName;
    }

    public void setSignKeyName(String signKeyName) {
        this.signKeyName = signKeyName;
    }
}
