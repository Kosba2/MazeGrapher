import java.util.LinkedHashSet;

public class testDriver
{
	public static void main(String[] args)
	{
		// Test Run
		/* Tests Graph and Djikstra
		// Creates new Dijkstra
		DjikstraAlgorithm<String> djik;

		// Vertex Data
		Point firstPoint = new Point(1, 1);
		String firstData = "Cheese Pizza";

		Point secondPoint = new Point(2, 2);
		String secondData = "Pepperoni Pizza";

		Point thirdPoint = new Point(3, 3);
		String thirdData = "Pineapple Pizza";

		Point fourthPoint = new Point(4, 4);
		String fourthData = "Olive Pizza";

		// Vertexes
		Vertex<String> firstVertex = new Vertex<String>(firstPoint, firstData);
		Vertex<String> secondVertex = new Vertex<String>(secondPoint, secondData);
		Vertex<String> thirdVertex = new Vertex<String>(thirdPoint, thirdData);
		Vertex<String> fourthVertex = new Vertex<String>(fourthPoint, fourthData);

		// Edges
		// Creates Path 1-2-3
		// 1-2
		Edge<String> oneTwoEdge = new Edge<String>(firstVertex, secondVertex, 5);
		Edge<String> twoOneEdge = new Edge<String>(secondVertex, firstVertex, 5);
		// 2-3
		Edge<String> twoThreeEdge = new Edge<String>(secondVertex, thirdVertex, 10);
		Edge<String> threeTwoEdge = new Edge<String>(thirdVertex, secondVertex, 10);

		// Creates Path 1-4-3
		// 1-4
		Edge<String> oneFourEdge = new Edge<String>(firstVertex, fourthVertex, 2);
		Edge<String> fourOneEdge = new Edge<String>(fourthVertex, firstVertex, 2);

		// 4-3
		Edge<String> fourThreeEdge = new Edge<String>(fourthVertex, thirdVertex, 4);
		Edge<String> threeFourEdge = new Edge<String>(thirdVertex, fourthVertex, 4);

		// Creates Adjacencies
		// Path 1-2-3
		// 1-2
		firstVertex.addAdjacency(oneTwoEdge);
		secondVertex.addAdjacency(twoOneEdge);
		// 2-3
		secondVertex.addAdjacency(twoThreeEdge);
		thirdVertex.addAdjacency(threeTwoEdge);

		// Path 1-4-3
		// 1-4
		firstVertex.addAdjacency(oneFourEdge);
		fourthVertex.addAdjacency(fourOneEdge);
		// 4-3
		fourthVertex.addAdjacency(fourThreeEdge);
		thirdVertex.addAdjacency(threeFourEdge);

		// Graph Data
		// Vertices
		LinkedHashSet<Vertex<String>> vertices = new LinkedHashSet<Vertex<String>>();
		vertices.add(firstVertex);
		vertices.add(secondVertex);
		vertices.add(thirdVertex);
		vertices.add(fourthVertex);

		// Edges
		LinkedHashSet<Edge<String>> edges = new LinkedHashSet<Edge<String>>();
		// Path 1-2-3
		// 1-2
		edges.add(oneTwoEdge);
		edges.add(twoOneEdge);
		// 2-3
		edges.add(twoThreeEdge);
		edges.add(threeTwoEdge);

		// Path 1-4-3
		// 1-4
		edges.add(oneFourEdge);
		edges.add(fourOneEdge);
		// 4-3
		edges.add(fourThreeEdge);
		edges.add(threeFourEdge);

		// Graph
		Graph<String> pizzaGraph = new Graph<String>(vertices, edges);
		System.out.println(pizzaGraph.toString());

		// As is, the graph is Two Vertices, with a Path from First Vertex to Second
		// but no path back from Second Vertex to First
		System.out.println("The Graph is Undirected: " + pizzaGraph.isUndirected());

		// Shortest Path
		djik = new DjikstraAlgorithm<String>(pizzaGraph, firstVertex, thirdVertex);

		// Prints out the Shortest Path
		System.out.println("\nShortest Path: \n" + djik.toString());
		*/
		
		// Creates Maze
		int[][] intMaze = new int[9][9];
		
		// Sets each Row's Columns
		intMaze[0] = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1};
		intMaze[1] = new int[]{1, 2, 0, 0, 0, 0, 0, 1, 1};
		intMaze[2] = new int[]{1, 1, 1, 0, 1, 1, 0, 0, 1};
		intMaze[3] = new int[]{1, 0, 1, 0, 1, 1, 1, 0, 1};
		intMaze[4] = new int[]{1, 0, 0, 0, 1, 1, 1, 0, 1};
		intMaze[5] = new int[]{1, 1, 1, 0, 0, 0, 0, 0, 1};
		intMaze[6] = new int[]{1, 1, 1, 1, 0, 1, 1, 0, 1};
		intMaze[7] = new int[]{1, 1, 1, 1, 0, 0, 0, 3, 1};
		intMaze[8] = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1};
		
		// Chooses Starting and Ending Points
		Point start = new Point(1, 1);
		Point end = new Point(7, 7);
		
		// Creates a Maze Object of the above Maze
		Maze<String> newMaze = new Maze<String>(intMaze, start, end);
		
		// Prints out the Maze
		System.out.println(newMaze.toString());
		
		// Generates a Graph out of the Maze
		newMaze.generateGraph();
		
		// Stores Graph
		Graph<String> mazeGraph = newMaze.getGraph();
		
		// Stores Start & End Vertices
		Vertex<String> startV = newMaze.getStart();
		Vertex<String> endV = newMaze.getFinish();
		
		// Prints the Graph
		System.out.println(mazeGraph.toString());
		
		// Creates a Djikstra Object to Interpret Maze
		DjikstraAlgorithm<String> mazeDjik = new DjikstraAlgorithm<String>(mazeGraph, startV, endV);
		
		// Prints out the Shortest Path
		System.out.println("Shortest Path Through Maze:\n" + mazeDjik.toString());
	}
}
