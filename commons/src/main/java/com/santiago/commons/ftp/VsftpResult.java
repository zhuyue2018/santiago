package com.santiago.commons.ftp;

import java.util.List;

public class VsftpResult {

    private boolean status;

    private byte[] byteArry;

    private String[] fileNames;

    private List<ErrorInfo> errors;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public byte[] getByteArry() {
        return byteArry;
    }

    public void setByteArry(byte[] byteArry) {
        this.byteArry = byteArry;
    }

    public String[] getFileNames() {
        return fileNames;
    }

    public void setFileNames(String[] fileNames) {
        this.fileNames = fileNames;
    }

    public List<ErrorInfo> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorInfo> errors) {
        this.errors = errors;
    }
}