package wordgraph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

/**
 * Directed Colored Graph.
 * 
 * @author HanYue
 *
 */
public class Graph {
  /**
   * @see Node
   */
  public Graph() {}

  private Map<String, Node> nodes = new HashMap<>();

  /**
   * Add a node to the graph with the node name.
   * <p>
   * Initial color will be set to "none", node name is same as name.
   * 
   * @param name String
   */
  public void addNode(String name) {
    nodes.put(name, new Node(name));
  }

  /**
   * Delete a node.
   * 
   * @param name String
   */
  public void deleteNode(String name) {
    nodes.remove(name);
  }

  /**
   * An auxiliary function which help find the edge index.
   * 
   * @param from String
   * @param to String
   * @return Integer get the index of the edge by from and to
   */
  private Integer findEdgeIndex(String from, String to) {
    Node node = nodes.get(from);
    if (node == null)
      return null;
    for (int i = 0; i < node.edges.size(); i++) {
      if (node.edges.get(i).to.equals(to)) {
        return i;
      }
    }
    return null;
  }

  /**
   * Add an edge with from and to node name with name and weight.
   * <p>
   * from---weight---&gt;to.<br>
   * nodes will be created if it is not existed.<br>
   * if the edge has been in the graph, its weight will increases by 1.
   * 
   * @param from String
   * @param to String
   * @param weight int
   */
  public void addEdge(String from, String to, int weight) {
    if (nodes.get(from) == null)
      nodes.put(from, new Node(from));
    if (nodes.get(to) == null)
      nodes.put(to, new Node(to));
    Integer index = findEdgeIndex(from, to);
    if (index != null)
      nodes.get(from).edges.get(index).weight += weight;
    else
      nodes.get(from).edges.add(new Edge(from, to, weight));
  }

  /**
   * Add an edge with from and to node name, default edge weight is 1.
   * 
   * @param from String
   * @param to String
   */
  public void addEdge(String from, String to) {
    addEdge(from, to, 1);
  }

  /**
   * Delete an existed edge.
   * 
   * @param from String
   * @param to String
   */
  public void deleteEdge(String from, String to) {
    Integer index = findEdgeIndex(from, to);
    if (index != null)
      nodes.get(from).edges.remove((int) index);
  }

  /**
   * Set a node's color.
   * 
   * @param name String
   * @param color String
   */
  public void setNodeColor(String name, String color) {
    Node node = nodes.get(name);
    if (node != null)
      node.color = color;
  }

  /**
   * Set an edge's color.
   * 
   * @param from String
   * @param to String
   * @param color String
   */
  public void setEdgeColor(String from, String to, String color) {
    Integer index = findEdgeIndex(from, to);
    if (index != null)
      nodes.get(from).edges.get(index).color = color;
  }

  /**
   * Get a reference of a node by the name.
   * 
   * @param name String
   * @return Node Ref
   */
  public Node getNode(String name) {
    return nodes.get(name);
  }

  /**
   * Get a reference of an edge by from name to to name.
   * 
   * @param from String
   * @param to String
   * @return Edge Ref
   */
  public Edge getEdge(String from, String to) {
    Integer index = findEdgeIndex(from, to);
    if (index == null)
      return null;
    return nodes.get(from).edges.get(index);
  }

  /**
   * Get a list of reference of all nodes.
   * 
   * @return List&lt;Node&gt;-ArrayList
   */
  public List<Node> getNodeList() {
    List<Node> nodeList = new ArrayList<>();
    for (Node node : nodes.values())
      nodeList.add(node);
    return nodeList;
  }

  /**
   * Clear the Graph.
   */
  public void clear() {
    nodes.clear();
  }

  /**
   * Set all nodes' and edges'color to default.
   * 
   * @see Node#clearColor()
   * @see Edge#clearColor()
   */
  public void clearColor() {
    if (nodes.isEmpty())
      return;
    for (Node node : nodes.values()) {
      node.clearColor();
      for (Edge edge : node.edges) {
        edge.clearColor();
      }
    }
  }

  public String createDirectedGraph(String filename) {
    return "1";
  }

  public String queryBridgeWords(String word1, String word2) {
    return "1";
  }

  public String generateNewText(String text) {
    return "1";
  }

  public String calcShortestPath(String word1, String word2) {
    return "1";
  }

  public String randomWalk() {
    return "1";
  }

  public RandomWalk(Graph G, String name) {
      this.startNode=G.getNode(name);
      this.nowNode=null;
      this.path=new StringBuilder();
      this.stop=false;
      this.eSet=new HashSet<>();
  }

  /**
   * random select a start node.
   * @param G Graph
   */
  public RandomWalk(Graph G) {
      this(G,G.getNodeList().get(randomInt(0,G.getNodeList().size()-1)).name);
  }

  /**
   * Reset the Graph and start with another node.
   * 
   * @param G Graph
   * @param name String
   */
  public void setStartNode(Graph G, String name) {
    clear(G);
    this.startNode = G.getNode(name);
  }

