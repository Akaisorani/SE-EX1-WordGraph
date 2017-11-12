package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestShortest4 {
	wordgraph.Graph testGraph;

	@Before
	public void setUp() throws Exception {
		testGraph=wordgraph.MyFile.createDirectedGraph("./testcase/testTextShort.txt");
	}

	@After
	public void tearDown() throws Exception {
		testGraph=null;
	}

	@Test
	public void testCalcShortestPath() {
		List result=wordgraph.ShortestPath.calcShortestPath(testGraph, "to", "to");
		System.out.println(result.toString());
		assertEquals("[to]",result.toString());
	}
	
	@Test
	public void testPathDistance() {
		int result=wordgraph.ShortestPath.calcPathDistance(testGraph, "to", "to");
		System.out.println(result);
		assertEquals(0,result);
	}

}
