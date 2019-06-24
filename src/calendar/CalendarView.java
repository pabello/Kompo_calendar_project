package calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * 
 * Klasa głownego okna programu mającego wyglądać jak klendarz ścienny
 *
 */
public class CalendarView extends JFrame{
	
	static EventList eventList;
	static DayView dayView;
	static OptionWindow winnie;
	static AboutWindow about;
	static TransferEffect transferPopup;
	static boolean darkThemed = true;

	JPanel spaceHolder;
	JPanel backToPresent;
	JPanel header;
	JPanel master;
	Calendar calendarInstance;
	int currentDayOfMonth;
	int currentMonth;
	int currentYear;
	
	final Date realCurrentDate;
	SimpleDateFormat todayFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
	protected static SearchWindow searchWindow;	

	/**
	 * Konstrutor
	 * @param calendarInstance instacja Klendarza
	 * @param eventList Lista wydarzeń
	 */
	public CalendarView(Calendar calendarInstance, EventList eventList) {
		super("Calendar");

		this.calendarInstance = calendarInstance;
		this.realCurrentDate = calendarInstance.getTime();
		CalendarView.eventList = eventList;
		
		init(null);
	}
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Metoda rysująca okno i wszystkie jego komponenty
	 * @param dim wymiary okna
	 */
	public void init(Dimension dim) {
		
		currentDayOfMonth = calendarInstance.get(Calendar.DAY_OF_MONTH);
		currentMonth = calendarInstance.get(Calendar.MONTH);
		currentYear = calendarInstance.get(Calendar.YEAR);
		
		if(darkThemed) this.getContentPane().setBackground(Color.DARK_GRAY);
		
		this.setLayout(new BorderLayout());
		
		Color dniPowszednie = new Color(235,235,235);
		Color weekend = new Color(225,225,225);
		Color dniPowszednieDark = new Color(123,123,123);
		Color weekendDark = new Color(91,91,91);
		
		this.header = new JPanel();
		header.setLayout(new BorderLayout());
		
		backToPresent = new JPanel();
		JLabel presentLabel = new JLabel(todayFormat.format(realCurrentDate));
		Font font = presentLabel.getFont();
		presentLabel.setFont(font.deriveFont(Font.PLAIN));
		if(darkThemed) presentLabel.setForeground(Color.CYAN);
		else presentLabel.setForeground(Color.BLUE);
//		Font font2 = presentLabel.getFont();
		presentLabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
			 	calendarInstance.setTime(realCurrentDate);
				update();
				
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
				attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
				presentLabel.setFont(font.deriveFont(attributes));
				Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
				setCursor(cursor);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				presentLabel.setFont(font.deriveFont(Font.PLAIN));
				Cursor cursor = Cursor.getDefaultCursor();
				setCursor(cursor);
			}
		});
		backToPresent.add(presentLabel);

		JPanel menuIconPanel = new JPanel();
		JPanel infoIconPanel = new JPanel();
		ImageIcon menuIcon = new ImageIcon("images\\gear-icon48.png");
		ImageIcon infoIcon = new ImageIcon("images\\about24.png");
		ImageIcon searchIcon = new ImageIcon("images\\mglass24.png");
		JLabel menuIconLabel = new JLabel(menuIcon);
		JLabel infoIconLabel = new JLabel(infoIcon);
		JLabel searchIconLabel = new JLabel(searchIcon);
		JPanel leftIcons = new JPanel();
		leftIcons.setLayout(new GridLayout(1,2,0,0));
		
		menuIconLabel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(winnie != null)
					winnie.dispose();
				winnie = new OptionWindow(CalendarView.this);
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
				setCursor(cursor);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Cursor cursor = Cursor.getDefaultCursor();
				setCursor(cursor);
			}
		});
		infoIconLabel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(about != null)
					about.dispose();
				about = new AboutWindow();
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
				setCursor(cursor);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Cursor cursor = Cursor.getDefaultCursor();
				setCursor(cursor);
			}
		});
		searchIconLabel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(searchWindow!=null) searchWindow.dispose();
				searchWindow = new SearchWindow(eventList);
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {
				Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
				setCursor(cursor);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Cursor cursor = Cursor.getDefaultCursor();
				setCursor(cursor);
			}
		});
		menuIconPanel.add(menuIconLabel);
		infoIconPanel.add(infoIconLabel);
		infoIconPanel.add(searchIconLabel);
		

		JPanel navigation = new JPanel();
		JButton yearLeft = new JButton("<<");
		yearLeft.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				calendarInstance.add(Calendar.YEAR, -1);
				update();
			}
		});
		JButton monthLeft = new JButton("<");
		monthLeft.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				calendarInstance.add(Calendar.MONTH, -1);
				currentMonth = calendarInstance.get(Calendar.MONTH);
				update();
			}
		});
		JLabel monthName = new JLabel("" + 
				calendarInstance.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US) + "  " +
				calendarInstance.get(Calendar.YEAR));
		monthName.setPreferredSize(new Dimension(115, 25));
		monthName.setHorizontalAlignment(SwingConstants.CENTER);
		if(darkThemed) monthName.setForeground(Color.WHITE);
		JButton monthRight = new JButton(">");
		monthRight.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				calendarInstance.add(Calendar.MONTH, +1);
				update();
			}
		});
		JButton yearRight = new JButton(">>");
		yearRight.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				calendarInstance.add(Calendar.YEAR, +1);
				update();
			}
		});

		yearLeft.setPreferredSize(new Dimension(25,25));
		yearLeft.setMargin(new Insets(0,0,0,0));
		navigation.add(yearLeft);
		monthLeft.setPreferredSize(new Dimension(25,25));
		monthLeft.setMargin(new Insets(0,0,0,0));
		navigation.add(monthLeft);
		navigation.add(monthName);
		monthRight.setPreferredSize(new Dimension(25,25));
		monthRight.setMargin(new Insets(0,0,0,0));
		navigation.add(monthRight);
		yearRight.setPreferredSize(new Dimension(25,25));
		yearRight.setMargin(new Insets(0,0,0,0));
		navigation.add(yearRight);
		spaceHolder = new JPanel(new BorderLayout());

		spaceHolder.add(backToPresent, BorderLayout.NORTH);
		spaceHolder.add(navigation, BorderLayout.SOUTH);
		this.header.add(infoIconPanel, BorderLayout.WEST);
		this.header.add(spaceHolder, BorderLayout.CENTER);
		this.header.add(menuIconPanel, BorderLayout.EAST);
		
		this.master = new JPanel();
		this.master.setLayout(new GridLayout(7,7,1,1));

		if(calendarInstance.get(Calendar.DAY_OF_MONTH) != 1)
			calendarInstance.add(Calendar.DAY_OF_MONTH, 1-calendarInstance.get(Calendar.DAY_OF_MONTH));
		if(calendarInstance.get(Calendar.DAY_OF_WEEK) == 2)
			calendarInstance.add(Calendar.DAY_OF_MONTH, -7);
			calendarInstance.add(Calendar.DAY_OF_MONTH, -((((calendarInstance.get(Calendar.DAY_OF_WEEK)-2)%7)+7)%7));
		
		calendarInstance.roll(Calendar.DAY_OF_WEEK, 2-calendarInstance.get(Calendar.DAY_OF_WEEK));
		for(int i=0; i<7; i++) {
			JLabel dayLabel = new JLabel(calendarInstance.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US), SwingConstants.CENTER);
			if(darkThemed) dayLabel.setForeground(Color.WHITE);
			master.add(dayLabel);
			calendarInstance.roll(Calendar.DAY_OF_WEEK, 1);
		}
		
		for(int i=0; i<42; i++) {
			DayButton button = new DayButton(String.valueOf(calendarInstance.get(Calendar.DAY_OF_MONTH)), calendarInstance.getTime());
			if(darkThemed) button.setForeground(Color.WHITE);
			button.setPreferredSize(new Dimension(60,40));
			if((calendarInstance.get(Calendar.DAY_OF_MONTH) == currentDayOfMonth) && 
			   (calendarInstance.get(Calendar.MONTH) == currentMonth)) {
				if(darkThemed)
					button.setBackground(new Color(185,185,185));
				else
					button.setBackground(new Color(250,250,250));
				Color darker = new Color(255,192,76);
				Color lighter = new Color(255,219,153);
				button.setBorder(BorderFactory.createBevelBorder(1, lighter, darker, lighter, darker));
				button.addMouseListener(new DayButtonMouseAdapter(true));
			}
			else {
				if((calendarInstance.get(Calendar.DAY_OF_WEEK) == 1) ||
				   (calendarInstance.get(Calendar.DAY_OF_WEEK) == 7)) {
					if(calendarInstance.get(Calendar.MONTH) != currentMonth) {
						button.setBorder(BorderFactory.createLineBorder(new Color(200,200,200), 1));
						button.setEnabled(false);
						if(darkThemed) button.setBackground(new Color(102,102,102));
						else button.setBackground(weekend);
					}
					else {
						button.addMouseListener(new DayButtonMouseAdapter(false));
						if(darkThemed) button.setBackground(weekendDark);
						else button.setBackground(weekend);
					}
				}
				else
					if(calendarInstance.get(Calendar.MONTH) != currentMonth) {
						button.setBorder(BorderFactory.createLineBorder(new Color(210,210,210), 1));
						button.setEnabled(false);
						if(darkThemed) button.setBackground(new Color(112,112,112));
						else button.setBackground(dniPowszednie);
					}
					else {
						button.addMouseListener(new DayButtonMouseAdapter(false));
						if(darkThemed) button.setBackground(dniPowszednieDark);
						else button.setBackground(dniPowszednie);
					}
			}
			this.master.add(button);
			calendarInstance.add(Calendar.DAY_OF_MONTH, 1);
		}
		calendarInstance.add(Calendar.MONTH, currentMonth-calendarInstance.get(Calendar.MONTH));
		calendarInstance.add(Calendar.DAY_OF_MONTH, currentDayOfMonth-calendarInstance.get(Calendar.DAY_OF_MONTH));
		calendarInstance.add(Calendar.YEAR,  currentYear-calendarInstance.get(Calendar.YEAR));
		
		if(darkThemed) { 
			Color dark = Color.DARK_GRAY;
			header.setBackground(dark);
			master.setBackground(dark);
			backToPresent.setBackground(dark);
			navigation.setBackground(dark);
			menuIconPanel.setBackground(dark);
			infoIconPanel.setBackground(dark);
		}
		

		spaceHolder.add(backToPresent, BorderLayout.NORTH);
		spaceHolder.add(navigation, BorderLayout.SOUTH);
		this.header.add(infoIconPanel, BorderLayout.WEST);
		this.header.add(spaceHolder, BorderLayout.CENTER);
		this.header.add(menuIconPanel, BorderLayout.EAST);
		
		
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(header, BorderLayout.NORTH);
		this.add(master, BorderLayout.CENTER);
        if(dim != null) 
        	this.setPreferredSize(dim);
        else
        	this.pack();
        this.setVisible(true);
        
	}
	
	/**
	 * Metoda uaktualniająca komponenty przy zmianie miesiąca lub roku
	 */
	public void update() {
		this.remove(backToPresent);
		this.remove(header);
		this.remove(master);
		init(this.getSize());
	}
}
