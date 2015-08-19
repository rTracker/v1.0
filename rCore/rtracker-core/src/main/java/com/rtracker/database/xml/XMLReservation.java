package com.rtracker.database.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.rtracker.entries.ITrackable;
import com.rtracker.entries.IUser;
import com.rtracker.reservation.IReservation;
import com.rtracker.reservation.SimpleReservation;
import com.rtracker.reservation.SimultaneousReservation;

/**
 * IReservation xml representation
 * 
 * @author dantonov
 *
 */
public class XMLReservation {
	public static Element getReservation(IReservation reservation, Document doc) {

		Element reservationElement = doc.createElement(reservaionTag);
		XMLBase.idToXML(reservation, reservationElement);

		Map<String, String> additionalProperties = reservation.getAdditionalProperties();
		if (additionalProperties != null) {
			for (Entry<String, String> additionalProperty : additionalProperties.entrySet()) {
				String key = additionalProperty.getKey();
				String value = additionalProperty.getValue();
				if (key != null && value != null) {
					reservationElement.setAttribute(key, value);
				}
			}
		}

		Element item = XMLTrackable.getElement(reservation.getItem(), doc);
		reservationElement.appendChild(item);

		ArrayList<IUser> users = reservation.getUsers();
		for (IUser user : users) {
			reservationElement.appendChild(XMLUser.getUser(user, doc));
		}

		return reservationElement;
	}

	public static final String reservaionTag = "reservation";

	public static IReservation parseReservation(Node reservationNode) {
		IReservation reservation = null;
		if (reservationNode.getNodeType() == Node.ELEMENT_NODE) {
			Element reservationElement = (Element) reservationNode;
			String reservationName = XMLBase.getIdNameFromXML(reservationElement);
			String reservationInfo = XMLBase.getIdInfoFromXML(reservationElement);
			NamedNodeMap reservationElementNodeMap = reservationElement.getAttributes();
			Map<String, String> attributes = new HashMap<>();
			for (int i = 0; i < reservationElementNodeMap.getLength(); i++) {
				Node node = reservationElementNodeMap.item(i);
				if (node.getNodeType() == Node.ATTRIBUTE_NODE) {
					Attr attr = (Attr) node;
					attributes.put(attr.getName(), attr.getValue());
				} else {
					// TODO:
				}
			}
			NodeList trackableNodeList = reservationElement.getElementsByTagName(XMLTrackable.trackableTag);
			ITrackable item = null;
			if (trackableNodeList.getLength() == 1) {
				Node trackableNode = trackableNodeList.item(0);
				item = XMLTrackable.parceTrackable(trackableNode);
			} else {
				// TODO:
			}
			String className = attributes.get("class");
			switch (className) {
			case "SimpleReservation":
				reservation = new SimpleReservation(item);
				break;
			case "SimultaneousReservation":
				reservation = new SimultaneousReservation(item, Integer.valueOf(attributes.get("count")));
				break;
			default:
				// TODO:
				break;
			}

			reservation.setNameAndInfo(reservationName, reservationInfo);

			NodeList userNodeList = reservationElement.getElementsByTagName(XMLUser.userTag);
			for (int i = 0; i < userNodeList.getLength(); i++) {
				IUser user = XMLUser.parceUser(userNodeList.item(i));
				reservation.addToQuiue(user);
			}
		} else {
			// TODO:
		}
		return reservation;
	}
}
