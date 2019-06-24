package calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 
 * Klasa tworząca okienko "O Programie"
 *
 */
public class AboutWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	
	JPanel master;
	JLabel authors;
	JLabel description;
	JLabel copyright;
	
	/**
	 *  Kontruktor tworzący okno
	 */
	public AboutWindow() {
		super("About");
		
		master = new JPanel();
		authors = new JLabel("<html><b>Authors of this calendar are</b><br>   Andrzej Zabski<br>   Pawel Waclawiak</html>");
		description = new JLabel("<html>The application is still in its Beta version and it's licenced as Freeware, although we are considering changing it to Beerware after final release.</html>");
		Font font = description.getFont();
		authors.setFont(font.deriveFont(Font.PLAIN));
		description.setFont(font.deriveFont(Font.PLAIN));
		copyright = new JLabel("<html>Copyright © 2019 by Zabcia Ltd. All rights reserved.</html>");
		
		master.setLayout(new BorderLayout());
		master.add(authors, BorderLayout.NORTH);
		master.add(description, BorderLayout.CENTER);
		master.add(copyright, BorderLayout.SOUTH);
		master.setPreferredSize(new Dimension(300, 180));
		
		copyright.setForeground(Color.GRAY);
		
		colorUpdate();
		this.add(master);
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * Metoda zmieniająca kolorystykę okna w zależności od ustawień	
	 */
	public void colorUpdate() {
		if(CalendarView.darkThemed) {
			master.setBackground(Color.DARK_GRAY);
			
			authors.setForeground(Color.WHITE);
			description.setForeground(Color.WHITE);
//			copyright.setForeground(Color.WHITE);
		} else {
			master.setBackground(new Color(238,238,238));

			authors.setForeground(Color.BLACK);
			description.setForeground(Color.BLACK);
//			copyright.setForeground(Color.BLACK);
		}
	}
}
