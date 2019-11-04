package com.santiago.settlement.service;

import com.santiago.settlement.entity.domain.AccountCheckBatch;
import org.joda.time.DateTime;

import java.util.Date;

public interface AccountCheckBatchService {
    void insert(AccountCheckBatch accountCheckBatch);

    AccountCheckBatch getByBillDate(DateTime billDate);
    AccountCheckBatch get(AccountCheckBatch accountCheckBatch);
}
