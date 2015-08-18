package com.rtracker.entries;

public class SimpleItem implements ITrackable {

	public SimpleItem(String name, String info) {
		id = simpleItemId++;
		this.name = name;
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

	private String name;
	private String info;;
	private long id;
	private static long simpleItemId = 100000000;
}
