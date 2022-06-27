package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Query {
	private static final String JDBC_CONNECTION = "jdbc:postgresql://localhost:5432/postgres";
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
	
	public static int insertTaskInfo(Integer userId, String content, Boolean status, Date deadline) {
		
		int taskId = 0;

		Connection con = null;
		try {
			con = DriverManager.getConnection(JDBC_CONNECTION, USER, PASS);
			
			PreparedStatement pstmt_addUser = con.prepareStatement("INSERT INTO tasks(user_id, content, status, due_date) VALUES(?, ?, ?, ?) RETURNING task_id");
			pstmt_addUser.setInt(1,userId);
			pstmt_addUser.setString(2,content);
			pstmt_addUser.setBoolean(3, status);
			pstmt_addUser.setDate(4,deadline);

			// Insert文の実行と、戻り値でシリアル値のタスクIDを取得
			ResultSet rs = pstmt_addUser.executeQuery();
			
			while(rs.next()) {
				taskId = rs.getInt("task_id");
			}

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
		return taskId;

	}

	public static void switchTaskStatus(int taskId) {

		Connection con = null;
		try {
			con = DriverManager.getConnection(JDBC_CONNECTION, USER, PASS);

			PreparedStatement pstmt_getStatus = con.prepareStatement("SELECT status FROM tasks WHERE task_id = ?");
			pstmt_getStatus.setInt(1,taskId);

			ResultSet rs = pstmt_getStatus.executeQuery();
			Boolean flag = false;
			while(rs.next()) {
				flag = rs.getBoolean("status");
			}			
			
			PreparedStatement pstmt_status = con.prepareStatement("UPDATE tasks SET status=? WHERE task_id = ?");
			pstmt_status.setInt(2,taskId);
			if(flag){
				pstmt_status.setBoolean(1, false);
				pstmt_status.executeQuery();
			} else {
				pstmt_status.setBoolean(1, true);
				pstmt_status.executeQuery();
			}

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

	}
	
	public static void deleteTaskInfo(int taskId) {
		

		Connection con = null;
		try {
			con = DriverManager.getConnection(JDBC_CONNECTION, USER, PASS);

			PreparedStatement pstmt_delTask = con.prepareStatement("DELETE from tasks WHERE task_id = ?");
			pstmt_delTask.setInt(1,taskId);

			// DELETE文の実行
			pstmt_delTask.executeQuery();

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

	}
	
	public static ArrayList<DTO> getTaskInfo(Integer userId) {
		
		Connection con = null;
		ArrayList<DTO> taskList = new ArrayList<DTO>();

		try {
			con = DriverManager.getConnection(JDBC_CONNECTION, USER, PASS);
			
			// 引数のuserIdをもつユーザのタスク一覧を取得
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM tasks WHERE user_id = ?");
			pstmt.setInt(1,userId);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				DTO dto = new DTO();
				dto.setTaskId(rs.getInt("task_id"));
				dto.setUserId(rs.getInt("user_id"));
				dto.setContent(rs.getString("content"));
				dto.setStatus(rs.getBoolean("status"));
				dto.setDueDate(rs.getDate("due_date"));
				taskList.add(dto);
			}

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
		return taskList;
		
	}
	
}