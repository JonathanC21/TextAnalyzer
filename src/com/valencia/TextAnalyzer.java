package com.valencia;

public class TextAnalyzer {
	
	public void run() {
		
		/*
		 * Creates two objects that are used for the program
		 * FileReader turns the file into a string
		 * TopWords uses the string from FileReader to compute the top occurring ones.
		 */
		System.out.println("Starting program...\n");
		
		FileReader fileReader = new FileReader();
		wordCount topWords = new wordCount(fileReader.getFileString());
	}
}
