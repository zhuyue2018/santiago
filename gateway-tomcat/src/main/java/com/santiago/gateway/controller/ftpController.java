package com.santiago.gateway.controller;

import com.santiago.commons.ftp.ErrorInfo;
import com.santiago.commons.ftp.VsftpResult;
import com.santiago.commons.ftp.Vsftpd;
import com.santiago.gateway.consts.FtpConstants;
import com.santiago.gateway.service.IFtpUploadStrategy;
import com.santiago.gateway.service.impl.FtpDeleteStrategy;
import com.santiago.gateway.service.impl.FtpDisplayStrategy;
import com.santiago.gateway.service.impl.FtpDownLoadStrategy;
import com.santiago.gateway.service.impl.FtpUploadStrategy;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/vsftpdService")
public class ftpController {

    private static Logger LOGGER = LoggerFactory.getLogger(ftpController.class);

    @ResponseBody
    @RequestMapping(path = "/vsftpd", method = RequestMethod.POST)
    public VsftpResult getAuthInfo(@RequestBody Vsftpd vsftpd){
        LOGGER.info("ftpController.getAuthInfo start");
        IFtpUploadStrategy strategy = null;
        List<ErrorInfo> errors =  new ArrayList<>();
        VsftpResult result = new VsftpResult();
        //第一步校验参数是否合法
        if (StringUtils.isEmpty(vsftpd.getOptionType())) {
            ErrorInfo errorInfo = new ErrorInfo("PARAMETER.FAIL","调用参数[type]不能为空！");
            errors.add(errorInfo);
        }
        if (StringUtils.isEmpty(vsftpd.getProjectCode())) {
            ErrorInfo errorInfo = new ErrorInfo("PARAMETER.FAIL","参数[projectCode]不能为空！");
            errors.add(errorInfo);
        }
        //根据请求类型使用不同策略
        if(FtpConstants.UPLOAD.equals(vsftpd.getOptionType())){
            strategy = new FtpUploadStrategy();
        }else if(FtpConstants.REPLACE.equals(vsftpd.getOptionType())){
            strategy = new FtpUploadStrategy();
        }else if(FtpConstants.DOWNLOAD.equals(vsftpd.getOptionType())){
            strategy = new FtpDownLoadStrategy();
        }else if(FtpConstants.DISPLAY.equals(vsftpd.getOptionType())){
            strategy = new FtpDisplayStrategy();
        }else if(FtpConstants.DELETE.equals(vsftpd.getOptionType())){
            strategy = new FtpDeleteStrategy();
        }else {
            ErrorInfo errorInfo = new ErrorInfo("PARAMETER.FAIL","调用参数[type]错误！");
            errors.add(errorInfo);
        }
        if (CollectionUtils.isEmpty(errors)) {
            result = strategy.vsftpMethod(vsftpd);
        }else{
            result.setStatus(false);
            result.setErrors(errors);
        }
        return result;
    }
}