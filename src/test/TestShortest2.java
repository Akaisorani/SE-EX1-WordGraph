package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestShortest2 {
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
		//2word,dis1,path1
		List result=wordgraph.ShortestPath.calcShortestPath(testGraph, "civilizations", "to");
		System.out.println(result.toString());
		assertEquals("[]",result.toString());
	}
	
	@Test
	public void testPathDistance() {
		int result=wordgraph.ShortestPath.calcPathDistance(testGraph, "civilizations", "to");
		System.out.println(result);
		assertEquals(-1,result);
	}

}
