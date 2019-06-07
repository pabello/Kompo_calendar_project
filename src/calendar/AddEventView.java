package calendar;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
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
	        master.setLayout(new BoxLayout(master, BoxLayout.Y_AXIS));
	        inptPanel = new InputPanel(date);
	        master.add(inptPanel);
	        btnPanel = new ButtonPanel();
	        master.add(btnPanel);
	        btnPanel.getAddBtn().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						events.add(new Event("aa",
								inptPanel.getYearInput(),
								inptPanel.getMonthInput(),
								inptPanel.getDayInput(),
								inptPanel.getHourInput(),
								inptPanel.getMinutesInput()));
				
						}catch(InputMismatchException arg) {
							System.out.println("Wrong Parameter!");
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
		hourPanel.add(new JLabel("Hour"));
		hourTf = new JTextField("HH");
		hourPanel.add(this.hourTf);
		this.add(this.hourPanel);
		
		minutesPanel = new JPanel();
		minutesPanel.setLayout(new BoxLayout(minutesPanel, BoxLayout.Y_AXIS));
		minutesPanel.add(new JLabel("Minutes"));
		minutesTf = new JTextField("MM");
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
	
	public int getHourInput() {
		Scanner s = new Scanner(hourTf.getText());
		int buff = s.nextInt();
		s.close();
		return buff;
	}
	
	public int getMinutesInput() {
		Scanner s = new Scanner(minutesTf.getText());
		int buff = s.nextInt();
		s.close();
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



