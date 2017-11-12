package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestShortest1 {
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
		List result=wordgraph.ShortestPath.calcShortestPath(testGraph, "to", "explore");
		System.out.println(result.toString());
		assertEquals("[to->explore]",result.toString());
	}

}
