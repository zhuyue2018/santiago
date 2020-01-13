package com.santiago.commons.dto.req;

/**
 * @program: sac-fa
 * @description:
 * @author: zhuyue
 * @create: 2019-11-21 10:44
 **/
public class Sort {
    private String fieldName;
    private String orderBy;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
