package com.santiago.gateway.service.impl;

import com.santiago.commons.ftp.ErrorInfo;
import com.santiago.commons.ftp.VsftpResult;
import com.santiago.commons.ftp.Vsftpd;
import com.santiago.gateway.service.IFtpUploadStrategy;
import com.santiago.gateway.util.FtpUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class FtpDeleteStrategy implements IFtpUploadStrategy {

    private static Logger LOGGER = LoggerFactory.getLogger(FtpDeleteStrategy.class);

    @Override
    public VsftpResult vsftpMethod(Vsftpd vsftpd){
        LOGGER.info("FtpDeleteStrategy.vsftpMethod start");
        VsftpResult result = new VsftpResult();
        List<ErrorInfo> errors = new ArrayList<>();
        if (StringUtils.isEmpty(vsftpd.getFileName())) {
            ErrorInfo errorInfo = new ErrorInfo("PARAMETER.FAIL","参数[fileName]不能为空！");
            errors.add(errorInfo);
        }
        //删除文件
        try {
            if(CollectionUtils.isEmpty(errors)){
                FtpUtil.deleteFile(vsftpd.getProjectCode(),vsftpd.getFileName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            ErrorInfo errorInfo = new ErrorInfo("FTP.ERROR","删除失败！服务端异常！");
            errors.add(errorInfo);
        }

        if(CollectionUtils.isEmpty(errors)){
            result.setStatus(true);
        }else{
            result.setStatus(false);
            result.setErrors(errors);
        }
        LOGGER.info("FtpDeleteStrategy.vsftpMethod end");
        return result;
    }
}