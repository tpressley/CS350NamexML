package edu.odu.cs.cs350.namex;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

/**
 * Test class for TextBlock
 *
 */
public class TestTextBlock {
	/**
	 * test for constructor of TestBlock
	 */
	@Test
	public void testTextBlock() {
		// uninitialized TextBlock
		TextBlock tb1 = new TextBlock();
		// TextBlock composed of a string
		TextBlock tb2 = new TextBlock("Hello World");
		// empty TextBlock
		TextBlock tb3 = new TextBlock("");
		// TextBlock() constructor called with a concatenated string parameter
		TextBlock tb4 = new TextBlock("Hello" + " " + "Java");

		assertEquals("", tb1.getTextBlock());
		assertEquals("Hello World", tb2.getTextBlock());
		assertEquals("", tb3.getTextBlock());
		assertTrue(tb4.equals("Hello Java"));
	}

	/**
	 * test for setTextBlock() and getTextBlock()
	 */
	@Test
	public void testSetTextBlockGetTextBlock() {
		TextBlock tb = new TextBlock();

		tb.setTextBlock("Hello World");

		assertEquals("Hello World", tb.getTextBlock());
	}

	/**
	 * test for getTokens()
	 */
	@Test
	public void testGetTokens() {
		TextBlock tb = new TextBlock();
		Token token1 = new Token("John");
		Token token2 = new Token("Smith");

		tb.addToken(token1);
		tb.addToken(token2);

		ArrayList<Token> tokens = tb.getTokens();

		assertEquals(token1.toString(), tokens.get(0).toString());
		assertEquals(token2.toString(), tokens.get(1).toString());

	}

	/**
	 * test for setTokens()
	 */
	@Test
	public void testSetTokens() {
		TextBlock tb = new TextBlock();

		Token token1 = new Token("John");
		Token token2 = new Token("Smith");

		ArrayList<Token> tokens = new ArrayList<Token>();

		tokens.add(token1);
		tokens.add(token2);

		tb.setTokens(tokens);

		assertEquals(token1.toString(), tokens.get(0).toString());
		assertEquals(token2.toString(), tokens.get(1).toString());
	}

	/**
	 * test for addToken()
	 */
	@Test
	public void testAddToken() {
		TextBlock tb = new TextBlock();

		Token token1 = new Token("John");
		Token token2 = new Token("Smith");

		tb.addToken(token1);
		tb.addToken(token2, 0);

		assertEquals(token2.toString(), tb.getTokens().get(0).toString());
		assertEquals(token1.toString(), tb.getTokens().get(1).toString());
	}

	/**
	 * test for removeToken()
	 */
	@Test
	public void testRemoveToken() {
		TextBlock tb = new TextBlock();

		Token token1 = new Token("John");
		Token token2 = new Token("Smith");
		Token token3 = new Token("Jones");

		tb.addToken(token1);
		tb.addToken(token2);
		tb.addToken(token3);

		tb.removeToken(0);
		tb.removeToken(token2);

		assertEquals(1, tb.getTokens().size());
	}

	/**
	 * test for toString()
	 */
	@Test
	public void testToString() {
		TextBlock tb = new TextBlock();

		tb.addToken(new Token("John"));
		tb.addToken(new Token(" "));
		tb.addToken(new Token("Smith"));
		tb.addToken(new Token(" "));
		tb.addToken(new Token("Jones"));

		assertEquals("John Smith Jones", tb.toString());
	}
}
