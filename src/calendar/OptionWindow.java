package calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * 
 * Klasa okna opcji.
 */
public class OptionWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	JPanel master;
	JPanel management;
	JPanel csvHolder;
	CalendarView calendarRef;
	
	/**
	 * Konstuktor okna opcji.
	 * @param calendar Okno kaledarza krórego dotyczy okno opcji.
	 */
	public OptionWindow(CalendarView calendar) {
		super("Options");
		this.calendarRef = calendar;
		init();
	}
	
	/**
	 * Funkcja rysująca okno opcji.
	 */
	private void init() {
		this.setVisible(true);
		
		master = new JPanel();
		management = new JPanel();
		csvHolder = new JPanel();
		
		master.setLayout(new BorderLayout());
		management.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
		
		JCheckBox darkThemedBox = new JCheckBox("Dark themed mode", CalendarView.darkThemed);
		darkThemedBox.setBackground(Color.LIGHT_GRAY);
//		darkThemedBox.setForeground(new Color(255,237,15)); // z�oty kolor, �eby by�o wida�, �e premium apka xd		problem w tym, ze jes s�abo widoczny xdd
		darkThemedBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CalendarView.darkThemed = !CalendarView.darkThemed;
				update();
			}
		});
		darkThemedBox.setHorizontalAlignment(SwingConstants.CENTER);

		ImageIcon dbImportIcon = new ImageIcon("images\\dbimport32.png");
		JLabel dbImport = new JLabel("DB Import", dbImportIcon, SwingConstants.CENTER);
		dbImport.setToolTipText("Import events from database");
		ImageIcon dbExportIcon = new ImageIcon("images\\dbexport32.png");
		JLabel dbExport = new JLabel("DB Export", dbExportIcon, SwingConstants.CENTER);
		dbExport.setToolTipText("Export events to database");
		ImageIcon xmlImportIcon = new ImageIcon("images\\xmlimport32.png");
		JLabel xmlImport = new JLabel("XML Import", xmlImportIcon, SwingConstants.CENTER);
		xmlImport.setToolTipText("Import events from XML sheet");
		ImageIcon xmlExportIcon = new ImageIcon("images\\xmlexport32.png");
		JLabel xmlExport = new JLabel("XML Export", xmlExportIcon, SwingConstants.CENTER);
		xmlExport.setToolTipText("Export events to XML sheet");
		ImageIcon csvImportIcon = new ImageIcon("images\\csvimport32.png");
		JLabel csvImport = new JLabel("CSV Import", csvImportIcon, SwingConstants.CENTER);
		csvImport.setToolTipText("Load events from standard .csv format");
		ImageIcon csvExportIcon = new ImageIcon("images\\csvexport32.png");
		JLabel csvExport = new JLabel("CSV Export", csvExportIcon, SwingConstants.CENTER);
		csvExport.setToolTipText("Save events in standard .csv format");
		
		csvImport.addMouseListener(new TransferButtonListener(0));
		csvExport.addMouseListener(new TransferButtonListener(1));
		xmlImport.addMouseListener(new TransferButtonListener(2));
		xmlExport.addMouseListener(new TransferButtonListener(3));
		dbImport.addMouseListener(new TransferButtonListener(4));
		dbExport.addMouseListener(new TransferButtonListener(5));
		
		management.add(csvImport);
		management.add(csvExport);
		management.add(xmlImport);
		management.add(xmlExport);
		management.add(dbImport);
		management.add(dbExport);

		//		GroupLayout layout = new GroupLayout(management);
//		management.setLayout(layout);
//		layout.setAutoCreateGaps(true);
//		layout.setAutoCreateContainerGaps(true);
//		layout.setHorizontalGroup(
//			layout.createSequentialGroup()
//			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
//				.addComponent(xmlImport)
//				.addComponent(dbImport))
//			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
//				.addComponent(xmlExport)
//				.addComponent(dbExport))
//		);
//		layout.setVerticalGroup(
//			layout.createSequentialGroup()
//			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
//				.addComponent(xmlImport)
//				.addComponent(xmlExport))
//			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
//				.addComponent(dbImport)
//				.addComponent(dbExport))
//		);
		
		
//		JPanel elko = new JPanel();
//		elko.setLayout(new BorderLayout());
//		elko.add(management, BorderLayout.CENTER);
//		ImageIcon csvExportIcon = new ImageIcon("images\\csvexport32.png");
//		JLabel csvExport = new JLabel("Save as .csv", csvExportIcon, SwingConstants.CENTER);
//		csvExport.setToolTipText("Save events in standard .csv format.");
//		csvExport.setHorizontalTextPosition(JLabel.CENTER);
//		csvExport.setVerticalTextPosition(JLabel.BOTTOM);
//		csvHolder.add(csvExport);
		
		if(CalendarView.darkThemed) {
			management.setBackground(Color.DARK_GRAY);
//			csvHolder.setBackground(Color.DARK_GRAY);
			
			dbImport.setForeground(Color.WHITE);
			dbExport.setForeground(Color.WHITE);
			xmlImport.setForeground(Color.WHITE);
			xmlExport.setForeground(Color.WHITE);
			csvImport.setForeground(Color.WHITE);
			csvExport.setForeground(Color.WHITE);
		}
		
//		master.add(csvHolder, BorderLayout.NORTH);
		master.add(management, BorderLayout.CENTER);
		master.add(darkThemedBox, BorderLayout.SOUTH);
		master.setPreferredSize(new Dimension(270, 180));
		this.add(master);
		this.pack();
	}
	
	void update() {
		calendarRef.update();
		if(CalendarView.dayView != null) { 
			CalendarView.dayView.colorUpdate();
			if(CalendarView.dayView.a != null)
				CalendarView.dayView.a.colorUpdate();
		}
		if(CalendarView.about != null)
			CalendarView.about.colorUpdate();
		if(CalendarView.searchWindow != null)
			CalendarView.searchWindow.updateColor();
		init();
	}
}
