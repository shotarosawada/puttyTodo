package model;

import java.io.Serializable;

public class User implements Serializable {
	private int id;
	private String name;
	
	public User() {}
	public User(int id, String name) {
		this.id = id;
		this.name = name;
		
	}
	public int getId() { return id; }
	public String getName() { return name; }
}