package calendar;

import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;

public class DayButton extends JButton{
	private static final long serialVersionUID = 1L;
	
	Calendar date;

	DayButton(Calendar date) {
		super();
		this.date = date;
	}
	DayButton(String desc, Calendar date) {
		super(desc);
		this.date = date;
	}
}
