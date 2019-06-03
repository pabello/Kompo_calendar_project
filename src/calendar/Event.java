package calendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Event {
	DateFormat dateFormat;
	Calendar creationTime;
	Calendar eventTime;
	Calendar cal;
	
	Event(int year, int month, int day, int hour, int minutes) {
		creationTime = Calendar.getInstance();
		cal.set(year, month, day, hour, minutes);
		eventTime = cal.getInstance();
	};
}

//		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss	EEE dd.MM.yyyy"); //hour:minute:sec	Tue 02.01.1978