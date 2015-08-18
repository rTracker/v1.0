package com.rtracker.database.xml;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

		Element poolElement = doc.createElement(poolTag);
		XMLBase.idToXML(pool, poolElement);

		ArrayList<IReservation> reservations = pool.getReservations();
		for (IReservation reservation : reservations) {
			poolElement.appendChild(XMLReservation.getReservation(reservation, doc));
		}

		doc.appendChild(poolElement);
		return poolElement;
	}

	public static ReservationPool parsePool(Document doc) {
		ReservationPool reservationPool = null;
		NodeList poolNodeList = doc.getElementsByTagName(poolTag);
		if (poolNodeList.getLength() == 1) {
			Node poolNode = poolNodeList.item(0);
			if (poolNode.getNodeType() == Node.ELEMENT_NODE) {
				Element poolElement = (Element) poolNode;
				String poolName = XMLBase.getIdNameFromXML(poolElement);
				String poolInfo = XMLBase.getIdInfoFromXML(poolElement);
				reservationPool = new ReservationPool(poolName, poolInfo);
				NodeList reservationNodeList = poolElement.getElementsByTagName(XMLReservation.reservaionTag);
				for (int i = 0; i < reservationNodeList.getLength(); i++) {
					IReservation reservation = XMLReservation.parseReservation(reservationNodeList.item(i));
					reservationPool.addReservation(reservation);
				}
			} else {
				// TODO:
			}
		} else {
			// TODO:
		}
		return reservationPool;
	}

	public static final String poolTag = "pool";
}
