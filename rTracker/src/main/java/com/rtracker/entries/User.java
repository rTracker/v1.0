package com.rtracker.entries;

public class User implements IUser {

	public User(String name) {
		this.name = name;
		userId++;
		id = userId;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	private String name;
	private String info = "Just a user";
	private long id;
	private static long userId = 1000;
}
