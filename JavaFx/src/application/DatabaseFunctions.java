package application;
import java.sql.*;
import java.util.ArrayList;
public class DatabaseFunctions {
	
	public DatabaseFunctions() {
		createNewDatabase("scores.db");
	}
	
	public static void createNewDatabase(String fileName) {
		String url = "jdbc:sqlite:C:/testfile/db/" + fileName;
		try(Connection conn = DriverManager.getConnection(url)){
			if(conn != null) {
				DatabaseMetaData meta = conn.getMetaData();
				System.out.println("The driver name is " + meta.getDriverName());
				System.out.println("A new database was created.");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insertPerson(Person person) {
		final String userName = person.userName;
		final int userScore = person.userScore;
		final double time = person.time;
	
		Connection conn = null;
		
		try {
			String url = "jdbc:sqlite:C:/testfile/db/scores.db";
			conn = DriverManager.getConnection(url);
			
			System.out.println("Connection to SQLite has been established");
			
			String sql = "INSERT INTO scores (userName, userScore, time) VALUES (?,?,?)";
			
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setInt(2, userScore);
			pstmt.setDouble(3, time);
			pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally  {
			try {
				if (conn != null) {
					conn.close();
					System.out.println("Database closed.");
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
		
		
	}
	
	
//	Select Person Method
	public static Person selectPerson(String userName) {
		
		Connection conn2 = null;
		
		try {
			String url = "jdbc:sqlite:C:/testfile/db/scores.db";
			conn2 = DriverManager.getConnection(url);
			
			System.out.println("Connection to SQLite has been established");
			
			String sql = "SELECT * FROM scores WHERE userName = '" + userName + "';";
			
			Statement stmt = conn2.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			Person result = new Person(rs.getString(1),rs.getInt(2),rs.getDouble(3));
			return result;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally  {
			try {
				if (conn2 != null) {
					conn2.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
	
	public static ArrayList<Person> findTopTen(){
		Connection conn = null;
		ArrayList<Person> result = new ArrayList<>();
		try {
			String url = "jdbc:sqlite:C:/testfile/db/scores.db";
			conn = DriverManager.getConnection(url);
			
			System.out.println("Connection to SQLite has been established");
			
			String sql = "SELECT * FROM scores ORDER BY userScore DESC LIMIT 10";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Person personResult = new Person(rs.getString(2),rs.getInt(3), rs.getDouble(4));
				result.add(personResult);
			}
			return result;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally  {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
	
	
	public static void connect() {
		Connection conn = null;
		try {
			String url = "jdbc:sqlite:C:/testfile/db/people.db";
			conn = DriverManager.getConnection(url);
			
			System.out.println("Connection to SQLite has been established");
			
			
			
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally  {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
	
	
	
//	public static void main( String args[] ) {
//	      createNewDatabase("scores.db");
//	      
//	   }
	}
