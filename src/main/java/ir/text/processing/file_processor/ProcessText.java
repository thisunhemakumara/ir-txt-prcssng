package ir.text.processing.file_processor;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;

import ir.text.processing.functions.IsolatedWordSpellCheckerText;
import ir.text.processing.functions.TextLemmatizer;
import ir.text.processing.functions.TextSentenceParser;
import ir.text.processing.functions.TextStemmer;
import ir.text.processing.functions.TextTokenizer;

import java.util.ArrayList;

public class ProcessText {

	public static void main(String[] args) {
		
		File directoryPath = new File(Paths.sInputDir);
		File[] files=directoryPath.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".txt");
			}
		});
		
		TextTokenizer textTokenizer = new TextTokenizer();
		TextStemmer textStemmer = new TextStemmer();
		TextLemmatizer textLemmatizer = new TextLemmatizer();
		
		IsolatedWordSpellCheckerText isolatedWordSpellCheckerText = new IsolatedWordSpellCheckerText();
		try {
			isolatedWordSpellCheckerText.indexDictionary();
		} catch (IOException e) {
			System.out.println("Error during Indexing the Dictionary : "+Paths.sModelDir+"dictionary.txt"+System.lineSeparator()+" -->> "+e);
			e.printStackTrace();
		}
		
		TextSentenceParser textSentenceParser = new TextSentenceParser();
		
		for (File file : files) {
			
			System.out.println("Get File : "+file.getName());
			
			try {
				
				textTokenizer.tokenizeFile(Paths.sInputDir+file.getName(), Paths.sTokenizedPath+file.getName());
			
			} catch (IOException e) {
				System.out.println("Error during Tokenization of File : "+Paths.sInputDir+file.getName()+System.lineSeparator()+" -->> "+e);
				e.printStackTrace();
			}
			
			try {
				
				textStemmer.stemFile(Paths.sTokenizedPath+file.getName(), Paths.sStemmedPath+file.getName());
			
			} catch (IOException e) {
				System.out.println("Error during Stemming of File : "+Paths.sTokenizedPath+file.getName()+System.lineSeparator()+" -->> "+e);
				e.printStackTrace();
			}
			
			try {
				
				textLemmatizer.lemmatizeFile(Paths.sTokenizedPath+file.getName(), Paths.sLemmatizedPath+file.getName());
			
			} catch (IOException e) {
				System.out.println("Error during Lemmatization of File : "+Paths.sTokenizedPath+file.getName()+System.lineSeparator()+" -->> "+e);
				e.printStackTrace();
			}
			
			try {
				
				isolatedWordSpellCheckerText.isolatedWordSuggestion(Paths.sTokenizedPath+file.getName(), Paths.sIsolatedSpellCorrectedPath+file.getName());
			
			} catch (IOException e) {
				System.out.println("Error during Isolated Spell Correction of File : "+Paths.sTokenizedPath+file.getName()+System.lineSeparator()+" -->> "+e);
				e.printStackTrace();
			}
			
			try {
				
				textSentenceParser.parseSentencesToFile(Paths.sInputDir+file.getName(), Paths.sSentencesPath+file.getName());
			
			} catch (IOException e) {
				System.out.println("Error during Parsing Sentences of File : "+Paths.sInputDir+file.getName()+System.lineSeparator()+" -->> "+e);
				e.printStackTrace();
			}
			
		} 
		
		
		
		

	}

}
