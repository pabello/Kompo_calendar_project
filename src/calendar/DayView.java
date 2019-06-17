package calendar;

import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class DayView extends JFrame {
	private JList <Event> l;
	private DefaultListModel<Event> lm;
	private JButton add;
	private EventList v;
	private AddEventView a;
	private Date date;
	 

	public DayView() {
		super();
		this.setLayout(new GridLayout(0, 1, 10, 10));
		v = new EventList();
		date = new Date();
		v.add(new Event("a", "a", 2018, 11, 28, 15, 0));
		v.add(new Event("a", "a", 2018, 11, 28, 15, 0));
		v.add(new Event("a", "a", 2018, 11, 28, 15, 0));
		v.add(new Event("aa", "a", 2018, 11, 28, 15, 0));
		lm = new DefaultListModel();
		for(Event e : v.events) lm.addElement(e);
		l = new JList(lm);
		JScrollPane s = new JScrollPane(l);
		add = new JButton("Add");
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for(Event event : DayView.this.v.events) {
					if(!lm.contains(event))
						lm.addElement(event);
				}
				a = new AddEventView(v, date);
			}
			
		});
		//this.add(l);
		this.add(s);
		this.add(add);
		this.setVisible(true);
		this.pack();
	}

}
