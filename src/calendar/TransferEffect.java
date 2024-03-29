package calendar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class TransferEffect extends JFrame {
	private static final long serialVersionUID = 1L;
	JPanel master;
	JLabel message;

	/**
	 * Klasa wyswielająca czy udałos ie zapisać, odczytać z pliku.
	 * @param action efekt zapisu/odczytu.
	 */
	public TransferEffect(String action) {
		super(action);
		master = new JPanel();
		
		ImageIcon icon = new ImageIcon("images\\"+action+".png");
		message = new JLabel(action, icon, JLabel.CENTER);

        master.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "closeWindow");
        master.getActionMap().put("closeWindow", new CloseWindowAction(this));
		master.setLayout(new java.awt.BorderLayout());
		master.add(message, java.awt.BorderLayout.CENTER);
		master.setPreferredSize(new java.awt.Dimension(200,120));
		this.add(master);
		this.pack();
		this.setVisible(true);
		
		colorUpdate();
	}
	
	/**
	 * Metoda ustawiająca kolorystykę zgodnie z ustawieniami.
	 */
	private void colorUpdate() {
		if(CalendarView.darkThemed) {
			master.setBackground(Color.DARK_GRAY);
			message.setForeground(Color.WHITE);
		} else {
			master.setBackground(new Color(238,238,238));
			message.setForeground(Color.BLACK);
		}
	}
}
