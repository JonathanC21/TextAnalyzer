package com.valencia;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Contains methods used to find the amount of words from a string and their frequencies
 */
public class WordCount {
	
	private HashMap<String, Integer> wordFrequency;
	private Object[] wordsArray;
	private int count = 1;
	private Connection con;
	
	/**
	 * Constructor method for wordCount class, Counts the occurrences of each unique word in a given string. Then, uses a HashMap to sort the words and add them to the database.
	 * 
	 * @param fullString String to be analyzed
	 * @throws Exception 
	 */
	public WordCount(String fullString) throws Exception {
		
		Database.truncateWord();
		String[] s;
		
		fullString = fullString.toLowerCase();
		s = fullString.split("\\W+");
		
		wordFrequency = new HashMap<String, Integer>();
		
		System.out.println("Removing duplicates from the string...");
		
		for (int i = 0; i < s.length; i++) {
		  
			String key = s[i]; int occur = wordFrequency.getOrDefault(key, 0);
			wordFrequency.put(key, ++occur); 
		}
		
		System.out.println("Inserting words into database...");
		
		con = Database.getConnection();
		
		for (int i = 0; i < s.length; i++) {
			
			Database.insertIntoDatabase(s[i],con);
		}
	}
	
	/**
	 * Selects a row from the database using a count value that increments each time the method is called. This returns the database data from a specified row as a single line
	 * 
	 * @return Returns a string containing a single line from a database row
	 * @throws Exception
	 */
	public String getWordsFromDatabase() throws Exception {
		
		String line = Database.selectRowFromDatabase(con,count);
		count++;
		
		return line;
	}
}
