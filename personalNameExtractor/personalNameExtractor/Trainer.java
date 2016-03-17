package personalNameExtractor;

public class Trainer {
	
	/**
	 * Takes the initial trainingMaterial and converts it into a tokenized form
	 * and outputs the tokenized text split by space and punctuation
	 * E.G. Input "This function's input is split into tokens. It is split by punctuation, it is also split by spaces."
	 * Output: 
	 * "
	 * This
	 * function
	 * 's
	 * input
	 * is
	 * split
	 * into
	 * tokens.
	 * It
	 * is
	 * split
	 * by
	 * punctuation,
	 * it
	 * is
	 * also
	 * split
	 * by
	 * spaces."
	 * I/O should be identical to the Standford English Tokenizer
	 *
	 * @param inputText
	 */
	public String tokenize(String trainingMaterial){ /* should be easy enough to implement, java has a StringTokenizer class*/ };
	
	/**
	 * Uses the tokenized text to convert the text into a form using identifiers 
	 * which can be used to train the learning machine
	 * @param tokenizedText
	 */
	public String parse(String tokenizedText){ /* Still looking into formatting I/O, probably will work like the Stanford NL Parser*/ };
	
	/**
	 * Returns the the token count for a specific token
	 * @param token
	 */
	public int getTokenCount(String token){};
	
	/**
	 * Initializes the trainer and gets the training materials from std input
	 */
	public Trainer();
	
}
