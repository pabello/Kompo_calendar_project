package calendar;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.BorderFactory;

public class DayButtonMouseAdapter extends MouseAdapter{
	boolean isThisDayToday;
	CalendarView lord;
	
	public DayButtonMouseAdapter(boolean isThisDayToday, CalendarView lord) {
		this.isThisDayToday = isThisDayToday;
		this.lord = lord;
	}
	
	Color darker = new Color(255,192,76);
	Color lighter = new Color(255,219,153);
	
	public void mouseEntered(MouseEvent event) {
		DayButton source = (DayButton) event.getComponent();
		if(isThisDayToday) {
			source.setBorder(BorderFactory.createBevelBorder(1, lighter, darker, lighter, darker));
		}
	}
	
	public void mouseExited(MouseEvent event) {
		DayButton source = (DayButton) event.getComponent();
		if(isThisDayToday) {
			source.setBorder(BorderFactory.createLineBorder(new Color(255, 160, 0), 2));
		}
	}
	
	public void mouseClicked(MouseEvent event) {
		DayButton source = (DayButton) event.getComponent();
		lord.dayView = new DayView(lord.eventList, source.date);
	}
}
