package com.rtracker.reservation;

import java.util.ArrayList;
import java.util.Map;

import com.rtracker.entries.ITrackable;
import com.rtracker.entries.IUser;
import com.rtracker.entries.Id;

/**
 * Items reservation
 * 
 * @author dantonov
 *
 */
public interface IReservation extends Id {
	void addToQuiue(IUser iUser);

	int getOrder(IUser iUser);

	void leaveQuieue(IUser iUser);

	ITrackable getItem();

	ArrayList<IUser> getUsers();

	Map<String, String> getAdditionalProperties();

	void setAdditionalProperties(Map<String, String> additionalProperties);

	void setNameAndInfo(String name, String info);
}
