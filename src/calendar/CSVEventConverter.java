package calendar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Klasa obsługująca zapis i odczyt - format CSV
 *
 */
public class CSVEventConverter {
	/**
	 * Zapisuje Listę wydarzeń do pliku .csv
	 * @param eventList lista plików
	 * @throws Exception
	 */
	public static void CSVwrite(EventList eventList) throws Exception{
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("export.csv")));
			for(Event event : eventList) {
				StringBuffer line = new StringBuffer();
				line.append(event.getName());
				line.append(';');
				line.append(event.getPlace());
				line.append(';');
				line.append( new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(event.getEventTime()));
				line.append(';');
				if(event.getAlarmTime() != null) {
					line.append( new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(event.getAlarmTime()));
					line.append(';');					
				} else {
					line.append("null");
					line.append(";");
				}
				bw.write(line.toString());
				bw.newLine();
			}
			bw.flush();
			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new Exception();
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception();
		}
	}
	
	/**
	 * Odczytuje listę wydarzeń z pliku .csv
	 * @return oczytana list wydarzeń
	 * @throws Exception
	 */
	public static EventList CSVRead() throws Exception {
		EventList buff = new EventList();
		try {
			BufferedReader br = new BufferedReader(new FileReader("export.csv"));
			String line = "";
			while((line = br.readLine())!= null) {
				String[] params = line.split(";");
				Event event = new Event(params[0], params[1], params[2], params[3]);
				buff.add(event);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new Exception();
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception();
		}
		return buff;
	}
}
