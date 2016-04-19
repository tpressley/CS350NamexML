package edu.odu.cs.cs350.namex;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class LearningMachine implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -246225729791282722L;
	private Classifier clssf;
	private FastVector attrs;
	private int numAttr;
	private Instances trainInsts;
	private String evalSummary;
	
	public LearningMachine() 
	{
		int k = 5;    // k value defaulted to 5
		
		int dimension = (((2 * k) + 1) * 14) + k + 1;

		// Initialize the Classifier as a Naive Bayes Classifier
		clssf = (Classifier) new NaiveBayes();

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
		// Attribute Lexical = new Attribute("Lexical", NominalValLexical);

		// Declare PartOfSpeech Attribute with its values
		FastVector NominalValPoS = new FastVector(6);
		NominalValPoS.addElement("article");
		NominalValPoS.addElement("conjunction");
		NominalValPoS.addElement("period");
		NominalValPoS.addElement("comma");
		NominalValPoS.addElement("hyphen");
		NominalValPoS.addElement("other");
		// Attribute PartOfSpeech = new Attribute("PartOfSpeech",
		// NominalValPoS);

		// Declare Gazetteer Attributes with its values
		FastVector NominalValGazetteer = new FastVector(2);
		NominalValGazetteer.addElement("0");
		NominalValGazetteer.addElement("1");

		// Declare Name Attribute
		FastVector NominalValName = new FastVector(3);
		NominalValName.addElement("beginning");
		NominalValName.addElement("continuing");
		NominalValName.addElement("other");
		// Attribute Name = new Attribute("Name", NominalValName);

		// Declare the Feature vector
		attrs = new FastVector(dimension);

		// Dimensions for the first (2k + 1) tokens
		for (int i = 0; i < ((2 * k) + 1); i++) {
			attrs.addElement(getAttribute("Lexical" + i, NominalValLexical));
			attrs.addElement(getAttribute("PartOfSpeech" + i, NominalValPoS));
			attrs.addElement(getAttribute("DictionaryWord" + i, NominalValGazetteer));
			attrs.addElement(getAttribute("City" + i, NominalValGazetteer));
			attrs.addElement(getAttribute("Country" + i, NominalValGazetteer));
			attrs.addElement(getAttribute("Places" + i, NominalValGazetteer));
			attrs.addElement(getAttribute("DTICFirst" + i, NominalValGazetteer));
			attrs.addElement(getAttribute("DTICLast" + i, NominalValGazetteer));
			attrs.addElement(getAttribute("CommonFirst" + i, NominalValGazetteer));
			attrs.addElement(getAttribute("CommonLast" + i, NominalValGazetteer));
			attrs.addElement(getAttribute("Honorific" + i, NominalValGazetteer));
			attrs.addElement(getAttribute("Prefix" + i, NominalValGazetteer));
			attrs.addElement(getAttribute("Suffix" + i, NominalValGazetteer));
			attrs.addElement(getAttribute("Kill" + i, NominalValGazetteer));
		}
		
		// Dimensions for the first k classified tokens plus the token to be classified
		for (int i = 0; i < (k + 1); i++)
		{
			attrs.addElement(getAttribute("Name" + i, NominalValName));
		}
		
		numAttr = attrs.size();
	}
	
	public String getShingle(ArrayList<Token> tokens, int k)
	{	
		// add k null tokens to the beginning of the arraylist and k null tokens at the end
		for (int i = 0; i < k; i++)
		{
			tokens.add(0, new Token("null"));
			tokens.add(new Token("null"));
		}
		
		// build a shingle
		for (int i = 5; i < (tokens.size() - k); i++)
		{
			StringBuilder b = new StringBuilder();
			
			b.append(tokens.get(i - 5).toString() + ",");
			b.append(tokens.get(i - 4).toString() + ",");
			b.append(tokens.get(i - 3).toString() + ",");
			b.append(tokens.get(i - 2).toString() + ",");
			b.append(tokens.get(i - 1).toString() + ",");
			b.append(tokens.get(i).toString() + ",");
			b.append(tokens.get(i + 1).toString() + ",");
			b.append(tokens.get(i + 2).toString() + ",");
			b.append(tokens.get(i + 3).toString() + ",");
			b.append(tokens.get(i + 4).toString() + ",");
			b.append(tokens.get(i + 5).toString() + ",");
			b.append(tokens.get(i - 5).getName() + ",");
			b.append(tokens.get(i - 4).getName() + ",");
			b.append(tokens.get(i - 3).getName() + ",");
			b.append(tokens.get(i - 2).getName() + ",");
			b.append(tokens.get(i - 1).getName() + ",");
			
			try 
			{
				tokens.get(i).setName(classify(b.toString()));
				
				if (tokens.get(i).isKillWord() == 1)
				{
					boolean reachedBeginning = false;
					
					tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme());
					tokens.get(i).setName("other");
					
					for (int j = 1; j < 6; j++)
					{
						if (reachedBeginning == false)
						{
							if (tokens.get(i - j).getName().equals("beginning"))
							{
								tokens.get(i - j).setTaggedLexeme(tokens.get(i - j).getLexeme());
								tokens.get(i - j).setName("other");
								reachedBeginning = true;
							}
							else
							{
								tokens.get(i - j).setTaggedLexeme(tokens.get(i - j).getLexeme());
								tokens.get(i - j).setName("other");
							}
						}
						else
						{
							tokens.get(i - j).setTaggedLexeme(tokens.get(i - j).getLexeme());
							tokens.get(i - j).setName("other");
						}
					}
				}
				else if (tokens.get(i).isHonorific() == 1)
				{
					tokens.get(i).setTaggedLexeme("<PER>" + tokens.get(i).getLexeme());
					//tokens.get(i).setTaggedLexeme("<PER>2" + tokens.get(i).getLexeme());
					tokens.get(i).setName("beginning");
				}
				else if (tokens.get(i).getName().equals("beginning"))
				{
					if (tokens.get(i - 1).isHonorific() == 1)
					{
						tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme());
					}
					else if (tokens.get(i - 2).isHonorific() == 1)
					{
						tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme());
					}
					else if (tokens.get(i).isSuffix() == 1)
					{
						tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme());						
					}
					else if (tokens.get(i - 1).getName().equals("continuing"))
					{
						tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme());
					}
					else if (tokens.get(i).getLexical().equals("other"))
					{
						tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme());
					}
					/*
					// J. Smith
					else if ((tokens.get(i - 2).getName().equals("continuing") 
							&& tokens.get(i - 2).getLexical().equals("capLetter"))
					&& tokens.get(i - 1).getPartOfSpeech().equals("period")
					&& (tokens.get(i).getName().equals("continuing")
							|| tokens.get(i).getName().equals("beginning")))
					{
						tokens.get(i - 2).setTaggedLexeme("<PER>2" + tokens.get(i - 2).getLexeme());
					}
					// Smith, John
					else if (tokens.get(i - 2).getName().equals("continuing") 
					&& tokens.get(i - 1).getPartOfSpeech().equals("comma")
					&& tokens.get(i).getName().equals("beginning"))
					{
						tokens.get(i - 2).setTaggedLexeme("<PER>2" + tokens.get(i - 2).getLexeme());
						tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme());
					}
					*/
					else
					{
						tokens.get(i).setTaggedLexeme("<PER>" + tokens.get(i).getLexeme());
						//tokens.get(i).setTaggedLexeme("<PER>1" + tokens.get(i).getLexeme());
					}
				}
				else
				{
					tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme());
				}
				//tokens.get(i).setDistribution(getDistribution(b.toString()));
			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//System.out.println(tokens.get(i).getLexeme() + " - " + tokens.get(i).getName());
			//double[] distribution = tokens.get(i).getDistribution();
			
			/*
			System.out.println(" beginning: " + distribution[0]);
			System.out.println("continuing: " + distribution[1]);
			System.out.println("     other: " + distribution[2]);
			System.out.print("    previous: " + tokens.get(i - 5).getName() + ",");
			System.out.print(tokens.get(i - 4).getName() + ",");
			System.out.print(tokens.get(i - 3).getName() + ",");
			System.out.print(tokens.get(i - 2).getName() + ",");
			System.out.print(tokens.get(i - 1).getName() + "\n");
			*/
			
		}
		
		StringBuilder b = new StringBuilder();
		b.append("<NER>");
		for (int i = 5; i < (tokens.size() - k); i++)
		{
			// honorific first last
			if (tokens.get(i + 1).getName().equals("other")
					&& !tokens.get(i + 1).getPartOfSpeech().equals("comma")
					&& !tokens.get(i + 1).getPartOfSpeech().equals("period")
					&& tokens.get(i + 2).isSuffix() != 1
					&& tokens.get(i).getName().equals("continuing")
					&& tokens.get(i - 1).getName().equals("beginning")
					&& tokens.get(i - 2).isHonorific() == 1)
			{
				tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme() + "</PER>");
				//tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme() + "</PER>6");
			}
			// honorific period first last comma suffix period
			else if (tokens.get(i + 1).getName().equals("other")
					&& tokens.get(i).getPartOfSpeech().equals("period")
					&& tokens.get(i - 1).isSuffix() == 1
					&& tokens.get(i - 2).getPartOfSpeech().equals("comma")
					&& tokens.get(i - 3).getName().equals("continuing"))
			{
				tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme() + "</PER>");
				//tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme() + "</PER>5.5");
				//tokens.get(i - 1).setTaggedLexeme(tokens.get(i - 1).getLexeme());
				//tokens.get(i - 2).setTaggedLexeme(tokens.get(i - 2).getLexeme());
				//tokens.get(i - 3).setTaggedLexeme(tokens.get(i - 3).getLexeme());
			}
			// honorific period first last
			else if (!tokens.get(i + 1).getPartOfSpeech().equals("comma")
					&& tokens.get(i + 2).isSuffix() != 1
					&& tokens.get(i).getName().equals("continuing")
					&& tokens.get(i - 1).getName().equals("beginning")
					&& tokens.get(i - 2).getPartOfSpeech().equals("period")
					&& tokens.get(i - 3).isHonorific() == 1)
			{
				tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme() + "</PER>");
				//tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme() + "</PER>5.35");
			}
			// honorific period first mi period last
			else if (tokens.get(i + 1).getName().equals("other")
					&& !tokens.get(i + 1).getPartOfSpeech().equals("comma")
					&& tokens.get(i + 2).isSuffix() != 1
					&& tokens.get(i).getName().equals("continuing")
					&& tokens.get(i - 1).getPartOfSpeech().equals("period")
					&& tokens.get(i - 2).getName().equals("continuing")
					&& tokens.get(i - 3).getName().equals("beginning"))
			{
				tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme() + "</PER>");
				//tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme() + "</PER>5.35");
			}
			// honorific period first middle last
			else if (tokens.get(i + 1).getName().equals("other")
					&& !tokens.get(i + 1).getPartOfSpeech().equals("comma")
					&& (tokens.get(i - 2).getName().equals("beginning")
							|| tokens.get(i - 2).getName().equals("continuing"))
					&& tokens.get(i).getLexical().equals("capitalized")
					&& tokens.get(i - 1).getName().equals("continuing")
					&& tokens.get(i - 2).getName().equals("beginning"))
			{
				tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme() + "</PER>");
				//tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme() + "</PER>5.25");
			}
			// matches Dr. John Smith
			else if ((tokens.get(i + 1).getName().equals("other")
					&& !tokens.get(i + 1).getPartOfSpeech().equals("comma")
					&& tokens.get(i + 2).isSuffix() != 1)
					&& !tokens.get(i + 3).getPartOfSpeech().equals("period")
					&& tokens.get(i + 4).getName().equals("continuing")
					&& tokens.get(i).getName().equals("continuing")
					&& tokens.get(i - 1).getName().equals("beginning")
					&& tokens.get(i - 2).getPartOfSpeech().equals("period")
					&& tokens.get(i - 3).isHonorific() == 1)
			{
				tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme() + "</PER>");
				//tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme() + "</PER>5");
			}
			// honorific period first middle last comma honorific period
			else if (tokens.get(i + 1).getName().equals("other")
					&& tokens.get(i).getPartOfSpeech().equals("period")
					&& tokens.get(i - 1).isSuffix() == 1
					&& tokens.get(i - 2).getPartOfSpeech().equals("comma")
					&& tokens.get(i - 3).getPartOfSpeech().equals("continuing")
					&& tokens.get(i - 4).getPartOfSpeech().equals("continuing")
					&& tokens.get(i - 5).getPartOfSpeech().equals("beginning"))
			{
				tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme() + "</PER>");
				//tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme() + "</PER>4");
			}
			// first middle last
			else if (tokens.get(i + 1).getName().equals("other")
					&& tokens.get(i).getName().equals("continuing")
					&& !tokens.get(i + 1).getPartOfSpeech().equals("comma")
					&& tokens.get(i + 2).isSuffix() != 1
					&& tokens.get(i - 1).getName().equals("continuing")
					&& tokens.get(i - 2).getName().equals("beginning"))
			{
				tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme() + "</PER>");
				//tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme() + "</PER>3");
			}
			// first last comma suffix period
			else if (tokens.get(i + 1).getName().equals("other")
					&& tokens.get(i).getPartOfSpeech().equals("period")
					&& tokens.get(i + 1).isSuffix() == 1
					&& tokens.get(i - 2).getPartOfSpeech().equals("comma")
					&& tokens.get(i - 3).getName().equals("continuing")
					&& tokens.get(i - 4).getName().equals("beginning"))
			{
				tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme() + "</PER>");
				//tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme() + "</PER>2.75");
			}
			// first middleInitial period LastName
			else if (tokens.get(i + 1).getName().equals("other")
					&& !tokens.get(i + 1).getPartOfSpeech().equals("comma")
					&& tokens.get(i + 1).isSuffix() != 1
					&& tokens.get(i).getName().equals("continuing")
					&& tokens.get(i - 1).getPartOfSpeech().equals("period")
					&& tokens.get(i - 2).getName().equals("continuing")
					&& tokens.get(i - 3).getName().equals("beginning"))
			{
				tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme() + "</PER>");
				//tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme() + "</PER>2");
			}
			// first last
			else if (tokens.get(i + 1).getName().equals("other")
					&& (!tokens.get(i + 1).getPartOfSpeech().equals("period")
							&& !tokens.get(i + 1).getPartOfSpeech().equals("comma"))
					&& tokens.get(i).getName().equals("continuing")
					&& tokens.get(i - 1).getName().equals("beginning"))
			{
				tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme() + "</PER>");
				//tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme() + "</PER>1");
			}		
			// firstInitial period last
			else if (tokens.get(i + 1).getName().equals("other")
					&& !tokens.get(i + 1).getPartOfSpeech().equals("period")
					&& !tokens.get(i + 1).getPartOfSpeech().equals("comma")
					&& (tokens.get(i).getName().equals("continuing")
							|| tokens.get(i).getName().equals("beginning"))
					&& tokens.get(i - 1).getPartOfSpeech().equals("period")
					&& tokens.get(i - 2).getLexical().equals("capLetter"))
			{
				tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme() + "</PER>");
				//tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme() + "</PER>0");
			}	
			// last comma first period
			else if (tokens.get(i + 1).getName().equals("other")
					&& !tokens.get(i + 1).getPartOfSpeech().equals("period")
					&& !tokens.get(i + 1).getPartOfSpeech().equals("comma")
					&& tokens.get(i).getPartOfSpeech().equals("period")
					&& tokens.get(i - 1).getName().equals("beginning")
					&& tokens.get(i - 2).getPartOfSpeech().equals("comma")
					&& tokens.get(i - 3).getName().equals("continuing"))
			{
				tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme() + "</PER>");
				//tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme() + "</PER>0");
			}
			// last comma first
			else if (tokens.get(i + 1).getName().equals("other")
					&& !tokens.get(i + 1).getPartOfSpeech().equals("period")
					&& !tokens.get(i + 1).getPartOfSpeech().equals("comma")
					&& (tokens.get(i).getName().equals("continuing")
							|| tokens.get(i).getName().equals("beginning"))
					&& tokens.get(i - 1).getPartOfSpeech().equals("comma")
					&& tokens.get(i - 2).getName().equals("continuing"))
			{
				tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme() + "</PER>");
				//tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme() + "</PER>0");
			}	
			// first mi period last
			else if (tokens.get(i + 1).getName().equals("other")
					&& tokens.get(i).getName().equals("continuing")
					&& tokens.get(i - 1).getPartOfSpeech().equals("period"))
			{
				tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme() + "</PER>");
				//tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme() + "</PER>-0");
			}	
			// first last
			else if (tokens.get(i + 1).getName().equals("other")
					&& tokens.get(i).getName().equals("continuing")
					&& tokens.get(i).getLexical().equals("capitalized")
					&& tokens.get(i - 1).getName().equals("beginning"))
			{
				tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme() + "</PER>");
				//tokens.get(i).setTaggedLexeme(tokens.get(i).getLexeme() + "</PER>-0");
			}	
			
			// Combine and mark shingles
			if (!tokens.get(i).getLexeme().equals("null"))
			{
				if (tokens.get(i + 1).getPartOfSpeech().equals("comma")
						|| tokens.get(i + 1).getPartOfSpeech().equals("period"))
				{
					b.append(tokens.get(i).getTaggedLexeme());
					//System.out.print(tokens.get(i).getTaggedLexeme());
					//System.out.print(tokens.get(i).getName() + " ");
				}
				else
				{
					b.append(tokens.get(i).getTaggedLexeme() + " ");
					//System.out.print(tokens.get(i).getTaggedLexeme() + " ");
					//System.out.print(tokens.get(i).getName() + " ");
				}
			}
		}
		b.append("</NER>");
		System.out.println();
		
		return b.toString();
	}
	
	public HashSet<String> getTrainingShingles(ArrayList<Token> tks, int k, boolean showOutput)
	{
		ArrayList<Token> tokens = new ArrayList<Token>();
		HashSet<String> shingles = new HashSet<String>();
		
		for (int i = 0; i < tks.size(); i++)
		{
			if (tks.get(i).getLexeme().equals("<PER>"))
			{
				tks.get(i + 1).setName("beginning");
				tks.get(i + 1).setPER("start");
			}
			else if (tks.get(i).getLexeme().equals("</PER>"))
			{
				tks.get(i - 1).setName("continuing");
				tks.get(i - 1).setPER("end");
			}
			else
			{
				tokens.add(tks.get(i));
			}
		}
		
		// add k null tokens to the beginning of the arraylist and k null tokens at the end
		for (int i = 0; i < k; i++)
		{
			tokens.add(0, new Token("null"));
			tokens.add(new Token("null"));
		}
		
		//System.out.println("Tokens size: " + tokens.size());
		boolean withinPER = false;

		
		// build a shingle
		for (int i = 5; i < (tokens.size() - k); i++)
		{
			//System.out.println("LEXEME TO BE CLASSIFIED: " + tokens.get(i).getLexeme());
			if (tokens.get(i).getPER().equals("start"))
			{
				withinPER = true;
			}			
			
			if (withinPER)
			{
				if (tokens.get(i).getLexical().equals("capitalized")
					|| tokens.get(i).getLexical().equals("capLetter")
					|| tokens.get(i).getLexical().equals("allCaps"))
				{
					
					// set the classification of the token following a <PER> tag to beginning
					if (tokens.get(i).getPER().equals("start"))
					{
						//System.out.println("<PER>start");
						// if after PER and before a comma, the name is in the format lastname, firstname
						if (tokens.get(i + 1).getPartOfSpeech().equals("comma"))
						{
							tokens.get(i).setName("continuing");
						}
						else
						{
							tokens.get(i).setName("beginning");							
						}
					}
					
					// if the token before previous token was an honorific then a period, set to beginning
					else if (tokens.get(i - 2).isHonorific() == 1 && tokens.get(i - 1).getPartOfSpeech().equals("period"))
					{
						tokens.get(i).setName("beginning");
					}
					
					// if the previous token was an honorific, set to beginning
					else if (tokens.get(i - 1).isHonorific() == 1)
					{
						tokens.get(i).setName("beginning");		
					}
					
					// if the next token is a comma, and the previous was a <PER> tag
					else if (tokens.get(i + 1).getPartOfSpeech().equals("comma") && tokens.get(i - 1).getLexeme().equals("<PER>"))
					{
						tokens.get(i).setName("continuing");
					}
					
					// if the previous token was a comma and the one before that was a continuing
					else if (tokens.get(i - 1).getPartOfSpeech().equals("comma") && tokens.get(i - 2).getName().equals("continuing"))
					{
						// If the token is a suffix, then set it to continuing
						if (tokens.get(i).isSuffix() == 1)
						{
							tokens.get(i).setName("continuing");
						}
						else
						{
							tokens.get(i).setName("beginning");							
						}
					}
					
					// if the previous token was a beginning 
					else if (tokens.get(i - 1).getName().equals("beginning"))
					{
						tokens.get(i).setName("continuing");
					}
					
					else if (!tokens.get(i - 1).getPartOfSpeech().equals("period"))
					{
						tokens.get(i).setName("beginning");
					}
					
					else
					{
						tokens.get(i).setName("continuing");
					}
				}
				else
				{
					tokens.get(i).setName("other");
				}	
			}
			else
			{
				tokens.get(i).setName("other");
			}
			
			// test output
			if (showOutput)
			//if (true)
			{
				System.out.println(tokens.get(i).getLexeme() + " | "
						+ tokens.get(i).getName());
			}
			
			StringBuilder b = new StringBuilder();
			
			b.append(tokens.get(i - 5).toString() + ",");
			b.append(tokens.get(i - 4).toString() + ",");
			b.append(tokens.get(i - 3).toString() + ",");
			b.append(tokens.get(i - 2).toString() + ",");
			b.append(tokens.get(i - 1).toString() + ",");
			b.append(tokens.get(i).toString() + ",");
			b.append(tokens.get(i + 1).toString() + ",");
			b.append(tokens.get(i + 2).toString() + ",");
			b.append(tokens.get(i + 3).toString() + ",");
			b.append(tokens.get(i + 4).toString() + ",");
			b.append(tokens.get(i + 5).toString() + ",");
			b.append(tokens.get(i - 5).getName() + ",");
			b.append(tokens.get(i - 4).getName() + ",");
			b.append(tokens.get(i - 3).getName() + ",");
			b.append(tokens.get(i - 2).getName() + ",");
			b.append(tokens.get(i - 1).getName() + ",");	
			b.append(tokens.get(i).getName() + ",");
			
			shingles.add(b.toString());
			
			if (tokens.get(i).getPER().equals("end"))
			{
				withinPER = false;
			}
		}
		
		return shingles;	
}
	
	public Attribute getAttribute(String name, FastVector nominalVal) {
		return new Attribute(name, nominalVal);
	}
	
	/*
	//default constructor for LearningMachine
	public LearningMachine() {
		// Initialize the Classifier as a Naive Bayes Classifier
		clssf = (Classifier) new NaiveBayes();

		FastVector nominValLexi = new FastVector(9);
		initNominValLexi(nominValLexi);
		Attribute lexic1 = new Attribute("Lexical", nominValLexi);
		Attribute lexic2 = new Attribute("Lexical", nominValLexi);
		Attribute lexic3 = new Attribute("Lexical", nominValLexi);
		Attribute lexic4 = new Attribute("Lexical", nominValLexi);
		Attribute lexic5 = new Attribute("Lexical", nominValLexi);
		Attribute lexic6 = new Attribute("Lexical", nominValLexi);
		Attribute lexic7 = new Attribute("Lexical", nominValLexi);
		Attribute lexic8 = new Attribute("Lexical", nominValLexi);
		Attribute lexic9 = new Attribute("Lexical", nominValLexi);
		Attribute lexic10 = new Attribute("Lexical", nominValLexi);
		Attribute lexic11 = new Attribute("Lexical", nominValLexi);

		// Declare PartOfSpeech Attribute with its values
		FastVector nominValPoS = new FastVector(6);
		initNominValPos(nominValPoS);
		Attribute PartOfSpeech1 = new Attribute("PartOfSpeech", nominValPoS);
		Attribute PartOfSpeech2 = new Attribute("PartOfSpeech", nominValPoS);
		Attribute PartOfSpeech3 = new Attribute("PartOfSpeech", nominValPoS);
		Attribute PartOfSpeech4 = new Attribute("PartOfSpeech", nominValPoS);
		Attribute PartOfSpeech5 = new Attribute("PartOfSpeech", nominValPoS);
		Attribute PartOfSpeech6 = new Attribute("PartOfSpeech", nominValPoS);
		Attribute PartOfSpeech7 = new Attribute("PartOfSpeech", nominValPoS);
		Attribute PartOfSpeech8 = new Attribute("PartOfSpeech", nominValPoS);
		Attribute PartOfSpeech9 = new Attribute("PartOfSpeech", nominValPoS);
		Attribute PartOfSpeech10 = new Attribute("PartOfSpeech", nominValPoS);
		Attribute PartOfSpeech11 = new Attribute("PartOfSpeech", nominValPoS);

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
		attrs = new FastVector(71);
		// word one
		attrs.addElement(lexic1);
		attrs.addElement(PartOfSpeech1);
		attrs.addElement(DictionaryWord1);
		attrs.addElement(City1);
		attrs.addElement(Country1);
		attrs.addElement(Places1);
		attrs.addElement(DTICFirst1);
		attrs.addElement(DTICLast1);
		attrs.addElement(CommonFirst1);
		attrs.addElement(CommonLast1);
		attrs.addElement(Honorific1);
		attrs.addElement(Prefix1);
		attrs.addElement(Suffix1);
		attrs.addElement(Kill1);
		// word two
		attrs.addElement(lexic2);
		attrs.addElement(PartOfSpeech2);
		attrs.addElement(DictionaryWord2);
		attrs.addElement(City2);
		attrs.addElement(Country2);
		attrs.addElement(Places2);
		attrs.addElement(DTICFirst2);
		attrs.addElement(DTICLast2);
		attrs.addElement(CommonFirst2);
		attrs.addElement(CommonLast2);
		attrs.addElement(Honorific2);
		attrs.addElement(Prefix2);
		attrs.addElement(Suffix2);
		attrs.addElement(Kill2);

		// word three
		attrs.addElement(lexic3);
		attrs.addElement(PartOfSpeech3);
		attrs.addElement(DictionaryWord3);
		attrs.addElement(City3);
		attrs.addElement(Country3);
		attrs.addElement(Places3);
		attrs.addElement(DTICFirst3);
		attrs.addElement(DTICLast3);
		attrs.addElement(CommonFirst3);
		attrs.addElement(CommonLast3);
		attrs.addElement(Honorific3);
		attrs.addElement(Prefix3);
		attrs.addElement(Suffix3);
		attrs.addElement(Kill3);
		// word four
		attrs.addElement(lexic4);
		attrs.addElement(PartOfSpeech4);
		attrs.addElement(DictionaryWord4);
		attrs.addElement(City4);
		attrs.addElement(Country4);
		attrs.addElement(Places4);
		attrs.addElement(DTICFirst4);
		attrs.addElement(DTICLast4);
		attrs.addElement(CommonFirst4);
		attrs.addElement(CommonLast4);
		attrs.addElement(Honorific4);
		attrs.addElement(Prefix4);
		attrs.addElement(Suffix4);
		attrs.addElement(Kill4);

		// word five
		attrs.addElement(lexic5);
		attrs.addElement(PartOfSpeech5);
		attrs.addElement(DictionaryWord5);
		attrs.addElement(City5);
		attrs.addElement(Country5);
		attrs.addElement(Places5);
		attrs.addElement(DTICFirst5);
		attrs.addElement(DTICLast5);
		attrs.addElement(CommonFirst5);
		attrs.addElement(CommonLast5);
		attrs.addElement(Honorific5);
		attrs.addElement(Prefix5);
		attrs.addElement(Suffix5);
		attrs.addElement(Kill5);

		// word six
		attrs.addElement(lexic6);
		attrs.addElement(PartOfSpeech6);
		attrs.addElement(DictionaryWord6);
		attrs.addElement(City6);
		attrs.addElement(Country6);
		attrs.addElement(Places6);
		attrs.addElement(DTICFirst6);
		attrs.addElement(DTICLast6);
		attrs.addElement(CommonFirst6);
		attrs.addElement(CommonLast6);
		attrs.addElement(Honorific6);
		attrs.addElement(Prefix6);
		attrs.addElement(Suffix6);
		attrs.addElement(Kill6);

		// word seven
		attrs.addElement(lexic7);
		attrs.addElement(PartOfSpeech7);
		attrs.addElement(DictionaryWord7);
		attrs.addElement(City7);
		attrs.addElement(Country7);
		attrs.addElement(Places7);
		attrs.addElement(DTICFirst7);
		attrs.addElement(DTICLast7);
		attrs.addElement(CommonFirst7);
		attrs.addElement(CommonLast7);
		attrs.addElement(Honorific7);
		attrs.addElement(Prefix7);
		attrs.addElement(Suffix7);
		attrs.addElement(Kill7);
		// word eight
		attrs.addElement(lexic8);
		attrs.addElement(PartOfSpeech8);
		attrs.addElement(DictionaryWord8);
		attrs.addElement(City8);
		attrs.addElement(Country8);
		attrs.addElement(Places8);
		attrs.addElement(DTICFirst8);
		attrs.addElement(DTICLast8);
		attrs.addElement(CommonFirst8);
		attrs.addElement(CommonLast8);
		attrs.addElement(Honorific8);
		attrs.addElement(Prefix8);
		attrs.addElement(Suffix8);
		attrs.addElement(Kill8);
		// word nine
		attrs.addElement(lexic9);
		attrs.addElement(PartOfSpeech9);
		attrs.addElement(DictionaryWord9);
		attrs.addElement(City9);
		attrs.addElement(Country9);
		attrs.addElement(Places9);
		attrs.addElement(DTICFirst9);
		attrs.addElement(DTICLast9);
		attrs.addElement(CommonFirst9);
		attrs.addElement(CommonLast9);
		attrs.addElement(Honorific9);
		attrs.addElement(Prefix9);
		attrs.addElement(Suffix9);
		attrs.addElement(Kill9);
		// word ten
		attrs.addElement(lexic10);
		attrs.addElement(PartOfSpeech10);
		attrs.addElement(DictionaryWord10);
		attrs.addElement(City10);
		attrs.addElement(Country10);
		attrs.addElement(Places10);
		attrs.addElement(DTICFirst10);
		attrs.addElement(DTICLast10);
		attrs.addElement(CommonFirst10);
		attrs.addElement(CommonLast10);
		attrs.addElement(Honorific10);
		attrs.addElement(Prefix10);
		attrs.addElement(Suffix10);
		attrs.addElement(Kill10);
		// word eleven
		attrs.addElement(lexic11);
		attrs.addElement(PartOfSpeech11);
		attrs.addElement(DictionaryWord11);
		attrs.addElement(City11);
		attrs.addElement(Country11);
		attrs.addElement(Places11);
		attrs.addElement(DTICFirst11);
		attrs.addElement(DTICLast11);
		attrs.addElement(CommonFirst11);
		attrs.addElement(CommonLast11);
		attrs.addElement(Honorific11);
		attrs.addElement(Prefix11);
		attrs.addElement(Suffix11);
		attrs.addElement(Kill11);
		// Middle word name classification
		attrs.addElement(Name1);
		attrs.addElement(Name2);
		attrs.addElement(Name3);
		attrs.addElement(Name4);
		attrs.addElement(Name5);
		attrs.addElement(Name6);

	}
*/

	public LearningMachine(String machineModel) {

		if (machineModel.equalsIgnoreCase("new"))
			clssf = (Classifier) new NaiveBayes();
		else
			loadLM(machineModel);

		// Initialize Attributes
		// Declare Lexical Attribute with its values
		FastVector NominalValLexical = new FastVector(9);
		initNominValLexi(NominalValLexical);
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
		initNominValPos(NominalValPoS);
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
		attrs = new FastVector(71);
		// word one
		attrs.addElement(Lexical1);
		attrs.addElement(PartOfSpeech1);
		attrs.addElement(DictionaryWord1);
		attrs.addElement(City1);
		attrs.addElement(Country1);
		attrs.addElement(Places1);
		attrs.addElement(DTICFirst1);
		attrs.addElement(DTICLast1);
		attrs.addElement(CommonFirst1);
		attrs.addElement(CommonLast1);
		attrs.addElement(Honorific1);
		attrs.addElement(Prefix1);
		attrs.addElement(Suffix1);
		attrs.addElement(Kill1);
		// word two
		attrs.addElement(Lexical2);
		attrs.addElement(PartOfSpeech2);
		attrs.addElement(DictionaryWord2);
		attrs.addElement(City2);
		attrs.addElement(Country2);
		attrs.addElement(Places2);
		attrs.addElement(DTICFirst2);
		attrs.addElement(DTICLast2);
		attrs.addElement(CommonFirst2);
		attrs.addElement(CommonLast2);
		attrs.addElement(Honorific2);
		attrs.addElement(Prefix2);
		attrs.addElement(Suffix2);
		attrs.addElement(Kill2);

		// word three
		attrs.addElement(Lexical3);
		attrs.addElement(PartOfSpeech3);
		attrs.addElement(DictionaryWord3);
		attrs.addElement(City3);
		attrs.addElement(Country3);
		attrs.addElement(Places3);
		attrs.addElement(DTICFirst3);
		attrs.addElement(DTICLast3);
		attrs.addElement(CommonFirst3);
		attrs.addElement(CommonLast3);
		attrs.addElement(Honorific3);
		attrs.addElement(Prefix3);
		attrs.addElement(Suffix3);
		attrs.addElement(Kill3);
		// word four
		attrs.addElement(Lexical4);
		attrs.addElement(PartOfSpeech4);
		attrs.addElement(DictionaryWord4);
		attrs.addElement(City4);
		attrs.addElement(Country4);
		attrs.addElement(Places4);
		attrs.addElement(DTICFirst4);
		attrs.addElement(DTICLast4);
		attrs.addElement(CommonFirst4);
		attrs.addElement(CommonLast4);
		attrs.addElement(Honorific4);
		attrs.addElement(Prefix4);
		attrs.addElement(Suffix4);
		attrs.addElement(Kill4);

		// word five
		attrs.addElement(Lexical5);
		attrs.addElement(PartOfSpeech5);
		attrs.addElement(DictionaryWord5);
		attrs.addElement(City5);
		attrs.addElement(Country5);
		attrs.addElement(Places5);
		attrs.addElement(DTICFirst5);
		attrs.addElement(DTICLast5);
		attrs.addElement(CommonFirst5);
		attrs.addElement(CommonLast5);
		attrs.addElement(Honorific5);
		attrs.addElement(Prefix5);
		attrs.addElement(Suffix5);
		attrs.addElement(Kill5);

		// word six
		attrs.addElement(Lexical6);
		attrs.addElement(PartOfSpeech6);
		attrs.addElement(DictionaryWord6);
		attrs.addElement(City6);
		attrs.addElement(Country6);
		attrs.addElement(Places6);
		attrs.addElement(DTICFirst6);
		attrs.addElement(DTICLast6);
		attrs.addElement(CommonFirst6);
		attrs.addElement(CommonLast6);
		attrs.addElement(Honorific6);
		attrs.addElement(Prefix6);
		attrs.addElement(Suffix6);
		attrs.addElement(Kill6);

		// word seven
		attrs.addElement(Lexical7);
		attrs.addElement(PartOfSpeech7);
		attrs.addElement(DictionaryWord7);
		attrs.addElement(City7);
		attrs.addElement(Country7);
		attrs.addElement(Places7);
		attrs.addElement(DTICFirst7);
		attrs.addElement(DTICLast7);
		attrs.addElement(CommonFirst7);
		attrs.addElement(CommonLast7);
		attrs.addElement(Honorific7);
		attrs.addElement(Prefix7);
		attrs.addElement(Suffix7);
		attrs.addElement(Kill7);

		attrs.addElement(Lexical8);
		attrs.addElement(PartOfSpeech8);
		attrs.addElement(DictionaryWord8);
		attrs.addElement(City8);
		attrs.addElement(Country8);
		attrs.addElement(Places8);
		attrs.addElement(DTICFirst8);
		attrs.addElement(DTICLast8);
		attrs.addElement(CommonFirst8);
		attrs.addElement(CommonLast8);
		attrs.addElement(Honorific8);
		attrs.addElement(Prefix8);
		attrs.addElement(Suffix8);
		attrs.addElement(Kill8);

		attrs.addElement(Lexical9);
		attrs.addElement(PartOfSpeech9);
		attrs.addElement(DictionaryWord9);
		attrs.addElement(City9);
		attrs.addElement(Country9);
		attrs.addElement(Places9);
		attrs.addElement(DTICFirst9);
		attrs.addElement(DTICLast9);
		attrs.addElement(CommonFirst9);
		attrs.addElement(CommonLast9);
		attrs.addElement(Honorific9);
		attrs.addElement(Prefix9);
		attrs.addElement(Suffix9);
		attrs.addElement(Kill9);

		attrs.addElement(Lexical10);
		attrs.addElement(PartOfSpeech10);
		attrs.addElement(DictionaryWord10);
		attrs.addElement(City10);
		attrs.addElement(Country10);
		attrs.addElement(Places10);
		attrs.addElement(DTICFirst10);
		attrs.addElement(DTICLast10);
		attrs.addElement(CommonFirst10);
		attrs.addElement(CommonLast10);
		attrs.addElement(Honorific10);
		attrs.addElement(Prefix10);
		attrs.addElement(Suffix10);
		attrs.addElement(Kill10);

		attrs.addElement(Lexical11);
		attrs.addElement(PartOfSpeech11);
		attrs.addElement(DictionaryWord11);
		attrs.addElement(City11);
		attrs.addElement(Country11);
		attrs.addElement(Places11);
		attrs.addElement(DTICFirst11);
		attrs.addElement(DTICLast11);
		attrs.addElement(CommonFirst11);
		attrs.addElement(CommonLast11);
		attrs.addElement(Honorific11);
		attrs.addElement(Prefix11);
		attrs.addElement(Suffix11);
		attrs.addElement(Kill11);

		attrs.addElement(Name1);
		attrs.addElement(Name2);
		attrs.addElement(Name3);
		attrs.addElement(Name4);
		attrs.addElement(Name5);
		attrs.addElement(Name6);
	}

	/**
	 * initialize nominValLexi[] fastvector
	 */
	public void initNominValLexi(FastVector nvl) {

		nvl.addElement("punct");
		nvl.addElement("capLetter");
		nvl.addElement("capitalized");
		nvl.addElement("allCaps");
		nvl.addElement("lineFeed");
		nvl.addElement("whiteSpace");
		nvl.addElement("number");
		nvl.addElement("other");
		nvl.addElement("null");
	}

	/**
	 * initialize nominValPos[] fastvector
	 */
	public void initNominValPos(FastVector nvp) {

		nvp.addElement("article");
		nvp.addElement("conjunction");
		nvp.addElement("period");
		nvp.addElement("comma");
		nvp.addElement("hyphen");
		nvp.addElement("other");
	}

	/**
	 * Uses the training data to train the Learning Machine
	 * 
	 * @param trainingData
	 * @throws Exception
	 */
	public boolean train(Instances trainingData) throws Exception {

		try {
			clssf.buildClassifier(trainingData);

			// Test the Model
			Evaluation evaluation = new Evaluation(trainingData);
			evaluation.evaluateModel(clssf, trainingData);

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
			clssf.buildClassifier(this.trainInsts);

			// Test the Model
			Evaluation eval = new Evaluation(this.trainInsts);
			eval.evaluateModel(clssf, this.trainInsts);

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
	/*public double[] getDistribution(String input) throws Exception {
		Instances classificationInstances = new Instances("toBeClassified", this.attrs, 1);

		// Build the instance to be classified
		String[] dataToClassify = input.split(",");

		Instance toClassify = new Instance(this.numAttr - 1);
		toClassify.setDataset(classificationInstances);

		toClassify.setValue((Attribute) attrs.elementAt(0), dataToClassify[0]);
		toClassify.setValue((Attribute) attrs.elementAt(1), dataToClassify[1]);
		toClassify.setValue((Attribute) attrs.elementAt(2), dataToClassify[2]);
		toClassify.setValue((Attribute) attrs.elementAt(3), dataToClassify[3]);
		toClassify.setValue((Attribute) attrs.elementAt(4), dataToClassify[4]);
		toClassify.setValue((Attribute) attrs.elementAt(5), dataToClassify[5]);
		toClassify.setValue((Attribute) attrs.elementAt(6), dataToClassify[6]);
		toClassify.setValue((Attribute) attrs.elementAt(7), dataToClassify[7]);
		toClassify.setValue((Attribute) attrs.elementAt(8), dataToClassify[8]);
		toClassify.setValue((Attribute) attrs.elementAt(9), dataToClassify[9]);
		toClassify.setValue((Attribute) attrs.elementAt(10), dataToClassify[10]);
		toClassify.setValue((Attribute) attrs.elementAt(11), dataToClassify[11]);
		toClassify.setValue((Attribute) attrs.elementAt(12), dataToClassify[12]);
		toClassify.setValue((Attribute) attrs.elementAt(13), dataToClassify[13]);

		// Specify that the instance belong to the training set
		// in order to inherit from the set description
		toClassify.setDataset(this.trainInsts);

		// Get the likelihood of each classes
		double[] distribution = this.clssf.distributionForInstance(toClassify);

		return distribution;
	}*/

	/**
	 * Classifies tokens to create training data
	 * 
	 * @param input
	 * @return
	 * @throws Exception
	 */
	public String classify(String input) throws Exception {
		Instances classificationInstances = new Instances("toBeClassified", this.attrs, 1);

		// Build the instance to be classified
		String[] dataToClassify = input.split(",");

		Instance toClassify = new Instance(this.numAttr - 1);
		toClassify.setDataset(classificationInstances);

		for (int i = 0; i < (numAttr - 1); i++) {
			toClassify.setValue((Attribute) attrs.elementAt(i), dataToClassify[i]);
		}

		// Specify that the instance belong to the training set
		// in order to inherit from the set description
		toClassify.setDataset(this.trainInsts);

		// Get the likelihood of each classes
		double[] distribution = this.clssf.distributionForInstance(toClassify);

		if (distribution[0] >= distribution[1] && distribution[0] >= distribution[2]) {
			return "beginning";
		} else if (distribution[1] >= distribution[0] && distribution[1] >= distribution[2]) {
			return "continuing";
		} else {
			return "other";
		}
	}

	/*
	 * /8 Creates the singling data from the tokenized input
	 * 
	 * @param input
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	/*public String classifyShingle(String input) throws Exception {
		Instances classificationInstances = new Instances("toBeClassified", this.attrs, 1);

		// Build the instance to be classified
		String[] dataToClassify = input.split(",");

		Instance toClassify = new Instance(this.numAttr - 1);
		toClassify.setDataset(classificationInstances);

		for (int i = 0; i < (numAttr - 1); i++) {
			// System.out.println("Attribute " + i + " - " + dataToClassify[i]);
			toClassify.setValue((Attribute) attrs.elementAt(i), dataToClassify[i]);
		}

		toClassify.setDataset(this.trainInsts);

		// Get the likelihood of each classes
		double[] distribution = this.clssf.distributionForInstance(toClassify);

		if (distribution[0] >= distribution[1]) {
			return "yes";
		} else {
			return "no";
		}
	}*/

	/**
	 * Get the likelihood of each classes distribution[0] is the probability of
	 * the Shingle containing a name distribution[1] is the probability of the
	 * Shingle not containing a name
	 */
	public double[] getShingleDistribution(String input) throws Exception {
		Instances classificationInstances = new Instances("toBeClassified", this.attrs, 1);

		// Build the instance to be classified
		String[] dataToClassify = input.split(",");

		Instance toClassify = new Instance(this.numAttr - 1);
		toClassify.setDataset(classificationInstances);

		for (int i = 0; i < (numAttr - 1); i++) {
			// System.out.println("Attribute " + i + " - " + dataToClassify[i]);
			toClassify.setValue((Attribute) attrs.elementAt(i), dataToClassify[i]);
		}

		// Specify that the instance belong to the training set
		// in order to inherit from the set description
		toClassify.setDataset(this.trainInsts);

		// Get the likelihood of each classes
		double[] distribution = this.clssf.distributionForInstance(toClassify);

		return distribution;
	}

	/**
	 * print the Evaluation Summary of the classifier
	 *
	 */
	public boolean printEvaluationSummary() throws Exception {

		try {
			System.out.println("\n*******************************");

			System.out.println("      Evaluation Summary");
			System.out.println("*******************************");
			System.out.println(this.evalSummary);
		} catch (Exception e47) {
			return false;
		}
		return true;
	}

	/**
	 * returns evalSummary
	 */
	public String getEvalSummary() throws Exception {

		try {
			return this.evalSummary;

		} catch (Exception e639) {
			return "error";
		}
	}

	/**
	 * print the values of ARFF data from trainingInstances
	 */
	public boolean printARFF() {

		if (this.trainInsts != null) {
			System.out.println(this.trainInsts);
			return true;
		}
		return false;
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
			writer.println(trainInsts);
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
			trainInsts = source.getDataSet();

			// setting class attribute if the data format does not provide this
			// information
			// For example, the XRFF format saves the class attribute
			// information as
			// well
			if (trainInsts.classIndex() == -1) {
				trainInsts.setClassIndex(trainInsts.numAttributes() - 1);
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
	public boolean importARFF(HashSet<String> trainingData) {

		this.trainInsts = new Instances("Classification", attrs, trainingData.size());

		// Make the last attribute be the class
		this.trainInsts.setClassIndex(numAttr - 1);

		for (String sdata : trainingData) {
			// System.out.println(trainingData);

			String[] values = sdata.split(",");

			Instance instance = new Instance(numAttr);

			for (int i = 0; i < numAttr; i++) {
				instance.setValue((Attribute) attrs.elementAt(i), values[i]);
			}

			this.trainInsts.add(instance); // Add new instance to
													// training data
		}
		
		return true;
	}

	/**
	 * Imports an arff file from the given trainingdata HashSet
	 * 
	 * @param HashSet<string>
	 *            trainingData
	 */
	public void importARFF(LinkedList<String> trainingData) {

		this.trainInsts = new Instances("Classification", attrs, trainingData.size());

		// Make the last attribute be the class
		this.trainInsts.setClassIndex(numAttr - 1);

		for (String sdata : trainingData) {
			// System.out.println(trainingData);

			String[] values = sdata.split(",");

			Instance instance = new Instance(numAttr);

			for (int i = 0; i < numAttr; i++) {
				instance.setValue((Attribute) attrs.elementAt(i), values[i]);
			}

			this.trainInsts.add(instance); // Add new instance to
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
			this.trainInsts = new Instances("Classification", attrs, trainingData.length);

			// Make the last attribute be the class
			this.trainInsts.setClassIndex(numAttr - 1);

			for (String sdata : trainingData) {
				// System.out.println(trainingData);

				String[] values = sdata.split(",");

				Instance instance = new Instance(numAttr);

				for (int i = 0; i < numAttr; i++) {
					instance.setValue((Attribute) attrs.elementAt(i), values[i]);
				}

				this.trainInsts.add(instance); // Add new instance to
												// training data
			}
			return true;
		} catch (Exception e10) {
			return false;
		}
	}

	/**
	 * returns Classifier
	 * 
	 * @return
	 */
	public Classifier getClassifier() {

		return clssf;
	}

	/**
	 * returns copy of Attribute
	 * 
	 * @param name
	 * @param nominalVal
	 * @return
	 */
	public Attribute getCopyAttribute(String name, FastVector nominalVal) {
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

	/**
	 * returns size of attributes[]
	 * 
	 * @return
	 */
	public int getSizeOfAttributes() {

		return this.attrs.size();
	}

	public Instances getTrainingInstances() {
		return trainInsts;
	}

	/**
	 * Saves the learning machine to a file
	 */
	public void saveLM() {

		try {
			System.out.print("Saving Learning Machine to trainedmachine.model");
			weka.core.SerializationHelper.write("trainedmachine.model", clssf);
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
			clssf = (Classifier) weka.core.SerializationHelper.read(LMBrain);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return clssf;

	}
}
