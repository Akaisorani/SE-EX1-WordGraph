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
		testGraph=wordgraph.MyFile.createDirectedGraph("./testcase/testText.txt");
	}

	@After
	public void tearDown() throws Exception {
		testGraph=null;
	}

	@Test
	public void testCalcShortestPath() {
		List result=wordgraph.ShortestPath.calcShortestPath(testGraph, "the", "of");
		System.out.println(result.toString());
		assertEquals("[the->content->of, the->heat->of, the->sons->of, the->state->of, the->table->of]",result.toString());
	}

	@Test
	public void testCalcPathDistance() {
		int dis=wordgraph.ShortestPath.calcPathDistance(testGraph, "the", "of");
		assertEquals(dis,2);
	}

}
