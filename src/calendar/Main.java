package calendar;

import java.util.Calendar;
import java.util.Date;

import javax.swing.SwingUtilities;
import calendar.AddEventView;

public class Main {
	
    public static void main(String[] args) {
    	
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
//            	DayView d = new DayView(new EventList(), new Date());
//                AddEventView a = new AddEventView(new EventList(), new Date());
            	CalendarView TheCalendar = new CalendarView(Calendar.getInstance()); Gladko jak po pupci niemowlaka ( ͡° ͜ʖ ͡°)
            }
    	
        });
    	
    }
    
  
}

