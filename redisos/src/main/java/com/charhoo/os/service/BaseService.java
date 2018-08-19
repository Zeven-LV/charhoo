package com.charhoo.os.service;

import com.charhoo.os.model.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.util.HashMap;
import java.util.Map;

@Service
//@Repository
public class BaseService {

    private static Logger logger = LoggerFactory.getLogger(BaseService.class);

    @Autowired
    private JedisCluster jedisCluster;

    public ResponseModel stringAction(String action, String key, String value){

        Object obj = null;
        try{
            switch (action){
                case "set":
                    obj = jedisCluster.set(key, value);
                    break;
                case "get":
                    obj = jedisCluster.get(key);
                    break;
                case "getrange":
                    String[] values = value.split(" ");
                    obj = jedisCluster.getrange(key, Long.getLong(values[0]),Long.getLong(values[1]));
                    break;
                case "getset":
                    obj = jedisCluster.getSet(key, value);
                    break;
                case "getbit":
                    obj = jedisCluster.getbit(key, Long.valueOf(value));
                    break;
                case "mget":
                    obj = jedisCluster.mget(key);
                    break;
                case "del":
                    obj = jedisCluster.del(key);
                    break;
                default:
                    obj = "error";
                    break;
            }
            if(obj == null){
                return ResponseModel.getInstance(ResponseModel.STATUS_NORESULT,null,null);
            }else{
                return ResponseModel.getInstance(ResponseModel.STATUS_SUCCESS,obj,null);
            }
        }catch (Exception e){
            logger.error("stringAction action:{},key:{},value:{};ERROR:{}", action, key, value, e);
            return ResponseModel.getInstance(ResponseModel.STATUS_FAIL,null,e.getMessage());
        }

    }

    public Object hashAction(String action, String key, String value){

        Object obj = null;
        switch (action){
            case "hset":
                String[] hsetValues = value.split(":");
                obj = jedisCluster.hset(key, hsetValues[0],hsetValues[1]);
                break;
            case "hsetnx":
                String[] hsetnxValues = value.split(":");
                obj = jedisCluster.hsetnx(key, hsetnxValues[0],hsetnxValues[1]);
                break;
            case "hmset":
                Map<String,String> map = new HashMap<>();
                for(String entry : value.split(";")){
                    String[] keyValue = entry.split(":");
                    map.put(keyValue[0],keyValue[1]);
                }
                obj = jedisCluster.hmset(key, map);
                break;
            case "hkeys":
                obj = jedisCluster.hkeys(key);
                break;
            case "hlen":
                obj = jedisCluster.hlen(key);
                break;
            case "hget":
                obj = jedisCluster.hget(key,value);
                break;
            case "hgetall":
                obj = jedisCluster.hgetAll(key);
                break;
            case "hmget":
                String[] values = value.split(" ");
                obj = jedisCluster.hmget(key, values);
                break;
            case "hvals":
                obj = jedisCluster.hvals(key);
                break;
            case "del":
                obj = jedisCluster.del(key, value);
                break;
            default:
                obj = "error";
                break;
        }

        return obj;
    }

    /**
     * 操作redis list数据类型
     * 头部（l）--》尾部(r)
     * 添加（push）删除（pop）
     * @param action
     * @param key
     * @param value
     * @return
     */
    public Object listAction(String action, String key, String value){

        Object obj = null;
        switch (action){
            case "rpush":
                String[] rpushValues = value.split(" ");
                obj = jedisCluster.rpush(key, rpushValues);
                break;
            case "lpush":
                String[] lpushValues = value.split(" ");
                obj = jedisCluster.lpush(key,lpushValues);
                break;
            case "lrange":
                String[] lrangeValues = value.split(" ");
                obj = jedisCluster.lrange(key, Long.getLong(lrangeValues[0]),Long.getLong(lrangeValues[1]));
                break;
            case "lpop":
                obj = jedisCluster.lpop(key);
                break;
            case "rpop":
                obj = jedisCluster.rpop(key);
                break;
            case "ltrim":
                String[] ltrimValues = value.split(" ");
                obj = jedisCluster.ltrim(key, Long.getLong(ltrimValues[0]),Long.getLong(ltrimValues[1]));
                break;
            default:
                obj = "error";
                break;
        }
        return obj;
    }



}
