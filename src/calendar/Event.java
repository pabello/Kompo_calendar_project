package calendar;


import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Event implements Comparable <Event>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3337576213019253810L;
	/**
	 * 
	 */
	private String name;
	private String place;
	private Date eventTime;

	public String getName() {
		return name;
	}

	public String getPlace() {
		return place;
	}

	public Date getEventTime() {
		return eventTime;
	}

	public Event() {
		super();
	}
	public Event(String name, int year, int month, int day, int hour, int minutes) {
		this.name = name;
		this.place = " ";
		Calendar c = Calendar.getInstance();
		c.set(year, month, day, hour, minutes, 0);
		eventTime = c.getTime();
	}
	
	public Event(String name, String place, int year, int month, int day, int hour, int minutes) {
		this.name = name;
		this.place = place;
		Calendar c = Calendar.getInstance();
		c.set(year, month, day, hour, minutes, 0);
		eventTime = c.getTime();
	}
	
	public Event(String name, String place, Date date) {
		this.name = name;
		this.place = place;
		this.eventTime = date;
	}
	
	public Event(String name, Date date) {
		this.name = name;
		this.eventTime = date;
	}
	
	public int getDayOfTheWeek() {
		Calendar c = Calendar.getInstance();
		c.setTime(eventTime);
		return c.get(Calendar.DAY_OF_WEEK);
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		
		buff.append("Event [name=" + name + ", place=");
		if(!place.isEmpty()) 
			buff.append(place);
		else 
			buff.append("Unknown");
		buff.append(", " + formatDate() + "]");
		
		return buff.toString();
	};
	
	public String formatDate() {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return format.format(eventTime);

	}

	@Override
	public int compareTo(Event arg0) {
		return this.getEventTime().compareTo(arg0.getEventTime());
	}

//	DEPRECATED xD
//	public String getHourAndMinutes(){
//		Calendar c = Calendar.getInstance();
//		c.setTime(eventTime);
//		return c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE);
//	}
	
}

//		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss	EEE dd.MM.yyyy"); //hour:minute:sec	Tue 02.01.1978
