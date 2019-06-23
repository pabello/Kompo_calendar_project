package calendar;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;

/**
 * 
 * Implementacja interfejsu MouseAdapter dla DayButton;
 *
 */
public class DayButtonMouseAdapter extends MouseAdapter{
	boolean isThisDayToday;
	
	public DayButtonMouseAdapter(boolean isThisDayToday) {
		this.isThisDayToday = isThisDayToday;
	}
	
	Color darker = new Color(255,192,76);
	Color lighter = new Color(255,219,153);
	
	public void mouseEntered(MouseEvent event) {
		DayButton source = (DayButton) event.getComponent();
		if(isThisDayToday) {
			source.setBorder(BorderFactory.createLineBorder(new Color(255, 160, 0), 2));
		}
	}
	
	public void mouseExited(MouseEvent event) {
		DayButton source = (DayButton) event.getComponent();
		if(isThisDayToday) {
			source.setBorder(BorderFactory.createBevelBorder(1, lighter, darker, lighter, darker));
		}
	}
	
	public void mouseClicked(MouseEvent event) {
		DayButton source = (DayButton) event.getComponent();
		if(CalendarView.dayView != null) CalendarView.dayView.dispose();
		CalendarView.dayView = new DayView(CalendarView.eventList, source.date);
	}
}
