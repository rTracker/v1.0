package com.rtracker.database.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.rtracker.entries.IUser;
import com.rtracker.entries.User;

/**
 * IUser xml representation
 * 
 * @author dantonov
 *
 */
public class XMLUser {
	public static Element getUser(IUser user, Document doc) {

		Element userElement = doc.createElement(userTag);
		XMLBase.idToXML(user, userElement);

		return userElement;
	}

	public static IUser parceUser(Node userNode) {

		IUser user = null;
		if (userNode.getNodeType() == Node.ELEMENT_NODE) {
			Element userElement = (Element) userNode;
			String itemName = XMLBase.getIdNameFromXML(userElement);
			String itemInfo = XMLBase.getIdInfoFromXML(userElement);
			user = new User(itemName, itemInfo);
		} else {
			// TODO:
		}
		return user;
	}

	public static final String userTag = "user";
}
