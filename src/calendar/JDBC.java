package calendar;

import java.sql.*;

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
	
	JDBC(String username) {
		connectionParameters = ("jdbc:mysql://127.0.0.1/calendar_events?user=luser&password=Hexagonal_User1&serverTimezone=Etc/GMT-1");
		this.username = username;
	}
	
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
	
	void insert(EventList eventList) throws SQLException {
		String insertString = "INSERT INTO " +username+ " (`event_name`, `event_place`, `event_time`)"
				+ " VALUES (?, ?, ?)";
		
		try {
			conn = DriverManager.getConnection(connectionParameters, databaseUsername, databasePassword);
			conn.setAutoCommit(false);
			Class.forName("com.mysql.cj.jdbc.Driver");
			writeStatement = conn.prepareStatement(insertString);
			
			for(Event event : eventList) {
				System.out.println("eventName=" + event.name);
				writeStatement.setString(1, event.name);
				System.out.println("eventPlace=" + event.place);
				writeStatement.setString(2, event.place);
				writeStatement.setTimestamp(3, new Timestamp(event.eventTime.getTime()));
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
