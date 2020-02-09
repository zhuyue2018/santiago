package com.santiago.account.controller;

import com.santiago.account.domain.entity.TransactionDTO;
import com.santiago.account.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 记账接口
 */
@RestController
public class AccountingWss {
    private static final Logger logger = LoggerFactory.getLogger(AccountingWss.class);
    @Autowired
    AccountService accountService;

    /**
     * 异步记账接口
     * @param transactionDTO
     */
    @PostMapping(value = "/asyncAccounting")
    public void asyncAccounting(TransactionDTO transactionDTO) {
        if (transactionDTO.getTrxType().equals("预扣")) {
            logger.error("预扣交易只能使用同步记账");
            return;
        }
        accountService.insertTransaction(transactionDTO);
        accountService.asyncAccounting();
    }

    /**
     * 同步记账接口，用于预扣交易
     */
    @PostMapping(value = "/accounting")
    public void accounting(TransactionDTO transactionDTO) {
        accountService.insertTransaction(transactionDTO);
        accountService.accounting(transactionDTO);
    }

}
