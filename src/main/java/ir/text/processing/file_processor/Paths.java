package ir.text.processing.file_processor;

public class Paths {
	
	public static String sModelDir = "src/main/resources/models/";
	public static String sInputDir = "src/main/resources/input/";
	public static String sOutputDir = "src/main/resources/results/";
	
	public static String sStudentFeedBack = "student_feedback.txt";
	public static String sTwitter = "twitter.txt";
	public static String sResearchPaper = "research_paper.txt";
	
	public static String sTokenized = "tokenized";
	public static String sLemmatized = "lemmatized";
	public static String sStemmed = "stemmed";
	public static String sIsolatedSpellCorrected = "isolated_spell_corrected";
	public static String sSentences = "sentences";
	public static String sContextualSpellCorrected = "contextual_spell_corrected";
	
	public static String sSlash = "/";
	public static String sUnderscore = "_";
	
	public static String sTokenizedPath = Paths.sOutputDir+Paths.sTokenized+Paths.sSlash+Paths.sTokenized+Paths.sUnderscore;
	public static String sStemmedPath = Paths.sOutputDir+Paths.sStemmed+Paths.sSlash+Paths.sStemmed+Paths.sUnderscore;
	public static String sLemmatizedPath = Paths.sOutputDir+Paths.sLemmatized+Paths.sSlash+Paths.sLemmatized+Paths.sUnderscore;
	public static String sIsolatedSpellCorrectedPath = Paths.sOutputDir+Paths.sIsolatedSpellCorrected+Paths.sSlash+Paths.sIsolatedSpellCorrected+Paths.sUnderscore;
	public static String sContextualSpellCorrectedPath = Paths.sOutputDir+Paths.sContextualSpellCorrected+Paths.sSlash+Paths.sContextualSpellCorrected+Paths.sUnderscore;
	public static String sSentencesPath = Paths.sOutputDir+Paths.sSentences+Paths.sSlash+Paths.sSentences+Paths.sUnderscore;
	
}
