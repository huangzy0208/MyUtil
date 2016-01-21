package com.sdeport.common.pojo;

/**
 * 主机信息
 *
 * @author zhangmeng
 * @date 2015/9/20,18:57
 */

public class HostInfo {

    String type; //主机类型 linux/windows等
    String protocol = "ftp"; //传输协议，默认ftp
    String ip; //主机IP
    String port; //主机通讯端口
    String usr;    //主机用户名
    String pwd; //主机密码
    String remotePath; //主机存放目录
    String localPath;//本地存放目录
    String regex;//匹配正则表达式

    public HostInfo(String type, String protocol, String ip, String port, String usr, String pwd, String remotePath, String localPath, String regex) {
        this.type = type;
        this.protocol = protocol;
        this.ip = ip;
        this.port = port;
        this.usr = usr;
        this.pwd = pwd;
        this.remotePath = remotePath;
        this.localPath = localPath;
        this.regex = regex;
    }

    public HostInfo() {
    }

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getProtocol() {
        return this.protocol;
    }

    public void setProtocol(final String protocol) {
        this.protocol = protocol;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(final String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return this.port;
    }

    public void setPort(final String port) {
        this.port = port;
    }


    public String getPwd() {
        return this.pwd;
    }

    public void setPwd(final String pwd) {
        this.pwd = pwd;
    }

    public String getRemotePath() {
        return this.remotePath;
    }

    public void setRemotePath(String remotePath) {
        this.remotePath = remotePath;
    }

    public String getUsr() {
        return this.usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public String getLocalPath() {
        return this.localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getRegex() {
        return this.regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String toString() {
        return "HostInfo{" +
                "type='" + type + '\'' +
                ", protocol='" + protocol + '\'' +
                ", ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", usr='" + usr + '\'' +
                ", pwd='" + pwd + '\'' +
                ", remotePath='" + remotePath + '\'' +
                ", localPath='" + localPath + '\'' +
                '}';
    }
}
