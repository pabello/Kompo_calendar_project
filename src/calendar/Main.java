package calendar;

import java.util.Calendar;
import java.util.Date;

import javax.swing.SwingUtilities;


public class Main {
	static boolean gui = true;
    public static void main(String[] args) {
    	EventList eventList = new EventList();
    	/*for(String arg : args)
    		if(arg.equals("--nogui")) gui = false;
    	if(gui) {
    		SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                	CalendarView TheCalendar = new CalendarView(Calendar.getInstance(), eventList);
                }
            });
    	}
    	else System.out.println("Invoke console ui");*/
    	eventList.add(new Event("aaaa", "", 1, 2, 3, 4, 5));
    	eventList.add(new Event("aaaa", "", 1, 3, 3, 4, 5));
    	eventList.add(new Event("aaaa", "", 1, 4, 3, 4, 5));
    	System.out.println(eventList.toString());
    	try {
			XMLEventConverter.writeXML(eventList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
			eventList = (EventList) XMLEventConverter.readXML();
			System.out.println(eventList.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//    	JDBC bazka = new JDBC("mordekaiser");
//    	EventList lista = new EventList();
//    	try {
//    		lista = bazka.read();
//    		System.out.println(lista.toString());
//    	} catch (Exception e) {
//    		System.out.println("No c�, jednak si� nie uda�o xd");
//    	}
//    	lista.add(new Event("Bede siedzial po nocach i pisal jakies gunwa", "W domu przed kompem", 2019, 6, 16, 23, 39));
//    	
//    	try {
////    		bazka.create();
//    		bazka.insert(lista);
//    	} catch (Exception e) {
//    		System.out.println("No co� se posz�o nie tak xd");
//    	}
//    	
//    	try {
//    		lista = bazka.read();
//    		System.out.println(lista.toString());
//    	} catch (Exception e) {
//    		System.out.println("No c�, jednak si� nie uda�o xd");
//    	}
    	
    }
    
  
}

