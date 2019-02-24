package ir.text.processing.functions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import opennlp.tools.stemmer.PorterStemmer;

public class TextStemmer {
	
	public void stemFile(String sInputFile, String sOutputFile) throws IOException
	{
		System.out.println("Stem File : "+sInputFile+" and write to : "+sOutputFile);

	    BufferedReader reader = new BufferedReader(new FileReader(sInputFile));
	    String line;
	    PorterStemmer stemmer = new PorterStemmer();
	    StringBuilder stems = new StringBuilder();

	    while ((line = reader.readLine()) != null) {
	      stems.append(stemmer.stem(line)).append(System.lineSeparator());
	    }
	    reader.close();
	    
	    BufferedWriter writer = new BufferedWriter(new FileWriter(sOutputFile));
	    writer.write(stems.toString());
	    writer.close();
	    
	    System.out.println("Stemming Completed for File : "+sInputFile);
	}
}
