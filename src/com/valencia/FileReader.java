package com.valencia;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class FileReader {

	final private String FILE_NAME = "The Project Gutenberg eBook of The Raven, by Edgar Allan Poe.htm";
	final private Path filePath = FileSystems.getDefault().getPath("", FILE_NAME);
	private String fileString = "";
	
	
		public FileReader() {
			
			/*
			 * Constructor method for FileReader Class
			 * Reads the text from the file using the readAllLines method with the file path as its parameter
			 * Builds string of the entire file by iterating each line of the file
			 * Replaces all of the html tags in the file to blank spaces
			 * Shortens the string to the poem only using the characters before and after the poem as index parameters for the substring method
			 */
			
			List<String> fileLineString = new ArrayList<String>();
			
			System.out.println("Reading " + FILE_NAME + "...");
			
			try {
				
				fileLineString = Files.readAllLines(filePath);
				
				StringBuilder sb = new StringBuilder();
				
				for (String i: fileLineString) {
					
					sb.append(i);
				}
				
				System.out.println("Finished reading!\nConverting to string and replacing HTML tags...");
				
				fileString = sb.toString();
				fileString = fileString.replaceAll("\\<.*?>", "");
				
				System.out.println("Getting the poem from the text...");
				
				fileString = fileString.substring(fileString.indexOf("EBOOK"));
				fileString = fileString.substring(fileString.indexOf("The Raven"));
				fileString = fileString.substring(0, fileString.indexOf("***"));
				
				System.out.println("Successfull! Poem is ready to be analyzed.\n");
				
			} catch (IOException e) {
				
				System.out.println("File path is incorrect.");
				System.exit(0);
			}
		}
		
		public String getFileString() {
			
			/*
			 * Accessor method for fileString, which is a string of the poem after it has been modified by the constructor
			 */
			
			return fileString;
		}
}
