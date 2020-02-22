package com.santiago.account.entity.domain;

import java.util.Map;

public class AccountingStrategy {
    private Map<String, String> directions;

    public Map<String, String> getDirections() {
        return directions;
    }

    public void setDirections(Map<String, String> directions) {
        this.directions = directions;
    }
}