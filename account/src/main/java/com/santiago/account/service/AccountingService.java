package com.santiago.account.service;

import java.math.BigDecimal;

public interface AccountingService {
    void accounting(String trxSerialNo, Long accountId, BigDecimal amount, Integer direction);
}
