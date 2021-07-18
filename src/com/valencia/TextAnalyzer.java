package com.valencia;

import java.sql.Connection;

/**
 * Handles the main analzsation classes used to get the word frequencies.
 * 
 *
 */
public class TextAnalyzer {
	
	FileReader fileReader;
	WordCount wordCount;
	
	/**
	 * Runs the programs by initiating the FileReader and WordCount classes as objects.
	 * @throws Exception 
	 * 
	 */
	public void run() throws Exception {
		
		System.out.println("Starting program...\n");
		
		fileReader = new FileReader();
		wordCount = new WordCount(fileReader.getFileString());
		
	}
	
	/**
	 * Accessor method for getting a string containing one word and its frequency. The index of the word is incremented each time the method is called.
	 * 
	 * @return Returns a string containing the word and its frequency in an organized format
	 * @throws Exception 
	 */
	public String getLines() throws Exception {

		return wordCount.getWordsFromDatabase();
	}
}
