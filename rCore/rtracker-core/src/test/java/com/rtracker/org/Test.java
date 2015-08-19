package com.rtracker.org;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import com.rtracker.database.xml.XMLBase;
import com.rtracker.entries.SimpleItem;
import com.rtracker.entries.User;
import com.rtracker.general.Configuration;
import com.rtracker.reservation.IReservation;
import com.rtracker.reservation.ReservationPool;
import com.rtracker.reservation.SimpleReservation;
import com.rtracker.reservation.SimultaneousReservation;

/**
 * Test
 * 
 * @author dantonov
 *
 */
public class Test {

	public static void main(String[] args) {
		gereratorExample();
		readExample();
		generateConfiguration();
		readConfiguration();
	}

	private static void readConfiguration() {
		XMLBase xmlBase = new XMLBase();
		try {
			Configuration configuration = xmlBase.parseConfiguration("configuration");
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void generateConfiguration() {
		Configuration conf = Configuration.getInstance();
		conf.addUser(new User("den", "1"), "1234");
		conf.addUser(new User("nata", "2"), "qwer");
		XMLBase xmlBase = new XMLBase();
		try {
			xmlBase.saveToXml(conf, "configuration");
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void gereratorExample() {

		ReservationPool pool = new ReservationPool("TP1", "test pool 1");
		IReservation reservation1 = new SimpleReservation(new SimpleItem("Item1", "info1"));
		reservation1.addToQuiue(new User("den", "a"));
		reservation1.addToQuiue(new User("ilya", "a"));
		pool.addReservation(reservation1);
		IReservation reservation2 = new SimultaneousReservation(new SimpleItem("Item2", "info2"), 66);
		reservation2.addToQuiue(new User("cat", "a"));
		reservation2.addToQuiue(new User("car", "a"));
		reservation2.addToQuiue(new User("table", "a"));
		pool.addReservation(reservation2);

		XMLBase xmlBase = new XMLBase();
		try {
			xmlBase.saveToXml(pool, "example");
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void readExample() {
		XMLBase xmlBase = new XMLBase();
		try {
			ReservationPool pool = xmlBase.parseXml("example");
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
