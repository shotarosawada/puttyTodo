package model;

import java.sql.Connection;
import java.sql.Date;
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
	
	public static Map<Integer, String> insertUserInfo(String user) {

		Map<Integer, String> userInfo = new HashMap<Integer, String>();

		Connection con = null;
		try {
			con = DriverManager.getConnection(JDBC_CONNECTION, USER, PASS);
			
			PreparedStatement pstmt_addUser = con.prepareStatement("INSERT INTO users(user_name) VALUES(?) RETURNING user_id");
			pstmt_addUser.setString(1,user);

			// Insert文の実行と、戻り値でシリアル値のユーザIDを取得
			ResultSet rs = pstmt_addUser.executeQuery();
			
			int serialUserId = 0;
			
			while(rs.next()) {
				serialUserId = rs.getInt("user_id");
			}
			Integer user_id = Integer.valueOf(serialUserId);

			userInfo.put(user_id, user);
			System.out.println(serialUserId);

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
	
	public static Map<Integer, Object> insertTaskInfo(Integer userId, String userName, String content, Date deadline) {
	
//		List<HashMap<Integer, Object>> usersList = new ArrayList<HashMap<Integer, Object>>();
		Map<Integer, Object> userInfo = new HashMap<Integer, Object>();

		
		Connection con = null;
		try {
			con = DriverManager.getConnection(JDBC_CONNECTION, USER, PASS);
			
			PreparedStatement pstmt_addUser = con.prepareStatement("INSERT INTO tasks(user_id, content, status, due_date) VALUES(?, ?, ?, ?) RETURNING task_id");
			pstmt_addUser.setInt(1,userId);
			pstmt_addUser.setString(2,content);
			pstmt_addUser.setBoolean(3, false);
			pstmt_addUser.setDate(4,deadline);

			// Insert文の実行と、戻り値でシリアル値のユーザIDを取得
			ResultSet rs = pstmt_addUser.executeQuery();
			
			int serialTaskId = 0;
			
			while(rs.next()) {
				serialTaskId = rs.getInt("task_id");
			}

			Task registerTask = new Task(serialTaskId, userId, content, false, deadline);
			userInfo.put(userId, registerTask);
			System.out.println(serialTaskId);

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