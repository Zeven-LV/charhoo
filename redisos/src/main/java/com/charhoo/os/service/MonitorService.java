package com.charhoo.os.service;

import com.charhoo.os.model.RedisDbSizeModel;
import com.charhoo.os.model.RedisInfoModel;
import com.charhoo.os.model.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Repository
public class MonitorService {

    private static final Logger logger = LoggerFactory.getLogger(MonitorService.class);

    @Autowired
    private JedisCluster jedisCluster;

    public ResponseModel clusterNodes(){
        //获取集群某个节点
        Jedis jedis = getJedis(null);
        if(jedis==null){
            return null;
        }
        Client client = jedis.getClient();
        if(client == null){
            return ResponseModel.getInstance(ResponseModel.STATUS_FAIL, null, "without redis client");
        }
        client.clusterNodes();
        String strInfos = client.getBulkReply();
        String[] strInfosArr = strInfos.split("\n");
        List<RedisInfoModel> list = new ArrayList<>();
        for(String strInfo : strInfosArr){
            String[] strInfoArr = strInfo.split(" ");
            RedisInfoModel redisInfoModel = new RedisInfoModel();
            redisInfoModel.setId(strInfoArr[0]);
            redisInfoModel.setIpAndPort(strInfoArr[1]);
            redisInfoModel.setFlags(strInfoArr[2]);
            redisInfoModel.setMaster(strInfoArr[3]);
            redisInfoModel.setPingSent(strInfoArr[4]);
            redisInfoModel.setPongrRecv(strInfoArr[5]);
            redisInfoModel.setConfigEpoch(strInfoArr[6]);
            redisInfoModel.setLinkState(strInfoArr[7]);
            if(strInfoArr.length>8){
                redisInfoModel.setSlot(strInfoArr[8]);
            }
            list.add(redisInfoModel);
        }
        jedis.close();
        return ResponseModel.getInstance(ResponseModel.STATUS_SUCCESS, list, null);
    }

    public ResponseModel clusterDbSize(){
        List<RedisDbSizeModel> list = new ArrayList<>();
        for(Map.Entry<String, JedisPool> entry : jedisCluster.getClusterNodes().entrySet()){
            Jedis jedis = entry.getValue().getResource();
            Client client = jedis.getClient();
            client.dbSize();
            RedisDbSizeModel redisDbSizeModel = new RedisDbSizeModel();
            redisDbSizeModel.setIp(entry.getKey());
            redisDbSizeModel.setDbSize(client.getIntegerReply());
            list.add(redisDbSizeModel);
            jedis.close();
        }
        return ResponseModel.getInstance(ResponseModel.STATUS_SUCCESS, list, null);
    }

    public ResponseModel clusterInfo(){
        List<String[]> list = new ArrayList<>();
        for(Map.Entry<String, JedisPool> entry : jedisCluster.getClusterNodes().entrySet()){
            Jedis jedis = entry.getValue().getResource();
            Client client = jedis.getClient();
            client.clusterInfo();
            String result = client.getBulkReply();
            list.add(result.split("\r \n"));
            jedis.close();
        }
        return ResponseModel.getInstance(ResponseModel.STATUS_SUCCESS, list, null);
    }

    public ResponseModel nodeInfo(){
        List<String[]> list = new ArrayList<>();
        for(Map.Entry<String, JedisPool> entry : jedisCluster.getClusterNodes().entrySet()){
            Jedis jedis = entry.getValue().getResource();
            Client client = jedis.getClient();
            client.info();
            String result = client.getBulkReply();
            list.add(result.split("\r\n"));
            jedis.close();
        }
        return ResponseModel.getInstance(ResponseModel.STATUS_SUCCESS, list, null);
    }

    /**
     * get a node from redis cluster
     */
    public Jedis getJedis(String ipPort){
        Map<String, JedisPool> nodes = jedisCluster.getClusterNodes();
        if(nodes == null){
            logger.info("there is no redis nodes discover!");
            return null;
        }
        if(ipPort == null){
            ipPort = (String) nodes.keySet().toArray()[0];
        }
        JedisPool jedisPool = nodes.get(ipPort);
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }

    public List<JedisPool> getAllJedis(){
        Map<String, JedisPool> nodes = jedisCluster.getClusterNodes();
        if(nodes == null){
            logger.info("there is no redis nodes discover!");
            return null;
        }
        List<JedisPool> list = new ArrayList<>();
        for(Map.Entry entry : nodes.entrySet()){
            System.out.println(entry.getKey());
            JedisPool jedisPool = nodes.get(entry.getKey());
            list.add(jedisPool);
        }
        return list;
    }




}
