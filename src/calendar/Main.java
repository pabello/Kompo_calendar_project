package calendar;

import javax.swing.SwingUtilities;
import calendar.AddEventView;

public class Main {
	
    public static void main(String[] args) {
    	
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
//                AddEventView a = new AddEventView();
            	CalendarView TheCalendar = new CalendarView();
            }
    	
        });
    	
    }
    
  
}

