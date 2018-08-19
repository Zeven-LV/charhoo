package com.charhoo.os;

import com.charhoo.os.model.RedisInfoModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Client;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.util.Slowlog;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisosApplicationTests {

	public Jedis jedis = null;

	@Before
	public void getJedis() {
		Jedis jedis = null;
		try {
			jedis = new Jedis("192.168.110.233",7379);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.jedis = jedis;
	}

	@After
	public void closeJedis(){
		jedis.close();
	}

	@Test
	public void nodes() {
		Client client = jedis.getClient();
		if(client==null){
			System.out.println("null");
			return;
		}
		client.clusterNodes();
		String strInfos = client.getBulkReply();
		System.out.println(strInfos);
		String[] strInfosArr = strInfos.split("\n");
		List<RedisInfoModel> list = new ArrayList<>();
		for(String strInfo : strInfosArr){
			String[] strInfoArr = strInfo.split("");
			RedisInfoModel redisInfoModel = new RedisInfoModel();
			redisInfoModel.setId(strInfoArr[0]);
			redisInfoModel.setIpAndPort(strInfoArr[1]);
			redisInfoModel.setFlags(strInfoArr[2]);
			redisInfoModel.setMaster(strInfoArr[3]);
			redisInfoModel.setPingSent(strInfoArr[4]);
			redisInfoModel.setPongrRecv(strInfoArr[5]);
			redisInfoModel.setConfigEpoch(strInfoArr[6]);
			redisInfoModel.setLinkState(strInfoArr[7]);
			redisInfoModel.setSlot(strInfoArr[8]);
			list.add(redisInfoModel);
		}
//		System.out.println(client.getStatusCodeReply());
	}

	@Test
	public void clasterinfo() {
		Client client = jedis.getClient();
		if(client==null){
			System.out.println("null");
			return;
		}
		client.clusterInfo();
		String strInfos = client.getBulkReply();
		System.out.println(strInfos);

	}

	@Test
	public void info() {
		Client client = jedis.getClient();
		if(client==null){
			System.out.println("null");
			return;
		}
		client.info();
		String strInfos = client.getBulkReply();
		System.out.println(strInfos);

	}

	/**
	 * 节点上key的数量
	 */
	@Test
	public void dbSize() {
		Client client = jedis.getClient();
		if(client==null){
			System.out.println("null");
			return;
		}
		client.dbSize();
		System.out.println(client.getIntegerReply());
	}

	@Test
	public void logs() {
		List<Slowlog> logList = jedis.slowlogGet(123);
		System.out.println(logList.size());
		for(Slowlog slowlog : logList){
			System.out.println(slowlog.toString());
		}
	}

}
