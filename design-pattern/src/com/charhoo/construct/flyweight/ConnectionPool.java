package com.charhoo.construct.flyweight;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

import com.charhoo.build.single.SingleHungry2;

public class ConnectionPool {

	private Vector<Connection> pool;

	/* 公有属性 */
	private String url = "jdbc:mysql://localhost:3306/hibernate";
	private String username = "root";
	private String password = "root";
	private String driverClassName = "com.mysql.jdbc.Driver";

	private int poolSize = 50;
	private static ConnectionPool instance = null;
	Connection conn = null;
	
	static{
		instance = new ConnectionPool();
	}

	/* 构造方法，做一些初始化工作 */
	private ConnectionPool() {
		pool = new Vector<Connection>(poolSize);

		for (int i = 0; i < poolSize; i++) {
			try {
				Class.forName(driverClassName);
				conn = DriverManager.getConnection(url, username, password);
				pool.add(conn);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/* 返回连接到连接池 */
	public synchronized void release() {
		pool.add(conn);
	}

	/* 返回连接池中的一个数据库连接 */
	public synchronized Connection getConnection() {
		if (pool.size() > 0) {
			Connection conn = pool.get(0);
			pool.remove(conn);
			return conn;
		} else {
			return null;
		}
	}
	
	public static ConnectionPool getInstance() {
		return instance;
	}

	public Vector<Connection> getPool() {
		return pool;
	}

	public void setPool(Vector<Connection> pool) {
		this.pool = pool;
	}
	
	

}
