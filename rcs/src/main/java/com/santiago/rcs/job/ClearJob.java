package com.santiago.rcs.job;

import com.santiago.rcs.entity.domain.ClearDetail;
import com.santiago.rcs.entity.domain.ClearMessage;
import com.santiago.rcs.service.ClearDetailService;
import com.santiago.rcs.service.ClearMessageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClearJob {
    private final ClearDetailService clearDetailService;
    private final ClearMessageService clearMessageService;

    @Autowired
    public ClearJob(ClearDetailService clearDetailService, ClearMessageService clearMessageService) {
        this.clearDetailService = clearDetailService;
        this.clearMessageService = clearMessageService;
    }

    public void clear() {
        List<ClearMessage> clearMessageList = clearMessageService.listForClear();
        
    }
}
