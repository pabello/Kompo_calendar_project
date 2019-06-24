package calendar;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

public class CloseWindowAction extends AbstractAction{
	private static final long serialVersionUID = 1L;
	private JFrame component;
	
	public CloseWindowAction(JFrame component) {
		this.component = component;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		component.dispose();
	}
}
