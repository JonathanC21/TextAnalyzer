package com.valencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Contains methods for viewing and manipulating the wordoccurences database
 *
 */
public final class Database {
	
	/**
	 * Sets up a connection to the wordoccurences database and returns the connection object
	 * 
	 * @return Returns the database connection object
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		
		try {
			
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost/wordoccurrences";
			String username = "root";
			String password = "pass";
			Class.forName(driver);
			
			Connection con = DriverManager.getConnection(url, username, password);;
			return con;
		}
		
		catch (SQLException e){
			
			System.out.println(e.getMessage());
		}

		return null;
	}
	
	
	/**
	 * Clears the word table in the wordoccurences database of all of its rows
	 * 
	 * @throws Exception
	 */
	public static void truncateWord() throws Exception {
		
		try {
			
			Connection con = Database.getConnection();
			PreparedStatement addWord = con.prepareStatement("TRUNCATE word");
			addWord.execute();
			
			System.out.println("Cleared word table");
			
		} catch (SQLException e) {
			
			System.out.println(e.getMessage());
		}
		finally {
			
			Database.getConnection().close();
		}
	}
	
	
	/**
	 * Inserts a string value into the word table's word column
	 * 
	 * @param word The word string to insert into the word table's word column
	 * @throws Exception
	 */
	public static void insertIntoDatabase(String word,Connection con) throws Exception {
		final String words = word;
		
		try {
			
			PreparedStatement addWord = con.prepareStatement("INSERT INTO word (word_id) VALUES ('"+ words +"')");
			addWord.execute();
			
			
		} catch (SQLException e) {
			
			
			if (e.getSQLState().equals("23000")) {
				
				PreparedStatement updateFrequency = con.prepareStatement("UPDATE word SET frequency = frequency + 1 WHERE word_id = '"+ words +"' ");
				updateFrequency.execute();
			}
			
		}
	}
	
	public static void selectRowFromDatabase(Connection con) throws Exception {
		
		try {
			
			PreparedStatement getRow = con.prepareStatement("SELECT * FROM word");
			ResultSet result = getRow.executeQuery();
			
			while (result.next()) {
				
				System.out.println(result.getString(1) + result.getInt(2));
			}
		}
		catch(SQLException e){
			
			
		}
	}
}
