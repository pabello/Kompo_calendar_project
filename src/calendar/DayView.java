package calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Klasa wyświetlająca listę wydarzeń w dniu
 *
 */

public class DayView extends JFrame {
	
	private static final long serialVersionUID = -6399041735933009077L;
	private JList <Event> l;
	private DefaultListModel<Event> lm;
	private JPanel master;
	private JButton add, remove;
	private EventList v;
	AddEventView a;
	private Date date;
	private final static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
	 
	/**
	 * Konstrutor tworzący okno z listą
	 * @param v lista wszystkich wydarzeń
	 * @param date data
	 */
	public DayView(EventList v, Date date) {
		super(sdf.format(date));
		
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if(a != null)
					a.dispose();
			}
		});
		
		this.setLayout(new BorderLayout());
		this.v = v;
		this.date = date;
		
		lm = new DefaultListModel<Event>();
		for(Event e : this.v) 
			if(isEventToday(e)) lm.addElement(e);
		l = new JList<Event>(lm);
		JScrollPane s = new JScrollPane(l, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		s.setPreferredSize(new Dimension(350, 150));
		master = new JPanel();
		
		add = new JButton("Add");
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				a = new AddEventView(v, date);
			}
		});
		
		remove = new JButton("Remove");
		remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!DayView.this.l.isSelectionEmpty()) {
					DayView.this.v.remove(DayView.this.l.getSelectedValue());
					DayView.this.lm.removeElement(DayView.this.l.getSelectedValue());
					updateList();
				}
				a = new AddEventView(v, date);
			}
		});	
		
		this.add(s, BorderLayout.CENTER);
		master.add(add);
		master.add(remove);
		this.add(master, BorderLayout.SOUTH);
		this.setVisible(true);
		this.pack();
		colorUpdate();
	}
	
	/**
	 * Metoda sprawdzająca czy wydarzenie jest w dniu zadanym przez datę
	 * @param e wydarzenie do sprawdzenia
	 * @return true jezeli wydarzenie jest w dniu zadanym przez date
	 * 		   false jezeli wydarzenie nie jest w tym dniu
	 */
	public boolean isEventToday(Event e) {
		String today = sdf.format(this.date);
		String eventDay = sdf.format(e.getEventTime());
		return today.equals(eventDay);
	}
	
	/**
	 * Metoda uaktualniająca listę wyswietlaną w oknie
	 */
	public void updateList() {
		for(Event event : DayView.this.v) {
			if((!lm.contains(event)) && this.isEventToday(event)) {
				lm.addElement(event);
			}
		}
	}
	
	/**
	 * Metoda ustawiająca kolory zgodnie z ustawieniami 
	 */
	public void colorUpdate() {
		if(CalendarView.darkThemed) {
			master.setBackground(Color.DARK_GRAY);
			l.setBackground(new Color(81,81,81));
			l.setForeground(Color.WHITE);
		}
		else {
			master.setBackground(new Color(238,238,238));
			l.setBackground(Color.WHITE);
			l.setForeground(Color.BLACK);
		}
	}

}
