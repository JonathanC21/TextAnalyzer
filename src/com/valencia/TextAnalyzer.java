package com.valencia;

/**
 * Handles the main analyzation classes used to get the word frequencies.
 * 
 *
 */
public class TextAnalyzer {
	
	FileReader fileReader;
	WordCount wordCount;
	
	/**
	 * Runs the programs by initiating the FileReader and WordCount classes as objects.
	 * 
	 */
	public void run() {
		
		System.out.println("Starting program...\n");
		
		fileReader = new FileReader();
		wordCount = new WordCount(fileReader.getFileString());
		
	}
	
	/**
	 * Accessor method for getting a string containing one word and its frequency. The index of the word is incremented each time the method is called.
	 * 
	 * @return Returns a string containing the word and its frequency in an organized format
	 */
	public String getLines() {

		return wordCount.getWords();
	}
}
