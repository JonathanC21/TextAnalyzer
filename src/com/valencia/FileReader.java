package com.valencia;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains methods used to read from a file and get its string value.
 */
public class FileReader {

	final private String FILE_NAME = "The Project Gutenberg eBook of The Raven, by Edgar Allan Poe.htm";
	final private Path filePath = FileSystems.getDefault().getPath("", FILE_NAME);
	private String fileString = "";
	
	
		/**
		 * Constructor method for FileReader. Removes html tags and gets a string value from a file.
		 */
		public FileReader() {
			
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
		
		/**
		 * Accessor method returning the string value from a file after its html tags have been removed.
		 * 
		 * @return Returns the string value from the file 
		 */
		public String getFileString() {
			
			return fileString;
		}
}
