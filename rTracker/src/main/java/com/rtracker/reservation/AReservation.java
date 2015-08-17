package com.rtracker.reservation;

import java.util.ArrayList;

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
		return "reservation info";

	}

	@Override
	public String getName() {
		return "reservation name";
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

	private ArrayList<IUser> userQuieue;
	private ITrackable item;
	private static long reservation_id = 1000;
	private long id;
}
