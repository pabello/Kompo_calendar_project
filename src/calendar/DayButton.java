package calendar;

import java.util.Date;

import javax.swing.JButton;

/**
 * Klasa rozszerzajaca JButton tak aby miał przypisaną do siebie datę
 *
 */
public class DayButton extends JButton{
	private static final long serialVersionUID = 1L;
	
	Date date;

	/**
	 * Konstrukor bez podania napisu na przycisku.
	 * @param date data przypisana do przycisku.
	 */
	DayButton(Date date) {
		super();
		this.date = date;
	}
	/**
	 * Konstruktor podający napis na przycisku
	 * @param desc napis na przycisku
	 * @param date data do przypisania
	 */
	DayButton(String desc, Date date) {
		super(desc);
		this.date = date;
	}
}
