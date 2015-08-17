package com.rtracker.database.xml;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.rtracker.reservation.IReservation;
import com.rtracker.reservation.ReservationPool;

/**
 * ReservationPool xml representation
 * 
 * @author dantonov
 *
 */
public class XMLReservationPool {
	public static Element getPool(ReservationPool pool, Document doc) {

		Element poolElement = doc.createElement("pool");
		XMLBase.idToXML(pool, poolElement);

		ArrayList<IReservation> reservations = pool.getReservations();
		for (IReservation reservation : reservations) {
			poolElement.appendChild(XMLReservation.getReservation(reservation, doc));
		}

		doc.appendChild(poolElement);
		return poolElement;
	}
}
