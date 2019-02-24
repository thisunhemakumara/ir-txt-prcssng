package ir.text.processing.functions;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TextTokenizer {
	
	public void tokenizeFile(String sInputFile, String sOutputFile) throws IOException 
	{
		System.out.println("Tokenize File : "+sInputFile+" and write to : "+sOutputFile);

	    StringBuilder tokens = new StringBuilder();

	    PTBTokenizer<CoreLabel> ptbt = new PTBTokenizer<>(new FileReader(sInputFile), new CoreLabelTokenFactory(), "");
	    while (ptbt.hasNext()) {
	      CoreLabel label = ptbt.next();
	      tokens.append(label).append(System.lineSeparator());
	    }
	    
	    BufferedWriter writer = new BufferedWriter(new FileWriter(sOutputFile));
	    writer.write(tokens.toString());
	    writer.close();
	    
	    System.out.println("Tokenization Completed for File : "+sInputFile);
	}
}
