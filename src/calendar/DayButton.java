package calendar;

import java.util.Date;

import javax.swing.JButton;

public class DayButton extends JButton{
	private static final long serialVersionUID = 1L;
	
	Date date;
	DayView dayView;

	DayButton(Date date) {
		super();
		this.date = date;
	}
	DayButton(String desc, Date date) {
		super(desc);
		this.date = date;
	}
}
