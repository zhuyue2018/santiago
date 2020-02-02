package com.santiago.gateway.controller;

import com.baidu.fsg.uid.impl.DefaultUidGenerator;
import com.santiago.commons.dto.resp.UnionResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ClearDetailCtrl {
    @Autowired
    DefaultUidGenerator defaultUidGenerator;

    public UnionResp receive(){
        defaultUidGenerator.getUID();
        return null;
    }
    public UnionResp update(){
        return null;
    }
}
