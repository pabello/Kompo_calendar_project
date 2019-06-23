package calendar;

import java.util.Scanner;

public class CUI {
	private EventList eventList;
	public CUI(EventList eventList) {
		this.eventList = eventList;
		menu();
	}
	
	public void printMenu() {
		System.out.println("Calendar:");
		System.out.println("a - add event");
		System.out.println("r - remove event");
		System.out.println("p - prints out list of events");
		
		
		System.out.println("q - quits the program");
		System.out.println("Enter your command:");
	}

	public void menu() {
		while(true) {
			printMenu();
			Scanner scanner =  new Scanner(System.in);
			String c = scanner.next("[a-z]");
			switch(c) {
			case "a":
				eventList.add(this.addEvent());
				break;
			case "r":
				System.out.println(eventList.toString());
				System.out.println("Remove event at index:");
				Scanner s = new Scanner(System.in);
				eventList.removeElementAt(s.nextInt()-1);
				break;
			case "p":
				System.out.println(eventList.toString());
				break;
			case "i":
				try {
					menuImport();
				} catch (Exception e1) {
					e1.printStackTrace();
					scanner.close();
				}
				break;
			case "e":
				try {
					menuExport();
				} catch (Exception e) {
					e.printStackTrace();
					scanner.close();
				}
				break;
			case "q":
				scanner.close();
				return;
				
			default:
				System.out.println("Wrong choise");	
			}
		}
	}

	private void menuExport() throws Exception {
		
		Scanner scanner =  new Scanner(System.in);
		String c = scanner.next("[a-z]");
		switch(c) {
		case "c":
			CSVEventConverter.CSVwrite(eventList);
			break;
		case "x":
			XMLEventConverter.writeXML(eventList);
			break;
		default:
			System.out.println("Wrong Choise");
		}
		scanner.close();
	}

	private void menuImport() throws Exception {
		Scanner scanner =  new Scanner(System.in);
		String c = scanner.next("[a-z]");
		switch(c) {
		case "c":
			CSVEventConverter.CSVRead();
			break;
		case "x":
			XMLEventConverter.readXML();
			break;
		default:
			System.out.println("Wrong Choise");
		}
		scanner.close();
	}

	private Event addEvent() {
		System.out.println("Write name:");
		
		Scanner scanner = new Scanner(System.in);
		String name = scanner.next();
		//scanner.close();
		
		System.out.println("Write place:");
		scanner = new Scanner(System.in);
		String place = scanner.next();

		
		System.out.println("Write year:");
		scanner = new Scanner(System.in);
		int year = scanner.nextInt();
		
		System.out.println("Write month:");
		scanner = new Scanner(System.in);
		int month = scanner.nextInt();

		
		System.out.println("Write day:");
		scanner = new Scanner(System.in);
		int day = scanner.nextInt();
		
		System.out.println("Write hour:");
		scanner = new Scanner(System.in);
		int hour = scanner.nextInt();
		
		System.out.println("Write minutes:");
		scanner = new Scanner(System.in);
		int minute = scanner.nextInt();
		
		return new Event(name, place, year, month, day, hour, minute);
	}
}
