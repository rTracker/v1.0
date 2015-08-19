package com.rtracker.database.xml;

import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

	public static Configuration parseConfiguration(Document doc) {
		Configuration configuration = Configuration.getInstance();
		NodeList configurationNodeList = doc.getElementsByTagName(configurationTag);
		if (configurationNodeList.getLength() == 1) {
			Node configurationNode = configurationNodeList.item(0);
			if (configurationNode.getNodeType() == Node.ELEMENT_NODE) {
				Element configurationElement = (Element) configurationNode;
				NodeList usersList = configurationElement.getElementsByTagName(XMLUser.userTag);
				for (int i = 0; i < usersList.getLength(); i++) {
					Node userNode = usersList.item(i);
					if (userNode.getNodeType() == Node.ELEMENT_NODE) {
						Element userElement = (Element) userNode;
						String password = userElement.getAttribute("password");
						IUser user = XMLUser.parceUser(userElement);
						configuration.loadUser(user, password);
					} else {
						// TODO:
					}
				}
			} else {
				// TODO:
			}
		} else {
			// TODO:
		}
		return configuration;
	}

	public static final String configurationTag = "configuration";
}
