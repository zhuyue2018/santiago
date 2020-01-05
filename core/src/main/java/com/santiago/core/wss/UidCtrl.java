package com.santiago.core.wss;

import com.baidu.fsg.uid.impl.DefaultUidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UidCtrl {
    @Autowired
    DefaultUidGenerator defaultUidGenerator;

    @GetMapping(value = "/defaultUid")
    public Long getUid() {
        return defaultUidGenerator.getUID();
    }
}
