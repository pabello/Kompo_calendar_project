package calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/**
 * Okno wyszukiwania wydarzeń.
 *
 */
public class SearchWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	private EventList eventList;
	private JPanel search;
	private JTextField searchPattern;
	private JList<Event> foundList;
	private JButton searchBtn;
	private DefaultListModel<Event> listModel;
	private JPanel removePanel;
	private JTextField removeDate;
	private JButton remove;
	
	/**
	 * Konstrutor okna wyszukiwania wydarzeń.
	 * @param eventList lista, w której bedziemy szukać.
	 */
	public SearchWindow(EventList eventList){
		super("Search");
		this.eventList = eventList;
		this.setLayout(new BorderLayout());
		search = new JPanel(new BorderLayout());
		searchPattern = new JTextField();
		search.add(searchPattern, BorderLayout.NORTH);
	
		listModel = new DefaultListModel<Event>();
		foundList = new JList<Event>(listModel);
		JScrollPane s = new JScrollPane(foundList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		search.add(s, BorderLayout.SOUTH);
		
		searchBtn = new JButton("Search!");
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				EventList buff = SearchWindow.this.eventList.searchEvent(searchPattern.getText());
				for(Event e : buff) {
					listModel.addElement(e);
				}
				
			}
		});
		search.add(searchBtn, BorderLayout.CENTER);
		
		removePanel = new JPanel(new BorderLayout());
		removeDate = new JTextField("dd-mm-rrrr");
		removePanel.add(removeDate, BorderLayout.NORTH);
		
		remove = new JButton("Remove all events before date!");
		
		remove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Date date = new SimpleDateFormat("dd-MM-yyyy").parse(removeDate.getText());
					SearchWindow.this.eventList.removeEventsBeforeDate(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				
			}
		});
		
		removePanel.add(remove, BorderLayout.CENTER);
		this.add(removePanel, BorderLayout.CENTER);
		this.add(search, BorderLayout.NORTH);
		this.setVisible(true);
		this.pack();
		this.updateColor();
		
	}
	
	public void updateColor() {
		if(CalendarView.darkThemed) {
			this.foundList.setBackground(Color.DARK_GRAY);
			this.searchBtn.setBackground(Color.DARK_GRAY);
			this.search.setBackground(Color.DARK_GRAY);
			this.searchPattern.setBackground(Color.DARK_GRAY);
			this.remove.setBackground(Color.DARK_GRAY);
			this.removeDate.setBackground(Color.DARK_GRAY);
			this.removePanel.setBackground(Color.DARK_GRAY);
				
			this.foundList.setForeground(Color.WHITE);
			this.searchBtn.setForeground(Color.WHITE);
			this.search.setForeground(Color.WHITE);
			this.searchPattern.setForeground(Color.WHITE);
			this.remove.setForeground(Color.WHITE);
			this.removeDate.setForeground(Color.WHITE);
			this.removePanel.setForeground(Color.WHITE);
		} else {
			Color color = new Color(238, 238, 238);
			this.foundList.setBackground(color);
			this.searchBtn.setBackground(color);
			this.search.setBackground(color);
			this.searchPattern.setBackground(color);
			this.remove.setBackground(color);
			this.removeDate.setBackground(color);
			this.removePanel.setBackground(color);
				
			this.foundList.setForeground(Color.BLACK);
			this.searchBtn.setForeground(Color.BLACK);
			this.search.setForeground(Color.BLACK);
			this.searchPattern.setForeground(Color.BLACK);
			this.remove.setForeground(Color.BLACK);
			this.removeDate.setForeground(Color.BLACK);
			this.removePanel.setForeground(Color.BLACK);
		}
	
	}

}
