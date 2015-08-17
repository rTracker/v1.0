package com.rtracker.test;

import javax.xml.transform.TransformerException;

import com.rtracker.database.xml.XMLBase;
import com.rtracker.entries.SimpleItem;
import com.rtracker.entries.User;
import com.rtracker.reservation.IReservation;
import com.rtracker.reservation.ReservationPool;
import com.rtracker.reservation.SimpleReservation;

/**
 * Test
 * 
 * @author dantonov
 *
 */
public class Test {

	public static void main(String[] args) {

		ReservationPool pool = new ReservationPool("TP1", "test pool 1");
		IReservation reservation1 = new SimpleReservation(new SimpleItem("Item1"));
		reservation1.addToQuiue(new User("den"));
		reservation1.addToQuiue(new User("ilya"));
		pool.addReservation(reservation1);
		IReservation reservation2 = new SimpleReservation(new SimpleItem("Item2"));
		reservation2.addToQuiue(new User("cat"));
		reservation2.addToQuiue(new User("car"));
		reservation2.addToQuiue(new User("table"));
		pool.addReservation(reservation2);

		XMLBase xmlBase = new XMLBase();
		try {
			xmlBase.saveToXml(pool);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
