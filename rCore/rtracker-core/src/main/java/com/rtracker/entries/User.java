package com.rtracker.entries;

public class User implements IUser {

	public User(String name, String info) {
		this.name = name;
		userId++;
		id = userId;
		this.info = info;
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
	private String info;
	private long id;
	private static long userId = 1000;
}
