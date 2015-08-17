package com.rtracker.reservation;

import com.rtracker.entries.ITrackable;

public class SimultaneousReservation extends AReservation {

	public SimultaneousReservation(ITrackable item, int count) {
		super(item);
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	private int count;
}
