package calendar;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Vector;

/**
 * Obs�uguje zapis i odczyt wydarze� do i z pliku export.xml
 */
public class XMLEventConverter {

	/**
	 * Zapisuje wszystkie wydarzenia do pliku export.xml
	 * 
	 * @param events Lista wszystkich wydarze� do zapisania
	 * @throws Exception
	 */
	public static void writeXML(Vector<Event> events) throws Exception {
		FileOutputStream fos = new FileOutputStream("export.xml");
		XMLEncoder fileEncoder = new XMLEncoder(fos);
		fileEncoder.writeObject(events);
		fileEncoder.flush();
		fileEncoder.close();
		fos.close();
	}

	@SuppressWarnings("unchecked")
	public static Vector<Event> readXML() throws Exception {
		Vector<Event> events = new Vector<Event>();
		FileInputStream fis = new FileInputStream("export.xml");
		XMLDecoder fileDecoder = new XMLDecoder(fis);
		events = (Vector<Event>) fileDecoder.readObject();
		fileDecoder.close();
		fis.close();
		return events;
	}
}
