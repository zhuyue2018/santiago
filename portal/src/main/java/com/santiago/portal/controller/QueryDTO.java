package com.santiago.portal.controller;

import java.util.Map;

public class QueryDTO {
    private Map largeThanMap;
    private Map lessThanMap;
    private Map largeThanOrEqualToMap;
    private Map lessThanOrEqualToMap;

    public Map getLargeThanMap() {
        return largeThanMap;
    }

    public void setLargeThanMap(Map largeThanMap) {
        this.largeThanMap = largeThanMap;
    }

    public Map getLessThanMap() {
        return lessThanMap;
    }

    public void setLessThanMap(Map lessThanMap) {
        this.lessThanMap = lessThanMap;
    }

    public Map getLargeThanOrEqualToMap() {
        return largeThanOrEqualToMap;
    }

    public void setLargeThanOrEqualToMap(Map largeThanOrEqualToMap) {
        this.largeThanOrEqualToMap = largeThanOrEqualToMap;
    }

    public Map getLessThanOrEqualToMap() {
        return lessThanOrEqualToMap;
    }

    public void setLessThanOrEqualToMap(Map lessThanOrEqualToMap) {
        this.lessThanOrEqualToMap = lessThanOrEqualToMap;
    }
}
