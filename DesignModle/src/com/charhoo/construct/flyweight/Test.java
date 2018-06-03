package com.charhoo.construct.flyweight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
	
	public static void main(String[] args) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		String sql = "select * from t_user";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				System.out.println(rs.getInt(1));
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			System.out.println(pool.getPool().size());
			pool.release();
			System.out.println(pool.getPool().size());
		}
		
	}

}
