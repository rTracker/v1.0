package com.rtracker.database.xml;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.rtracker.entries.IUser;
import com.rtracker.reservation.IReservation;

/**
 * IReservation xml representation
 * 
 * @author dantonov
 *
 */
public class XMLReservation {
	public static Element getReservation(IReservation reservation, Document doc) {

		Element reservationElement = doc.createElement("reservation");
		XMLBase.idToXML(reservation, reservationElement);

		Element item = XMLTrackable.getElement(reservation.getItem(), doc);
		reservationElement.appendChild(item);

		ArrayList<IUser> users = reservation.getUsers();
		for (IUser user : users) {
			reservationElement.appendChild(XMLUser.getUser(user, doc));
		}

		return reservationElement;
	}
}
