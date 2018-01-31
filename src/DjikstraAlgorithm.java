import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class DjikstraAlgorithm<D>
{
	// Instance Variables
	// Graph to work with
	private Graph<D>					graph;
	private Vertex<D>					sourceVertex;
	private Vertex<D>					destinationVertex;

	// Set S
	private LinkedHashSet<Vertex<D>>	processed		= new LinkedHashSet<Vertex<D>>();
	// Set V-S
	private LinkedHashSet<Vertex<D>>	toBeProcessed	= new LinkedHashSet<Vertex<D>>();

	// Parallel Arrays to provided Graph
	// d[v]
	private ArrayList<Double>			shortestPath	= new ArrayList<Double>();
	// p[v]
	private ArrayList<Vertex<D>>		previousVertex	= new ArrayList<Vertex<D>>();

	// Relates each Vertex to an Integer in the Hash Map
	LinkedHashMap<Vertex<D>, Integer>	map				= new LinkedHashMap<Vertex<D>, Integer>();

	// Array List Representation of Shortest Path to Destination
	private ArrayList<Vertex<D>>		djikstrasPath;

	// String Representing Shortest path
	private String						djikstrasPathString;

	// Djikstra's Constructor
	public DjikstraAlgorithm(Graph<D> graph, Vertex<D> source, Vertex<D> destination)
	{
		// Sets the Graph
		setGraph(graph);
		setSourceVertex(source);
		setDestinationVertex(destination);

		// Defaults to helpful Message
		this.djikstrasPathString = "No Path Calculated.";

		// Checks Graph
		boolean proceed = tryDjikstra();

		if (proceed)
		{
			loopAlgorithm();
			getShortestPath();
			setShortestPathString();
		}
		else
		{
			System.out.println("Graph had one or less vertices.");
		}
	}

	// Accessors
	// Gets the Graph
	public Graph<D> getGraph()
	{
		return this.graph;
	}

	// Gets the Djikstra's Path List
	public ArrayList<Vertex<D>> getDjikstrasPath()
	{
		return this.djikstrasPath;
	}

	// Gets the String Representation of the Path
	public String getDjikstrasPathString()
	{
		return this.djikstrasPathString;
	}

	// Mutators
	// Sets the Graph
	public void setGraph(Graph<D> graph)
	{
		this.graph = graph;
	}

	// Sets the Source Vertex
	public void setSourceVertex(Vertex<D> source)
	{
		this.sourceVertex = source;
	}

	// Sets the Destination Vertex
	public void setDestinationVertex(Vertex<D> destination)
	{
		this.destinationVertex = destination;
	}

	// Sets the Shortest Path List
	public void setShortestPath(ArrayList<Vertex<D>> shortestPath)
	{
		this.djikstrasPath = shortestPath;
	}

	// Sets the Shortest Path String
	public void setShortestPathString()
	{
		// Erases Default Text
		this.djikstrasPathString = "";

		// Stores Path List Length
		int listLength = getDjikstrasPath().size() - 1;

		// Formats the String nicely
		this.djikstrasPathString += "Source = ";

		// Excludes the last element momentarily
		int i;
		for (i = 0; i < listLength; i++)
			this.djikstrasPathString += getDjikstrasPath().get(i) + " -> ";

		this.djikstrasPathString += getDjikstrasPath().get(i) + " = Destination";
	}

	// Utility Methods
	// Checks whether Graph is worth finding the Path for
	// Returns True if successfully initialized, else returns False.
	public boolean tryDjikstra()
	{
		// If the Graph doesn't have any vertices to run algorithm on
		if (getGraph().getVertices().size() < 2)
			return false;

		// Else initialize Djikstra's Algorithm
		else
		{
			initDjikstra();
			return true;
		}
	}

	// Initializes Djikstra's Algorithm
	public void initDjikstra()
	{
		// Sets the Unprocessed Set V-S
		toBeProcessed.addAll(graph.getVertices());

		// Builds the Map incrementally
		int index = 0;
		// Gives matches each vertex to an Integer Key
		for (Vertex<D> vert : toBeProcessed)
			map.put(vert, index++);

		// Sets all distances to Infinity
		for (int i = 0; i < index; i++)
			shortestPath.add(i, Double.POSITIVE_INFINITY);

		// Shortest Path from Starting Point to itself is 0
		shortestPath.set(0, 0.0);

		// Sets all Previous Indexes to be the Starting Point
		for (int i = 0; i < index; i++)
			previousVertex.add(sourceVertex);

		// Default Previous to Source doesn't exist
		previousVertex.set(0, null);
	}

	// Performs Core Loop of the Algorithm
	public void loopAlgorithm()
	{
		// While there exists vertices to process
		while (!toBeProcessed.isEmpty())
		{
			// Defaults next Vertex to null for Break Condition
			Vertex<D> processVertex = null;

			// Sets the Cap to compare against as Infinity
			double cap = Double.POSITIVE_INFINITY;

			// Finds the smallest Vertex to process in d[v]
			for (Vertex<D> v : toBeProcessed)
			{
				double weight = shortestPath.get(map.get(v));

				// Replaces Process Vertex and Cap until smallest is found
				if (weight < cap)
				{
					processVertex = v;
					cap = weight;
				}
			}

			// If processVertex is null then V-S is empty so Djikstra's done
			if (processVertex == null)
				break;

			// Process the Vertex's edges
			for (Edge<D> adjEdge : processVertex.getAdjacencies())
			{
				// Stores this Edge's Information
				Vertex<D> edgeSource = adjEdge.getSource();
				Vertex<D> edgeDest = adjEdge.getDest();

				// Gets the index of the destination from the Map
				int destinationIndex = map.get(edgeDest);
				int sourceIndex = map.get(edgeSource);

				// Only Processes if the Destination is unvisited
				if (toBeProcessed.contains(edgeDest))
				{
					// New Path Distance
					double newDistance = shortestPath.get(sourceIndex) + adjEdge.getWeight();

					// Old Path Distance
					double oldDistance = shortestPath.get(destinationIndex);

					// Compares current distances
					if (newDistance < oldDistance)
					{
						// Changes to the new Shorter Distance
						shortestPath.set(destinationIndex, newDistance);

						// Changes to the new closer Predecessor
						previousVertex.add(destinationIndex, edgeSource);
					}
				}
			}

			// Acknowledges Completion of Processing on Index
			toBeProcessed.remove(processVertex);
			processed.remove(processVertex);
		}
	}

	// Gives List of Shortest Path
	public ArrayList<Vertex<D>> getShortestPath()
	{
		// Formats the Path from Source to Destination
		ArrayList<Vertex<D>> shortestPathToDest = new ArrayList<Vertex<D>>();

		// Adds the final Vertex to Path
		shortestPathToDest.add(destinationVertex);

		// Gets the Position of the the Destination Vertex
		int position = map.get(destinationVertex);

		// Iterates through the Integer Wrapper Map until it hits the Source Vertex
		while (position != 0)
		{
			// Stores the Previous
			Vertex<D> previousPath = previousVertex.get(position);

			// Adds each Previous Vertex up until the Source
			shortestPathToDest.add(previousPath);

			// Essentially Decrements the position
			position = map.get(previousPath);
		}

		// The Path is currently flipped, Reverses it
		Collections.reverse(shortestPathToDest);

		// Sets the Instance Variable
		this.setShortestPath(shortestPathToDest);

		// Returns the shortest Path as an inorder List
		return shortestPathToDest;
	}

	// Utility Method
	// To String
	public String toString()
	{
		return this.getDjikstrasPathString() + "\nDistance: "
				+ shortestPath.get(map.get(destinationVertex));
	}
}
