package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Query {
	//private static final String POSTGRES_DRIVER = "org.postgresql.Driver";
	private static final String JDBC_CONNECTION = "jdbc:postgresql://localhost:5432/postgres";
	//for TEST
	private static final String USER = "postgres";
	private static final String PASS = "postgres";
	
	public static Map<String, Integer> insertUserInfo(String user) {

		Map<String, Integer> userInfo = new HashMap<String, Integer>();
		
		Connection con = null;
		try {
			con = DriverManager.getConnection(JDBC_CONNECTION, USER, PASS);
			
			PreparedStatement pstmt_addUser = con.prepareStatement("INSERT INTO users(user_name) VALUES(?) RETURNING user_id");
			pstmt_addUser.setString(0,user);

			// Insert文の実行と、戻り値でシリアル値のユーザIDを取得
			ResultSet rs = pstmt_addUser.executeQuery();
			int serialUesrId = rs.getInt(1);
			Integer user_id = Integer.valueOf(serialUesrId);

			userInfo.put(user, user_id);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return userInfo;
		
	}
	
}