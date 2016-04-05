package edu.odu.cs.cs350.namex;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestTextBlock 
{
	@Test
	public void testTextBlock()
	{
		TextBlock tb1 = new TextBlock();
		TextBlock tb2 = new TextBlock("Hello World");
		
		assertEquals("", tb1.getTextBlock());
		assertEquals("Hello World", tb2.getTextBlock());
	}
	
	@Test
	public void testGetSetTextBlock() 
	{
		TextBlock tb = new TextBlock();
		
		tb.setTextBlock("Hello World");
		
		assertEquals("Hello World", tb.getTextBlock());
	}
	
	@Test
	public void testGetTokens()
	{
		TextBlock tb = new TextBlock();
		Token token1 = new Token("John");
		Token token2 = new Token("Smith");
		
		tb.addToken(token1);
		tb.addToken(token2);
		
		ArrayList<Token> tokens = tb.getTokens();

		assertEquals(token1.toString(), tokens.get(0).toString());
		assertEquals(token2.toString(), tokens.get(1).toString());
	}
	
	@Test
	public void testSetTokens()
	{
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
	
	@Test
	public void testAddToken()
	{
		TextBlock tb = new TextBlock();
		
		Token token1 = new Token("John");
		Token token2 = new Token("Smith");
		
		tb.addToken(token1);
		tb.addToken(token2, 0);

		assertEquals(token2.toString(), tb.getTokens().get(0).toString());
		assertEquals(token1.toString(), tb.getTokens().get(1).toString());
	}
	
	@Test
	public void testRemoveToken()
	{
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
	
	@Test
	public void testToString()
	{
		TextBlock tb = new TextBlock();
		
		tb.addToken(new Token("John"));
		tb.addToken(new Token(" "));
		tb.addToken(new Token("Smith"));
		tb.addToken(new Token(" "));
		tb.addToken(new Token("Jones"));
		
		assertEquals("John Smith Jones", tb.toString());
	}
}
