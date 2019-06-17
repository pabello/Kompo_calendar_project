package calendar;


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
import javax.swing.SwingUtilities;

public class AddEventView extends JFrame{

	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ButtonPanel btnPanel;
	private InputPanel inptPanel;
	public  EventList events;
	
	public AddEventView(EventList  events, Date date) {
			super("Add Event");
			this.events = events;
	        System.out.println("Created GUI on EDT? "+
	                SwingUtilities.isEventDispatchThread());

	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        this.setSize(250,20);
	        JPanel master = new JPanel();
	        master.setLayout(new GridLayout(3, 1, 0, 5));
	        JPanel msgPanel = new JPanel();
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
									AddEventView.this.events.add(new Event(inptPanel.getNameInput(),
										inptPanel.getYearInput(),
										inptPanel.getMonthInput(),
										inptPanel.getDayInput(),
										inptPanel.getHourInput(),
										inptPanel.getMinutesInput()));
										msgLabel.setText("Event added!");
								}else {
									AddEventView.this.events.add(new Event(inptPanel.getNameInput(),
											inptPanel.getPlaceInput(),
											inptPanel.getYearInput(),
											inptPanel.getMonthInput(),
											inptPanel.getDayInput(),
											inptPanel.getHourInput(),
											inptPanel.getMinutesInput()));
											msgLabel.setText("Event added!");
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
	        
	    }


}

class InputPanel extends JPanel{ 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField hourTf, minutesTf;
	private JPanel yearPanel, monthPanel, dayPanel, hourPanel, minutesPanel;
	Calendar c;
	
	public InputPanel(Date date) {
		super();
		this.setLayout(new GridLayout(1, 5, 5, 5));
		this.c = Calendar.getInstance();
		c.setTime(date);
		
		namePanel = new JPanel();
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
		namePanel.setToolTipText("Insert event description here.");
		namePanel.add(new JLabel("Name"));
		nameTf = new JTextField();
		nameTf.setToolTipText("Insert event description here.");
		namePanel.add(nameTf);
		this.add(namePanel);
		
		placePanel = new JPanel();
		placePanel.setLayout(new BoxLayout(placePanel, BoxLayout.Y_AXIS));
		placePanel.setToolTipText("You can specify a place the event is going to happen.");
		placePanel.add(new JLabel("Place"));
		placeTf = new JTextField(); 
		placeTf.setToolTipText("You can specify a place the event is going to happen.");
		placePanel.add(placeTf);
		this.add(placePanel);
		
		yearPanel = new JPanel();
		//yearPanel.setAlignmentX(CENTER_ALIGNMENT);
		yearPanel.setLayout(new BoxLayout(yearPanel, BoxLayout.Y_AXIS));
		yearPanel.add(new JLabel("Year"));
		yearPanel.add(new JLabel("" + c.get(Calendar.YEAR)));
		this.add(this.yearPanel);
		
		monthPanel = new JPanel();
		monthPanel.setLayout(new BoxLayout(monthPanel, BoxLayout.Y_AXIS));
		monthPanel.add(new JLabel("Month"));
		monthPanel.add(new JLabel("" + (c.get(Calendar.MONTH)+1), SwingConstants.RIGHT));
		this.add(this.monthPanel);
		
		dayPanel = new JPanel();
		dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.Y_AXIS));
		dayPanel.add(new JLabel("Day"));
		dayPanel.add(new JLabel("" + c.get(Calendar.DAY_OF_MONTH)));
		this.add(this.dayPanel);
		
		hourPanel = new JPanel();
		hourPanel.setLayout(new BoxLayout(hourPanel, BoxLayout.Y_AXIS));
		hourPanel.setToolTipText("Hour format is HH");
		hourPanel.add(new JLabel("Hour"));
		hourTf = new JTextField("");
		hourTf.setToolTipText("Hour format is HH");
		hourPanel.add(this.hourTf);
		this.add(this.hourPanel);
		
		minutesPanel = new JPanel();
		minutesPanel.setLayout(new BoxLayout(minutesPanel, BoxLayout.Y_AXIS));
		minutesPanel.setToolTipText("Minutes format is MM");
		minutesPanel.add(new JLabel("Minutes"));
		minutesTf = new JTextField("00");
		minutesTf.setToolTipText("Minutes format is MM");
		minutesPanel.add(this.minutesTf);
		this.add(this.minutesPanel);
	}
	
	public int getYearInput() {
		return this.c.get(Calendar.YEAR);
	}
	
	public int getMonthInput() {
		return this.c.get(Calendar.MONTH);
	}
	
	public int getDayInput() {
		return this.c.get(Calendar.DAY_OF_MONTH);
	}
	
	public int getHourInput() throws NoSuchElementException {
		Scanner s = new Scanner(hourTf.getText());
		int buff = s.nextInt();
		s.close();
		if((buff < 0) || (buff > 23)) throw new WrongTimeException("Hours take values between 0 and 23!");
		return buff;
	}
	
	public int getMinutesInput() throws NoSuchElementException {
		Scanner s = new Scanner(minutesTf.getText());
		int buff = s.nextInt();
		s.close();
		if((buff < 0) || (buff > 59)) throw new WrongTimeException("Minutes take values between 0 and 59!");
		return buff;
	}
	
	
	
}

class ButtonPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton addBtn, listBtn, cancelBtn;
	
	public JButton getAddBtn() {
		return addBtn;
	}
	public JButton getListBtn() {
		return listBtn;
	}
	public JButton getCancelBtn() {
		return cancelBtn;
	}
	
	public ButtonPanel(){
		//setBorder(BorderFactory.createLineBorder(Color.black));
		super();
		addBtn = new JButton("Add");
		listBtn = new JButton("List");
		cancelBtn = new JButton("Cancel");
		this.add(addBtn);
		this.add(listBtn);
		this.add(cancelBtn);		
	}
	

}

class WrongTimeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public WrongTimeException(String string) {
		 super(string);
	}}
