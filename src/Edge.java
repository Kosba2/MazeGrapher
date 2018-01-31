public class Edge<D>
{
	// Instance Variables
	private Vertex<D>	source;
	private Vertex<D>	dest;
	private double		weight;

	// Constructor for unweighted Edge
	public Edge(Vertex<D> source, Vertex<D> dest)
	{
		setSource(source);
		setDest(dest);
		setWeight(1.0);
	}

	// Constructor for weighted Edge
	public Edge(Vertex<D> source, Vertex<D> dest, double weight)
	{
		setSource(source);
		setDest(dest);
		setWeight(weight);
	}

	// Constructor for reverse Edge
	public Edge(Edge<D> toReverse)
	{
		setSource(toReverse.getDest());
		setDest(toReverse.getSource());
		setWeight(toReverse.getWeight());
	}

	// Accessors
	// Gets the Source
	public Vertex<D> getSource()
	{
		return this.source;
	}

	// Gets the Destination
	public Vertex<D> getDest()
	{
		return this.dest;
	}

	// Gets the Weight
	public double getWeight()
	{
		return this.weight;
	}

	// Mutators
	// Sets the Source
	public void setSource(Vertex<D> source)
	{
		this.source = source;
	}

	// Sets the Destination
	public void setDest(Vertex<D> dest)
	{
		this.dest = dest;
	}

	// Sets the Weight
	public void setWeight(double weight)
	{
		this.weight = weight;
	}

	// Utility Methods
	// Equals Method
	@SuppressWarnings("unchecked")
	public boolean equals(Object other)
	{
		// Temporary Storage
		Edge<D> otherEdge;

		// Checks Object isn't null and verifies it's an Edge
		if (other != null && (other instanceof Edge))
			otherEdge = (Edge<D>) other;

		// If it's null or not a Edge then return false by default
		else
			return false;

		// Returns a comparison of the Destinations, Sources and Weight
		return (this.getDest() == otherEdge.getDest() && this.getSource() == otherEdge.getSource()
				&& this.getWeight() == otherEdge.getWeight());
	}

	// HashCode Method
	public int hashCode()
	{
		return (this.getSource().hashCode() + this.getDest().hashCode());
	}

	// ToString Method
	public String toString()
	{
		return "From " + this.getSource().toString() + " to " + this.getDest().toString()
				+ " w/ weight " + this.getWeight();
	}
}
