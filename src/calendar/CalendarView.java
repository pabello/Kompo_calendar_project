package calendar;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class CalendarView extends JFrame{

	JButton [][] ButtonsTable;
//	JPanel[] columns;
	JPanel labels;
	JPanel header;
	JPanel master;
	/**
	 * 
	 */
	//xd
	private static final long serialVersionUID = 1L;
	public CalendarView() {
		super("Calendar");
//		this.setLayout(new GridLayout(2,1,0,20));
		
		this.header = new JPanel();
		JButton yearLeft = new JButton("<<");
		JButton monthLeft = new JButton("<");
		JLabel monthName = new JLabel("month");
		JButton monthRigth = new JButton(">");
		JButton yearRigth = new JButton(">>");

		yearLeft.setPreferredSize(new Dimension(20,20));
		this.header.add(yearLeft);
		monthLeft.setPreferredSize(new Dimension(20,20));
		this.header.add(monthLeft);
		this.header.add(monthName);
		monthRigth.setPreferredSize(new Dimension(20,20));
		this.header.add(monthRigth);
		yearRigth.setPreferredSize(new Dimension(20,20));
		this.header.add(yearRigth);
		
		
		this.master = new JPanel();
		this.master.setLayout(new GridLayout(6,7,1,1));
		
		for(int i=0; i<6; i++) { // 6 = number of rows
			for(int j=1; j<8; j++) {
				JButton button = new JButton(String.valueOf((7*i) + j));
				button.setPreferredSize(new Dimension(60,60));
				this.master.add(button);
			}
		}

//		this.master.setLayout(new GridBagLayout());
////		this.columns = new JPanel[6];
//		int j = 0;
////		for(JPanel r: columns) {
//		for(int k=0; k<columns; k++) {
//			JPanel row = new JPanel();
//			row.setLayout(new GridLayout());
//			
//			for(int i = 1; i <= 7; i++) {
//				JButton buf = new JButton();
//				buf.setText(""+((i)+j));
//				buf.setPreferredSize(new Dimension(50,50));
//				row.add(buf);
//				
//			}
//			j+=7;
//			this.master.add(row);
////			addComponent();
//		}

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(header);
		this.add(master);
        this.pack();
        this.setVisible(true);
        
	}
	

}
