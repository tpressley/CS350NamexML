package personalNameExtractor;

//import weka.core.Instances;
import java.io.BufferedReader;
import java.io.FileReader;

public class Trainer {

	/**
	 * Takes the initial trainingMaterial and converts it into a tokenized form
	 * and outputs the tokenized text split by space and punctuation E.G. Input
	 * "This function's input is split into tokens. It is split by punctuation, it is also split by spaces."
	 * Output: " This function 's input is split into tokens. It is split by
	 * punctuation, it is also split by spaces." I/O should be identical to the
	 * Standford English Tokenizer
	 *
	 * @param inputText
	 */
	public String[] tokenize(String trainingMaterial) {
		return null;
		/*
		 * should be easy enough to implement, java has a StringTokenizer class
		 */ };

	/**
	 * Uses the tokenized text to convert the text into a form using identifiers
	 * which can be used to train the learning machine
	 * 
	 * @param tokenizedText
	 */
	public String parse(String tokenizedText) {
		/*
		 * Still looking into formatting I/O, probably will work like the
		 * Stanford NL Parser
		 */ 
		return null;
	};

	/**
	 * Returns the the token count for a specific token
	 * 
	 * @param token
	 */
	public int getTokenCount(String token) {
		
		return 0;
	};

	/**
	 * Initializes the trainer and gets the training data from std input
	 */
	public Trainer() {

	};

	public String getTrainingData() {
		
		return null;
	};

	/**
	 * prepare training data for Learning Machine based on input parameter?
	 */
	public String prepareData(String in) {
		
		return null;

	}
	
	/**
	 * use existing data to train Learning Machine
	 */
	public boolean trainLM(String data) {
		//returns true if successful
		return false;
		
	}
/**
 * *
 * @param fileLoc
 * Loads a trained learning machine from file
 */
	public void LoadLM(String fileLoc){
	
	}
	/**
	 * 
	 * @param fileLoc
	 * Saves a trained learning machine to a file
	 */
	public void SaveLM(String fileLoc){
		
		
	}

}
