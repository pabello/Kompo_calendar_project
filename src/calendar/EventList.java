package calendar;


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
		for(Event e : this) {
			buff+=e.toString()+"\n";
		}
		return buff;
	}
}
