package com.santiago.gateway.service.impl;

import com.santiago.commons.ftp.ErrorInfo;
import com.santiago.commons.ftp.VsftpResult;
import com.santiago.commons.ftp.Vsftpd;
import com.santiago.gateway.consts.FtpConstants;
import com.santiago.gateway.service.IFtpUploadStrategy;
import com.santiago.gateway.util.FtpUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class FtpUploadStrategy implements IFtpUploadStrategy {

    private static Logger LOGGER = LoggerFactory.getLogger(FtpUploadStrategy.class);

    @Override
    public VsftpResult vsftpMethod(Vsftpd vsftpd){
        LOGGER.info("FtpUploadStrategy.vsftpMethod start");
        VsftpResult result = new VsftpResult();
        List<ErrorInfo> errors = new ArrayList<>();
        if (StringUtils.isEmpty(vsftpd.getFileName())) {
            ErrorInfo errorInfo = new ErrorInfo("PARAMETER.FAIL","参数[fileName]不能为空！");
            errors.add(errorInfo);
        }
        if (vsftpd.getByteArry()==null) {
            ErrorInfo errorInfo = new ErrorInfo("PARAMETER.FAIL","参数[byteArry]不能为空！");
            errors.add(errorInfo);
        }
        //当不强制上传的时候   文件若已存在则上传失败
        boolean flag = false;
        try {
            if(FtpConstants.UPLOAD.equals(vsftpd.getOptionType())) {
                //判断文件是否存在
                boolean b = FtpUtil.fileExist(vsftpd.getProjectCode(), vsftpd.getFileName());
                if (b) {
                    ErrorInfo errorInfo = new ErrorInfo("PARAMETER.FAIL", "文件[" + vsftpd.getFileName() + "]已存在！");
                    errors.add(errorInfo);
                }
            }
            //上传文件（文件若存在则覆盖）
            if(CollectionUtils.isEmpty(errors)){
                flag = FtpUtil.uploadFile(vsftpd.getFileName(),vsftpd.getByteArry(),vsftpd.getOptionType());
            }
        } catch (Exception e) {
            e.printStackTrace();
            ErrorInfo errorInfo = new ErrorInfo("FTP.ERROR","下载失败！服务端异常！");
            errors.add(errorInfo);
        }
        if(!flag){
            ErrorInfo errorInfo = new ErrorInfo("FTP.ERROR","上传失败！系统异常！");
            errors.add(errorInfo);
        }
        if(CollectionUtils.isEmpty(errors)){
            result.setStatus(true);
        }else{
            result.setStatus(false);
            result.setErrors(errors);
        }
        LOGGER.info("FtpUploadStrategy.vsftpMethod end");
        return result;
    }
}