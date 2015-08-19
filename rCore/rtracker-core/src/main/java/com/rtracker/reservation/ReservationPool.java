package com.rtracker.reservation;

import java.util.ArrayList;

import com.rtracker.entries.Id;

/**
 * Reservations container
 * 
 * @author dantonov
 *
 */
public class ReservationPool implements Id {

	public ReservationPool(String name, String info) {
		id = reservationPoolId++;
		this.name = name;
		this.info = info;
		reservationList = new ArrayList<>();
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

	public void addReservation(IReservation reservation) {
		reservationList.add(reservation);
	}

	public ArrayList<IReservation> getReservations() {
		return reservationList;
	}

	private static long reservationPoolId = 10000000;
	private long id;
	private String name;
	private String info;

	private ArrayList<IReservation> reservationList;
}
