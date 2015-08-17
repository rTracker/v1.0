package com.rtracker.database.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.rtracker.entries.ITrackable;

/**
 * ITrackable xml representation
 * 
 * @author dantonov
 *
 */
public class XMLTrackable {
	public static Element getElement(ITrackable item, Document doc) {

		Element itemElement = doc.createElement("item");
		XMLBase.idToXML(item, itemElement);

		return itemElement;
	}
}
