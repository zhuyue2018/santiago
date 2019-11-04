package com.santiago.core.entity.result;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2019-10-25 14:14
 **/
public class RecCountResult {
    private String count;
    private String totalAmount;

    public RecCountResult(String count, String totalAmount) {
        this.count = count;
        this.totalAmount = totalAmount;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}
