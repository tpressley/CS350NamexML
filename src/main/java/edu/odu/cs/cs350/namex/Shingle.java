package edu.odu.cs.cs350.namex;


public class Shingle {

	private String lexemes;
	private String classifications;
	private String arffData;
	private String containsName;
	private double[] distribution;

	public Shingle(String lexemes, String classifications, String arffData) {
		this.lexemes = lexemes;
		this.arffData = arffData;
		this.classifications = classifications;
		containsName = "";
		distribution = new double[2];
	}

	public String getLexemes() {
		return lexemes;
	}

	public void setLexemes(String lexemes) {
		this.lexemes = lexemes;
	}

	public String getClassifications() {
		return classifications;
	}

	public void setClassifications(String classifications) {
		this.classifications = classifications;
	}

	public String getArffData() {
		return arffData;
	}

	public void setArffData(String arffData) {
		this.arffData = arffData;
	}

	public String getContainsName() {
		return containsName;
	}

	public void setContainsName(String containsName) {
		this.containsName = containsName;
	}

	public double[] getDistribution() {
		return distribution;
	}

	public void setDistribution(double[] distribution) {
		this.distribution = distribution;
	}

	public void printShingle() {
		System.out.println("         Lexemes:   " + lexemes);
		System.out.println(" Classifications:   " + classifications);
		System.out.println("   Contains Name:   " + containsName);
		System.out.println("             Yes:   " + Math.round(distribution[0] * 100.00) + "%");
		System.out.println("              No:   " + Math.round(distribution[1] * 100.00) + "%\n");
	}
}