package com.santiago.gateway.controller;

import com.baidu.fsg.uid.impl.DefaultUidGenerator;
import com.santiago.commons.dto.resp.SimpleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ClearDetailCtrl {
    @Autowired
    DefaultUidGenerator defaultUidGenerator;

    public SimpleResponse receive(){
        defaultUidGenerator.getUID();
        return null;
    }
    public SimpleResponse update(){
        return null;
    }
}
