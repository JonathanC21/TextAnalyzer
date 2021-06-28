package com.valencia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WordCountTest {

	@Test
	void test() {
		WordCount test = new WordCount("test");
		assertNotNull(test);
		
	}
}
