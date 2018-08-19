package com.charhoo.os.utils;

import java.util.HashMap;
import java.util.Map;

public class CurrentConnectUtil {


    private String redisCluster;
    private long connectTime;

    private CurrentConnectUtil(){
    }

    public static CurrentConnectUtil instants(){
        return CurrentConnect.instants;
    }

    /**
     * 设置当前连接
     * @param redisCluster
     * @param connectTime
     */
    public void set(String redisCluster, long connectTime){
        this.redisCluster = redisCluster;
        this.connectTime = connectTime;
    }

    /**
     * 当前是否有连接
     * @return
     */
    public boolean hasConnect(){
        if(this.redisCluster == null)
            return false;
        return true;
    }

    /**
     * 获取连接信息
     * @return
     */
    public Map getMap(){
        Map map = new HashMap();
        map.put("redisCluster",this.redisCluster);
        map.put("connectTime",this.connectTime);
        return map;
    }

    private static class CurrentConnect{
        private static CurrentConnectUtil instants = new CurrentConnectUtil();
    }

}
