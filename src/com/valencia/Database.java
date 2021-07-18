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
			String url = "jdbc:mysql://localhost/wordoccurences";
			String username = "root";
			String password = "";
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
	 * Inserts a string value into the word table's word column. Since the word_id column is unique, each duplicate word triggers an increment to the frequency column for the same row
	 * 
	 * @param word The word string to insert into the word table's word column
	 * @param con The database connection
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
	
	/**
	 * Selects a row from the wordoccurences database using a database and outputs the word and frequency column as a string
	 * 
	 * @param con The database connection
	 * @param count The row to select from
	 * @return Returns the string value of the word and frequency column formatted
	 * @throws Exception
	 */
	public static String selectRowFromDatabase(Connection con,int count) throws Exception {
		
		String line = "";
		
		try {
			
			PreparedStatement getRow = con.prepareStatement("SELECT * FROM word ORDER BY frequency DESC");
			ResultSet result = getRow.executeQuery();
			
			for (int  i = 0; i < count; i++) {
				
				result.next();
			}
			
			line =  count + ". Word: '" + result.getString(1) + "' Frequency: " + result.getInt(2);
		}
		catch(SQLException e){
			
			System.out.println(e.getMessage());
		}
		
		return line;
	}
}
