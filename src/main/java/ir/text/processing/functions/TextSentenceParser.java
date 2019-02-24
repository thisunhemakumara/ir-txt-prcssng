package ir.text.processing.functions;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.process.DocumentPreprocessor;
import opennlp.tools.stemmer.PorterStemmer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextSentenceParser {
	
	public void parseSentencesToFile(String sInputFile, String sOutputFile) throws IOException
	{
		System.out.println("Parse Sentences From File : "+sInputFile+" and write to : "+sOutputFile);

		//List<String> sentenceList = new ArrayList<>();
	    DocumentPreprocessor docProcessor = new DocumentPreprocessor(sInputFile);
	    
	    BufferedWriter writer = new BufferedWriter(new FileWriter(sOutputFile));
	    
	    for (List<HasWord> sentence : docProcessor) {
	      StringBuilder sPrintSentence = new StringBuilder();
	      for (HasWord word : sentence) {

	          sPrintSentence.append(word).append(" ");
	      }
	      System.out.println("Sentence : "+sPrintSentence.toString());
	      sPrintSentence.append(System.lineSeparator());
	     // sentenceList.add(stringBuilder.toString());
	      writer.write(sPrintSentence.toString());
	    }

	    writer.close();
	    
	    System.out.println("Parsing Sentences Completed for File : "+sInputFile);
	}/*

  public List<String> getSentences(String input){
    List<String> sentenceList = new ArrayList<>();
    DocumentPreprocessor dp = new DocumentPreprocessor(input);
    for (List<HasWord> sentence : dp) {
      StringBuilder stringBuilder = new StringBuilder();
      for (HasWord word : sentence) {

          stringBuilder.append(word).append(" ");
      }
      sentenceList.add(stringBuilder.toString());
    }

    return sentenceList;
  }*/
}
