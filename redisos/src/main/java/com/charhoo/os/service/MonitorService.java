package com.charhoo.os.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@Service
@Repository
public class MonitorService {

    private static final Logger log = LoggerFactory.getLogger(MonitorService.class);

    @Autowired
    private JedisCluster jedisCluster;

    public String clusterNodes(){
        //获取集群某个节点
        Jedis jedis = getJedis(null);
        try{
            Client client = jedis.getClient();
            client.clusterNodes();
            String result = client.getBulkReply();
            return result.replace("\n","@");
        }catch (Exception e){
            log.error(" error:{}",e);
            return "error";
        }finally {
            jedis.close();
        }
    }

    public Jedis getJedis(String ipPort){
        Map<String, JedisPool> nodes = jedisCluster.getClusterNodes();
        if(ipPort == null){
            ipPort = (String) nodes.keySet().toArray()[0];
        }
        JedisPool jedisPool = nodes.get(ipPort);
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }




}
