package com.santiago.account.service.impl;

import com.santiago.account.entity.domain.*;
import com.santiago.account.mapper.AccountMapper;
import com.santiago.account.mapper.AccountingDetailMapper;
import com.santiago.account.mapper.TrxDetailMapper;
import com.santiago.account.service.AccountService;
import com.santiago.commons.enums.AccountStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    TrxDetailMapper trxDetailMapper;
    @Autowired
    AccountingDetailMapper accountingDetailMapper;
    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @Override
    public Account createDefaultAccount(String accountNo, String merchantNo) {
        Account account = new Account();
        account.setGmtCreate(new Date());
        account.setGmtModified(new Date());
        account.setVersion("1.0.0");
        account.setRemark("2");
        account.setAccountNo(accountNo);
        account.setBalance(BigDecimal.ZERO);
        account.setFreezeBalance(BigDecimal.ZERO);
        account.setSecurityMoney(BigDecimal.ZERO);
        account.setStatus(AccountStatusEnum.ACTIVE.getCode());
        account.setTotalExpend(BigDecimal.ZERO);
        account.setTotalIncome(BigDecimal.ZERO);
        account.setTodayIncome(BigDecimal.ZERO);
        account.setTodayExpend(BigDecimal.ZERO);
        account.setAccountType("0");
        account.setUnsettBalance(BigDecimal.ZERO);
        account.setMerchantNo(merchantNo);
        account.setDelete("0");
        return account;
    }
    @Override
    public Account getByAccountNo(String accountNo) {
        Account account = new Account();
        account.setAccountNo(accountNo);
        return get(account);
    }

    @Override
    public void update(Account account) {

    }

    @Override
    public Account get(Account account) {
        return accountMapper.selectOne(account);
    }

    @Override
    public List<Account> list(Account account) {
        return accountMapper.select(account);
    }

    @Override
    public List<Account> listAll() {
        Account account = new Account();
        return list(account);
    }

    @Override
    public void insertTransaction(TransactionDTO transactionDTO) {
        TrxDetail trxDetail = buildTrxDetail(transactionDTO);
        trxDetailMapper.insert(trxDetail);
    }

    private TrxDetail buildTrxDetail(TransactionDTO transactionDTO) {
        TrxDetail trxDetail = new TrxDetail();
        trxDetail.setTrxSerialNo(transactionDTO.getTrxSerialNo());
        trxDetail.setTrxType(transactionDTO.getTrxType());
        trxDetail.setAmount(transactionDTO.getAmount());
        trxDetail.setRescAccountNo(transactionDTO.getRescAccountNo());
        trxDetail.setDestAccountNo(transactionDTO.getDestAccountNo());
        trxDetail.setAccountingState("0");
        trxDetail.setCreateTime(new Date());
        return trxDetail;
    }

    @Override
    public void accounting(TransactionDTO transactionDTO) {
        String[] accountingStrategy = getAccountingStrategy(transactionDTO.getTrxType());
        BigDecimal amount = transactionDTO.getAmount();
        StringBuffer rescAccountNo = new StringBuffer(transactionDTO.getRescAccountNo());
        StringBuffer destAccountNo = new StringBuffer(transactionDTO.getDestAccountNo());
        for (int i = 0 ; i < 6 ; i++) {
            if (i < 3) {
                changeBalance(rescAccountNo.append(accountingStrategy[i]).toString(), amount.negate());
                insertAccountingDetail(transactionDTO.getTrxSerialNo(), rescAccountNo.toString(), "1", amount);
            } else {
                changeBalance(destAccountNo.append(accountingStrategy[i]).toString(), amount);
                insertAccountingDetail(transactionDTO.getTrxSerialNo(), rescAccountNo.toString(), "0", amount);
            }
        }
    }

    private void insertAccountingDetail(String trxSerialNo, String accountNo, String direction, BigDecimal amount) {
        AccountingDetail accountingDetail = new AccountingDetail();
        accountingDetail.setTrxSerialNo(trxSerialNo);
        accountingDetail.setAccountNo(accountNo);
        accountingDetail.setDirection(direction);
        accountingDetail.setAmount(amount);
        accountingDetail.setCreateTime(new Date());
        accountingDetailMapper.insert(accountingDetail);
    }

    private void changeBalance(String accountNo, BigDecimal amount) {

    }

    private String[] getAccountingStrategy(String trxType) {
        String[] accountingStrategy = {"00", "00", "00", "00", "00", "00"};
        return accountingStrategy;
    }

    @Override
    public void asyncAccounting(TransactionDTO transactionDTO) {
        kafkaTemplate.send("testTopic", transactionDTO);
    }

    @Override
    @Async
    public void asyncAccounting2(TransactionDTO transactionDTO) {
        accounting(transactionDTO);
    }

    @KafkaListener(topics = "testTopic")
    public void onMessage(TransactionDTO transactionDTO){
        String[] accountingStrategy = getAccountingStrategy(transactionDTO.getTrxType());
        BigDecimal amount = transactionDTO.getAmount();
        StringBuffer rescAccountNo = new StringBuffer(transactionDTO.getRescAccountNo());
        StringBuffer destAccountNo = new StringBuffer(transactionDTO.getDestAccountNo());
        for (int i = 0 ; i < 6 ; i++) {
            if (i < 3) {
                changeBalance(rescAccountNo.append(accountingStrategy[i]).toString(), amount.negate());
                insertAccountingDetail(transactionDTO.getTrxSerialNo(), rescAccountNo.toString(), "1", amount);
            } else {
                changeBalance(destAccountNo.append(accountingStrategy[i]).toString(), amount);
                insertAccountingDetail(transactionDTO.getTrxSerialNo(), rescAccountNo.toString(), "0", amount);
            }
        }
    }
}
