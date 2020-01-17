package com.santiago.commons.ftp;

public class Vsftpd {
    /**
     * 请求类型[必填]
     * upload: 上传 文件已存在上传失败
     * replace：上传 文件已存在则覆盖
     * download: 下载
     * display: 查看文件列表
     * delete: 删除文件
     */
    private String optionType;
    /**
     * 请求者在接口管理系统中维护的项目编码[必填]
     */
    private String projectCode;
    /**
     * 上传/下载 文件名[非必填]
     */
    private String fileName;
    /**
     * 上传/下载 文件的字节数组[非必填]
     */
    private byte[] byteArry;

    public String getOptionType() {
        return optionType;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getByteArry() {
        return byteArry;
    }

    public void setByteArry(byte[] byteArry) {
        this.byteArry = byteArry;
    }
}