package model;

import java.io.Serializable;
import java.sql.Date;

public class DTO implements Serializable {
	private int taskId;
	private int userId;
	private String content;
	private boolean status;
	private Date deadline;
	
	public DTO() {}
	public DTO(int taskId, int userId, String content, boolean status, Date deadline) {
		this.taskId = taskId;
		this.userId = userId;
		this.content = content;
		this.status = status;
		this.deadline = deadline;
	}

	public int getTaskId() { return taskId; }
	public int getUserId() { return userId; }
	public String getContent() { return content; }
	public boolean getStatus() { return status; }
	public Date getDeadline() { return deadline; }
	
	public void setTaskId(int task_id) {
		this.taskId = task_id;
	}

	public void setUserId(int user_id) {
		this.userId = user_id;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setDueDate(Date due_date) {
		this.deadline = due_date;
	}

}