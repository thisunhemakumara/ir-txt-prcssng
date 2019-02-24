package ir.text.processing.functions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.spell.NGramDistance;
import org.apache.lucene.search.spell.PlainTextDictionary;
import org.apache.lucene.search.spell.SpellChecker;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import ir.text.processing.file_processor.Paths;
import opennlp.tools.stemmer.PorterStemmer;

public class IsolatedWordSpellCheckerText {

  private Directory indexDir;
  private SpellChecker wordCorrector;
  
  public void indexDictionary() throws IOException
  {
	  System.out.println("Start Indexing the Dictionary");
	  
	  this.indexDir = FSDirectory.open(new File(Paths.sModelDir+"fsDirectory"));
	  this.wordCorrector = new SpellChecker(indexDir);
	  this.wordCorrector.indexDictionary(new PlainTextDictionary(new File(Paths.sModelDir+"dictionary.txt")), new IndexWriterConfig(Version.LUCENE_36, null), false);
	  this.wordCorrector.setStringDistance(new NGramDistance());
	  
	  System.out.println("Indexing the Dictionary Completed");
  }
  
  public void isolatedWordSuggestion(String sInputFile, String sOutputFile) throws IOException
  {
	  System.out.println("Isolated Word Suggestion for File : "+sInputFile+" and write to : "+sOutputFile);

	    BufferedReader reader = new BufferedReader(new FileReader(sInputFile));
	    BufferedWriter writer = new BufferedWriter(new FileWriter(sOutputFile));
	    String line;

	    while ((line = reader.readLine()) != null) {

	    	int iNoOfSuggestedWords = 5;
	        StringBuilder sPrintLine = new StringBuilder();
	        line = line.toLowerCase(Locale.US);

	        if(!wordCorrector.exist(line)) {
	          String[] wordSuggestions = wordCorrector.
	              suggestSimilar(line, iNoOfSuggestedWords);

	          if (wordSuggestions!=null && wordSuggestions.length>0) {
	        	  
	        	  sPrintLine.append("Incorrect Spellings detected \n");
	        	  System.out.println("Incorrect Spellings detected ");
	        	  
	        	  sPrintLine.append("Suggested Spellings for word " +line + ": ");
	        	  System.out.println("Suggested Spellings for word " +line + ": ");
	        	  
	        	  for (int i = 0 ; i < wordSuggestions.length; i++) {
	        		  if(i == wordSuggestions.length -1) {
	        			  
	        			  sPrintLine.append(wordSuggestions[i]);
	        			  System.out.println(wordSuggestions[i]);
	        			  
		              } else {
		            	  
		                sPrintLine.append(wordSuggestions[i] + ", ");
		                System.out.println(wordSuggestions[i] + ", ");
		                
		              }
	        	  }
	        	  sPrintLine.append("\n");
	          }
	          else {
	        	  
	        	  sPrintLine.append("No alternative spellings found for word:"+line + "\n");
	        	  System.out.println("No alternative spellings found for word:"+line + "\n");
	        	  
	          }
	        } else {
	        	
	          sPrintLine.append(line + " has correct spelling\n");
	          System.out.println(line + " has correct spelling\n");
	          
	        }

	        writer.write(sPrintLine.toString());

	    }
	    System.out.println("Accuracy of the Spell Checker : "+wordCorrector.getAccuracy());
	    reader.close();
	    writer.close();
	    
	    System.out.println("Isolated Word Suggestion Completed for File : "+sInputFile);
  }
}
