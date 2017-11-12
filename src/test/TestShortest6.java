package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestShortest6 {
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
		String ans = "";
		for(wordgraph.Node node : testGraph.getNodeList()) {
			List result=wordgraph.ShortestPath.calcShortestPath(testGraph, "to", node.name);
			ans=ans+result+"\n";
			
		}
		System.out.println(ans.toString());
		
		assertEquals("[to->explore->strange->new, to->seek->out->new]\n" + 
				"[to->explore->strange->new->worlds, to->seek->out->new->worlds]\n" + 
				"[to->explore]\n" + 
				"[to->explore->strange->new->life->and, to->seek->out->new->life->and]\n" + 
				"[to]\n" + 
				"[to->explore->strange->new->civilizations, to->seek->out->new->civilizations]\n" + 
				"[to->seek]\n" + 
				"[to->explore->strange]\n" + 
				"[to->explore->strange->new->life, to->seek->out->new->life]\n" + 
				"[to->seek->out]\n",ans.toString());
	}

}
