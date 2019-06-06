package calendar;

import java.util.Date;

import javax.accessibility.Accessible;
import javax.swing.AbstractButton;

public class DayButton extends AbstractButton implements Accessible{
	Date date;
	
	DayButton(Date date) {
		super();
		this.date = date;
	}
}
