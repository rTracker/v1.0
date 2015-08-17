package com.rtracker.entries;

public class SimpleItem implements ITrackable {

	public SimpleItem(String name) {
		id = simpleItemId++;
		this.name = name;
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

	private String name;
	private String info = "Just a trackable item";
	private long id;
	private static long simpleItemId = 100000000;
}
