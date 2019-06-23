package calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Klasa tworząca okno do dodawania Wydarzeń do listy
 *
 */
public class AddEventView extends JFrame{

	private static final long serialVersionUID = 1L;
	private ButtonPanel btnPanel;
	private InputPanel inptPanel;
	private EventList events;
	private JPanel master, msgPanel;
	private JLabel msgLabel;

	/**
	 * Kontrutor tworzący okno
	 * @param events Lista wydarzeń do której bedzie dodawane wydarzenie
	 * @param date Data wydarzenia która bedzie dodawana
	 */
	public AddEventView(EventList  events, Date date) {
			super("Add Event");
			this.events = events;
	        master = new JPanel();
	        master.setLayout(new GridLayout(3, 1, 0, 5));
	        msgPanel = new JPanel();
	        msgLabel = new JLabel(" ");
	        msgPanel.add(msgLabel);
	        master.add(msgPanel);
	        inptPanel = new InputPanel(date);
	        master.add(inptPanel);
	        btnPanel = new ButtonPanel();
	        master.add(btnPanel);
	        btnPanel.getAddBtn().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						if(!inptPanel.getNameInput().equals("")) {
							if(inptPanel.getPlaceInput().equals("")) {
								AddEventView.this.events.add( new Event(
									inptPanel.getNameInput(),
									inptPanel.getYearInput(),
									inptPanel.getMonthInput(),
									inptPanel.getDayInput(),
									inptPanel.getHourInput(),
									inptPanel.getMinutesInput()));
									setVisible(false);
									CalendarView.dayView.updateList();
									dispose();
							} else {
								AddEventView.this.events.add( new Event(
									inptPanel.getNameInput(),
									inptPanel.getPlaceInput(),
									inptPanel.getYearInput(),
									inptPanel.getMonthInput(),
									inptPanel.getDayInput(),
									inptPanel.getHourInput(),
									inptPanel.getMinutesInput()));
									CalendarView.dayView.updateList();
									setVisible(false);
									dispose();
							}
						} else if (inptPanel.getNameInput().equals("")) {
							msgLabel.setText("Insert name!");
						}
						
						} catch (InputMismatchException arg) {
							msgLabel.setText("Wrong Parameter!");
						} catch (NoSuchElementException nsee) {
							msgLabel.setText("Set proper event time!");
						} catch (WrongTimeException wte) {
							msgLabel.setText(wte.getMessage());
						}
					}
	        });
	        
	        btnPanel.getListBtn().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					System.out.println(events.toString());
				}
	        	
	        });
	        
	        btnPanel.getCancelBtn().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO dispose on pressing
					AddEventView.this.dispose();
				}
	        	
	        });

	        this.add(master);
	        this.pack();
	        this.setVisible(true);
	        colorUpdate();
	}
	
	/**
	 * Metoda zmieniająca kolory w zależności od ustawień
	 */
	public void colorUpdate() {
		if(CalendarView.darkThemed) {
			master.setBackground(Color.DARK_GRAY);
			msgPanel.setBackground(Color.DARK_GRAY);
			msgLabel.setForeground(Color.WHITE);
			btnPanel.setBackground(Color.DARK_GRAY);
		} else {
			Color color = new Color(238,238,238);
			master.setBackground(color);
			msgPanel.setBackground(color);
			msgLabel.setForeground(Color.BLACK);
			btnPanel.setBackground(color);
		}
		btnPanel.colorUpdate();
		inptPanel.colorUpdate();
	}


}

/**
 * 
 * Klasa przechowująca panel w którym podawane są dane do podania
 *
 */
class InputPanel extends JPanel{ 

