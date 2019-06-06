package calendar;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class CalendarView extends JFrame{

	JButton [][] ButtonsTable;
	JPanel[] columns;
	JPanel labels;
	JPanel master;
	/**
	 * 
	 */
	//xd
	private static final long serialVersionUID = 1L;
	public CalendarView() {
		super("Calendar");
		this.master = new JPanel();
		this.master.setLayout(new BoxLayout(master, BoxLayout.Y_AXIS));
	
		this.columns = new JPanel[6];
		int j = 0;
		for(JPanel r: columns) {
			r = new JPanel();
			r.setLayout(new BoxLayout(r, BoxLayout.Y_AXIS));
			
			for(int i = 1; i <= 7; i++) {
				JButton buf = new JButton();
				buf.setText(""+((i)+j));
				buf.setSize(30, 30);
				r.add(buf);
			}
			j+=7;
			this.master.add(r);
		}

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(master);
        this.pack();
        this.setVisible(true);
        
	}
	

}
