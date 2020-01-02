package com.santiago.zookeeper.service;

import com.santiago.zookeeper.entity.domain.AccountCheckBatch;
import org.joda.time.DateTime;

public interface AccountCheckBatchService {
    void insert(AccountCheckBatch accountCheckBatch);

    AccountCheckBatch getByBillDate(DateTime billDate);
    AccountCheckBatch get(AccountCheckBatch accountCheckBatch);
}
