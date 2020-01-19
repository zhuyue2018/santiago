package com.santiago.account.controller;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;
import com.santiago.account.service.AccountService;
import com.santiago.api.dto.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountWss {
    @Autowired
    AccountService accountService;
    @GetMapping(value = "/api/accounts")
    public List<Account> list() {
        return accountService.listAll();
    }

    @GetMapping(value = "/api/accounts/{id}")
    public Account get() {
        return accountService.getByAccountNo("");
    }

    @PostMapping(value = "/api/accounts")
    public void create(String accountNo, String merchantNo) {
        accountService.createDefaultAccount(accountNo, merchantNo);

    }

//    @PostMapping(value = "/asyncAccount")
//    public void asyncAccount(TransactionDTO transactionDTO) {
//        accountService.insertTransaction();
//        accountService.send2Kafka();
//    }
//
//    @PostMapping(value = "/account")
//    public void account() {
//        accountService.insertTransaction();
//        accountService.account();
//    }
    public static void main(String[] args) {
        BloomFilter<String> stringBloomFilter = BloomFilter.create(new Funnel<String>() {
            @Override
            public void funnel(String s, PrimitiveSink primitiveSink) {
                primitiveSink.putString(s, Charsets.UTF_8);
            }
        }, 1024 * 1024 * 32, 0.0000001d);
        System.out.println(stringBloomFilter.put("123"));
        System.out.println(stringBloomFilter.put("123"));
        System.out.println(stringBloomFilter.put("124"));
        BloomFilter<String> stringBloomFilter2 = BloomFilter
                .create((s, primitiveSink) -> {primitiveSink.putString(s, Charsets.UTF_8);},
                1024 * 1024 * 32, 0.0000001d);
        System.out.println(stringBloomFilter2.put("123"));
        System.out.println(stringBloomFilter2.put("123"));
        System.out.println(stringBloomFilter2.put("124"));
    }
}
