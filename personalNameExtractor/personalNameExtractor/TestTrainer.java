package personalNameExtractor;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;
public class TestTrainer {

	@Test
	public void testTokenize() {
		ArrayList<String> tokenizedOutput = new ArrayList();
		StringTokenizer st = new StringTokenizer("\"Oh, no,\" she\'s saying, \"our $400 blender can\'t handle something this hard!\"",",.:;\'\"/?[]{}\\|=-!@#$%^&*()_+ ");
		
		while(st.hasMoreElements())
		{
			tokenizedOutput.add(st.nextToken());
		}
		
		Trainer trainer = new Trainer();
		assertEquals(tokenizedOutput, trainer.tokenize("<NER>\"Oh, no,\" she\'s saying, \"our $400 blender can\'t handle something this hard!\"</NER>"));
		
		fail("Not yet implemented");
	}

	@Test
	public void testParse() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTokenCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testTrainer() {
		fail("Not yet implemented");
	}

}
