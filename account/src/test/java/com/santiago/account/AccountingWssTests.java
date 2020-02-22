package com.santiago.account;

import com.santiago.account.controller.AccountingWss;
import com.santiago.account.domain.entity.TransactionDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class AccountingWssTests extends AccountApplicationTests {
    @Autowired
    AccountingWss accountingWss;
    @Test
    public void test() {
        accountingWss.accounting(build());
    }

    private TransactionDTO build() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAmount(BigDecimal.ONE);
        transactionDTO.setTrxType("0001");
        transactionDTO.setRescAccountNo("00000001");
        transactionDTO.setDestAccountNo("00000002");
        return transactionDTO;
    }


}
