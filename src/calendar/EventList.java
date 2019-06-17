package calendar;


import java.util.Vector;

public class EventList {
	Vector<Event> events;
	
	public EventList() {
		this.events = new Vector<Event>();
	}
	
	public Event get(int i) {
		return events.get(i);
	}
	
	public void add(Event arg) {
		events.add(arg);
	}
	
	public Vector<Event> getAll(){
		return events;
	}
	
	public int getSize() {
		return this.events.size();
	}
	
	public String toString() {
		String buff="";
		for(Event e:this.events) {
			buff+=e.toString()+"\n";
		}
		return buff;
	}
}
