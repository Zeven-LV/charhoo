package com.charhoo.os.model;

public class RedisInfoModel {

    private String id;
    private String ipAndPort;
    private String flags;
    private String master;
    private String pingSent;
    private String pongrRecv;
    private String configEpoch;
    private String linkState;
    private String slot;

    public RedisInfoModel(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIpAndPort() {
        return ipAndPort;
    }

    public void setIpAndPort(String ipAndPort) {
        this.ipAndPort = ipAndPort;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getPingSent() {
        return pingSent;
    }

    public void setPingSent(String pingSent) {
        this.pingSent = pingSent;
    }

    public String getPongrRecv() {
        return pongrRecv;
    }

    public void setPongrRecv(String pongrRecv) {
        this.pongrRecv = pongrRecv;
    }

    public String getConfigEpoch() {
        return configEpoch;
    }

    public void setConfigEpoch(String configEpoch) {
        this.configEpoch = configEpoch;
    }

    public String getLinkState() {
        return linkState;
    }

    public void setLinkState(String linkState) {
        this.linkState = linkState;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }
}
