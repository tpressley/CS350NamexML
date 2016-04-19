
package edu.odu.cs.cs350.namex;

import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

/**
 * Test class for Shingle.java
 * 
 * @author Caroline Chey
 *
 */
public class TestShingle {

	@Test
	/**
	 * test for default constructor
	 */
	public void testShingle() {
		/*
		 * k = 5; 5 * 2 + 1
		 */
		Shingle sh = new Shingle();
		assertTrue(sh != null);
		Shingle sh2 = new Shingle();
		assertTrue(sh2 != null);
		Shingle sh3 = new Shingle();
		assertTrue(sh3 != null);
		Shingle sh4 = new Shingle();
		assertTrue(sh4 != null);
		Shingle sh5 = new Shingle();
		assertTrue(sh5 != null);

		assertEquals(11, sh.getTokens().size());
		assertEquals(11, sh2.getTokens().size());
		assertEquals(11, sh3.getTokens().size());
		assertEquals(11, sh4.getTokens().size());
		assertEquals(11, sh5.getTokens().size());

	}

	@Test
	/**
	 * test for public Shingle(Shingle shingle)
	 */
	public void testShingleCopy() {
		Shingle orig = new Shingle();
		Shingle copy = new Shingle(orig);
		Shingle copycat = new Shingle(copy);

		assertTrue(copy.getTokens() != null);
		assertTrue(copy.getTokens().getFirst() != null);
		assertTrue(copy.getTokens().getLast() != null);
		assertEquals(orig.getTokens(), copy.getTokens());
		assertEquals(orig.getTokens().size(), copy.getTokens().size());
		assertTrue(copycat.getTokens() != null);
		assertTrue(copycat.getTokens().getFirst() != null);
		assertTrue(copycat.getTokens().getLast() != null);
		assertEquals(orig.getTokens(), copycat.getTokens());
		assertEquals(copycat.getTokens(), copy.getTokens());
		assertEquals(orig.getTokens().size(), copycat.getTokens().size());
		assertEquals(copycat.getTokens().size(), copy.getTokens().size());
	}

	@Test
	/**
	 * test for public LinkedList<Token> getTokens()
	 */
	public void testGetTokens() {
		/*
		 * private LinkedList<Token> tokens;
		 */

		Shingle sh1 = new Shingle();
		assertTrue(sh1.getTokens() != null);
		assertFalse(sh1.getTokens().isEmpty());
		assertTrue(sh1.getTokens().size() != 0);
		assertTrue(sh1.getTokens().size() > 0);
		assertEquals(11, sh1.getTokens().size());
		assertTrue(sh1.getTokens().getFirst() != null);
		assertTrue(sh1.getTokens().getLast() != null);
	}

	@Test
	/**
	 * test for public void setTokens(LinkedList<Token> tokens)
	 */
	public void testSetTokens() {

		Shingle sh1 = new Shingle();
		LinkedList<Token> toks1 = new LinkedList<Token>();
		Token tk = new Token("test");
		for (int i = 0; i < (5 * 2 + 1); i++) {
			toks1.add(tk);
		}

		sh1.setTokens(toks1);

		assertFalse(sh1.getTokens().isEmpty());
		assertTrue(sh1.getTokens().size() != 0);
		assertTrue(sh1.getTokens().size() > 0);
		assertEquals(11, sh1.getTokens().size());
		assertTrue(sh1.getTokens().getFirst() != null);
		assertTrue(sh1.getTokens().getLast() != null);

		assertEquals(toks1.getFirst(), sh1.getTokens().getFirst());
		assertEquals(toks1.getLast(), sh1.getTokens().getLast());
		assertEquals(toks1.size(), sh1.getTokens().size());
		assertEquals(toks1.getClass(), sh1.getTokens().getClass());
	}

	@Test
	/**
	 * test for public Object clone() throws CloneNotSupportedException
	 */
	public void testClone() throws Exception {
		try {
			Shingle sh1 = new Shingle();
			Shingle copy = new Shingle(sh1);
			Shingle clo = (Shingle) sh1.clone();

			assertTrue(sh1 != null);
			assertTrue(copy != null);
			assertTrue(clo != null);
			assertTrue(sh1.getTokens().size() != 0);
			assertTrue(sh1.getTokens().size() > 0);
			assertEquals(11, sh1.getTokens().size());
			assertTrue(copy.getTokens().size() != 0);
			assertTrue(copy.getTokens().size() > 0);
			assertEquals(11, copy.getTokens().size());
			assertTrue(clo.getTokens().size() != 0);
			assertTrue(clo.getTokens().size() > 0);
			assertEquals(11, clo.getTokens().size());

			assertEquals(sh1.getTokens().getFirst(), clo.getTokens().getFirst());
			assertEquals(sh1.getTokens().getFirst(), copy.getTokens().getFirst());
			assertEquals(copy.getTokens().getFirst(), clo.getTokens().getFirst());
			assertEquals(sh1.getTokens().getLast(), clo.getTokens().getLast());
			assertEquals(sh1.getTokens().getLast(), copy.getTokens().getLast());
			assertEquals(copy.getTokens().getLast(), clo.getTokens().getLast());

			assertEquals(sh1.getTokens().size(), copy.getTokens().size());
			assertEquals(sh1.getTokens().size(), clo.getTokens().size());
			assertEquals(clo.getTokens().size(), copy.getTokens().size());

			assertEquals(sh1.getTokens().get(1) , clo.getTokens().get(1));
			
			assertEquals(sh1.getTokens().getClass(), copy.getTokens().getClass());
			assertEquals(clo.getTokens().getClass(), copy.getTokens().getClass());
			assertEquals(sh1.getTokens().getClass(), clo.getTokens().getClass());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}