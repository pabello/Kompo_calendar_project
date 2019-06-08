package calendar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class CalendarView extends JFrame{

	JButton [][] ButtonsTable;
//	JPanel[] columns;
	JPanel labels;
	JPanel header;
	JPanel master;
	int currentDayOfMonth;
	int currentMonth;
	/**
	 * 
	 */
	//xd
	private static final long serialVersionUID = 1L;
	public CalendarView(Calendar calendarInstance) {
		super("Calendar");
		this.setLayout(new GridLayout(2,1,0,20));

//		calendarInstance.setFirstDayOfWeek(Calendar.MONDAY);
		currentDayOfMonth = calendarInstance.get(Calendar.DAY_OF_MONTH);
		currentMonth = calendarInstance.get(Calendar.MONTH);
		
		this.header = new JPanel();
		JButton yearLeft = new JButton("<<");
		JButton monthLeft = new JButton("<");
		JLabel monthName = new JLabel("month");
		JButton monthRight = new JButton(">");
		JButton yearRight = new JButton(">>");

		yearLeft.setPreferredSize(new Dimension(25,25));
		yearLeft.setMargin(new Insets(0,0,0,0));
		this.header.add(yearLeft);
		monthLeft.setPreferredSize(new Dimension(25,25));
		monthLeft.setMargin(new Insets(0,0,0,0));
		this.header.add(monthLeft);
		this.header.add(monthName);
		monthRight.setPreferredSize(new Dimension(25,25));
		monthRight.setMargin(new Insets(0,0,0,0));
		this.header.add(monthRight);
		yearRight.setPreferredSize(new Dimension(25,25));
		yearRight.setMargin(new Insets(0,0,0,0));
		this.header.add(yearRight);
		
		
		this.master = new JPanel();
		this.master.setLayout(new GridLayout(6,7,1,1));
		
		System.out.println(calendarInstance.get(Calendar.DAY_OF_MONTH));
		if(calendarInstance.get(Calendar.DAY_OF_MONTH) != 1)
			calendarInstance.set(Calendar.DAY_OF_MONTH, 1);
		System.out.println(calendarInstance.get(Calendar.DAY_OF_MONTH));
		System.out.println(calendarInstance.get(Calendar.DAY_OF_WEEK)+"\r\n");
		if(calendarInstance.get(Calendar.DAY_OF_WEEK) != 1)
			calendarInstance.add(Calendar.DAY_OF_MONTH, -(Calendar.DAY_OF_WEEK-2));
		System.out.println(calendarInstance.get(Calendar.DAY_OF_MONTH));
		System.out.println(calendarInstance.get(Calendar.DAY_OF_WEEK));
		
		for(int i=0; i<42; i++) {
			DayButton button = new DayButton(String.valueOf(calendarInstance.get(Calendar.DAY_OF_MONTH)), calendarInstance);
			button.setPreferredSize(new Dimension(60,60));
			if((calendarInstance.get(Calendar.DAY_OF_MONTH) == currentDayOfMonth) && 
			   (calendarInstance.get(Calendar.MONTH) == currentMonth)) {
				button.setBackground(new Color(250,250,250));
				System.out.println("\r\n" + calendarInstance.get(Calendar.DAY_OF_MONTH)+" vs "+currentDayOfMonth);
				System.out.println(calendarInstance.get(Calendar.DAY_OF_WEEK));
			}
			else {
				if((calendarInstance.get(Calendar.DAY_OF_WEEK) == 1) ||
				   (calendarInstance.get(Calendar.DAY_OF_WEEK) == 7))
					button.setBackground(new Color(225,225,225));
				else
					button.setBackground(new Color(235,235,235));
			}
			this.master.add(button);
			calendarInstance.add(Calendar.DAY_OF_MONTH, 1);
		}

//		this.master.setLayout(new GridBagLayout());
////		this.columns = new JPanel[6];
//		int j = 0;
////		for(JPanel r: columns) {
//		for(int k=0; k<columns; k++) {
//			JPanel row = new JPanel();
//			row.setLayout(new GridLayout());
//			
//			for(int i = 1; i <= 7; i++) {
//				JButton buf = new JButton();
//				buf.setText(""+((i)+j));
//				buf.setPreferredSize(new Dimension(50,50));
//				row.add(buf);
//				
//			}
//			j+=7;
//			this.master.add(row);
////			addComponent();
//		}

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(header);
		this.add(master);
        this.pack();
        this.setVisible(true);
        
	}
	

}
