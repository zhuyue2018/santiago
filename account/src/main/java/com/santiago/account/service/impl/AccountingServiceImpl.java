package com.santiago.account.service.impl;

import com.santiago.account.service.AccountingService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class AccountingServiceImpl implements AccountingService {
    private static final ConcurrentHashMap<Long, ArrayList<Object>> accountLock = new ConcurrentHashMap<>(64);
    private static final ArrayBlockingQueue<Long> fairLockWaitPool = new ArrayBlockingQueue<>(10);
    @Override
    public void accounting(String trxSerialNo, Long accountId, BigDecimal amount, Integer direction) {
        new ReentrantLock(false).lock();
//        BigDecimal balance = getAccount4Update(accountId);
//        insertAccountingRecord(balance, trxSerialNo, accountId, amount, direction);
//        updateBalance(balance, amount, direction);
    }



}
