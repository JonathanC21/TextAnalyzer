package com.valencia;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class wordCount {
	
	private HashMap<String, Integer> wordFrequency;
	private Object[] wordsArray;
	
	public wordCount(String fullString) {
		
		/*
		 * Constructor method for wordCount class: Counts the occurrences of each unique word in a given string.
		 * @Param fullString is the string for the method to analyze for words
		 * fullString is first converted to lowercase and then split into words using the split method and then set to String s for further computation
		 * HashMap wordFrequency is created and used to map each unique word to a value which increments for each unique occurrence in String s.
		 * sortWords() is called to sort wordFrequency into the top occurring values and printWords() prints the top 20 words and their occurrences.
		 */
		
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
	
	public void printWords() {
		
		/*
		 * Prints the top 20 highest occurring words and their occurrences to the console.
		 * Uses the Int count to only print the first 20 values before breaking from the loop.
		 * 
		 */
		int count = 1;
		
		for (Object i : wordsArray) {
			
		    System.out.println(count + ": (Word: " + ((Map.Entry<String, Integer>) i).getKey() + " , Occurences: "+ ((Map.Entry<String, Integer>) i).getValue() + ")");
		    count++;
		    
		    if (count > 20) {
		    	break;
		    }
		}
		
		System.out.println("\nSuccess!");
	}
	
	private void sortWords() {
		
		/*
		 * Sorts the wordFrequency HashMap by turning it into an Object array to have its second dimension(the occurring values) be compared to each other to sort it by highest occurring values first.
		 * The Comparator compares the value of the return from compare to determine if the value of o1 is greater than the value of o2.
		 */
		
		wordsArray = wordFrequency.entrySet().toArray();
		
		Arrays.sort(wordsArray, new Comparator() {
			
			   public int compare(Object o1, Object o2) {
				   
			        return ((Map.Entry<String, Integer>) o2).getValue().compareTo(((Map.Entry<String, Integer>) o1).getValue());
			    }
		});
	}
}
