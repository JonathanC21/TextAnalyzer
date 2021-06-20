package com.valencia;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Contains methods used to find the amount of words from a string and their frequencies
 */
public class wordCount {
	
	private HashMap<String, Integer> wordFrequency;
	private Object[] wordsArray;
	private int count = 1;
	
	/**
	 * Constructor method for wordCount class, Counts the occurrences of each unique word in a given string. Then, uses a hashmap to sort the words and print them to the console.
	 * 
	 * @param fullString String to be analyzed
	 */
	public wordCount(String fullString) {
		
		String[] s;
		
		fullString = fullString.toLowerCase();
		s = fullString.split("\\W+");
		
		wordFrequency = new HashMap<String, Integer>();
		
		System.out.println("Finding the most frequent words...\n");
		
		for (int i = 0; i < s.length; i++) {
			
			String key = s[i];
			int occur = wordFrequency.getOrDefault(key, 0);
			
			wordFrequency.put(key, ++occur);
		}
		
		sortWords();
		printWords();
	}
	
	/**
	 * Returns a single line of string of a word and its occurrences. The index of the word is incremented by one each time the method is called.
	 * 
	 * @return Returns a string with a formatted line containing the word and its frequency of occurrence.
	 */
	public String getWords() {
		
		String line = "";
		
		for (Object i : wordsArray) {
			
			i = wordsArray[count - 1];
			
		    line = count + ": Word: " + ((Map.Entry<String, Integer>) i).getKey() + " , Occurences: "+ ((Map.Entry<String, Integer>) i).getValue() + "";
		    count++;
		    
		    return line;
		}
		
		return "";
	}
	
	/**
	 * Prints the top 20 highest occurring words and their occurrences to the console.
	 */
	public void printWords() {
		
		int c = 1;
		
		for (Object i : wordsArray) {
			
		    System.out.println(c + ": (Word: " + ((Map.Entry<String, Integer>) i).getKey() + " , Occurences: "+ ((Map.Entry<String, Integer>) i).getValue() + ")");
		    c++;
		    
		    if ( c > 20) {
		    	break;
		    }
		}
		
		System.out.println("\nSuccess!");
	}
	
	/**
	 * Sorts wordFrequency HashMap to make it organized by the highest occurring word first.
	 */
	private void sortWords() {
		
		wordsArray = wordFrequency.entrySet().toArray();
		
		Arrays.sort(wordsArray, new Comparator() {
			
			   public int compare(Object o1, Object o2) {
				   
			        return ((Map.Entry<String, Integer>) o2).getValue().compareTo(((Map.Entry<String, Integer>) o1).getValue());
			    }
		});
	}
}
