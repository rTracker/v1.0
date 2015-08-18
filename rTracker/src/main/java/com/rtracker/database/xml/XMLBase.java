package com.rtracker.database.xml;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.rtracker.entries.Id;
import com.rtracker.reservation.ReservationPool;

/**
 * Save data in xml format
 * 
 * @author dantonov
 *
 */
public class XMLBase {

	private Document createDocumentForSave() {
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

	private Document createDocumentForParse(String fileName) {
		File fXmlFile = new File(directory + FS + fileName + XML);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void saveToXml(ReservationPool reservationPool, String filename) throws TransformerException {
		Document doc = createDocumentForSave();
		XMLReservationPool.getPool(reservationPool, doc);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");

		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(directory + FS + filename + XML));

		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);
	}

	public ReservationPool parseXml(String fileName) throws ParserConfigurationException, SAXException, IOException {
		Document doc = createDocumentForParse(fileName);
		ReservationPool reservationPool = XMLReservationPool.parsePool(doc);
		return reservationPool;
	}

	public static void idToXML(Id idItem, Element element) {
		element.setAttribute("name", idItem.getName());
		element.setAttribute("info", idItem.getInfo());
	}

	public static String getIdNameFromXML(Element element) {
		return element.getAttribute("name");
	}

	public static Long getIdIdFromXML(Element element) {
		return Long.valueOf(element.getAttribute("id"));
	}

	public static String getIdInfoFromXML(Element element) {
		return element.getAttribute("info");
	}

	private static final String FS = FileSystems.getDefault().getSeparator();
	private static final String directory = "C:" + FS + "projects" + FS + "v1.0" + FS + "data";
	private static final String XML = ".xml";
}
