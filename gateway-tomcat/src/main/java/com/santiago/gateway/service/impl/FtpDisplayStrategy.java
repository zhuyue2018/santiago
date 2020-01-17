package com.santiago.gateway.service.impl;

import com.santiago.commons.ftp.ErrorInfo;
import com.santiago.commons.ftp.VsftpResult;
import com.santiago.commons.ftp.Vsftpd;
import com.santiago.gateway.service.IFtpUploadStrategy;
import com.santiago.gateway.util.FtpUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class FtpDisplayStrategy implements IFtpUploadStrategy {

    private static Logger LOGGER = LoggerFactory.getLogger(FtpDisplayStrategy.class);

    @Override
    public VsftpResult vsftpMethod(Vsftpd vsftpd){
        LOGGER.info("FtpDisplayStrategy.vsftpMethod start");
        VsftpResult result = new VsftpResult();
        List<ErrorInfo> errors = new ArrayList<>();
        String[] fileNames = new String[0];
        try {
            fileNames = FtpUtil.displayFile(vsftpd.getProjectCode());
        } catch (Exception e) {
            e.printStackTrace();
            ErrorInfo errorInfo = new ErrorInfo("FTP.ERROR","获取文件列表失败！服务端异常！");
            errors.add(errorInfo);
        }
        if(CollectionUtils.isEmpty(errors)){
            result.setStatus(true);
            result.setFileNames(fileNames);
        }else{
            result.setStatus(false);
            result.setErrors(errors);
        }
        LOGGER.info("FtpDisplayStrategy.vsftpMethod end");
        return result;
    }
}