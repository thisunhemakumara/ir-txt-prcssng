package ir.text.processing.functions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import ir.text.processing.file_processor.Paths;
import opennlp.tools.lemmatizer.SimpleLemmatizer;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

public class TextLemmatizer {
	
	public void lemmatizeFile(String sInputFile, String sOutputFile) throws IOException
	{
		System.out.println("Lemmatize File : "+sInputFile+" and write to : "+sOutputFile);
		
		BufferedReader reader = new BufferedReader(new FileReader(sInputFile));
	    String line;
	    StringBuilder lemmatizedWords = new StringBuilder();
	    List<String> lineList = new ArrayList<>();
	    String[] taggedArray = new String[lineList.size()];

	    InputStream posStrm = new FileInputStream(Paths.sModelDir+"en-pos-maxent.bin");
	    
	    POSModel model = new POSModel(posStrm);
	    POSTaggerME tagger = new POSTaggerME(model);

	    while ((line = reader.readLine()) != null) {
	      lineList.add(line);
	    }
	    reader.close();

	    String[] tags = tagger.tag(lineList.toArray(taggedArray));

	    InputStream lemmaStrm = new FileInputStream(Paths.sModelDir+"en-lemmatizer.dict");
	    
	    SimpleLemmatizer simpleLemmatizer = new SimpleLemmatizer(lemmaStrm);

	    for(int i = 0 ; i < lineList.size(); i++) {
	      lemmatizedWords.append(simpleLemmatizer.lemmatize(lineList.get(i), tags[i])).append(System.lineSeparator());
	    }
	    
	    BufferedWriter writer = new BufferedWriter(new FileWriter(sOutputFile));
	    writer.write(lemmatizedWords.toString());
	    writer.close();
		
		System.out.println("Lemmatization Completed for File : "+sInputFile);
	}
}
