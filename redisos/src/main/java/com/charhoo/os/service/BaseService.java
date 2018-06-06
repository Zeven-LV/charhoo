package com.charhoo.os.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

@Service
@Repository
public class BaseService {

    @Autowired
    private JedisCluster jedisCluster;

    public Object stringAction(String action, String key, String value){

        Object obj = null;
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
                obj = jedisCluster.set(key, value);
                break;
            default:
                obj = "error";
                break;
        }
        return obj;
    }
}
