package calendar;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
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
	
	/**
	 * Konstrutor okna wyszukiwania wydarzeń.
	 * @param eventList lista, w której bedziemy szukać.
	 */
	public SearchWindow(EventList eventList){
		super("Search");
		this.eventList = eventList;
		this.setLayout(new BorderLayout());
		JTextField searchPattern = new JTextField();
		this.add(searchPattern, BorderLayout.NORTH);
	
		DefaultListModel<Event> listModel = new DefaultListModel<Event>();
		JList<Event> foundList = new JList<Event>(listModel);
		JScrollPane s = new JScrollPane(foundList, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(s, BorderLayout.SOUTH);
		
		JButton searchBtn = new JButton("Search!");
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				EventList buff = SearchWindow.this.eventList.searchEvent(searchPattern.getText());
				for(Event e : buff) {
					listModel.addElement(e);
				}
				
			}
		});
		this.add(searchBtn, BorderLayout.CENTER);
		
		this.setVisible(true);
		this.pack();
		
	}

}
