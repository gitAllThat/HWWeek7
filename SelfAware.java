package edu.gcccd.csis;

import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;

public class SelfAware implements Language {
	
    public static void main(String[] args) throws IOException
	{
		// Set codeLoc to location of code
		final String codeLoc = System.getProperty("user.dir") + File.separator + SelfAware.class.getName().replace(".", File.separator) + ".java";
		// Sort the array of reserved words.
		Language.sort();
		// Create an object of itself so we can call non-static methods
		SelfAware me = new SelfAware();
		// Try to run the methods, catch exceptions and spit them out
		try{

			me.append(codeLoc, ""+ me.occurrences(codeLoc));
			//System.out.println("The total occurrences of reserved words is: " + me.occurrences(codeLoc));
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}	
	}
	// Method for occurences
	public int occurrences(String source) throws Exception
	{
		// Int to hold total count
		int count = 0;
		try 
		{
			// Objects used for reading file
			FileReader theFile = new FileReader(source);
			BufferedReader readFile = new BufferedReader (theFile);
			String line;
			
			// While there is a new line, ie.. != null, keep reading
			while ((line = readFile.readLine()) != null)
			{
				// For every line, cycle through reserved words array and increment the count if one is found
				for (int i = 0; i < ReservedWords.length; i++)
				{
					if (line.indexOf(ReservedWords[i]) != -1)
					{
						count++;
					}
				}
			}
		}
		// Catch exceptions. Spit them out
		catch (Exception ex)
		{
			System.out.println(ex.toString());
		}
		return count;
	}		
	// Method for appending the comment to the code
	public void append(String sourceFile, String message) throws IOException
	{
		// Object for writing
		FileWriter writeTo = new FileWriter(sourceFile, true);
		// Message to write
		writeTo.write("\n//Keyword occurrences: " + message);
		// Object needs to run close in order for changes to be saved into file
		writeTo.close();
	}
}
//Keyword occurrences: 54