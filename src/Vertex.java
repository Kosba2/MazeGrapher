import java.util.ArrayList;

public class Vertex<D>
{
	// Instance Variables
	private Point				point;
	private D					data;
	private ArrayList<Edge<D>>	adjacencies;

	// Constructors
	// Full Constructor
	public Vertex(Point point, ArrayList<Edge<D>> adjacencies, D data)
	{
		setPoint(point);
		setAdjacencies(adjacencies);
		setData(data);
	}

	// Partial Constructor
	public Vertex(Point point, D data)
	{
		setPoint(point);
		setAdjacencies(new ArrayList<Edge<D>>());
		setData(data);
	}

	// Partial Constructor
	public Vertex(Point point)
	{
		setPoint(point);
		setAdjacencies(new ArrayList<Edge<D>>());
		setData(null);
	}

	// Adds new adjacencies
	public void addAdjacency(Edge<D> edge)
	{
		adjacencies.add(edge);
	}

	// Accessors
	// Gets the Point
	public Point getPoint()
	{
		return point;
	}

	// Gets the Adjacency List
	public ArrayList<Edge<D>> getAdjacencies()
	{
		return adjacencies;
	}

	// Gets the Data
	public D getData()
	{
		return this.data;
	}

	// Sets the Point
	public void setPoint(Point point)
	{
		this.point = point;
	}

	// Sets for the Adjacencies
	public void setAdjacencies(ArrayList<Edge<D>> adjacencies)
	{
		this.adjacencies = adjacencies;
	}

	// Sets the Data
	public void setData(D data)
	{
		this.data = data;
	}

	// Utility Methods
	// Equals Method
	@SuppressWarnings("unchecked")
	public boolean equals(Object other)
	{
		// Temporary Storage
		Vertex<D> otherVertex;

		// Checks Object isn't null and verifies it's a Vertex
		if (other != null && (other instanceof Vertex))
			otherVertex = (Vertex<D>) other;

		// If it's null or not a Vertex then return false by default
		else
			return false;

		// Returns a comparison of their Points
		return (this.getPoint().equals(otherVertex.getPoint()));
	}

	// HashCode Method
	public int hashCode()
	{
		return this.point.hashCode();
	}

	// ToString Method
	public String toString()
	{
		return "V" + this.getPoint();
	}
}
