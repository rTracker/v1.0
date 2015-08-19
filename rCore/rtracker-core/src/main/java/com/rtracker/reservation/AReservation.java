package com.rtracker.reservation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.rtracker.entries.ITrackable;
import com.rtracker.entries.IUser;

public abstract class AReservation implements IReservation {

	public AReservation(ITrackable item) {
		this.item = item;
		userQuieue = new ArrayList<>();
		reservation_id++;
		id = reservation_id;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public String getInfo() {
		return (name == null ? "reservation info" : name);

	}

	@Override
	public String getName() {
		return (info == null ? "reservation name" : info);
	}

	@Override
	public void setNameAndInfo(String name, String info) {
		this.name = name;
		this.info = info;
	}

	@Override
	public void addToQuiue(IUser iUser) {
		if (!userQuieue.contains(iUser)) {
			userQuieue.add(iUser);
		}
	}

	@Override
	public int getOrder(IUser iUser) {
		return userQuieue.indexOf(iUser);
	}

	@Override
	public void leaveQuieue(IUser iUser) {
		userQuieue.remove(iUser);

	}

	@Override
	public ITrackable getItem() {
		return item;
	}

	@Override
	public ArrayList<IUser> getUsers() {
		return new ArrayList<>(userQuieue);
	}

	@Override
	public Map<String, String> getAdditionalProperties() {
		Map<String, String> additionalProperties = new HashMap<>();
		additionalProperties.put("class", this.getClass().getSimpleName());
		return additionalProperties;
	}

	@Override
	public void setAdditionalProperties(Map<String, String> additionalProperties) {
	}

	private ArrayList<IUser> userQuieue;
	private ITrackable item;
	private static long reservation_id = 1000;
	private long id;
	private String name;
	private String info;
}
