package com.charhoo.os.session;

public class ThisSession {

    private String loginName;
    private String connect;

    public ThisSession(String loginName, String connect){
        this.loginName = loginName;
        this.connect = connect;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getConnect() {
        return connect;
    }

    public void setConnect(String connect) {
        this.connect = connect;
    }
}
