package com.santiago.account.controller;

import com.santiago.account.entity.domain.TransactionDTO;
import com.santiago.account.service.AccountService;
import com.santiago.commons.util.KryoSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 记账接口
 */
@RestController
public class AccountingWss {
    private static final Logger logger = LoggerFactory.getLogger(AccountingWss.class);
    final AccountService accountService;
    @Autowired
    public AccountingWss(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 异步记账接口
     * @param transactionDTO
     */
    @PostMapping(value = "/asyncAccounting")
    public void asyncAccounting(TransactionDTO transactionDTO) {
        if (transactionDTO.getTrxType().equals("0001")) {
            logger.error("预扣交易只能使用同步记账");
            return;
        }
        accountService.insertTransaction(transactionDTO);
        accountService.asyncAccounting(transactionDTO);
    }

    /**
     * 同步记账接口，用于预扣交易
     */
    @PostMapping(value = "/accounting")
    public void syncAccounting(TransactionDTO transactionDTO) {
        accountService.insertTransaction(transactionDTO);
        accountService.accounting(transactionDTO);
    }

    /**
     * 统一记账接口，用于预扣交易
     */
    @PostMapping(value = "/accounting/union")
    public void unionAccounting(@RequestBody TransactionDTO transactionDTO) {
        if ("0001".equals(transactionDTO.getTrxType())) { // 预扣交易
            syncAccounting(transactionDTO);
        } else {
            asyncAccounting(transactionDTO);
        }
    }

}

