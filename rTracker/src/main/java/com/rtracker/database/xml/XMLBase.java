package com.rtracker.database.xml;

import java.io.File;
import java.nio.file.FileSystems;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.rtracker.entries.Id;
import com.rtracker.reservation.ReservationPool;

/**
 * Save data in xml format
 * 
 * @author dantonov
 *
 */
public class XMLBase {

	private Document createDocument() {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = docFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// root elements
		Document doc = docBuilder.newDocument();
		return doc;
	}

	public void saveToXml(ReservationPool reservationPool) throws TransformerException {
		Document doc = createDocument();
		XMLReservationPool.getPool(reservationPool, doc);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(directory + FS + "example" + XML));

		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);
	}

	public ReservationPool parseXml(String file) {
		return null;
	}

	public static void idToXML(Id idItem, Element element) {
		element.setAttribute("name", idItem.getName());
		element.setAttribute("id", String.valueOf(idItem.getId()));
		element.setAttribute("info", idItem.getInfo());
	}

	private static final String FS = FileSystems.getDefault().getSeparator();
	private static final String directory = "C:" + FS + "projects" + FS + "v1.0" + FS + "data";
	private static final String XML = ".xml";
}
