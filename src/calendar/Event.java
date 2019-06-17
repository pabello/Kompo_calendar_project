package calendar;


import java.util.Calendar;
import java.util.Date;

public class Event {
	String name;
	String place;
	Date creationTime;
	Date eventTime;

	Event(String name, int year, int month, int day, int hour, int minutes) {
		this.name = name;
		creationTime  = new Date();
		Calendar c = Calendar.getInstance();
		c.set(year, month-1, day, hour, minutes, 0);
		eventTime = c.getTime();
	}
	Event(String name, String place, int year, int month, int day, int hour, int minutes) {
		this.name = name;
		this.place = place;
		creationTime  = new Date();
		Calendar c = Calendar.getInstance();
		c.set(year, month-1, day, hour, minutes, 0);
		eventTime = c.getTime();
	}
	
	public int getDayOfTheWeek() {
		Calendar c = Calendar.getInstance();
		c.setTime(eventTime);
		return c.get(Calendar.DAY_OF_WEEK);
	}

	@Override
	public String toString() {
		return "Event [name=" + name + ", eventTime=" + eventTime.toString() + "]";
	};
	
	
	
	
	 
	
}

//		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss	EEE dd.MM.yyyy"); //hour:minute:sec	Tue 02.01.1978