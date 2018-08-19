package com.charhoo.os.model;

public class RedisDbSizeModel {

    private String ip;
    private Long dbSize;

    public RedisDbSizeModel() {
    }

    public RedisDbSizeModel(String ip, Long dbSize) {
        this.ip = ip;
        this.dbSize = dbSize;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getDbSize() {
        return dbSize;
    }

    public void setDbSize(Long dbSize) {
        this.dbSize = dbSize;
    }
}
