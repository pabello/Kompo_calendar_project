package calendar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

import javax.swing.JOptionPane;

public class EventReminder extends TimerTask {
	String eventName;
	Date eventTime;
	static final SimpleDateFormat format = new SimpleDateFormat("HH:mm");
	
	public EventReminder(String eventName, Date eventTime) {
		this.eventName = eventName;
		this.eventTime = eventTime;
	}
	
	@Override
	public void run() {
		JOptionPane.showConfirmDialog(null, "\""+eventName+"\" event is starting at "+format.format(eventTime), "Notification", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
	}

}
