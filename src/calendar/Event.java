package calendar;


import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 
 * Klasa reprezentująca wydarzenie
 *
 */
public class Event implements Comparable <Event>, Serializable{

	private static final long serialVersionUID = 3337576213019253810L;
	private String name;
	private String place;
	private Date eventTime;

	/**
	 * Kostruktor bezparametrowy niezbędny do zapisu w formacie .xml
	 */
	public Event() {
		super();
	}
	
	/**
	 * Konstruktor twąrzący wydarzenie bez podaniego miejsca, data podana jako liczby całkowite
	 * @param name Nazwa wydarzenia
	 * @param year Rok w którym odbywa się wydarzenie
	 * @param month Miesiąc w którym odbywa się wydarzenie
	 * @param day Dzień w miesiącu w którym odbywa się wydarzenie
	 * @param hour Godzina o której rozpoczyna sie wydarzenie 
	 * @param minutes Minuta w ktorej rozpoczyna się wydarzenie
	 */
	public Event(String name, int year, int month, int day, int hour, int minutes) {
		this.name = name;
		this.place = " ";
		Calendar c = Calendar.getInstance();
		c.set(year, month, day, hour, minutes, 0);
		eventTime = c.getTime();
	}
	
	/**
	 * Konstruktor Konstruktor twąrzący wydarzenie z podanym miejscem, data podana jako liczby całkowite
	 * @param name Nazwa wydarznia
	 * @param place Miejsce wydarzenia
	 * @param year Rok w którym odbywa się wydarzenie
	 * @param month Miesiąc w którym odbywa się wydarzenie
	 * @param day Dzień w miesiącu w którym odbywa się wydarzenie
	 * @param hour Godzina o której rozpoczyna sie wydarzenie 
	 * @param minutes Minuta w ktorej rozpoczyna się wydarzenie
	 */
	public Event(String name, String place, int year, int month, int day, int hour, int minutes) {
		this.name = name;
		this.place = place;
		Calendar c = Calendar.getInstance();
		c.set(year, month, day, hour, minutes, 0);
		eventTime = c.getTime();
	}
	/**
	 * Konstruktor tworzący wydarzenie z podanym jescem, data podana jako obiekt.
	 * @param name Nazwa wydarzenia
	 * @param place miejśce wydarzenia
	 * @param date Data wydarzenia
	 */
	public Event(String name, String place, Date date) {
		this.name = name;
		this.place = place;
		this.eventTime = date;
	}
	
	/**
	 * Konstruktor tworzący wydarzenie bez podania miejsca, data podana jako obiekt
	 * @param name nazwa wydarzenia	
	 * @param date Data wydarzenia
	 */
	public Event(String name, Date date) {
		this.name = name;
		this.eventTime = date;
	}

	/**
	 * Konstruktor tworzący wydarzenie z podanym miejscem , data podana jako String w formacie "dd-MM-yyyy HH:mm:ss"
	 * @param name Nazwa wydarzenia
	 * @param place Miejsce wydarzenia
	 * @param date Data wydarzenia
	 */
	public Event(String name, String place, String date) {
		this.name = name;
		this.place = place;
		try {
			this.eventTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Getter na numer dnia w tygodniu w którym odbywa sie wydarzenie
	 * @return na numer dnia w tygodniu w którym odbywa sie wydarzenie
	 */
	public int getDayOfTheWeek() {
		Calendar c = Calendar.getInstance();
		c.setTime(eventTime);
		return c.get(Calendar.DAY_OF_WEEK);
	}
	
	/**
	 * Zwraca nazwę wydarzenia	
	 * @return nazwę wydarzenia
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Zwraca miejsce wydarzenia
	 * @return miejsce wydarzenia
	 */
	public String getPlace() {
		return place;
	}
	
	/**
	 * Zwraca obiekt daty wydarzenia
	 * @return data wydarzenia
	 */
	public Date getEventTime() {
		return eventTime;
	}
	
	/**
	 * 
	 * @return
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Setter nazwy
	 * @param name nazwa do sutawienia
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Setter na miejsce wydarzenia
	 * @param place miejsce do ustawienia
	 */
	public void setPlace(String place) {
		this.place = place;
	}
	
	/**
	 * Setter na datę wydarzenia
	 * @param eventTime data do ustawienia
	 */
	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

	
	
	@Override
	/**
	 * Konwesja wydarzenia na String
	 * @return obiekt reprezentowany jako string
	 */
	public String toString() {
		StringBuffer buff = new StringBuffer();
		
		buff.append("[What]: " + name);
		if(!place.isEmpty()) { 
			buff.append("   [Where]: ");
			buff.append(place);
		}
		else 
			buff.append("Unknown");
		buff.append("   [When]: " + formatDate());
		
		return buff.toString();
	};
	
	/**
	 * Formatuje datę do formatu "yyyy-MM-dd HH:mm"
	 * @return data jako string w formacie "yyyy-MM-dd HH:mm"
	 */
	public String formatDate() {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return format.format(eventTime);

	}

	/**
	 * Porownuje event do arg0
	 * @param arg0 wydarzenie do porównania
	 * @return liczba dodatnia jezeli arg0 jest poźniej niz wydarzenie
	 * 		   0  jezeli wydarzenia odbywja sie w tym sammym momencie
	 * 		   liczba ujemna jeżeli arg0 odbywa sie przed wydarzeniem
	 */
	@Override
	public int compareTo(Event arg0) {
		return this.getEventTime().compareTo(arg0.getEventTime());
	}
}
