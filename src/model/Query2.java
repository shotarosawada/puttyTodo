//package model;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class Query {
//	//private static final String POSTGRES_DRIVER = "org.postgresql.Driver";
//	private static final String JDBC_CONNECTION = "jdbc:postgresql://localhost:5432/postgres";
//	//for TEST
//	private static final String USER = "postgres";
//	private static final String PASS = "postgres";
//	
//	public static ArrayList<String> insertUserInfo(String user) {
//		
//		Connection con = null;
//		ArrayList<String> names = new ArrayList<String>();
//		try {
//			con = DriverManager.getConnection(JDBC_CONNECTION, USER, PASS);
//			
//			PreparedStatement pstmt_addUser = con.prepareStatement("INSERT INTO users(user_name) VALUES(?) RETURNING user_id");
//			pstmt_addUser.setString(0,user);
//			// Insert文の実行と、戻り値でユーザIDを取得
//			ResultSet rs = pstmt_addUser.executeQuery();
//			int user_id = rs.getInt(1);
//
////			PreparedStatement pstmt_Search = con.prepareStatement("SELECT * from users");
////			ResultSet rs = pstmt_Search.executeQuery();
//
//			//PreparedStatement pstmt_update = con.prepareStatement("INSERT INTO tasks(task_name, task_deadline, task_status) VALUES(?, ?, ?)");
//			//pstmt_update.setString(1, "");
//			//pstmt_update.setString(2, "");
//			//pstmt_update.setBoolean(3, false);
//			//pstmt_update.executeUpdate();
//			//TODO insertしたuser名でtasksテーブルからwhere user_name = "";で絞って
//
//			while (rs.next()) {
//				String name =rs.getString("user_name");
//				names.add(name);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (con != null) {
//				try {
//					con.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return names;
//		
//	}
//	
//}