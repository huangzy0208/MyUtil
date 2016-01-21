package com.sdeport.common.utils;

import com.sdeport.common.pojo.HostInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ftp工具类
 * Created by zhangmeng on 2015/9/21.
 */
public class FtpUtils {
    private static final Logger log = LoggerFactory.getLogger(FtpUtils.class);

    /**
     * 获得ftp连接
     *
     * @param host
     * @return
     */
    public static FTPClient getFtpClient(HostInfo host) {
        log.info("get ftpClient,hostInfo:{}", host);
        FTPClient ftp = null;
        try {
            if (null != host) {
                int reply;
                ftp = new FTPClient();
                ftp.setDefaultTimeout(10 * 1000);
                ftp.setConnectTimeout(10 * 1000);
                ftp.setDataTimeout(10 * 1000);
                ftp.connect(host.getIp(), Integer.parseInt(host.getPort()));
                ftp.login(host.getUsr(), host.getPwd());
                ftp.enterLocalPassiveMode();
                ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
                reply = ftp.getReplyCode();
                if (!FTPReply.isPositiveCompletion(reply)) {
                    log.error(ftp.getReplyString());
                    ftp.disconnect();
                    ftp = null;
                    Thread.sleep(1000);
                }
            }
        } catch (IOException | InterruptedException e) {
            log.error("create ftp connection error,HostInfo:{}", host, e);
        }
        return ftp;
    }

    /**
     * 上传一个文件
     *
     * @param localFileName
     * @param remoteFileName
     * @param ftpClient
     * @return
     */
    public static boolean put(String localFileName, String remoteFileName, FTPClient ftpClient) {
        File localFile = new File(localFileName);
        FileInputStream input = null;

        try {
            input = new FileInputStream(localFile);
            String path = FileUtils.getFilePath(remoteFileName, "/");
            ftpClient.mkd(path);
            ftpClient.storeFile(remoteFileName, input);
            input.close();
            checkFtpReply(ftpClient);
            return true;
        } catch (Exception e) {
            log.error("file upload failure,localFile:{},remoteFile:{},ftpClient:{}", localFileName, remoteFileName, ftpClient, e);
            resetFtpClient(ftpClient);
        } finally {
            closeStream(input);
        }

        return false;
    }

    /**
     * 下载一个文件
     *
     * @param localFileName
     * @param remoteFileName
     * @param ftpClient
     * @return
     */
    public static boolean get(String localFileName, String remoteFileName, FTPClient ftpClient) {
        File localFile = new File(localFileName);
        FileOutputStream fos = null;
        try {
            String localPath = localFileName.substring(0, localFileName.lastIndexOf("/"));
            File dir = new File(localPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            fos = new FileOutputStream(localFile);
            ftpClient.retrieveFile(remoteFileName, fos);
            checkFtpReply(ftpClient);
            fos.close();
            return true;
        } catch (Exception e) {
            log.error("file download failure,localFile:{},remoteFile:{},ftpClient:{}", localFileName, remoteFileName, ftpClient, e);
            resetFtpClient(ftpClient);
        } finally {
            closeStream(fos);
        }

        return false;
    }

    /**
     * 删除一个文件
     *
     * @param remoteFileName
     * @param ftpClient
     * @return
     */
    public static boolean delete(String remoteFileName, FTPClient ftpClient) {
        try {
            ftpClient.deleteFile(remoteFileName);
            checkFtpReply(ftpClient);
            return true;
        } catch (Exception e) {
            log.error("file delete failure,remoteFile:{},ftpClient:{}", remoteFileName, ftpClient, e);
            resetFtpClient(ftpClient);
        }
        return false;
    }

    /**
     * 获得复合正则匹配的文件列表
     *
     * @param ftpClient
     * @param path
     * @param regex
     * @return
     */
    public static List<String> list(FTPClient ftpClient, String path, String regex) {
        List<String> listFiles = list(ftpClient, path);
        if (CollectionUtils.isNotEmpty(listFiles)) {
            for (int i = listFiles.size() - 1; i >= 0; i--) {
                if (!listFiles.get(i).matches(regex)) {
                    listFiles.remove(i);
                }
            }
        }
        return listFiles;
    }

    /**
     * 获得文件列表
     *
     * @param ftpClient
     * @param path
     * @return
     */
    public static List<String> list(FTPClient ftpClient, String path) {
        List<String> listFiles = new ArrayList<>();
        try {
            String[] files = ftpClient.listNames(path);
            if (null != files) {
                for (String file : files) {
                    if (!file.startsWith("/")) {
                        file = path + "/" + file;
                    }
                    listFiles.add(file);
                }
            }
            ;
        } catch (Exception e) {
            log.error("file list failure,path:{},ftpClient:{}", path, ftpClient, e);
            resetFtpClient(ftpClient);
        }
        return listFiles;
    }

    /**
     * 关闭流
     *
     * @param input
     */
    private static void closeStream(Closeable input) {
        if (null != input) {
            try {
                input.close();
            } catch (IOException e) {
                log.error("关闭流失败", e);
            }
        }
    }

    /**
     * 重置ftp连接
     *
     * @param ftpClient
     */
    public static void resetFtpClient(FTPClient ftpClient) {
        //如果失败，ftpclient重置以重连
        if (null != ftpClient) {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                log.error("ftp断开连接失败,ftpClient:{}", ftpClient, e);
            }
            ftpClient = null;
        }
    }

    /**
     * 检查ftp返回码
     *
     * @param ftp
     * @throws IOException
     * @throws InterruptedException
     */
    public static void checkFtpReply(FTPClient ftp) throws Exception {
        int reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            throw new Exception(ftp.getReplyString());
        }
    }
}
