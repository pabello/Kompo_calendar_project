package calendar;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Vector;

/**
 * Obsługuje zapis i odczyt wydarze� do i z pliku export.xml
 */
public class XMLEventConverter {

	/**
	 * Zapisuje wszystkie wydarzenia do pliku export.xml
	 * 
	 * @param events Lista wszystkich wydarzeń do zapisania
	 * @throws Exception
	 */
	public static void writeXML(Vector<Event> events) throws Exception {
		try {
			FileOutputStream fos = new FileOutputStream("export.xml");
			XMLEncoder fileEncoder = new XMLEncoder(fos);
			fileEncoder.writeObject(events);
			fileEncoder.flush();
			fileEncoder.close();
			fos.close();
		} catch (FileNotFoundException e) {
			throw new Exception("Could not save to export.xml\n Desc.: " + e.getMessage());
		} catch (IOException e) {
			throw new Exception("Could not save to export.xml\n Desc.: " + e.getMessage());
		} catch (Exception e) {
			throw new Exception("Could not save to export.xml\n Desc.: " + e.getMessage());
		}
	}

	/**
	 * Odczyt z pliku .xml
	 * @return odczytana lista
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Vector<Event> readXML() throws Exception {
		Vector<Event> events = new Vector<Event>();
		try {
			FileInputStream fis = new FileInputStream("export.xml");
			XMLDecoder fileDecoder = new XMLDecoder(fis);
			events = (Vector<Event>) fileDecoder.readObject();
			fileDecoder.close();
			fis.close();
		} catch (FileNotFoundException e) {
			throw new Exception("Could not read from export.xml\n Desc.: " + e.getMessage());
		} catch (IOException e) {
			throw new Exception("Could not read from export.xml\n Desc.: " + e.getMessage());
		} catch (Exception e) {
			throw new Exception("Could not read from export.xml\n Desc.: " + e.getMessage());
		}
		return events;
	}
}
