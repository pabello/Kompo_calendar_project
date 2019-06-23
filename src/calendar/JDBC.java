package calendar;

import java.sql.*;

/**
 * Klasa łącząca program z baza danych.
 *
 */
public class JDBC {
	String connectionParameters;
	String username;
	Connection conn = null;
	String databaseUsername = "luser";
	String databasePassword = "Hexagonal_User1";
	
	PreparedStatement writeStatement = null;
	String createString;
	String dropString;
	String readString;
	String writeString;
	
	/**
	 * Kontruktor konektora.
	 */
	JDBC() {
		connectionParameters = ("jdbc:mysql://127.0.0.1/calendar_events?user=luser&password=Hexagonal_User1&serverTimezone=Etc/GMT-1");
		this.username = Main.getUsername();
	}
	
	/**
	 * Metoda Czytająca liste wydarzeń z listy.
	 * @return odczytana z bazy lista wydarzeń.
	 * @throws SQLException
	 * @throws Exception
	 */
	EventList read() throws SQLException, Exception {
		EventList eventList = new EventList();
		String readString = "SELECT * FROM `" + username + "`";
		
		try {
			conn = DriverManager.getConnection(connectionParameters, databaseUsername, databasePassword);
			Class.forName("com.mysql.cj.jdbc.Driver");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(readString);
			
			while(rs.next()) {
				Event event = new Event(rs.getString(1), rs.getString(2), new Date((rs.getTimestamp(3).getTime())));
				eventList.add(event);
			}
			
		} catch(SQLException sqle) {
			System.out.println("Database connection error: " + sqle.getMessage());
			throw new Exception("Could not connect to the database.");
		} catch(ClassNotFoundException cnfe) {
			System.out.println("Could not load SQL driver.");
			throw new Exception("Could not connect to the database.");
		} finally {
			conn.close();
		}
		
		return eventList;
	}
	
	
	/**
	 * Metoda czyszcząca bazę danych.
	 * @throws SQLException
	 */
	void drop() throws SQLException {
		String dropString = "DROP TABLE IF EXISTS " + username;
		
		try {
			conn = DriverManager.getConnection(connectionParameters, databaseUsername, databasePassword);
			conn.setAutoCommit(false);
			Class.forName("com.mysql.cj.jdbc.Driver");
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(dropString);
			System.out.println(stmt.getUpdateCount());
			conn.commit();
			
		} catch (SQLException sqle) {
			System.out.println("Could not acces the database.");
			System.out.println(sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Could not load SQL driver.");
		} finally {
			conn.setAutoCommit(true);
			conn.close();
		}
	}
	
	/**
	 * Metoda tworząca tebelę w bazie.	
	 * @throws SQLException
	 */
	void create() throws SQLException {
		String createString = "CREATE OR REPLACE TABLE `" +username+ "` ("
				+ "`event_name` VARCHAR (100) NOT NULL, "
				+ "`event_place` VARCHAR (50) NULL, "
				+ "`event_time` TIMESTAMP NOT NULL);";
		
		try {
			conn = DriverManager.getConnection(connectionParameters, databaseUsername, databasePassword);
			conn.setAutoCommit(false);
			Class.forName("com.mysql.cj.jdbc.Driver");
			Statement stmt = conn.createStatement();
			System.out.println(createString);
			stmt.execute(createString);
			conn.commit();
		} catch (SQLException sqle) {
			System.out.println("Could not acces the database.");
			System.out.println(sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Could not load SQL driver.");
		} finally {
			conn.setAutoCommit(true);
			conn.close();
		}
	}
	
	/*
	 * Tabela wstawiająca dane do tabeli
	 */
	void insert() throws SQLException {
		String insertString = "INSERT INTO " +username+ " (`event_name`, `event_place`, `event_time`)"
				+ " VALUES (?, ?, ?)";
		
		try {
			conn = DriverManager.getConnection(connectionParameters, databaseUsername, databasePassword);
			conn.setAutoCommit(false);
			Class.forName("com.mysql.cj.jdbc.Driver");
			writeStatement = conn.prepareStatement(insertString);
			
			for(Event event : CalendarView.eventList) {
				System.out.println("eventName=" + event.getName());
				writeStatement.setString(1, event.getName());
				System.out.println("eventPlace=" + event.getPlace());
				writeStatement.setString(2, event.getPlace());
				writeStatement.setTimestamp(3, new Timestamp(event.getEventTime().getTime()));
				writeStatement.executeUpdate();
				conn.commit();
			}
		} catch (SQLException sqle) {
			System.out.println("Could not acces the database.");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Could not load SQL driver.");
		} finally {
			conn.setAutoCommit(true);
			conn.close();
		} 
	}
}
