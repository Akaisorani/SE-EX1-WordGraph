package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCase3 {
	wordgraph.Graph testGraph;
	
	@Before
	public void setUp() throws Exception {
		testGraph=wordgraph.MyFile.createDirectedGraph("./testcase/testText.txt");
	}

	@After
	public void tearDown() throws Exception {
		testGraph=null;
	}

	@Test
	public void testQueryBridgeWords() {
		String result=wordgraph.querry.queryBridgeWords(testGraph,"i","a");
		System.out.println(result);
		assertEquals("have",result);
	}

}
