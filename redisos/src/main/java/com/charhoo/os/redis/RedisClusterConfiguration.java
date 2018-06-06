package com.charhoo.os.redis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * RedisCluster custom config for springboot
 * @author ceruto
 */
@Configuration
@EnableConfigurationProperties(RedisClusterProperties.class)
@EnableTransactionManagement
public class RedisClusterConfiguration {

    @Autowired
    private RedisClusterProperties redisClusterProperties;


    @Bean
    public JedisCluster connectionFactory(){

        List<String> nodes = this.redisClusterProperties.getNodes();

        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        for(String redisAddr : nodes ){
            String[] datasourceArr = redisAddr.split(":");
            jedisClusterNodes.add(new HostAndPort(datasourceArr[0], Integer.parseInt(datasourceArr[1])));
        }

        JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes);
        return jedisCluster;
    }
}
