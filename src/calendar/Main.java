package calendar;

import java.util.Calendar;
import java.util.Timer;

import javax.swing.SwingUtilities;

/**
 * 
 * Klasa służaca do uruchomienia programu.
 *
 */
public class Main {

	private static String username = "mordekaiser";
	static Timer timer = new Timer("calendar_timer", true); 
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

