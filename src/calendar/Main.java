package calendar;

import javax.swing.SwingUtilities;
import calendar.EventView;

public class Main {
	
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                EventView.createAndShowGUI();
            }
    	
        });
    	
    }
    
  
}

