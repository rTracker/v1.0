package com.rtracker.database.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.rtracker.entries.IUser;

/**
 * IUser xml representation
 * 
 * @author dantonov
 *
 */
public class XMLUser {
	public static Element getUser(IUser user, Document doc) {

		Element userElement = doc.createElement("user");
		XMLBase.idToXML(user, userElement);

		return userElement;
	}
}
