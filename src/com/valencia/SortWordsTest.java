package com.valencia;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class SortWordsTest {

	@Test
	void test() {
		HashMap testHash = new HashMap();
		testHash.put("a", 1);
		testHash.put("b", 3);
		testHash.put("c", 2);
		
		String[] outputString = {"b=3","c=2","a=1"};
		
		Object[] output = Arrays.copyOf(outputString, outputString.length, Object[].class);
		
		
		WordCount test = new WordCount("");
		
		Object[] actOutput = test.sortWords(testHash);
				
		assertEquals(output[0],actOutput[0].toString());
	}

}