	private static final long serialVersionUID = 1L;
	private JTextField hourTf, minutesTf, nameTf, placeTf;
	private JPanel yearPanel, monthPanel, dayPanel, hourPanel, minutesPanel, namePanel, placePanel, alarmMinutesPanel, alarmHourPanel;
	private JLabel yearLabel, monthLabel, dayLabel, hourLabel, minutesLabel, nameLabel, placeLabel, yearLabel2, monthLabel2, dayLabel2;
	Calendar c;
	private JLabel alarmHourLabel;
	private JTextField alarmHourTf;
	private JLabel alarmMinutesLabel;
	private JTextField alarmMinutesTf;
	private JPanel event;
	private JPanel alarm;
	
	
	/**
	 * Konstrutor tworzący panel
	 * @param date data wydarzenia które jest dodawane
	 */
	public InputPanel(Date date) {
		super();
		this.setLayout(new BorderLayout());
		this.c = Calendar.getInstance();
		event = new JPanel(new GridLayout(1,5,5,0));
		alarm =  new JPanel(new GridLayout(1, 2, 5, 0));
		c.setTime(date);
		
		namePanel = new JPanel();
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
		namePanel.setToolTipText("Insert event description here.");
		nameLabel = new JLabel("Name");
		namePanel.add(nameLabel);
		nameTf = new JTextField();
		nameTf.setToolTipText("Insert event description here.");
		namePanel.add(nameTf);
		event.add(namePanel);
		
		placePanel = new JPanel();
		placePanel.setLayout(new BoxLayout(placePanel, BoxLayout.Y_AXIS));
		placePanel.setToolTipText("You can specify a place the event is going to happen.");
		placeLabel = new JLabel("Place");
		placePanel.add(placeLabel);
		placeTf = new JTextField(); 
		placeTf.setToolTipText("You can specify a place the event is going to happen.");
		placePanel.add(placeTf);
		event.add(placePanel);
		
		yearPanel = new JPanel();
		//yearPanel.setAlignmentX(CENTER_ALIGNMENT);
		yearPanel.setLayout(new BoxLayout(yearPanel, BoxLayout.Y_AXIS));
		yearLabel = new JLabel("Year");
		yearPanel.add(yearLabel);
		yearLabel2 = new JLabel("" + c.get(Calendar.YEAR));
		yearPanel.add(yearLabel2);
		event.add(this.yearPanel);
		
		monthPanel = new JPanel();
		monthPanel.setLayout(new BoxLayout(monthPanel, BoxLayout.Y_AXIS));
		monthLabel = new JLabel("Month");
		monthPanel.add(monthLabel);
		monthLabel2 = new JLabel("" + (c.get(Calendar.MONTH)+1), SwingConstants.RIGHT);
		monthPanel.add(monthLabel2);
		event.add(this.monthPanel);
		
		dayPanel = new JPanel();
		dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.Y_AXIS));
		dayLabel = new JLabel("Day");
		dayPanel.add(dayLabel);
		dayLabel2 = new JLabel("" + c.get(Calendar.DAY_OF_MONTH));
		dayPanel.add(dayLabel2);
		event.add(this.dayPanel);
		
		hourPanel = new JPanel();
		hourPanel.setLayout(new BoxLayout(hourPanel, BoxLayout.Y_AXIS));
		hourPanel.setToolTipText("Hour format is HH");
		hourLabel = new JLabel("Hour");
		hourPanel.add(hourLabel);
		hourTf = new JTextField("");
		hourTf.setToolTipText("Hour format is HH");
		hourPanel.add(this.hourTf);
		event.add(this.hourPanel);
		
		minutesPanel = new JPanel();
		minutesPanel.setLayout(new BoxLayout(minutesPanel, BoxLayout.Y_AXIS));
		minutesPanel.setToolTipText("Minutes format is MM");
		minutesLabel = new JLabel("Minutes");
		minutesPanel.add(minutesLabel);
		minutesTf = new JTextField("00");
		minutesTf.setToolTipText("Minutes format is MM");
		minutesPanel.add(this.minutesTf);
		event.add(this.minutesPanel);
		
		alarmHourPanel = new JPanel();
		alarmHourPanel.setLayout(new BoxLayout(alarmHourPanel, BoxLayout.Y_AXIS));
		alarmHourPanel.setToolTipText("Minutes format is MM");
		alarmHourLabel = new JLabel("Alarm hour");
		alarmHourPanel.add(alarmHourLabel);
		alarmHourTf = new JTextField("00");
		alarmHourTf.setToolTipText("Alarm hour format is HH");
		alarmHourPanel.add(this.alarmHourTf);
		alarm.add(this.alarmHourPanel);
		
		alarmMinutesPanel = new JPanel();
		alarmMinutesPanel.setLayout(new BoxLayout(alarmMinutesPanel, BoxLayout.Y_AXIS));
		alarmMinutesPanel.setToolTipText("Minutes format is MM");
		alarmMinutesLabel = new JLabel("Alarm minutes");
		alarmMinutesPanel.add(alarmMinutesLabel);
		alarmMinutesTf = new JTextField("00");
		alarmMinutesTf.setToolTipText("Alarm hour format is HH");
		alarmMinutesPanel.add(this.alarmMinutesTf);
		alarm.add(this.alarmMinutesPanel);
		
		this.add(event, BorderLayout.NORTH);
		this.add(alarm);
	}
	
	/**
	 * Getter podanego roku
	 * @return podany rok
	 */
	public int getYearInput() {
		return this.c.get(Calendar.YEAR);
	}
	
	/**
	 * Getter podanego miesiaca
	 * @return podany miesiąc
	 */
	public int getMonthInput() {
		return this.c.get(Calendar.MONTH);
	}
	
	/**
	 * Getter podanego dnia
	 * @return podany dzień
	 */
	public int getDayInput() {
		return this.c.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * Getter Podanej godziny
	 * @return podaną godzinę
	 * @throws NoSuchElementException
	 */
	public int getHourInput() throws NoSuchElementException {
		Scanner s = new Scanner(hourTf.getText());
		int buff = s.nextInt();
		s.close();
		if((buff < 0) || (buff > 23)) throw new WrongTimeException("Hours take values between 0 and 23!");
		return buff;
	}
	
	/**
	 * Getter podanej minuty 
	 * @return Podana minuta
	 * @throws NoSuchElementException
	 */
	public int getMinutesInput() throws NoSuchElementException {
		Scanner s = new Scanner(minutesTf.getText());
		int buff = s.nextInt();
		s.close();
		if((buff < 0) || (buff > 59)) throw new WrongTimeException("Minutes take values between 0 and 59!");
		return buff;
	}
	
	/**
	 * Getter podanej nazwy
	 * @return Podana nazwa
	 */
	public String getNameInput() {
		return this.nameTf.getText();
	}
	
	/**
	 * Getter podanego miejsca
	 * @return Podane miejsce
	 */
	public String getPlaceInput() {
		return this.placeTf.getText();
	}
	
	/** 
	 * Metoda ustawiająca kolory zgodnie z ustwaieniami
	 */
	public void colorUpdate() {
		if(CalendarView.darkThemed) {
			Color color = new Color(81,81,81);
			this.setBackground(Color.DARK_GRAY);
			namePanel.setBackground(Color.DARK_GRAY);
			placePanel.setBackground(Color.DARK_GRAY);
			yearPanel.setBackground(Color.DARK_GRAY);
			monthPanel.setBackground(Color.DARK_GRAY);
			dayPanel.setBackground(Color.DARK_GRAY);
			hourPanel.setBackground(Color.DARK_GRAY);
			minutesPanel.setBackground(Color.DARK_GRAY);
			nameTf.setBackground(color);
			placeTf.setBackground(color);
			hourTf.setBackground(color);
			minutesTf.setBackground(color);
			alarm.setBackground(Color.DARK_GRAY);
			event.setBackground(Color.DARK_GRAY);
			alarmMinutesPanel.setBackground(Color.DARK_GRAY);
			alarmHourPanel.setBackground(Color.DARK_GRAY);
			alarmHourTf.setBackground(Color.DARK_GRAY);
			alarmMinutesTf.setBackground(Color.DARK_GRAY);
			
			nameTf.setForeground(Color.WHITE);
			placeTf.setForeground(Color.WHITE);
			hourTf.setForeground(Color.WHITE);
			minutesTf.setForeground(Color.WHITE);
			nameLabel.setForeground(Color.WHITE);
			placeLabel.setForeground(Color.WHITE);
			yearLabel.setForeground(Color.WHITE);
			yearLabel2.setForeground(Color.WHITE);
			monthLabel.setForeground(Color.WHITE);
			monthLabel2.setForeground(Color.WHITE);
			dayLabel.setForeground(Color.WHITE);
			dayLabel2.setForeground(Color.WHITE);
			hourLabel.setForeground(Color.WHITE);
			minutesLabel.setForeground(Color.WHITE);
			alarmHourLabel.setForeground(Color.WHITE);
			alarmMinutesLabel.setForeground(Color.WHITE);
			alarmHourTf.setForeground(Color.WHITE);
			alarmMinutesTf.setForeground(Color.WHITE);
		} else {
			Color color = new Color(238,238,238);
			this.setBackground(color);
			namePanel.setBackground(color);
			placePanel.setBackground(color);
			yearPanel.setBackground(color);
			monthPanel.setBackground(color);
			dayPanel.setBackground(color);
			hourPanel.setBackground(color);
			minutesPanel.setBackground(color);
			nameTf.setBackground(Color.WHITE);
			placeTf.setBackground(Color.WHITE);
			hourTf.setBackground(Color.WHITE);
			minutesTf.setBackground(Color.WHITE);
			alarm.setBackground(Color.WHITE);
			event.setBackground(Color.WHITE);
			
			nameTf.setForeground(Color.BLACK);
			placeTf.setForeground(Color.BLACK);
			hourTf.setForeground(Color.BLACK);
			minutesTf.setForeground(Color.BLACK);
			nameLabel.setForeground(Color.BLACK);
			placeLabel.setForeground(Color.BLACK);
			yearLabel.setForeground(Color.BLACK);
			yearLabel2.setForeground(Color.BLACK);
			monthLabel.setForeground(Color.BLACK);
			monthLabel2.setForeground(Color.BLACK);
			dayLabel.setForeground(Color.BLACK);
			dayLabel2.setForeground(Color.BLACK);
			hourLabel.setForeground(Color.BLACK);
			minutesLabel.setForeground(Color.BLACK);
			alarmHourLabel.setForeground(Color.black);
			alarmMinutesLabel.setForeground(Color.BLACK);
			
		}
	}
}

