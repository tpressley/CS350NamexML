package personalNameExtractor;

public class Trainer {
	
	/**
	 * Takes the initial trainingMaterial and converts it into a tokenized form
	 * and outputs the tokenized text
	 * @param inputText
	 */
	public String tokenize(String trainingMaterial){};
	/**
	 * Uses the tokenized text to convert the text into a form using identifiers 
	 * which can be used to train the learning machine
	 * @param tokenizedText
	 */
	public String addIdentifiers(String tokenizedText){};
}
