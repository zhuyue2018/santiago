package com.santiago.gateway.service;

import com.santiago.commons.ftp.VsftpResult;
import com.santiago.commons.ftp.Vsftpd;

public interface IFtpUploadStrategy {
    VsftpResult vsftpMethod(Vsftpd vsftpd);
}
