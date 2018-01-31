import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Graph<D>
{
	// Instance Variables
	private LinkedHashSet<Vertex<D>>	vertices;
	private LinkedHashSet<Edge<D>>		edges;

	// Constructors
	// Full Constructor
	public Graph(LinkedHashSet<Vertex<D>> vertices, LinkedHashSet<Edge<D>> edges)
	{
		setVertices(vertices);
		setEdges(edges);
	}

	// Partial Constructor
	public Graph(LinkedHashSet<Vertex<D>> vertices)
	{
		setVertices(vertices);
	}

	// Accessors
	// Gets the Vertices
	public LinkedHashSet<Vertex<D>> getVertices()
	{
		return this.vertices;
	}

	// Gets the Edges
	public LinkedHashSet<Edge<D>> getEdges()
	{
		return this.edges;
	}

	// Gets the number of Vertices
	int getNumVertices()
	{
		return vertices.size();
	}

	// Gets a specific Edge if it exists, else returns null
	public Edge<D> getEdge(Vertex<D> source, Vertex<D> dest)
	{
		// Loops through all edges at the source
		for (Edge<D> sourceEdge : source.getAdjacencies())
			// Checks whether the destination contains the same edge in reverse
			if (dest.getAdjacencies().contains(new Edge<D>(dest, source)))
				return sourceEdge;

		// If it does not contain the Edge
		return null;
	}

	// Mutators
	// Sets the Vertices
	public void setVertices(LinkedHashSet<Vertex<D>> vertices)
	{
		this.vertices = vertices;
	}

	// Sets the Edges
	public void setEdges(LinkedHashSet<Edge<D>> edges)
	{
		this.edges = edges;
	}

	// Utility Methods
	// Insertion Method for a new Edge
	public void insert(Edge<D> edge)
	{
		// Variables
		Vertex<D> source = edge.getSource();
		Vertex<D> dest = edge.getDest();

		// If this graph contains the source and destination of the edge
		if (source != null && this.getVertices().contains(source) && dest != null
				&& this.getVertices().contains(dest))
		{
			// Adds Edge to HashSet of Edges
			edges.add(edge);

			// To Source Adds original Edge
			source.addAdjacency(edge);

			// To Destination Adds Inverse Edge
			dest.addAdjacency(new Edge<D>(edge));
		}
	}

	// Determines whether Graph is directed
	public boolean isUndirected()
	{
		// If for each Edge of Each Vertex, there is an Edge back to it
		// then it is not directed
		for (Vertex<D> vertex : vertices)
		{
			// Gets Adjacencies
			ArrayList<Edge<D>> adjacencies = vertex.getAdjacencies();

			// Goes through list of Edges
			for (Edge<D> edge : adjacencies)
			{
				// Gets each Adjacencies' Adjacencies
				ArrayList<Edge<D>> otherAdjacencies = edge.getDest().getAdjacencies();

				// Reverse Edge
				Edge<D> reverseEdge = new Edge<D>(edge);

				// If it doesn't contain an Edge back return false
				if (!otherAdjacencies.contains(reverseEdge))
					return false;
			}
		}

		// If successfully exited that monstrosity, then your runtime sucks
		// Oh and every Vertex had a 2 way connection with each adjacency. But who cares.
		return true;
	}

	// Determines whether Edge exists
	public boolean isEdge(Vertex<D> source, Vertex<D> dest)
	{
		return edges.contains(new Edge<D>(source, dest));
	}

	// Utility Methods
	// To String Method
	public String toString()
	{
		// Variables
		String resultString = "";

		// Iterates and Appends Vertices to Result String
		resultString += "Vertices: \n";
		for (Vertex<D> vert : vertices)
			resultString += vert.toString() + "\n";

		resultString += "\n";

		// Iterates and Appends Edges to Result String
		resultString += "Edges: \n";
		for (Edge<D> edge : edges)
			resultString += edge.toString() + "\n";

		// Returns the General Summary of Above Data
		return resultString;
	}
}
