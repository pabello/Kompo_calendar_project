package calendar;


import java.util.Collections;
import java.util.Date;
import java.util.Vector;


/**
 * 
 * Klasa Dziedzicząca po wektorze przechowujaca listę wydarzeń.
 */
public class EventList extends Vector<Event> {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Kostruktor tworzący pustą listę wydarzeń
	 */
	public EventList() {
		 super();
	}

	/**
	 * Metoda konwertująca Listę do postaci tekstowej
	 */
	public String toString() {
		String buff="";
		int i = 0;
		for(Event e : this) {
			i++;
			buff+=i+". "+e.toString()+"\n";
		}
		return buff;
	}
	
	/**
	 * Metoda sortująca listę
	 */
	public void sort() {
		Collections.sort(this);
	}
	
	/**
	 * Metoda szukająca w liście wydarzeń zawierających w sobie łańćuch pattern.
	 * @param patern łańsuch znaków mający zawierać sie w szukanych eventach.
	 * @return lista wydarzeń mających w sobie pattern.
	 */
	public EventList searchEvent(String patern) {
		EventList buff = new EventList();
		for(Event event : this) {
			if(event.toString().contains(patern)) {
				buff.add(event);
			}
		}
		return buff;
		
	}
	
	/**
	 * Metoda usuwająca z listy wszystkie wydarzenia z przed podaniej daty.
	 * @param date data sprzed ktorej metoda ma usunać wydarzenia.
	 */
	public void removeEventsBeforeDate(Date date) {
		for(Event event:this) {
			if(date.after(event.getEventTime())) {
				this.remove(event);
			}
		}
	}
}
