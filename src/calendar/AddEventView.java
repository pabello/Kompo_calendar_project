package calendar;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class AddEventView extends JFrame{

	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ButtonPanel btnPanel;
	private InputPanel inptPanel;
	public static EventList events;
	
	public AddEventView() {
			super("Add Event");
			events = new EventList();
	        System.out.println("Created GUI on EDT? "+
	                SwingUtilities.isEventDispatchThread());

	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        this.setSize(250,20);
	        JPanel master = new JPanel();
	        master.setLayout(new BoxLayout(master, BoxLayout.Y_AXIS));
	        inptPanel = new InputPanel();
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
	private JTextField yearTf, monthTf, dayTf, hourTf, minutesTf;
	private JPanel yearPanel, monthPanel, dayPanel, hourPanel, minutesPanel;
	
	public InputPanel() {
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		yearPanel = new JPanel();
		yearPanel.setLayout(new BoxLayout(yearPanel, BoxLayout.Y_AXIS));
		yearPanel.add(new JLabel("year:"));
		yearTf = new JTextField("YYYY");
		yearPanel.add(this.yearTf);
		this.add(this.yearPanel);
		
		monthPanel = new JPanel();
		monthPanel.setLayout(new BoxLayout(monthPanel, BoxLayout.Y_AXIS));
		monthPanel.add(new JLabel("month:"));
		monthTf = new JTextField("MM");
		monthPanel.add(this.monthTf);
		this.add(this.monthPanel);
		
		dayPanel = new JPanel();
		dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.Y_AXIS));
		dayPanel.add(new JLabel("day:"));
		dayTf = new JTextField("DD");
		dayPanel.add(this.dayTf);
		this.add(this.dayPanel);
		
		hourPanel = new JPanel();
		hourPanel.setLayout(new BoxLayout(hourPanel, BoxLayout.Y_AXIS));
		hourPanel.add(new JLabel("hour:"));
		hourTf = new JTextField("HH");
		hourPanel.add(this.hourTf);
		this.add(this.hourPanel);
		
		minutesPanel = new JPanel();
		minutesPanel.setLayout(new BoxLayout(minutesPanel, BoxLayout.Y_AXIS));
		minutesPanel.add(new JLabel("minutes:"));
		minutesTf = new JTextField("MM");
		minutesPanel.add(this.minutesTf);
		this.add(this.minutesPanel);
	}
	
	public int getYearInput() {
		Scanner s = new Scanner(yearTf.getText());
		s.close();
		return s.nextInt();
	}
	
	public int getMonthInput() {

		Scanner s = new Scanner(monthTf.getText());
		s.close();
		return s.nextInt();
	}
	
	public int getDayInput() {
		Scanner s = new Scanner(dayTf.getText());
		s.close();
		return s.nextInt();
	}
	
	public int getHourInput() {
		Scanner s = new Scanner(hourTf.getText());
		s.close();
		return s.nextInt();
	}
	
	public int getMinutesInput() {
		Scanner s = new Scanner(minutesTf.getText());
		s.close();
		return s.nextInt();
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



