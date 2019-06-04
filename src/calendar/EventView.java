package calendar;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class EventView extends JFrame{

	
	private EventList events;
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void createAndShowGUI() {
	        System.out.println("Created GUI on EDT? "+
	                SwingUtilities.isEventDispatchThread());
	        JFrame f = new JFrame("Calendar");
	        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        f.setSize(250,250);
	        f.add(new ButtonPanel());
	        f.pack();
	        f.setVisible(true);
	        
	    }

}

class ButtonPanel extends JPanel implements ActionListener{
	private JButton b1, b2, b3;
	public ButtonPanel(){
		//setBorder(BorderFactory.createLineBorder(Color.black));
		
		b1 = new JButton("Add");
		b1.addActionListener(this);
		b2 = new JButton("List");
		b2.addActionListener(this);
		b3 = new JButton("Hello!");
		b3.addActionListener(this);
		this.add(b1);
		this.add(b2);
		this.add(b3);

		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton src = (JButton) e.getSource();
		
		if(src == b1) System.out.println("Hey!");
		else if(src == b2) System.out.println("Hi!");
		else if(src == b3) System.out.println("Hello!");
		
	}
	
}



