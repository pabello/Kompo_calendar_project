package calendar;


import java.util.Collections;
import java.util.Date;
import java.util.Vector;

public class EventList extends Vector<Event> {
	private static final long serialVersionUID = 1L;
	
	public EventList() {
		 super();
	}
	
	public Vector<Event> getAll(){
		return events;
	}
	
	public int getSize() {
		return this.events.size();
	}
	
	public String toString() {
		String buff="";
		int i = 0;
		for(Event e : this) {
			i++;
			buff+=i+". "+e.toString()+"\n";
		}
		return buff;
	}
	
	public void sort() {
		Collections.sort(this);
	}
	
	public EventList searchEvent(String patern) {
		EventList buff = new EventList();
		for(Event event : this) {
			if(event.toString().contains(patern)) {
				buff.add(event);
			}
		}
		return buff;
		
	}
	
	public void removeEventsBeforeDate(Date date) {
		for(Event event:this) {
			if(date.after(event.getEventTime())) {
				this.remove(event);
			}
		}
	}
}
