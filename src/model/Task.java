package model;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {
	private int taskId;
	private int userId;
	private String content;
	private boolean status;
	private Date deadline;
	
	public Task() {}
	public Task(int taskId, int userId, String content, boolean status, Date deadline) {
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
}