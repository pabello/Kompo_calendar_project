package calendar;

import java.util.Calendar;

import javax.swing.SwingUtilities;


public class Main {

	private static String username = "mordekaiser";
	static boolean gui = true;
	
    public static void main(String[] args) {
    	EventList eventList = new EventList();
    	for(String arg : args)
    		if(arg.equals("--nogui")) gui = false;
    	if(gui) {
    		SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                	new CalendarView(Calendar.getInstance(), eventList);
                }
            });
    	}
    	else new CUI(eventList);	
    }

    public static String getUsername() {
    	return username;
    }
    
    public static void setUsername(String in) {
    	username = in;
    }  
}

