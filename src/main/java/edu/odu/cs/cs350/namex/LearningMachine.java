package edu.odu.cs.cs350.namex;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class LearningMachine {
	private Classifier classifier;
	private FastVector attributes;
	private int numAttr;
	private Instances trainingInstances;
	private String evalSummary;

	public LearningMachine() { 
		// Initialize the Classifier as a Naive Bayes Classifier
		classifier = (Classifier) new NaiveBayes();
		// Initialize Attributes
		// Declare Lexical Attribute with its values
		// OLD condtructor only added one word at a time.

		// FastVector NominalValLexical = new FastVector(9);
		//
		// NominalValLexical.addElement("punct");
		// NominalValLexical.addElement("capLetter");
		// NominalValLexical.addElement("capitalized");
		// NominalValLexical.addElement("allCaps");
		// NominalValLexical.addElement("lineFeed");
		// NominalValLexical.addElement("whiteSpace");
		// NominalValLexical.addElement("number");
		// NominalValLexical.addElement("other");
		// NominalValLexical.addElement("null");
		// Attribute Lexical = new Attribute("Lexical", NominalValLexical);
		//
		// // Declare PartOfSpeech Attribute with its values
		// FastVector NominalValPoS = new FastVector(6);
		// NominalValPoS.addElement("article");
		// NominalValPoS.addElement("conjunction");
		// NominalValPoS.addElement("period");
		// NominalValPoS.addElement("comma");
		// NominalValPoS.addElement("hyphen");
		// NominalValPoS.addElement("other");
		// Attribute PartOfSpeech = new Attribute("PartOfSpeech",
		// NominalValPoS);
		//
		// // Declare Gazetteer Attributes with its values
		// FastVector NominalValGazetteer = new FastVector(2);
		// NominalValGazetteer.addElement("0");
		// NominalValGazetteer.addElement("1");
		// Attribute DictionaryWord = new Attribute("DictionaryWord",
		// NominalValGazetteer);
		// Attribute City = new Attribute("City", NominalValGazetteer);
		// Attribute Country = new Attribute("Country", NominalValGazetteer);
		// Attribute Places = new Attribute("Places", NominalValGazetteer);
		// Attribute DTICFirst = new Attribute("DTICFirst",
		// NominalValGazetteer);
		// Attribute DTICLast = new Attribute("DTICLast", NominalValGazetteer);
		// Attribute CommonFirst = new Attribute("CommonFirst",
		// NominalValGazetteer);
		// Attribute CommonLast = new Attribute("CommonLast",
		// NominalValGazetteer);
		// Attribute Honorific = new Attribute("Honorific",
		// NominalValGazetteer);
		// Attribute Prefix = new Attribute("Prefix", NominalValGazetteer);
		// Attribute Suffix = new Attribute("Suffix", NominalValGazetteer);
		// Attribute Kill = new Attribute("Kill", NominalValGazetteer);
		//
		// // Declare Name Attribute
		// FastVector NominalValName = new FastVector(3);
		// NominalValName.addElement("beginning");
		// NominalValName.addElement("continuing");
		// NominalValName.addElement("other");
		// Attribute Name = new Attribute("Name", NominalValName);
		//
		// // Declare the Feature vector
		// attributes = new FastVector(15);
		// attributes.addElement(Lexical);
		// attributes.addElement(PartOfSpeech);
		// attributes.addElement(DictionaryWord);
		// attributes.addElement(City);
		// attributes.addElement(Country);
		// attributes.addElement(Places);
		// attributes.addElement(DTICFirst);
		// attributes.addElement(DTICLast);
		// attributes.addElement(CommonFirst);
		// attributes.addElement(CommonLast);
		// attributes.addElement(Honorific);
		// attributes.addElement(Prefix);
		// attributes.addElement(Suffix);
		// attributes.addElement(Kill);
		// attributes.addElement(Name);
		//
		// numAttr = attributes.size();

		FastVector NominalValLexical = new FastVector(9);
		NominalValLexical.addElement("punct");
		NominalValLexical.addElement("capLetter");
		NominalValLexical.addElement("capitalized");
		NominalValLexical.addElement("allCaps");
		NominalValLexical.addElement("lineFeed");
		NominalValLexical.addElement("whiteSpace");
		NominalValLexical.addElement("number");
		NominalValLexical.addElement("other");
		NominalValLexical.addElement("null");
		Attribute Lexical1 = new Attribute("Lexical", NominalValLexical);
		Attribute Lexical2 = new Attribute("Lexical", NominalValLexical);
		Attribute Lexical3 = new Attribute("Lexical", NominalValLexical);
		Attribute Lexical4 = new Attribute("Lexical", NominalValLexical);
		Attribute Lexical5 = new Attribute("Lexical", NominalValLexical);
		Attribute Lexical6 = new Attribute("Lexical", NominalValLexical);
		Attribute Lexical7 = new Attribute("Lexical", NominalValLexical);
		Attribute Lexical8 = new Attribute("Lexical", NominalValLexical);
		Attribute Lexical9 = new Attribute("Lexical", NominalValLexical);
		Attribute Lexical10 = new Attribute("Lexical", NominalValLexical);
		Attribute Lexical11 = new Attribute("Lexical", NominalValLexical);

		// Declare PartOfSpeech Attribute with its values
		FastVector NominalValPoS = new FastVector(6);
		NominalValPoS.addElement("article");
		NominalValPoS.addElement("conjunction");
		NominalValPoS.addElement("period");
		NominalValPoS.addElement("comma");
		NominalValPoS.addElement("hyphen");
		NominalValPoS.addElement("other");
		Attribute PartOfSpeech1 = new Attribute("PartOfSpeech", NominalValPoS);
		Attribute PartOfSpeech2 = new Attribute("PartOfSpeech", NominalValPoS);
		Attribute PartOfSpeech3 = new Attribute("PartOfSpeech", NominalValPoS);
		Attribute PartOfSpeech4 = new Attribute("PartOfSpeech", NominalValPoS);
		Attribute PartOfSpeech5 = new Attribute("PartOfSpeech", NominalValPoS);
		Attribute PartOfSpeech6 = new Attribute("PartOfSpeech", NominalValPoS);
		Attribute PartOfSpeech7 = new Attribute("PartOfSpeech", NominalValPoS);
		Attribute PartOfSpeech8 = new Attribute("PartOfSpeech", NominalValPoS);
		Attribute PartOfSpeech9 = new Attribute("PartOfSpeech", NominalValPoS);
		Attribute PartOfSpeech10 = new Attribute("PartOfSpeech", NominalValPoS);
		Attribute PartOfSpeech11 = new Attribute("PartOfSpeech", NominalValPoS);
		// Declare Gazetteer Attributes with its values
		FastVector NominalValGazetteer = new FastVector(2);
		NominalValGazetteer.addElement("0");
		NominalValGazetteer.addElement("1");
		Attribute DictionaryWord1 = new Attribute("DictionaryWord", NominalValGazetteer);
		Attribute City1 = new Attribute("City", NominalValGazetteer);
		Attribute Country1 = new Attribute("Country", NominalValGazetteer);
		Attribute Places1 = new Attribute("Places", NominalValGazetteer);
		Attribute DTICFirst1 = new Attribute("DTICFirst", NominalValGazetteer);
		Attribute DTICLast1 = new Attribute("DTICLast", NominalValGazetteer);
		Attribute CommonFirst1 = new Attribute("CommonFirst", NominalValGazetteer);
		Attribute CommonLast1 = new Attribute("CommonLast", NominalValGazetteer);
		Attribute Honorific1 = new Attribute("Honorific", NominalValGazetteer);
		Attribute Prefix1 = new Attribute("Prefix", NominalValGazetteer);
		Attribute Suffix1 = new Attribute("Suffix", NominalValGazetteer);
		Attribute Kill1 = new Attribute("Kill", NominalValGazetteer);
		Attribute DictionaryWord2 = new Attribute("DictionaryWord", NominalValGazetteer);
		Attribute City2 = new Attribute("City", NominalValGazetteer);
		Attribute Country2 = new Attribute("Country", NominalValGazetteer);
		Attribute Places2 = new Attribute("Places", NominalValGazetteer);
		Attribute DTICFirst2 = new Attribute("DTICFirst", NominalValGazetteer);
		Attribute DTICLast2 = new Attribute("DTICLast", NominalValGazetteer);
		Attribute CommonFirst2 = new Attribute("CommonFirst", NominalValGazetteer);
		Attribute CommonLast2 = new Attribute("CommonLast", NominalValGazetteer);
		Attribute Honorific2 = new Attribute("Honorific", NominalValGazetteer);
		Attribute Prefix2 = new Attribute("Prefix", NominalValGazetteer);
		Attribute Suffix2 = new Attribute("Suffix", NominalValGazetteer);
		Attribute Kill2 = new Attribute("Kill", NominalValGazetteer);
		Attribute DictionaryWord3 = new Attribute("DictionaryWord", NominalValGazetteer);
		Attribute City3 = new Attribute("City", NominalValGazetteer);
		Attribute Country3 = new Attribute("Country", NominalValGazetteer);
		Attribute Places3 = new Attribute("Places", NominalValGazetteer);
		Attribute DTICFirst3 = new Attribute("DTICFirst", NominalValGazetteer);
		Attribute DTICLast3 = new Attribute("DTICLast", NominalValGazetteer);
		Attribute CommonFirst3 = new Attribute("CommonFirst", NominalValGazetteer);
		Attribute CommonLast3 = new Attribute("CommonLast", NominalValGazetteer);
		Attribute Honorific3 = new Attribute("Honorific", NominalValGazetteer);
		Attribute Prefix3 = new Attribute("Prefix", NominalValGazetteer);
		Attribute Suffix3 = new Attribute("Suffix", NominalValGazetteer);
		Attribute Kill3 = new Attribute("Kill", NominalValGazetteer);
		Attribute DictionaryWord4 = new Attribute("DictionaryWord", NominalValGazetteer);
		Attribute City4 = new Attribute("City", NominalValGazetteer);
		Attribute Country4 = new Attribute("Country", NominalValGazetteer);
		Attribute Places4 = new Attribute("Places", NominalValGazetteer);
		Attribute DTICFirst4 = new Attribute("DTICFirst", NominalValGazetteer);
		Attribute DTICLast4 = new Attribute("DTICLast", NominalValGazetteer);
		Attribute CommonFirst4 = new Attribute("CommonFirst", NominalValGazetteer);
		Attribute CommonLast4 = new Attribute("CommonLast", NominalValGazetteer);
		Attribute Honorific4 = new Attribute("Honorific", NominalValGazetteer);
		Attribute Prefix4 = new Attribute("Prefix", NominalValGazetteer);
		Attribute Suffix4 = new Attribute("Suffix", NominalValGazetteer);
		Attribute Kill4 = new Attribute("Kill", NominalValGazetteer);
		Attribute DictionaryWord5 = new Attribute("DictionaryWord", NominalValGazetteer);
		Attribute City5 = new Attribute("City", NominalValGazetteer);
		Attribute Country5 = new Attribute("Country", NominalValGazetteer);
		Attribute Places5 = new Attribute("Places", NominalValGazetteer);
		Attribute DTICFirst5 = new Attribute("DTICFirst", NominalValGazetteer);
		Attribute DTICLast5 = new Attribute("DTICLast", NominalValGazetteer);
		Attribute CommonFirst5 = new Attribute("CommonFirst", NominalValGazetteer);
		Attribute CommonLast5 = new Attribute("CommonLast", NominalValGazetteer);
		Attribute Honorific5 = new Attribute("Honorific", NominalValGazetteer);
		Attribute Prefix5 = new Attribute("Prefix", NominalValGazetteer);
		Attribute Suffix5 = new Attribute("Suffix", NominalValGazetteer);
		Attribute Kill5 = new Attribute("Kill", NominalValGazetteer);
		Attribute DictionaryWord6 = new Attribute("DictionaryWord", NominalValGazetteer);
		Attribute City6 = new Attribute("City", NominalValGazetteer);
		Attribute Country6 = new Attribute("Country", NominalValGazetteer);
		Attribute Places6 = new Attribute("Places", NominalValGazetteer);
		Attribute DTICFirst6 = new Attribute("DTICFirst", NominalValGazetteer);
		Attribute DTICLast6 = new Attribute("DTICLast", NominalValGazetteer);
		Attribute CommonFirst6 = new Attribute("CommonFirst", NominalValGazetteer);
		Attribute CommonLast6 = new Attribute("CommonLast", NominalValGazetteer);
		Attribute Honorific6 = new Attribute("Honorific", NominalValGazetteer);
		Attribute Prefix6 = new Attribute("Prefix", NominalValGazetteer);
		Attribute Suffix6 = new Attribute("Suffix", NominalValGazetteer);
		Attribute Kill6 = new Attribute("Kill", NominalValGazetteer);
		Attribute DictionaryWord7 = new Attribute("DictionaryWord", NominalValGazetteer);
		Attribute City7 = new Attribute("City", NominalValGazetteer);
		Attribute Country7 = new Attribute("Country", NominalValGazetteer);
		Attribute Places7 = new Attribute("Places", NominalValGazetteer);
		Attribute DTICFirst7 = new Attribute("DTICFirst", NominalValGazetteer);
		Attribute DTICLast7 = new Attribute("DTICLast", NominalValGazetteer);
		Attribute CommonFirst7 = new Attribute("CommonFirst", NominalValGazetteer);
		Attribute CommonLast7 = new Attribute("CommonLast", NominalValGazetteer);
		Attribute Honorific7 = new Attribute("Honorific", NominalValGazetteer);
		Attribute Prefix7 = new Attribute("Prefix", NominalValGazetteer);
		Attribute Suffix7 = new Attribute("Suffix", NominalValGazetteer);
		Attribute Kill7 = new Attribute("Kill", NominalValGazetteer);
		Attribute DictionaryWord8 = new Attribute("DictionaryWord", NominalValGazetteer);
		Attribute City8 = new Attribute("City", NominalValGazetteer);
		Attribute Country8 = new Attribute("Country", NominalValGazetteer);
		Attribute Places8 = new Attribute("Places", NominalValGazetteer);
		Attribute DTICFirst8 = new Attribute("DTICFirst", NominalValGazetteer);
		Attribute DTICLast8 = new Attribute("DTICLast", NominalValGazetteer);
		Attribute CommonFirst8 = new Attribute("CommonFirst", NominalValGazetteer);
		Attribute CommonLast8 = new Attribute("CommonLast", NominalValGazetteer);
		Attribute Honorific8 = new Attribute("Honorific", NominalValGazetteer);
		Attribute Prefix8 = new Attribute("Prefix", NominalValGazetteer);
		Attribute Suffix8 = new Attribute("Suffix", NominalValGazetteer);
		Attribute Kill8 = new Attribute("Kill", NominalValGazetteer);
		Attribute DictionaryWord9 = new Attribute("DictionaryWord", NominalValGazetteer);
		Attribute City9 = new Attribute("City", NominalValGazetteer);
		Attribute Country9 = new Attribute("Country", NominalValGazetteer);
		Attribute Places9 = new Attribute("Places", NominalValGazetteer);
		Attribute DTICFirst9 = new Attribute("DTICFirst", NominalValGazetteer);
		Attribute DTICLast9 = new Attribute("DTICLast", NominalValGazetteer);
		Attribute CommonFirst9 = new Attribute("CommonFirst", NominalValGazetteer);
		Attribute CommonLast9 = new Attribute("CommonLast", NominalValGazetteer);
		Attribute Honorific9 = new Attribute("Honorific", NominalValGazetteer);
		Attribute Prefix9 = new Attribute("Prefix", NominalValGazetteer);
		Attribute Suffix9 = new Attribute("Suffix", NominalValGazetteer);
		Attribute Kill9 = new Attribute("Kill", NominalValGazetteer);
		Attribute DictionaryWord10 = new Attribute("DictionaryWord", NominalValGazetteer);
		Attribute City10 = new Attribute("City", NominalValGazetteer);
		Attribute Country10 = new Attribute("Country", NominalValGazetteer);
		Attribute Places10 = new Attribute("Places", NominalValGazetteer);
		Attribute DTICFirst10 = new Attribute("DTICFirst", NominalValGazetteer);
		Attribute DTICLast10 = new Attribute("DTICLast", NominalValGazetteer);
		Attribute CommonFirst10 = new Attribute("CommonFirst", NominalValGazetteer);
		Attribute CommonLast10 = new Attribute("CommonLast", NominalValGazetteer);
		Attribute Honorific10 = new Attribute("Honorific", NominalValGazetteer);
		Attribute Prefix10 = new Attribute("Prefix", NominalValGazetteer);
		Attribute Suffix10 = new Attribute("Suffix", NominalValGazetteer);
		Attribute Kill10 = new Attribute("Kill", NominalValGazetteer);
		Attribute DictionaryWord11 = new Attribute("DictionaryWord", NominalValGazetteer);
		Attribute City11 = new Attribute("City", NominalValGazetteer);
		Attribute Country11 = new Attribute("Country", NominalValGazetteer);
		Attribute Places11 = new Attribute("Places", NominalValGazetteer);
		Attribute DTICFirst11 = new Attribute("DTICFirst", NominalValGazetteer);
		Attribute DTICLast11 = new Attribute("DTICLast", NominalValGazetteer);
		Attribute CommonFirst11 = new Attribute("CommonFirst", NominalValGazetteer);
		Attribute CommonLast11 = new Attribute("CommonLast", NominalValGazetteer);
		Attribute Honorific11 = new Attribute("Honorific", NominalValGazetteer);
		Attribute Prefix11 = new Attribute("Prefix", NominalValGazetteer);
		Attribute Suffix11 = new Attribute("Suffix", NominalValGazetteer);
		Attribute Kill11 = new Attribute("Kill", NominalValGazetteer);

		// Declare Name Attribute
		FastVector NominalValName = new FastVector(3);
		NominalValName.addElement("beginning");
		NominalValName.addElement("continuing");
		NominalValName.addElement("other");
		Attribute Name1 = new Attribute("Name", NominalValName);
		Attribute Name2 = new Attribute("Name", NominalValName);
		Attribute Name3 = new Attribute("Name", NominalValName);
		Attribute Name4 = new Attribute("Name", NominalValName);
		Attribute Name5 = new Attribute("Name", NominalValName);
		Attribute Name6 = new Attribute("Name", NominalValName);

		// Declare the Feature vector
		attributes = new FastVector(71);
		// word one
		attributes.addElement(Lexical1);
		attributes.addElement(PartOfSpeech1);
		attributes.addElement(DictionaryWord1);
		attributes.addElement(City1);
		attributes.addElement(Country1);
		attributes.addElement(Places1);
		attributes.addElement(DTICFirst1);
		attributes.addElement(DTICLast1);
		attributes.addElement(CommonFirst1);
		attributes.addElement(CommonLast1);
		attributes.addElement(Honorific1);
		attributes.addElement(Prefix1);
		attributes.addElement(Suffix1);
		attributes.addElement(Kill1);
		// word two
		attributes.addElement(Lexical2); 
		attributes.addElement(PartOfSpeech2);
		attributes.addElement(DictionaryWord2);
		attributes.addElement(City2);
		attributes.addElement(Country2);
		attributes.addElement(Places2);
		attributes.addElement(DTICFirst2);
		attributes.addElement(DTICLast2);
		attributes.addElement(CommonFirst2);
		attributes.addElement(CommonLast2);
		attributes.addElement(Honorific2);
		attributes.addElement(Prefix2);
		attributes.addElement(Suffix2);
		attributes.addElement(Kill2);

		// word three
		attributes.addElement(Lexical3);
		attributes.addElement(PartOfSpeech3);
		attributes.addElement(DictionaryWord3);
		attributes.addElement(City3);
		attributes.addElement(Country3);
		attributes.addElement(Places3);
		attributes.addElement(DTICFirst3);
		attributes.addElement(DTICLast3);
		attributes.addElement(CommonFirst3);
		attributes.addElement(CommonLast3);
		attributes.addElement(Honorific3);
		attributes.addElement(Prefix3);
		attributes.addElement(Suffix3);
		attributes.addElement(Kill3);
		// word four
		attributes.addElement(Lexical4);
		attributes.addElement(PartOfSpeech4);
		attributes.addElement(DictionaryWord4);
		attributes.addElement(City4);
		attributes.addElement(Country4);
		attributes.addElement(Places4);
		attributes.addElement(DTICFirst4);
		attributes.addElement(DTICLast4);
		attributes.addElement(CommonFirst4);
		attributes.addElement(CommonLast4);
		attributes.addElement(Honorific4);
		attributes.addElement(Prefix4);
		attributes.addElement(Suffix4);
		attributes.addElement(Kill4);

		// word five
		attributes.addElement(Lexical5);
		attributes.addElement(PartOfSpeech5);
		attributes.addElement(DictionaryWord5);
		attributes.addElement(City5);
		attributes.addElement(Country5);
		attributes.addElement(Places5);
		attributes.addElement(DTICFirst5);
		attributes.addElement(DTICLast5);
		attributes.addElement(CommonFirst5);
		attributes.addElement(CommonLast5);
		attributes.addElement(Honorific5);
		attributes.addElement(Prefix5);
		attributes.addElement(Suffix5);
		attributes.addElement(Kill5);

		// word six
		attributes.addElement(Lexical6);
		attributes.addElement(PartOfSpeech6);
		attributes.addElement(DictionaryWord6);
		attributes.addElement(City6);
		attributes.addElement(Country6);
		attributes.addElement(Places6);
		attributes.addElement(DTICFirst6);
		attributes.addElement(DTICLast6);
		attributes.addElement(CommonFirst6);
		attributes.addElement(CommonLast6);
		attributes.addElement(Honorific6);
		attributes.addElement(Prefix6);
		attributes.addElement(Suffix6);
		attributes.addElement(Kill6);

		// word seven
		attributes.addElement(Lexical7);
		attributes.addElement(PartOfSpeech7);
		attributes.addElement(DictionaryWord7);
		attributes.addElement(City7);
		attributes.addElement(Country7);
		attributes.addElement(Places7);
		attributes.addElement(DTICFirst7);
		attributes.addElement(DTICLast7);
		attributes.addElement(CommonFirst7);
		attributes.addElement(CommonLast7);
		attributes.addElement(Honorific7);
		attributes.addElement(Prefix7);
		attributes.addElement(Suffix7);
		attributes.addElement(Kill7);
		// word eight
		attributes.addElement(Lexical8);
		attributes.addElement(PartOfSpeech8);
		attributes.addElement(DictionaryWord8);
		attributes.addElement(City8);
		attributes.addElement(Country8);
		attributes.addElement(Places8);
		attributes.addElement(DTICFirst8);
		attributes.addElement(DTICLast8);
		attributes.addElement(CommonFirst8);
		attributes.addElement(CommonLast8);
		attributes.addElement(Honorific8);
		attributes.addElement(Prefix8);
		attributes.addElement(Suffix8);
		attributes.addElement(Kill8);
		// word nine
		attributes.addElement(Lexical9);
		attributes.addElement(PartOfSpeech9);
		attributes.addElement(DictionaryWord9);
		attributes.addElement(City9);
		attributes.addElement(Country9);
		attributes.addElement(Places9);
		attributes.addElement(DTICFirst9);
		attributes.addElement(DTICLast9);
		attributes.addElement(CommonFirst9);
		attributes.addElement(CommonLast9);
		attributes.addElement(Honorific9);
		attributes.addElement(Prefix9);
		attributes.addElement(Suffix9);
		attributes.addElement(Kill9);
		// word ten
		attributes.addElement(Lexical10);
		attributes.addElement(PartOfSpeech10);
		attributes.addElement(DictionaryWord10);
		attributes.addElement(City10);
		attributes.addElement(Country10);
		attributes.addElement(Places10);
		attributes.addElement(DTICFirst10);
		attributes.addElement(DTICLast10);
		attributes.addElement(CommonFirst10);
		attributes.addElement(CommonLast10);
		attributes.addElement(Honorific10);
		attributes.addElement(Prefix10);
		attributes.addElement(Suffix10);
		attributes.addElement(Kill10);
		// word eleven
		attributes.addElement(Lexical11);
		attributes.addElement(PartOfSpeech11);
		attributes.addElement(DictionaryWord11);
		attributes.addElement(City11);
		attributes.addElement(Country11);
		attributes.addElement(Places11);
		attributes.addElement(DTICFirst11);
		attributes.addElement(DTICLast11);
		attributes.addElement(CommonFirst11);
		attributes.addElement(CommonLast11);
		attributes.addElement(Honorific11);
		attributes.addElement(Prefix11);
		attributes.addElement(Suffix11);
		attributes.addElement(Kill11);
		// Middle word name classification
		attributes.addElement(Name1);
		attributes.addElement(Name2);
		attributes.addElement(Name3);
		attributes.addElement(Name4);
		attributes.addElement(Name5);
		attributes.addElement(Name6);

	}

	public LearningMachine(String machineModel) {
		if (machineModel.equals("new"))
			classifier = (Classifier) new NaiveBayes();
		else
			loadLM(machineModel);

		// Initialize Attributes
		// Declare Lexical Attribute with its values
		FastVector NominalValLexical = new FastVector(9);
		NominalValLexical.addElement("punct");
		NominalValLexical.addElement("capLetter");
		NominalValLexical.addElement("capitalized");
		NominalValLexical.addElement("allCaps");
		NominalValLexical.addElement("lineFeed");
		NominalValLexical.addElement("whiteSpace");
		NominalValLexical.addElement("number");
		NominalValLexical.addElement("other");
		NominalValLexical.addElement("null");
		Attribute Lexical1 = new Attribute("Lexical", NominalValLexical);
		Attribute Lexical2 = new Attribute("Lexical", NominalValLexical);
		Attribute Lexical3 = new Attribute("Lexical", NominalValLexical);
		Attribute Lexical4 = new Attribute("Lexical", NominalValLexical);
		Attribute Lexical5 = new Attribute("Lexical", NominalValLexical);
		Attribute Lexical6 = new Attribute("Lexical", NominalValLexical);
		Attribute Lexical7 = new Attribute("Lexical", NominalValLexical);
		Attribute Lexical8 = new Attribute("Lexical", NominalValLexical);
		Attribute Lexical9 = new Attribute("Lexical", NominalValLexical);
		Attribute Lexical10 = new Attribute("Lexical", NominalValLexical);
		Attribute Lexical11 = new Attribute("Lexical", NominalValLexical);

		// Declare PartOfSpeech Attribute with its values
		FastVector NominalValPoS = new FastVector(6);
		NominalValPoS.addElement("article");
		NominalValPoS.addElement("conjunction");
		NominalValPoS.addElement("period");
		NominalValPoS.addElement("comma");
		NominalValPoS.addElement("hyphen");
		NominalValPoS.addElement("other");
		Attribute PartOfSpeech1 = new Attribute("PartOfSpeech", NominalValPoS);
		Attribute PartOfSpeech2 = new Attribute("PartOfSpeech", NominalValPoS);
		Attribute PartOfSpeech3 = new Attribute("PartOfSpeech", NominalValPoS);
		Attribute PartOfSpeech4 = new Attribute("PartOfSpeech", NominalValPoS);
		Attribute PartOfSpeech5 = new Attribute("PartOfSpeech", NominalValPoS);
		Attribute PartOfSpeech6 = new Attribute("PartOfSpeech", NominalValPoS);
		Attribute PartOfSpeech7 = new Attribute("PartOfSpeech", NominalValPoS);
		Attribute PartOfSpeech8 = new Attribute("PartOfSpeech", NominalValPoS);
		Attribute PartOfSpeech9 = new Attribute("PartOfSpeech", NominalValPoS);
		Attribute PartOfSpeech10 = new Attribute("PartOfSpeech", NominalValPoS);
		Attribute PartOfSpeech11 = new Attribute("PartOfSpeech", NominalValPoS);
		// Declare Gazetteer Attributes with its values
		FastVector NominalValGazetteer = new FastVector(2);
		NominalValGazetteer.addElement("0");
		NominalValGazetteer.addElement("1");
		Attribute DictionaryWord1 = new Attribute("DictionaryWord", NominalValGazetteer);
		Attribute City1 = new Attribute("City", NominalValGazetteer);
		Attribute Country1 = new Attribute("Country", NominalValGazetteer);
		Attribute Places1 = new Attribute("Places", NominalValGazetteer);
		Attribute DTICFirst1 = new Attribute("DTICFirst", NominalValGazetteer);
		Attribute DTICLast1 = new Attribute("DTICLast", NominalValGazetteer);
		Attribute CommonFirst1 = new Attribute("CommonFirst", NominalValGazetteer);
		Attribute CommonLast1 = new Attribute("CommonLast", NominalValGazetteer);
		Attribute Honorific1 = new Attribute("Honorific", NominalValGazetteer);
		Attribute Prefix1 = new Attribute("Prefix", NominalValGazetteer);
		Attribute Suffix1 = new Attribute("Suffix", NominalValGazetteer);
		Attribute Kill1 = new Attribute("Kill", NominalValGazetteer);
		Attribute DictionaryWord2 = new Attribute("DictionaryWord", NominalValGazetteer);
		Attribute City2 = new Attribute("City", NominalValGazetteer);
		Attribute Country2 = new Attribute("Country", NominalValGazetteer);
		Attribute Places2 = new Attribute("Places", NominalValGazetteer);
		Attribute DTICFirst2 = new Attribute("DTICFirst", NominalValGazetteer);
		Attribute DTICLast2 = new Attribute("DTICLast", NominalValGazetteer);
		Attribute CommonFirst2 = new Attribute("CommonFirst", NominalValGazetteer);
		Attribute CommonLast2 = new Attribute("CommonLast", NominalValGazetteer);
		Attribute Honorific2 = new Attribute("Honorific", NominalValGazetteer);
		Attribute Prefix2 = new Attribute("Prefix", NominalValGazetteer);
		Attribute Suffix2 = new Attribute("Suffix", NominalValGazetteer);
		Attribute Kill2 = new Attribute("Kill", NominalValGazetteer);
		Attribute DictionaryWord3 = new Attribute("DictionaryWord", NominalValGazetteer);
		Attribute City3 = new Attribute("City", NominalValGazetteer);
		Attribute Country3 = new Attribute("Country", NominalValGazetteer);
		Attribute Places3 = new Attribute("Places", NominalValGazetteer);
		Attribute DTICFirst3 = new Attribute("DTICFirst", NominalValGazetteer);
		Attribute DTICLast3 = new Attribute("DTICLast", NominalValGazetteer);
		Attribute CommonFirst3 = new Attribute("CommonFirst", NominalValGazetteer);
		Attribute CommonLast3 = new Attribute("CommonLast", NominalValGazetteer);
		Attribute Honorific3 = new Attribute("Honorific", NominalValGazetteer);
		Attribute Prefix3 = new Attribute("Prefix", NominalValGazetteer);
		Attribute Suffix3 = new Attribute("Suffix", NominalValGazetteer);
		Attribute Kill3 = new Attribute("Kill", NominalValGazetteer);
		Attribute DictionaryWord4 = new Attribute("DictionaryWord", NominalValGazetteer);
		Attribute City4 = new Attribute("City", NominalValGazetteer);
		Attribute Country4 = new Attribute("Country", NominalValGazetteer);
		Attribute Places4 = new Attribute("Places", NominalValGazetteer);
		Attribute DTICFirst4 = new Attribute("DTICFirst", NominalValGazetteer);
		Attribute DTICLast4 = new Attribute("DTICLast", NominalValGazetteer);
		Attribute CommonFirst4 = new Attribute("CommonFirst", NominalValGazetteer);
		Attribute CommonLast4 = new Attribute("CommonLast", NominalValGazetteer);
		Attribute Honorific4 = new Attribute("Honorific", NominalValGazetteer);
		Attribute Prefix4 = new Attribute("Prefix", NominalValGazetteer);
		Attribute Suffix4 = new Attribute("Suffix", NominalValGazetteer);
		Attribute Kill4 = new Attribute("Kill", NominalValGazetteer);
		Attribute DictionaryWord5 = new Attribute("DictionaryWord", NominalValGazetteer);
		Attribute City5 = new Attribute("City", NominalValGazetteer);
		Attribute Country5 = new Attribute("Country", NominalValGazetteer);
		Attribute Places5 = new Attribute("Places", NominalValGazetteer);
		Attribute DTICFirst5 = new Attribute("DTICFirst", NominalValGazetteer);
		Attribute DTICLast5 = new Attribute("DTICLast", NominalValGazetteer);
		Attribute CommonFirst5 = new Attribute("CommonFirst", NominalValGazetteer);
		Attribute CommonLast5 = new Attribute("CommonLast", NominalValGazetteer);
		Attribute Honorific5 = new Attribute("Honorific", NominalValGazetteer);
		Attribute Prefix5 = new Attribute("Prefix", NominalValGazetteer);
		Attribute Suffix5 = new Attribute("Suffix", NominalValGazetteer);
		Attribute Kill5 = new Attribute("Kill", NominalValGazetteer);
		Attribute DictionaryWord6 = new Attribute("DictionaryWord", NominalValGazetteer);
		Attribute City6 = new Attribute("City", NominalValGazetteer);
		Attribute Country6 = new Attribute("Country", NominalValGazetteer);
		Attribute Places6 = new Attribute("Places", NominalValGazetteer);
		Attribute DTICFirst6 = new Attribute("DTICFirst", NominalValGazetteer);
		Attribute DTICLast6 = new Attribute("DTICLast", NominalValGazetteer);
		Attribute CommonFirst6 = new Attribute("CommonFirst", NominalValGazetteer);
		Attribute CommonLast6 = new Attribute("CommonLast", NominalValGazetteer);
		Attribute Honorific6 = new Attribute("Honorific", NominalValGazetteer);
		Attribute Prefix6 = new Attribute("Prefix", NominalValGazetteer);
		Attribute Suffix6 = new Attribute("Suffix", NominalValGazetteer);
		Attribute Kill6 = new Attribute("Kill", NominalValGazetteer);
		Attribute DictionaryWord7 = new Attribute("DictionaryWord", NominalValGazetteer);
		Attribute City7 = new Attribute("City", NominalValGazetteer);
		Attribute Country7 = new Attribute("Country", NominalValGazetteer);
		Attribute Places7 = new Attribute("Places", NominalValGazetteer);
		Attribute DTICFirst7 = new Attribute("DTICFirst", NominalValGazetteer);
		Attribute DTICLast7 = new Attribute("DTICLast", NominalValGazetteer);
		Attribute CommonFirst7 = new Attribute("CommonFirst", NominalValGazetteer);
		Attribute CommonLast7 = new Attribute("CommonLast", NominalValGazetteer);
		Attribute Honorific7 = new Attribute("Honorific", NominalValGazetteer);
		Attribute Prefix7 = new Attribute("Prefix", NominalValGazetteer);
		Attribute Suffix7 = new Attribute("Suffix", NominalValGazetteer);
		Attribute Kill7 = new Attribute("Kill", NominalValGazetteer);
		Attribute DictionaryWord8 = new Attribute("DictionaryWord", NominalValGazetteer);
		Attribute City8 = new Attribute("City", NominalValGazetteer);
		Attribute Country8 = new Attribute("Country", NominalValGazetteer);
		Attribute Places8 = new Attribute("Places", NominalValGazetteer);
		Attribute DTICFirst8 = new Attribute("DTICFirst", NominalValGazetteer);
		Attribute DTICLast8 = new Attribute("DTICLast", NominalValGazetteer);
		Attribute CommonFirst8 = new Attribute("CommonFirst", NominalValGazetteer);
		Attribute CommonLast8 = new Attribute("CommonLast", NominalValGazetteer);
		Attribute Honorific8 = new Attribute("Honorific", NominalValGazetteer);
		Attribute Prefix8 = new Attribute("Prefix", NominalValGazetteer);
		Attribute Suffix8 = new Attribute("Suffix", NominalValGazetteer);
		Attribute Kill8 = new Attribute("Kill", NominalValGazetteer);
		Attribute DictionaryWord9 = new Attribute("DictionaryWord", NominalValGazetteer);
		Attribute City9 = new Attribute("City", NominalValGazetteer);
		Attribute Country9 = new Attribute("Country", NominalValGazetteer);
		Attribute Places9 = new Attribute("Places", NominalValGazetteer);
		Attribute DTICFirst9 = new Attribute("DTICFirst", NominalValGazetteer);
		Attribute DTICLast9 = new Attribute("DTICLast", NominalValGazetteer);
		Attribute CommonFirst9 = new Attribute("CommonFirst", NominalValGazetteer);
		Attribute CommonLast9 = new Attribute("CommonLast", NominalValGazetteer);
		Attribute Honorific9 = new Attribute("Honorific", NominalValGazetteer);
		Attribute Prefix9 = new Attribute("Prefix", NominalValGazetteer);
		Attribute Suffix9 = new Attribute("Suffix", NominalValGazetteer);
		Attribute Kill9 = new Attribute("Kill", NominalValGazetteer);
		Attribute DictionaryWord10 = new Attribute("DictionaryWord", NominalValGazetteer);
		Attribute City10 = new Attribute("City", NominalValGazetteer);
		Attribute Country10 = new Attribute("Country", NominalValGazetteer);
		Attribute Places10 = new Attribute("Places", NominalValGazetteer);
		Attribute DTICFirst10 = new Attribute("DTICFirst", NominalValGazetteer);
		Attribute DTICLast10 = new Attribute("DTICLast", NominalValGazetteer);
		Attribute CommonFirst10 = new Attribute("CommonFirst", NominalValGazetteer);
		Attribute CommonLast10 = new Attribute("CommonLast", NominalValGazetteer);
		Attribute Honorific10 = new Attribute("Honorific", NominalValGazetteer);
		Attribute Prefix10 = new Attribute("Prefix", NominalValGazetteer);
		Attribute Suffix10 = new Attribute("Suffix", NominalValGazetteer);
		Attribute Kill10 = new Attribute("Kill", NominalValGazetteer);
		Attribute DictionaryWord11 = new Attribute("DictionaryWord", NominalValGazetteer);
		Attribute City11 = new Attribute("City", NominalValGazetteer);
		Attribute Country11 = new Attribute("Country", NominalValGazetteer);
		Attribute Places11 = new Attribute("Places", NominalValGazetteer);
		Attribute DTICFirst11 = new Attribute("DTICFirst", NominalValGazetteer);
		Attribute DTICLast11 = new Attribute("DTICLast", NominalValGazetteer);
		Attribute CommonFirst11 = new Attribute("CommonFirst", NominalValGazetteer);
		Attribute CommonLast11 = new Attribute("CommonLast", NominalValGazetteer);
		Attribute Honorific11 = new Attribute("Honorific", NominalValGazetteer);
		Attribute Prefix11 = new Attribute("Prefix", NominalValGazetteer);
		Attribute Suffix11 = new Attribute("Suffix", NominalValGazetteer);
		Attribute Kill11 = new Attribute("Kill", NominalValGazetteer);

		// Declare Name Attribute
		FastVector NominalValName = new FastVector(3);
		NominalValName.addElement("beginning");
		NominalValName.addElement("continuing");
		NominalValName.addElement("other");

		Attribute Name1 = new Attribute("Name", NominalValName);
		Attribute Name2 = new Attribute("Name", NominalValName);
		Attribute Name3 = new Attribute("Name", NominalValName);
		Attribute Name4 = new Attribute("Name", NominalValName);
		Attribute Name5 = new Attribute("Name", NominalValName);
		Attribute Name6 = new Attribute("Name", NominalValName);

		// Declare the Feature vector
		attributes = new FastVector(71);
		// word one
		attributes.addElement(Lexical1);
		attributes.addElement(PartOfSpeech1);
		attributes.addElement(DictionaryWord1);
		attributes.addElement(City1);
		attributes.addElement(Country1);
		attributes.addElement(Places1);
		attributes.addElement(DTICFirst1);
		attributes.addElement(DTICLast1);
		attributes.addElement(CommonFirst1);
		attributes.addElement(CommonLast1);
		attributes.addElement(Honorific1);
		attributes.addElement(Prefix1);
		attributes.addElement(Suffix1);
		attributes.addElement(Kill1);
		// word two
		attributes.addElement(Lexical2);
		attributes.addElement(PartOfSpeech2);
		attributes.addElement(DictionaryWord2);
		attributes.addElement(City2);
		attributes.addElement(Country2);
		attributes.addElement(Places2);
		attributes.addElement(DTICFirst2);
		attributes.addElement(DTICLast2);
		attributes.addElement(CommonFirst2);
		attributes.addElement(CommonLast2);
		attributes.addElement(Honorific2);
		attributes.addElement(Prefix2);
		attributes.addElement(Suffix2);
		attributes.addElement(Kill2);

		// word three
		attributes.addElement(Lexical3);
		attributes.addElement(PartOfSpeech3);
		attributes.addElement(DictionaryWord3);
		attributes.addElement(City3);
		attributes.addElement(Country3);
		attributes.addElement(Places3);
		attributes.addElement(DTICFirst3);
		attributes.addElement(DTICLast3);
		attributes.addElement(CommonFirst3);
		attributes.addElement(CommonLast3);
		attributes.addElement(Honorific3);
		attributes.addElement(Prefix3);
		attributes.addElement(Suffix3);
		attributes.addElement(Kill3);
		// word four
		attributes.addElement(Lexical4);
		attributes.addElement(PartOfSpeech4);
		attributes.addElement(DictionaryWord4);
		attributes.addElement(City4);
		attributes.addElement(Country4);
		attributes.addElement(Places4);
		attributes.addElement(DTICFirst4);
		attributes.addElement(DTICLast4);
		attributes.addElement(CommonFirst4);
		attributes.addElement(CommonLast4);
		attributes.addElement(Honorific4);
		attributes.addElement(Prefix4);
		attributes.addElement(Suffix4);
		attributes.addElement(Kill4);

		// word five
		attributes.addElement(Lexical5);
		attributes.addElement(PartOfSpeech5);
		attributes.addElement(DictionaryWord5);
		attributes.addElement(City5);
		attributes.addElement(Country5);
		attributes.addElement(Places5);
		attributes.addElement(DTICFirst5);
		attributes.addElement(DTICLast5);
		attributes.addElement(CommonFirst5);
		attributes.addElement(CommonLast5);
		attributes.addElement(Honorific5);
		attributes.addElement(Prefix5);
		attributes.addElement(Suffix5);
		attributes.addElement(Kill5);

		// word six
		attributes.addElement(Lexical6);
		attributes.addElement(PartOfSpeech6);
		attributes.addElement(DictionaryWord6);
		attributes.addElement(City6);
		attributes.addElement(Country6);
		attributes.addElement(Places6);
		attributes.addElement(DTICFirst6);
		attributes.addElement(DTICLast6);
		attributes.addElement(CommonFirst6);
		attributes.addElement(CommonLast6);
		attributes.addElement(Honorific6);
		attributes.addElement(Prefix6);
		attributes.addElement(Suffix6);
		attributes.addElement(Kill6);

		// word seven
		attributes.addElement(Lexical7);
		attributes.addElement(PartOfSpeech7);
		attributes.addElement(DictionaryWord7);
		attributes.addElement(City7);
		attributes.addElement(Country7);
		attributes.addElement(Places7);
		attributes.addElement(DTICFirst7);
		attributes.addElement(DTICLast7);
		attributes.addElement(CommonFirst7);
		attributes.addElement(CommonLast7);
		attributes.addElement(Honorific7);
		attributes.addElement(Prefix7);
		attributes.addElement(Suffix7);
		attributes.addElement(Kill7);

		attributes.addElement(Lexical8);
		attributes.addElement(PartOfSpeech8);
		attributes.addElement(DictionaryWord8);
		attributes.addElement(City8);
		attributes.addElement(Country8);
		attributes.addElement(Places8);
		attributes.addElement(DTICFirst8);
		attributes.addElement(DTICLast8);
		attributes.addElement(CommonFirst8);
		attributes.addElement(CommonLast8);
		attributes.addElement(Honorific8);
		attributes.addElement(Prefix8);
		attributes.addElement(Suffix8);
		attributes.addElement(Kill8);

		attributes.addElement(Lexical9);
		attributes.addElement(PartOfSpeech9);
		attributes.addElement(DictionaryWord9);
		attributes.addElement(City9);
		attributes.addElement(Country9);
		attributes.addElement(Places9);
		attributes.addElement(DTICFirst9);
		attributes.addElement(DTICLast9);
		attributes.addElement(CommonFirst9);
		attributes.addElement(CommonLast9);
		attributes.addElement(Honorific9);
		attributes.addElement(Prefix9);
		attributes.addElement(Suffix9);
		attributes.addElement(Kill9);

		attributes.addElement(Lexical10);
		attributes.addElement(PartOfSpeech10);
		attributes.addElement(DictionaryWord10);
		attributes.addElement(City10);
		attributes.addElement(Country10);
		attributes.addElement(Places10);
		attributes.addElement(DTICFirst10);
		attributes.addElement(DTICLast10);
		attributes.addElement(CommonFirst10);
		attributes.addElement(CommonLast10);
		attributes.addElement(Honorific10);
		attributes.addElement(Prefix10);
		attributes.addElement(Suffix10);
		attributes.addElement(Kill10);

		attributes.addElement(Lexical11);
		attributes.addElement(PartOfSpeech11);
		attributes.addElement(DictionaryWord11);
		attributes.addElement(City11);
		attributes.addElement(Country11);
		attributes.addElement(Places11);
		attributes.addElement(DTICFirst11);
		attributes.addElement(DTICLast11);
		attributes.addElement(CommonFirst11);
		attributes.addElement(CommonLast11);
		attributes.addElement(Honorific11);
		attributes.addElement(Prefix11);
		attributes.addElement(Suffix11);
		attributes.addElement(Kill11);

		attributes.addElement(Name1);
		attributes.addElement(Name2);
		attributes.addElement(Name3);
		attributes.addElement(Name4);
		attributes.addElement(Name5);
		attributes.addElement(Name6);
	}
	// Removing the int constructor for simplicity
	// public LearningMachine(int k) {
	// int dimension = (((2 * k) + 1) * 14) + k + 1;
	//
	// // Initialize the Classifier as a Naive Bayes Classifier
	// classifier = (Classifier) new NaiveBayes();
	//
	// // Initialize Attributes
	// // Declare Lexical Attribute with its values
	// FastVector NominalValLexical = new FastVector(9);
	// NominalValLexical.addElement("punct");
	// NominalValLexical.addElement("capLetter");
	// NominalValLexical.addElement("capitalized");
	// NominalValLexical.addElement("allCaps");
	// NominalValLexical.addElement("lineFeed");
	// NominalValLexical.addElement("whiteSpace");
	// NominalValLexical.addElement("number");
	// NominalValLexical.addElement("other");
	// NominalValLexical.addElement("null");
	// // Attribute Lexical = new Attribute("Lexical", NominalValLexical);
	//
	// // Declare PartOfSpeech Attribute with its values
	// FastVector NominalValPoS = new FastVector(6);
	// NominalValPoS.addElement("article");
	// NominalValPoS.addElement("conjunction");
	// NominalValPoS.addElement("period");
	// NominalValPoS.addElement("comma");
	// NominalValPoS.addElement("hyphen");
	// NominalValPoS.addElement("other");
	// // Attribute PartOfSpeech = new Attribute("PartOfSpeech",
	// // NominalValPoS);
	//
	// // Declare Gazetteer Attributes with its values
	// FastVector NominalValGazetteer = new FastVector(2);
	// NominalValGazetteer.addElement("0");
	// NominalValGazetteer.addElement("1");
	//
	// // Declare Name Attribute
	// FastVector NominalValName = new FastVector(3);
	// NominalValName.addElement("beginning");
	// NominalValName.addElement("continuing");
	// NominalValName.addElement("other");
	// // Attribute Name = new Attribute("Name", NominalValName);
	//
	// // Declare ContainsName Attribute
	// FastVector NominalValContainsName = new FastVector(3);
	// NominalValContainsName.addElement("yes");
	// NominalValContainsName.addElement("no");
	// Attribute ContainsName = new Attribute("ContainsName",
	// NominalValContainsName);
	//
	// // Declare the Feature vector
	// attributes = new FastVector(dimension);
	//
	// for (int i = 0; i < ((2 * k) + 1); i++) {
	// attributes.addElement(getAttribute("Lexical" + i, NominalValLexical));
	// attributes.addElement(getAttribute("PartOfSpeech" + i, NominalValPoS));
	// attributes.addElement(getAttribute("DictionaryWord" + i,
	// NominalValGazetteer));
	// attributes.addElement(getAttribute("City" + i, NominalValGazetteer));
	// attributes.addElement(getAttribute("Country" + i, NominalValGazetteer));
	// attributes.addElement(getAttribute("Places" + i, NominalValGazetteer));
	// attributes.addElement(getAttribute("DTICFirst" + i,
	// NominalValGazetteer));
	// attributes.addElement(getAttribute("DTICLast" + i, NominalValGazetteer));
	// attributes.addElement(getAttribute("CommonFirst" + i,
	// NominalValGazetteer));
	// attributes.addElement(getAttribute("CommonLast" + i,
	// NominalValGazetteer));
	// attributes.addElement(getAttribute("Honorific" + i,
	// NominalValGazetteer));
	// attributes.addElement(getAttribute("Prefix" + i, NominalValGazetteer));
	// attributes.addElement(getAttribute("Suffix" + i, NominalValGazetteer));
	// attributes.addElement(getAttribute("Kill" + i, NominalValGazetteer));
	// attributes.addElement(getAttribute("Name" + i, NominalValName));
	// }
	// attributes.addElement(ContainsName);
	//
	// numAttr = attributes.size();
	// }

	/**
	 * Uses the training data to train the Learning Machine
	 * 
	 * @param trainingData
	 * @throws Exception
	 */
	public boolean train(Instances trainingData) throws Exception {

		try {
			classifier.buildClassifier(trainingData);

			// Test the Model
			Evaluation evaluation = new Evaluation(trainingData);
			evaluation.evaluateModel(classifier, trainingData);

			// Print the Evaluation Summary:
			String summary = evaluation.toSummaryString();
			System.out.println(summary);
			return true;

		} catch (Exception e2) {
			return false;
		}

		// Get the confusion matrix
		// double[][] cmMatrix = evaluation.confusionMatrix();
	}

	/**
	 * builds the classifier based on the ARFF data imported into
	 * trainingInstances
	 * 
	 * @throws Exception
	 */
	public boolean train() throws Exception {

		try {

			// Build the Classifier
			classifier.buildClassifier(this.trainingInstances);

			// Test the Model
			Evaluation eval = new Evaluation(this.trainingInstances);
			eval.evaluateModel(classifier, this.trainingInstances);

			// Set the Evaluation Summary
			evalSummary = eval.toSummaryString();

			return true;

		} catch (Exception e1) {

			e1.printStackTrace();

			return false;
		}
	}

	/**
	 * Get the likelihood of each classes distribution[0] is the probability of
	 * the Token beginning a name distribution[1] is the probability of the
	 * Token continuing a name distribution[2] is the probability of the Token
	 * being other
	 */
	public double[] getDistribution(String input) throws Exception {
		Instances classificationInstances = new Instances("toBeClassified", this.attributes, 1);

		// Build the instance to be classified
		String[] dataToClassify = input.split(",");

		Instance toClassify = new Instance(this.numAttr - 1);
		toClassify.setDataset(classificationInstances);

		toClassify.setValue((Attribute) attributes.elementAt(0), dataToClassify[0]);
		toClassify.setValue((Attribute) attributes.elementAt(1), dataToClassify[1]);
		toClassify.setValue((Attribute) attributes.elementAt(2), dataToClassify[2]);
		toClassify.setValue((Attribute) attributes.elementAt(3), dataToClassify[3]);
		toClassify.setValue((Attribute) attributes.elementAt(4), dataToClassify[4]);
		toClassify.setValue((Attribute) attributes.elementAt(5), dataToClassify[5]);
		toClassify.setValue((Attribute) attributes.elementAt(6), dataToClassify[6]);
		toClassify.setValue((Attribute) attributes.elementAt(7), dataToClassify[7]);
		toClassify.setValue((Attribute) attributes.elementAt(8), dataToClassify[8]);
		toClassify.setValue((Attribute) attributes.elementAt(9), dataToClassify[9]);
		toClassify.setValue((Attribute) attributes.elementAt(10), dataToClassify[10]);
		toClassify.setValue((Attribute) attributes.elementAt(11), dataToClassify[11]);
		toClassify.setValue((Attribute) attributes.elementAt(12), dataToClassify[12]);
		toClassify.setValue((Attribute) attributes.elementAt(13), dataToClassify[13]);

		// Specify that the instance belong to the training set
		// in order to inherit from the set description
		toClassify.setDataset(this.trainingInstances);

		// Get the likelihood of each classes
		double[] distribution = this.classifier.distributionForInstance(toClassify);

		return distribution;
	}

	/**
	 * Classifies tokens to create training data
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public String classify(String input) throws Exception {
		Instances classificationInstances = new Instances("toBeClassified", this.attributes, 1);

		// Build the instance to be classified
		String[] dataToClassify = input.split(",");

		Instance toClassify = new Instance(this.numAttr - 1);
		toClassify.setDataset(classificationInstances);

		for (int i = 0; i < (numAttr - 1); i++) {
			toClassify.setValue((Attribute) attributes.elementAt(i), dataToClassify[i]);
		}

		// Specify that the instance belong to the training set
		// in order to inherit from the set description
		toClassify.setDataset(this.trainingInstances);

		// Get the likelihood of each classes
		double[] distribution = this.classifier.distributionForInstance(toClassify);

		if (distribution[0] >= distribution[1] && distribution[0] >= distribution[2]) {
			return "beginning";
		} else if (distribution[1] >= distribution[0] && distribution[1] >= distribution[2]) {
			return "continuing";
		} else {
			return "other";
		}
	}

	/**
	 * Creates the singling data from the tokenized input
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public String classifyShingle(String input) throws Exception {
		Instances classificationInstances = new Instances("toBeClassified", this.attributes, 1);

		// Build the instance to be classified
		String[] dataToClassify = input.split(",");

		Instance toClassify = new Instance(this.numAttr - 1);
		toClassify.setDataset(classificationInstances);

		for (int i = 0; i < (numAttr - 1); i++) {
			// System.out.println("Attribute " + i + " - " + dataToClassify[i]);
			toClassify.setValue((Attribute) attributes.elementAt(i), dataToClassify[i]);
		}

		toClassify.setDataset(this.trainingInstances);

		// Get the likelihood of each classes
		double[] distribution = this.classifier.distributionForInstance(toClassify);

		if (distribution[0] >= distribution[1]) {
			return "yes";
		} else {
			return "no";
		}
	}

	/**
	 * Get the likelihood of each classes distribution[0] is the probability of
	 * the Shingle containing a name distribution[1] is the probability of the
	 * Shingle not containing a name
	 */
	public double[] getShingleDistribution(String input) throws Exception {
		Instances classificationInstances = new Instances("toBeClassified", this.attributes, 1);

		// Build the instance to be classified
		String[] dataToClassify = input.split(",");

		Instance toClassify = new Instance(this.numAttr - 1);
		toClassify.setDataset(classificationInstances);

		for (int i = 0; i < (numAttr - 1); i++) {
			// System.out.println("Attribute " + i + " - " + dataToClassify[i]);
			toClassify.setValue((Attribute) attributes.elementAt(i), dataToClassify[i]);
		}

		// Specify that the instance belong to the training set
		// in order to inherit from the set description
		toClassify.setDataset(this.trainingInstances);

		// Get the likelihood of each classes
		double[] distribution = this.classifier.distributionForInstance(toClassify);

		return distribution;
	}

	/**
	 * print the Evaluation Summary of the classifier
	 */
	public void printEvaluationSummary() {
		System.out.println("\n*******************************");
		System.out.println("      Evaluation Summary");
		System.out.println("*******************************");
		System.out.println(this.evalSummary);
	}

	/**
	 * print the values of ARFF data from trainingInstances
	 */
	public void printARFF() {
		System.out.println(this.trainingInstances);
	}

	/**
	 * Exports training data to an ARFF file
	 * 
	 * @param outputFilePath
	 */
	public boolean exportARFF(String outputFilePath) {
		// System.out.println("Exporting ARFF...");

		PrintWriter writer;
		try {
			writer = new PrintWriter(outputFilePath, "UTF-8");
			writer.println(trainingInstances);
			writer.close();

		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		}

		// Test Output
		System.out.println("Wrote to: " + outputFilePath);
		return true;
	}

	/**
	 * Imports an ARFF from a given filepath
	 * 
	 * @param filePath
	 * @throws Exception
	 */
	public boolean importARFF(String filePath) throws Exception {

		try {

			DataSource source = new DataSource(filePath);
			trainingInstances = source.getDataSet();

			// setting class attribute if the data format does not provide this
			// information
			// For example, the XRFF format saves the class attribute
			// information as
			// well
			if (trainingInstances.classIndex() == -1) {
				trainingInstances.setClassIndex(trainingInstances.numAttributes() - 1);
			}
			return true;
		} catch (Exception e3) {
			return false;
		}

	}

	/**
	 * Imports an arff file from the given trainingdata HashSet
	 * 
	 * @param HashSet<string>
	 *            trainingData
	 */
	public void importARFF(HashSet<String> trainingData) {

		this.trainingInstances = new Instances("Classification", attributes, trainingData.size());

		// Make the last attribute be the class
		this.trainingInstances.setClassIndex(numAttr - 1);

		for (String sdata : trainingData) {
			// System.out.println(trainingData);

			String[] values = sdata.split(",");

			Instance instance = new Instance(numAttr);

			for (int i = 0; i < numAttr; i++) {
				instance.setValue((Attribute) attributes.elementAt(i), values[i]);
			}

			this.trainingInstances.add(instance); // Add new instance to
													// training data
		}
	}

	/**
	 * Imports an arff file from the given training data string array
	 * 
	 * @param String[]
	 *            trainingData
	 */
	public boolean importARFF(String[] trainingData) throws Exception {

		try {
			this.trainingInstances = new Instances("Classification", attributes, trainingData.length);

			// Make the last attribute be the class
			this.trainingInstances.setClassIndex(numAttr - 1);

			for (String sdata : trainingData) {
				// System.out.println(trainingData);

				String[] values = sdata.split(",");

				Instance instance = new Instance(numAttr);

				for (int i = 0; i < numAttr; i++) {
					instance.setValue((Attribute) attributes.elementAt(i), values[i]);
				}

				this.trainingInstances.add(instance); // Add new instance to
														// training data
			}
			return true;
		} catch (Exception e10) {
			return false;
		}
	}

	public Classifier getClassifier() {
		return classifier;
	}

	public Attribute getAttribute(String name, FastVector nominalVal) {
		return new Attribute(name, nominalVal);
	}

	/**
	 * Returns the number of attributes being used by the file
	 * 
	 * @return
	 */
	public int getNumberOfAttributes() {
		return numAttr;
	}

	public Instances getTrainingInstances() {
		return trainingInstances;
	}

	/**
	 * Saves the learning machine to a file
	 */
	public void saveLM() {

		try {
			System.out.print("Saving Learning Machine to trainedmachine.model");
			weka.core.SerializationHelper.write("trainedmachine.model", classifier);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Loads the LM From a file
	 * 
	 * @param LMBrain
	 */
	public Classifier loadLM(String LMBrain) {

		System.out.print("Loading Learning machine from file.");
		try {
			Classifier classifier = (Classifier) weka.core.SerializationHelper.read(LMBrain);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return classifier;

	}
}
