package com.santiago.gateway.netty.domain;

import java.util.HashMap;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2020-01-14 20:56
 **/
public class ValidateResult {
    private Boolean correct;
    private HashMap<String, String> errorMap;

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public HashMap<String, String> getErrorMap() {
        return errorMap;
    }

    public void setErrorMap(HashMap<String, String> errorMap) {
        this.errorMap = errorMap;
    }
}
