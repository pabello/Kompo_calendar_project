package calendar;

import java.util.Date;

import javax.swing.SwingUtilities;


public class Main {
	
    public static void main(String[] args) {
    	
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	EventList l = new EventList();
            	l.add(new Event("a", "a", 2019, 6, 25, 4, 5));
            	System.out.println(l.toString());
            	DayView d = new DayView();
                //AddEventView a = new AddEventView(new EventList(), new Date());
            	//CalendarView TheCalendar = new CalendarView(Calendar.getInstance());
            }
    	
        });
    	
    }
    
  
}

