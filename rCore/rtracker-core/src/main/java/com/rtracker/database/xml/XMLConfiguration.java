package com.rtracker.database.xml;

import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.rtracker.entries.IUser;
import com.rtracker.general.Configuration;

/**
 * Configuration xml representation
 * 
 * @author dantonov
 *
 */
public class XMLConfiguration {

	public static Element getConfiguration(Configuration configuration, Document doc) {
		Element configurationElement = doc.createElement(configurationTag);
		Map<IUser, String> users = configuration.getUsers();
		for (Entry<IUser, String> userEntry : users.entrySet()) {
			Element userElement = XMLUser.getUser(userEntry.getKey(), doc);
			userElement.setAttribute("password", userEntry.getValue());
			configurationElement.appendChild(userElement);
		}
		doc.appendChild(configurationElement);
		return configurationElement;
	}

	public static final String configurationTag = "configuration";
}
