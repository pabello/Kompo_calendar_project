package calendar;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Implementacja intefejsu MouseListener dla przycisków zapisu i odczytu do plików.
 */
public class TransferButtonListener implements MouseListener {
	final int buttonNumber;
	
	public TransferButtonListener(int buttonNumber) {
		 this.buttonNumber = buttonNumber;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(CalendarView.transferPopup != null)
			CalendarView.transferPopup.dispose();
		if(buttonNumber == 0) {
			try {
				CalendarView.eventList.addAll(CSVEventConverter.CSVRead());
				CalendarView.transferPopup = new TransferEffect("CSV transfer success");
			} catch(Exception ex) {
				CalendarView.transferPopup = new TransferEffect("CSV transfer error");
			}
		} else if(buttonNumber == 1) {
			try {
				CSVEventConverter.CSVwrite(CalendarView.eventList);
				CalendarView.transferPopup = new TransferEffect("CSV transfer success");
			} catch(Exception ex) {
				CalendarView.transferPopup = new TransferEffect("CSV transfer error");
			}
		} else if(buttonNumber == 2) {
			try {
				CalendarView.eventList.addAll(XMLEventConverter.readXML());
				CalendarView.transferPopup = new TransferEffect("XML transfer success");
			} catch(Exception ex){
				CalendarView.transferPopup = new TransferEffect("XML transfer error");
			}
		} else if(buttonNumber == 3) {
			try {
				XMLEventConverter.writeXML(CalendarView.eventList);
				CalendarView.transferPopup = new TransferEffect("XML transfer success");
			} catch(Exception ex){
				CalendarView.transferPopup = new TransferEffect("XML transfer error");
			}
		} else if(buttonNumber == 4) {
			JDBC jdbc = new JDBC();
			try {
				CalendarView.eventList.addAll(jdbc.read());
				CalendarView.transferPopup = new TransferEffect("Database transfer success");
			} catch(Exception ex){
				CalendarView.transferPopup = new TransferEffect("Database transfer error");
			}
		} else if(buttonNumber == 5) {
			JDBC jdbc = new JDBC();
			try {
				jdbc.create();
				jdbc.insert();
				CalendarView.transferPopup = new TransferEffect("Database transfer success");
			} catch(Exception ex){
				CalendarView.transferPopup = new TransferEffect("Database transfer error");
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
		e.getComponent().setCursor(cursor);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Cursor cursor = Cursor.getDefaultCursor();
		e.getComponent().setCursor(cursor);
	}

}