  /**
   * Clear the graph and the path, the start node will be regenerate randomly.
   * 
   * @param G Graph
   */
  public void clear(Graph G) {
    this.startNode = G.getNodeList().get(randomInt(0, G.getNodeList().size() - 1));
    this.nowNode = null;
    this.path = new StringBuilder();
    this.stop = false;
    this.eSet.clear();
  }

  /**
   * generate a random int between l and r. inclusive [l,r]
   * 
   * @param l int
   * @param r int
   * @return int
   */
  private static int randomInt(int l, int r) {
    if (r <= 0 || l > r)
      return 0;
    return new Random().nextInt(((r + 1 + r - l) / (r - l + 1)) * (r - l + 1)) % (r - l + 1) + l;
  }

  /**
   * Move to next node and return the node name.
   * <p>
   * if can't move,return null.
   * 
   * @param G Graph
   * @return String node name now
   */
  private Node getNextNode(Graph G) {
    if (startNode == null || stop)
      return null;
    if (nowNode == null) {
      nowNode = startNode;
      return nowNode;
    }
    if (nowNode.edges.size() <= 0) {
      stop = true;
      return null;
    }
    Edge nextEdge = nowNode.edges.get(randomInt(0, nowNode.edges.size() - 1));
    Node nextNode = G.getNode(nextEdge.to);
    if (eSet.contains(nextEdge.from + "->" + nextEdge.to))
      stop = true;
    else
      eSet.add(nextEdge.from + "->" + nextEdge.to);

    path.append(nextNode.name);
    nowNode = nextNode;

    return nowNode;
  }

  /**
   * Check whether can move to the next node.
   * 
   * @return Boolean
   */
  public boolean hasNext() {
    return !stop && ((startNode != null && nowNode == null)
        || (nowNode != null && nowNode.edges.size() > 0));
  }

  /**
   * Take a move and return the node name.
   * 
   * @param G Graph
   * @return String node name now
   */
  public String randomWalk(Graph G) {
    Node node = getNextNode(G);
    if (node == null)
      return "";
    else
      return node.name;
  }

  /**
   * Get the path that the random walk has passed. start-&gt;B-&gt;C-&gt;now
   * 
   * @return String
   */
  public String getPath() {

  }

  private static void init(Graph G) {
    inQueue.clear();
    dis.clear();
    for (Node node : G.getNodeList()) {
      inQueue.put(node.name, false);
      dis.put(node.name, INF);
    }
  }

  /**
   * calculate the distance from source to all other node by spfa.
   * 
   * @param G Graph
   * @param st source node
   */
  private static void spfa(Graph G, String st) {
    List<String> Q = new ArrayList<>();
    String u;

    init(G);
    Q.add(st);
    Map<String, Node> inQueue;
    inQueue.put(st, true);
    dis.put(st, 0);
    while (!Q.isEmpty()) {
      u = Q.get(0);
      Q.remove(0);
      inQueue.put(u, false);
      for (Edge edge : G.getNode(u).edges) {
        if (dis.get(edge.to) > dis.get(u) + edge.weight) {
          dis.put(edge.to, dis.get(u) + edge.weight);
          if (!inQueue.get(edge.to)) {
            Q.add(edge.to);
            inQueue.put(edge.to, true);
          }
        }
      }
    }
  }

  /**
   * find all path by dfs on the shortest path graph.
   * 
   * @param G Graph
   * @param u now node
   * @param ed target node
   */
  private static void dfs(Graph G, String u, String ed) {
    if (u.equals(ed)) {
      StringBuilder path = new StringBuilder();
      for (String v : tmpPath) {
        path.append(v + "->");
      }
      path.append(ed);
      paths.add(path.toString());
      return;
    }

    for (Edge edge : G.getNode(u).edges) {
      if (dis.get(edge.to) == dis.get(u) + edge.weight) {
        tmpPath.add(u);
        dfs(G, edge.to, ed);
        tmpPath.remove(tmpPath.size() - 1);
      }
    }
  }

  /**
   * calculate all shortest path from node word1 to word2.
   * 
   * @param G Graph
   * @param word1 String
   * @param word2 String
   * @return a list of all shortest path. each path is like this<br>
   *         A-&gt;B-&gt;C
   */
  public static List<String> calcShortestPath(Graph G, String word1, String word2) {
    tmpPath.clear();
    paths.clear();
    if ((word1 != null && G.getNode(word1) == null) || (word2 != null && G.getNode(word2) == null))
      return paths;
    spfa(G, word1);
    dfs(G, word1, word2);
    return paths;
  }

  /**
   * calculate all distance from node word1 to word2 without path.
   * 
   * @param G Graph
   * @param word1 String
   * @param word2 String
   * @return Integer distance
   */
  public static Integer calcPathDistance(Graph G, String word1, String word2) {
    spfa(G, word1);
    if (dis.get(word2).equals(INF))
      return -1;
    else
      return dis.get(word2);
  }

}
