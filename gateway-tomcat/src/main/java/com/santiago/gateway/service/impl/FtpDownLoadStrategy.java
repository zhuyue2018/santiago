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

public class FtpDownLoadStrategy implements IFtpUploadStrategy {

    private static Logger LOGGER = LoggerFactory.getLogger(FtpDownLoadStrategy.class);

    @Override
    public VsftpResult vsftpMethod(Vsftpd vsftpd){
        LOGGER.info("FtpDownLoadStrategy.vsftpMethod start");
        VsftpResult result = new VsftpResult();
        List<ErrorInfo> errors = new ArrayList<>();
        byte[] arryArry = null;
        if (StringUtils.isEmpty(vsftpd.getFileName())) {
            ErrorInfo errorInfo = new ErrorInfo("PARAMETER.FAIL","参数[fileName]不能为空！");
            errors.add(errorInfo);
        }
        //判断文件是否存在
        try {
            boolean b = FtpUtil.fileExist(vsftpd.getProjectCode(),vsftpd.getFileName());
            if (!b){
                ErrorInfo errorInfo = new ErrorInfo("PARAMETER.FAIL","文件[fileName]不存在！");
                errors.add(errorInfo);
            }
            //下载
            if(CollectionUtils.isEmpty(errors)){
                arryArry = FtpUtil.downloadFile(vsftpd.getProjectCode(),vsftpd.getFileName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            ErrorInfo errorInfo = new ErrorInfo("FTP.ERROR","下载失败！服务端异常！");
            errors.add(errorInfo);
        }
        if(arryArry == null){
            ErrorInfo errorInfo = new ErrorInfo("FTP.ERROR","下载失败！系统异常！");
            errors.add(errorInfo);
        }
        if(CollectionUtils.isEmpty(errors)){
            result.setStatus(true);
            result.setByteArry(arryArry);
        }else{
            result.setStatus(false);
            result.setErrors(errors);
        }
        LOGGER.info("FtpDownLoadStrategy.vsftpMethod end");
        return result;
    }
}