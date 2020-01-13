package com.santiago.gateway.util;

import com.santiago.gateway.consts.FtpConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

public class FtpUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(FtpUtil.class);
    private static String LOCAL_CHARSET = "GBK";
    private static String SERVER_CHARSET = "ISO-8859-1";
    private static String host;
    private static String port;
    private static String username;
    private static String password;
    private static String basePath;
    private static String filePath;
    private static String localPath;

    /**
     *读取配置文件信息
     * @return
     */
    public static  void getPropertity(){
        Properties properties = new Properties();
        ClassLoader load = FtpUtil.class.getClassLoader();
        InputStream is = load.getResourceAsStream("conf/vsftpd.properties");
        try {
            properties.load(is);
            host=properties.getProperty("vsftpd.ip");
            port=properties.getProperty("vsftpd.port");
            username=properties.getProperty("vsftpd.user");
            password=properties.getProperty("vsftpd.pwd");
            //服务器端 基路径
            basePath=properties.getProperty("vsftpd.remote.base.path");
            //服务器端 文件路径
            filePath=properties.getProperty("vsftpd.remote.file.path");
            //本地 下载到本地的目录
            localPath=properties.getProperty("vsftpd.local.file.path");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传重载
     * @param filename  上传到服务器端口重命名
     * @param buffer    byte[] 文件流
     * @return
     */
    public static boolean uploadFile(String filename, byte[] buffer, String optionType) throws Exception{
        getPropertity();
       return uploadFile( host,  port,  username,  password,  basePath,
                 filePath,  filename,  buffer, optionType);
    }

    /**
     * 获取文件列表
     * @param filePath
     * @return
     * @throws Exception
     */
    public static String[] displayFile(String filePath) throws Exception{
        getPropertity();
       return displayFile(host,  port,  username,  password,  basePath, filePath);
    }

    /**
     * 删除文件
     * @param filePath
     * @return
     */
    public static boolean deleteFile(String filePath, String fileName) throws Exception{
        getPropertity();
       return deleteFile(host,  port,  username,  password,  basePath, filePath, fileName);
    }

    /**
     * 判断文件是否存在
     * @param filePath
     * @return
     * @throws Exception
     */
    public static boolean fileExist(String filePath,String filename) throws Exception{
        if(StringUtils.isEmpty(filename)){
            return false;
        }
        getPropertity();
        String[] names =  displayFile(filePath);
        for (String name : names) {
            if(filename.equals(name)){
                return true;
            }
        }
        return false;
    }


    /**
     *下载重载
     * @param filePath  要下载的文件所在服务器的相对路径
     * @param fileName  要下载的文件名
     * @return
     */
    public static byte[] downloadFile(String filePath,String fileName) throws Exception{
        getPropertity();
        return downloadFile( host,  port,  username,  password,  basePath,
                filePath, fileName);
    }


    /**
     * Description: 向FTP服务器上传文件
     * @param host FTP服务器hostname
     * @param port FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param basePath FTP服务器基础目录
     * @param filePath FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath
     * @param fileName 上传到FTP服务器上的文件名
     * @return 成功返回true，否则返回false
     */
    public static boolean uploadFile(String host, String port, String username, String password, String basePath,
                                     String filePath, String fileName, byte[] buffer, String optionType)  throws Exception{
        FTPClient ftp = new FTPClient();
        try {
            fileName = new String(fileName.getBytes(LOCAL_CHARSET));
            boolean result = connectFtp(ftp, host, port, username, password, basePath, filePath);
            if(!result){
                return result;
            }
            //为了加大上传文件速度，将InputStream转成BufferInputStream  , InputStream input
            InputStream inputStream  = new ByteArrayInputStream(buffer);
            //加大缓存区
            ftp.setBufferSize(1024*1024);
            //设置上传文件的类型为二进制类型
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            if(FtpConstants.REPLACE.equals(optionType)){
                ftp.deleteFile(fileName);
            }
            //上传文件
            if (!ftp.storeFile(fileName, inputStream)) {
                return false;
            }
            inputStream.close();
            ftp.logout();
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return true;
    }


    /**
     * Description: 从FTP服务器下载文件
     * @param host FTP服务器hostname
     * @param port FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param basePath FTP服务器上的相对路径
     * @param fileName 要下载的文件名
     * @return
     */
    public static byte[] downloadFile(String host, String port, String username, String password, String basePath,
                                       String filePath, String fileName)  throws Exception{
        FTPClient ftp = new FTPClient();
        try {
            fileName = new String(fileName.getBytes(LOCAL_CHARSET));
            boolean result = connectFtp(ftp, host, port, username, password, basePath, filePath);
            if(!result){
                return null;
            }
            FTPFile[] fs = ftp.listFiles();
            boolean flag = true;
            for (FTPFile ff : fs) {
                if (ff.getName().equals(fileName)) {
                    InputStream input = ftp.retrieveFileStream(ff.getName());
                    BufferedInputStream in = new BufferedInputStream(input);
                    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len = -1;
                    while((len = in.read(buffer)) != -1){
                        outStream.write(buffer, 0, len);
                    }
                    outStream.close();
                    in.close();
                    byte[] arryArry = outStream.toByteArray();
                    return arryArry;
                }
            }
            if(flag) {
                LOGGER.info("服务器端文件不存在...");
                return null;
            }
            ftp.logout();
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return null;
    }

    /**
     * 获取服务器文件名列表
     * @param host
     * @param port
     * @param username
     * @param password
     * @param basePath
     * @param filePath
     * @return
     * @throws Exception
     */
    public static String[] displayFile(String host, String port, String username, String password, String basePath,
                                        String filePath) throws Exception{
        FTPClient ftp = new FTPClient();
        try {
            boolean result = connectFtp(ftp, host, port, username, password, basePath, filePath);
            if(!result){
                return null;
            }
            String[] names = ftp.listNames();
            ftp.logout();
           return names;
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
    }

    /**
     * 删除文件
     * @param host
     * @param port
     * @param username
     * @param password
     * @param basePath
     * @param filePath
     * @return
     */
    public static boolean deleteFile(String host, String port, String username, String password, String basePath,
                                     String filePath,String fileName) throws Exception{
        FTPClient ftp = new FTPClient();
        boolean b = false;
        try {
            boolean result = connectFtp(ftp, host, port, username, password, basePath, filePath);
            if(!result){
                return b;
            }
            b = ftp.deleteFile(fileName);
            ftp.logout();
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return b;
    }

    /**
     * 连接ftp服务器并切换到目的目录
     * 调用此方法需手动关闭ftp连接
     * @param ftp
     * @param host
     * @param port
     * @param username
     * @param password
     * @param basePath
     * @param filePath
     * @return
     */
    private static boolean connectFtp( FTPClient ftp,String host, String port, String username,
                                       String password, String basePath, String filePath) throws Exception{
        boolean result = false;
        try {
            int portNum = Integer.parseInt(port);
            int reply;
            // 连接FTP服务器
            ftp.connect(host, portNum);
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login(username, password);
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return result;
            }
            // 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）.
            if (FTPReply.isPositiveCompletion(ftp.sendCommand("OPTS UTF8", "ON"))) {
                LOCAL_CHARSET = "UTF-8";
            }
            ftp.setControlEncoding(LOCAL_CHARSET);
            //切换到上传目录
            if (!ftp.changeWorkingDirectory(basePath+filePath)) {
                //如果目录不存在创建目录
                String[] dirs = filePath.split("/");
                String tempPath = basePath;
                for (String dir : dirs) {
                    if (null == dir || "".equals(dir)) {
                        continue;
                    }
                    tempPath += "/" + dir;
                    if (!ftp.changeWorkingDirectory(tempPath)) {
                        if (!ftp.makeDirectory(tempPath)) {
                            return result;
                        } else {
                            ftp.changeWorkingDirectory(tempPath);
                        }
                    }
                }
            }
            result = true;
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
            e.printStackTrace();
            throw e;
        }
        return result;
    }
}