/**
 * 
 * Klasa panelu przechowującego przyciski
 *
 */
class ButtonPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JButton addBtn, listBtn, cancelBtn;
	
	/**
	 * Getter przycisku do doadawania wydarzeń
	 * @return przycisk do dodawania wydarzeń
	 */
	public JButton getAddBtn() {
		return addBtn;
	}
	
	/**
	 * Getter Przycisku anulującego dadanie eventu
	 * @return przycisk "anuluj"
	 */
	public JButton getCancelBtn() {
		return cancelBtn;
	}
	
	/**
	 * Konstruktor panelu z przyciskami
	 */
	public ButtonPanel(){
		//setBorder(BorderFactory.createLineBorder(Color.black));
		super();
		addBtn = new JButton("Add");
		cancelBtn = new JButton("Cancel");
		if(CalendarView.darkThemed) {
			Color color = new Color(81,81,81);
			addBtn.setBackground(color);
			cancelBtn.setBackground(color);
			addBtn.setForeground(Color.WHITE);
			cancelBtn.setForeground(Color.WHITE);
		} else {
			Color color = new Color(245,245,245);
			addBtn.setBackground(color);
			cancelBtn.setBackground(color);
			addBtn.setForeground(Color.BLACK);
			cancelBtn.setForeground(Color.BLACK);
		}
		this.add(addBtn);
		this.add(listBtn);
		this.add(cancelBtn);		
	}
	
	/**
	 * Metoda zmieniająca kolory według ustawień 
	 */
	public void colorUpdate() {
		if(CalendarView.darkThemed) {
			Color color = new Color(81,81,81);
			addBtn.setBackground(color);
			cancelBtn.setBackground(color);
			addBtn.setForeground(Color.WHITE);
			cancelBtn.setForeground(Color.WHITE);
		} else {
			Color color = new Color(245,245,245);
			addBtn.setBackground(color);
			cancelBtn.setBackground(color);
			addBtn.setForeground(Color.BLACK);
			cancelBtn.setForeground(Color.BLACK);
		}
	}
}

/**
 * 
 * Wyjątek rzucany jesli podana godzina lub minuta jest niepoprawna
 */
class WrongTimeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public WrongTimeException(String string) {
		 super(string);
	}}
