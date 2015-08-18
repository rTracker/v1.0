package com.rtracker.database.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.rtracker.entries.ITrackable;
import com.rtracker.entries.SimpleItem;

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

	public static final String trackableTag = "item";

	public static ITrackable parceTrackable(Node trackableNode) {

		ITrackable item = null;
		if (trackableNode.getNodeType() == Node.ELEMENT_NODE) {
			Element trackableElement = (Element) trackableNode;
			String itemName = XMLBase.getIdNameFromXML(trackableElement);
			String itemInfo = XMLBase.getIdInfoFromXML(trackableElement);
			item = new SimpleItem(itemName, itemInfo);
		}
		return item;
	}
}
