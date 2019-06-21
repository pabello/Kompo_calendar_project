package calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class CalendarView extends JFrame{
	
	EventList eventList;
	DayView dayView;

	JPanel header;
	JPanel master;
	Calendar calendarInstance;
	int currentDayOfMonth;
	int currentMonth;
	int currentYear;

	public CalendarView(Calendar calendarInstance, EventList eventList) {
		super("Calendar");

		this.calendarInstance = calendarInstance;
		this.eventList = eventList;
		
		init(null);
	}
	
	private static final long serialVersionUID = 1L;
	
	public void init(Dimension dim) {
		
		currentDayOfMonth = calendarInstance.get(Calendar.DAY_OF_MONTH);
		currentMonth = calendarInstance.get(Calendar.MONTH);
		currentYear = calendarInstance.get(Calendar.YEAR);
		
		this.setLayout(new BorderLayout());
		
		Color dniPowszednie = new Color(225,225,225);
		Color weekend = new Color(235,235,235);
		
		this.header = new JPanel();
		JButton yearLeft = new JButton("<<");
		yearLeft.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				calendarInstance.add(Calendar.YEAR, -1);
				update();
			}
		});
		JButton monthLeft = new JButton("<");
		monthLeft.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				calendarInstance.add(Calendar.MONTH, -1);
				currentMonth = calendarInstance.get(Calendar.MONTH);
				update();
			}
		});
		JLabel monthName = new JLabel("" + 
				calendarInstance.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US) + "  " +
				calendarInstance.get(Calendar.YEAR));
		monthName.setPreferredSize(new Dimension(115, 25));
		monthName.setHorizontalAlignment(SwingConstants.CENTER);
		JButton monthRight = new JButton(">");
		monthRight.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				calendarInstance.add(Calendar.MONTH, +1);
				update();
			}
		});
		JButton yearRight = new JButton(">>");
		yearRight.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				calendarInstance.add(Calendar.YEAR, +1);
				update();
			}
		});

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
		this.master.setLayout(new GridLayout(7,7,1,1));

		if(calendarInstance.get(Calendar.DAY_OF_MONTH) != 1)
			calendarInstance.add(Calendar.DAY_OF_MONTH, 1-calendarInstance.get(Calendar.DAY_OF_MONTH));
		if(calendarInstance.get(Calendar.DAY_OF_WEEK) == 2)
			calendarInstance.add(Calendar.DAY_OF_MONTH, -7);
			calendarInstance.add(Calendar.DAY_OF_MONTH, -((((calendarInstance.get(Calendar.DAY_OF_WEEK)-2)%7)+7)%7));
		
		calendarInstance.roll(Calendar.DAY_OF_WEEK, 2-calendarInstance.get(Calendar.DAY_OF_WEEK));
		for(int i=0; i<7; i++) {
			JLabel dayLabel = new JLabel(calendarInstance.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US), SwingConstants.CENTER);
			master.add(dayLabel);
			calendarInstance.roll(Calendar.DAY_OF_WEEK, 1);
		}
		
		for(int i=0; i<42; i++) {
			DayButton button = new DayButton(String.valueOf(calendarInstance.get(Calendar.DAY_OF_MONTH)), calendarInstance.getTime());
//			button.addActionListener(new DayButtonActionListener());
			button.setPreferredSize(new Dimension(60,40));
			if((calendarInstance.get(Calendar.DAY_OF_MONTH) == currentDayOfMonth) && 
			   (calendarInstance.get(Calendar.MONTH) == currentMonth)) {
				button.setBackground(new Color(250,250,250));
				button.setBorder(BorderFactory.createLineBorder(new Color(255,165,0), 2));
				button.addMouseListener(new DayButtonMouseAdapter(true, this));
			}
			else {
				if((calendarInstance.get(Calendar.DAY_OF_WEEK) == 1) ||
				   (calendarInstance.get(Calendar.DAY_OF_WEEK) == 7)) {
					if(calendarInstance.get(Calendar.MONTH) != currentMonth) {
						button.setBorder(BorderFactory.createLineBorder(new Color(200,200,200), 1));
						button.setEnabled(false);
					}
					else
						button.addMouseListener(new DayButtonMouseAdapter(false, this));
					button.setBackground(dniPowszednie);
				}
				else
					if(calendarInstance.get(Calendar.MONTH) != currentMonth) {
						button.setBorder(BorderFactory.createLineBorder(new Color(210,210,210), 1));
						button.setEnabled(false);	
					}
					else {
						button.addMouseListener(new DayButtonMouseAdapter(false, this));
						button.setBackground(weekend);
					}
			}
			this.master.add(button);
			calendarInstance.add(Calendar.DAY_OF_MONTH, 1);
		}
		calendarInstance.add(Calendar.MONTH, currentMonth-calendarInstance.get(Calendar.MONTH));
		calendarInstance.add(Calendar.DAY_OF_MONTH, currentDayOfMonth-calendarInstance.get(Calendar.DAY_OF_MONTH));
		calendarInstance.add(Calendar.YEAR,  currentYear-calendarInstance.get(Calendar.YEAR));
		
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(header, BorderLayout.NORTH);
		this.add(master, BorderLayout.CENTER);
        if(dim != null) 
        	this.setPreferredSize(dim);
        else
        	this.pack();
        this.setVisible(true);
        
	}
	
	public void update() {
		this.remove(header);
		this.remove(master);
		init(this.getSize());
	}
}
