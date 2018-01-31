/**
 * Maze.java - Holds a square matrix of integers that represents a maze
 * 			 - 0 == empty, 1 == wall, 2 == beginning point, and 3 == end point
 */

import java.util.LinkedHashSet;

public class Maze<D>
{
	// Constants
	public static final int	DEFAULT_SIZE	= 10;

	// Instance Variables
	// Maze Data
	private int[][]			maze;
	private int				cols;
	private int				rows;
	private Point			beginning;
	private Point			end;

	// Graph Related
	private Graph<D>		mazeGraph;
	private Vertex<D>		start;
	private Vertex<D>		finish;

	/**
	 * - default constructor that creates a 10x10 maze - places 1's (representing walls) at the
	 * perimeter of the square matrix - sets the beginning and end to the upper left and bottom
	 * right spots on the maze (within the walls)
	 */
	public Maze()
	{
		rows = DEFAULT_SIZE;
		cols = DEFAULT_SIZE;
		// Creates 2D Array of Default Size
		this.maze = new int[DEFAULT_SIZE][DEFAULT_SIZE];

		// Traverses every cell in Maze
		for (int row = 0; row < DEFAULT_SIZE; row++)
			for (int col = 0; col < DEFAULT_SIZE; col++)
			{
				// Check if we are on the perimeter of the maze
				if (row == 0 || col == 0 || row == (DEFAULT_SIZE - 1) || col == (DEFAULT_SIZE - 1))
					maze[row][col] = 1;

				// Interior is Empty
				else
					maze[row][col] = 0;
			}

		// 2 represents the beginning point of the maze
		maze[1][1] = 2;
		this.beginning = new Point(1, 1);

		// 3 represents the ending point of the maze
		maze[DEFAULT_SIZE - 2][DEFAULT_SIZE - 2] = 3;
		this.end = new Point(DEFAULT_SIZE - 2, DEFAULT_SIZE - 2);
	}

	/**
	 * - constructor for Maze that takes the size of the maze as input - places 1's (representing
	 * walls) at the perimeter of the square matrix - sets the beginning and end to the upper left
	 * and bottom right spots on the maze (within the walls)
	 * 
	 * @param size
	 *            The size of the maze
	 */
	public Maze(int size)
	{
		rows = size;
		cols = size;
		this.maze = new int[size][size];

		for (int row = 0; row < size; row++)
		{
			for (int col = 0; col < size; col++)
			{
				// check if we are on the perimeter of the maze
				if (row == 0 || col == 0 || row == (size - 1) || col == (size - 1))
				{
					// the perimeter is set to 1, which represents a wall
					maze[row][col] = 1;
				}
				else
				{
					// everything other than the perimeter starts as empty
					maze[row][col] = 0;
				}
			}
		}

		// 2 represents the beginning point of the maze
		maze[1][1] = 2;
		this.beginning = new Point(1, 1);

		// 3 represents the ending point of the maze
		maze[size - 2][size - 2] = 3;
		this.end = new Point(size - 2, size - 2);
	}

	/**
	 * - constructor for Maze that takes the size of the maze as input - places 1's (representing
	 * walls) at the perimeter of the square matrix - sets the beginning and end to the given points
	 * 
	 * @param size
	 * @param beginning
	 * @param end
	 */
	public Maze(int size, Point beginning, Point end)
	{
		this.maze = new int[size][size];
		rows = size;
		cols = size;

		for (int row = 0; row < size; row++)
		{
			for (int col = 0; col < size; col++)
			{
				// check if we are on the perimeter of the maze
				if (row == 0 || col == 0 || row == (size - 1) || col == (size - 1))
				{
					// the perimeter is set to 1, which represents a wall
					maze[row][col] = 1;
				}
				else
				{
					// everything other than the perimeter starts as empty
					maze[row][col] = 0;
				}
			}
		}

		// 2 represents the beginning point of the maze
		maze[beginning.getY()][beginning.getX()] = 2;
		this.beginning = beginning;

		// 3 represents the ending point of the maze
		maze[end.getY()][end.getX()] = 3;
		this.end = end;
	}

	// Takes in a pre-made Maze
	public Maze(int[][] maze, Point beginning, Point end)
	{
		rows = maze.length;
		cols = maze[0].length;
		setMaze(maze);
		setBeginning(beginning);
		setEnd(end);
	}

	/**
	 * Creates a rectangular wall in the maze with the start and end Points as the top left and
	 * bottom right points of the rectangle
	 * 
	 * @precondition: Start
	 * @param start
	 *            The top left of the rectangular wall being added
	 * @param end
	 *            The bottom right of the rectangular wall being added
	 */
	public void addWall(Point start, Point end)
	{
		try
		{
			// If wall is 1x1
			if (start.equals(end))
				maze[start.getY()][start.getX()] = 1;

			// If wall is > 1x1
			else
				// Makes a Rectangular wall across multiple rows/columns
				for (int row = start.getY(); row <= end.getY(); row++)
				for (int col = start.getX(); col <= end.getX(); col++)
				maze[row][col] = 1;
		}
		catch (IndexOutOfBoundsException e)
		{
			System.out.println("\nWALL INSERTION EXCEEDED BOUNDS... STOPPING\n");
		}
	}

	/**
	 * Creates a rectangular wall in the maze with the start and end Points as the top left and
	 * bottom right points of the rectangle
	 * 
	 * @param start
	 *            The top left of the rectangular wall being added
	 * @param end
	 *            The bottom right of the rectangular wall being added
	 */
	public void removeWall(Point start, Point end)
	{
		try
		{
			// If wall is 1x1
			if (start.equals(end))
				maze[start.getY()][start.getX()] = 0;

			// If wall is > 1x1
			else
				// Removes a Rectangular wall across multiple rows/columns
				for (int row = start.getY(); row <= end.getY(); row++)
				for (int col = start.getX(); col <= end.getX(); col++)
				maze[row][col] = 0;
		}
		catch (IndexOutOfBoundsException e)
		{
			System.out.println("\nWALL REMOVAL EXCEEDED BOUNDS... STOPPING\n");
		}
	}

	// Accessors
	/**
	 * getter method for beginning
	 * 
	 * @return The Point that represents the beginning of the maze
	 */
	public Point getBeginning()
	{
		return beginning;
	}

	/**
	 * getter method for end
	 * 
	 * @return The Point that represents the end of the maze
	 */
	public Point getEnd()
	{
		return end;
	}

	// Get Graph
	public Graph<D> getGraph()
	{
		return this.mazeGraph;
	}

	// Get Start
	public Vertex<D> getStart()
	{
		return this.start;
	}

	// Get Finish
	public Vertex<D> getFinish()
	{
		return this.finish;
	}

	// Mutators
	/**
	 * setter method for beginning
	 * 
	 * @param beginning
	 *            The new Point to set beginning to
	 */
	public void setBeginning(Point beginning)
	{
		this.maze[beginning.getY()][beginning.getX()] = 2;
		this.beginning = beginning;
	}

	/**
	 * setter method for end
	 * 
	 * @param end
	 *            The new Point to set end to
	 */
	public void setEnd(Point end)
	{
		this.maze[end.getY()][end.getX()] = 3;
		this.end = end;
	}

	// Set Maze
	public void setMaze(int[][] inputMaze)
	{
		this.maze = inputMaze;
	}

	// Set Graph
	public void setGraph(Graph<D> graph)
	{
		this.mazeGraph = graph;
	}

	// Sets Start
	public void setStart(Vertex<D> start)
	{
		this.start = start;
	}

	// Sets Finish
	public void setFinish(Vertex<D> finish)
	{
		this.finish = finish;
	}

	// Graph Methods
	// Generates a Graph for the Maze
	public void generateGraph()
	{
		// Generates Vertices
		LinkedHashSet<Vertex<D>> vertices = getVertices();
		LinkedHashSet<Edge<D>> edges = null;

		// Generates Start and End Vertex
		Vertex<D> start = new Vertex<D>(beginning);
		Vertex<D> finish = new Vertex<D>(end);

		// Updates Instance Variables
		setStart(start);
		setFinish(finish);

		// Adds to start of Vertice List
		vertices.add(start);

		// Adds to end of Vertice List
		vertices.add(finish);

		// For each Vertice
		for (Vertex<D> v : vertices)
		{
			// Get the Edges for each Vertice
			edges = getEdges(v.getPoint().getY(), v.getPoint().getX());

			// Add each Edge as an Adjacency for the Vertices
			for (Edge<D> e : edges)
				v.addAdjacency(e);
		}

		for (Vertex<D> v : vertices)
			edges.addAll(v.getAdjacencies());

		// Creates a Graph of the Maze
		this.mazeGraph = new Graph<D>(vertices, edges);
	}

	// Gets the Verticies of the Maze
	public LinkedHashSet<Vertex<D>> getVertices()
	{
		// Creates a Linked Hash Set to store Vertices
		LinkedHashSet<Vertex<D>> vertices = new LinkedHashSet<Vertex<D>>();

		// Traverses the entire 2D Array and Determines whether each point is a Vertex
		for (int row = 0; row < rows; row++)
			for (int col = 0; col < cols; col++)
				if (isVertex(row, col))
					vertices.add(new Vertex<D>(new Point(row, col)));

		// Returns the list of Vertices
		return vertices;
	}

	public LinkedHashSet<Edge<D>> getEdges(int row, int col)
	{
		// Creates a Source Vertex at a point
		Vertex<D> source = new Vertex<D>(new Point(row, col));

		// Creates a list of Edges to check adjacent points
		LinkedHashSet<Edge<D>> edges = new LinkedHashSet<Edge<D>>();

		// Adjacencies are in the order of Up, Left, Down and Right
		int[] adjacencies = adjacencies(row, col);

		// Each one continues until it finds another Vertice

		// Path Upward
		if (adjacencies[0] == 0)
			for (int i = row - 1; i >= 0; i--)
				if (isVertex(i, col))
				{
					Vertex<D> dest = new Vertex<D>(new Point(i, col));
					edges.add(new Edge<D>(source, dest, row - i));
					break;
				}

		// Path Right
		if (adjacencies[1] == 0)
			for (int i = col + 1; i < cols; i++)
				if (isVertex(row, i))
				{
					Vertex<D> dest = new Vertex<D>(new Point(row, i));
					edges.add(new Edge<D>(source, dest, i - col));
					break;
				}

		// Path Downward
		if (adjacencies[2] == 0)
			for (int i = row + 1; i < rows; i++)
				if (isVertex(i, col))
				{
					Vertex<D> dest = new Vertex<D>(new Point(i, col));
					edges.add(new Edge<D>(source, dest, i - row));
					break;
				}

		// Path Left
		if (adjacencies[3] == 0)
			for (int i = col - 1; i >= 0; i--)
				if (isVertex(row, i))
				{
					Vertex<D> dest = new Vertex<D>(new Point(row, i));
					edges.add(new Edge<D>(source, dest, col - i));
					break;
				}

		// Returns the list of Edges
		return edges;
	}

	// Checks Adjacencies
	public int[] adjacencies(int row, int col)
	{
		// Possible Directions
		int up = 0;
		int right = 0;
		int down = 0;
		int left = 0;

		// Upward
		if (row - 1 < 0 || maze[row - 1][col] == 1)
			up = 1;

		// Right
		if (col + 1 >= cols || maze[row][col + 1] == 1)
			right = 1;

		// Downward
		if (row + 1 >= rows || maze[row + 1][col] == 1)
			down = 1;

		// Left
		if (col - 1 < 0 || maze[row][col - 1] == 1)
			left = 1;

		// Returns in the format of Up, Right, Down then Left specifically
		return new int[]{up, right, down, left};
	}

	// Determines whether a Point is a Vertex
	public boolean isVertex(int row, int col)
	{
		// If it is a wall then it's not a vertex
		if (maze[row][col] == 1)
			return false;

		// The Node Above, Right, Below and Left
		int[] adjacencies = adjacencies(row, col);

		// Done for readability
		int up = adjacencies[0];
		int right = adjacencies[1];
		int down = adjacencies[2];
		int left = adjacencies[3];

		// Determines whether it is a corner or a split
		if ((up != down || right != left) || (up + right + down + left != 2))
			return true;

		// If it is neither it's not a vertex
		return false;
	}

	// Utility Methods
	/**
	 * gets the size of the array
	 * 
	 * @return The row length(should be equal to the column length) of the maze
	 */
	public int size()
	{
		return maze.length;
	}

	/**
	 * toString method
	 * 
	 * returns a String that contains all rows and columns of the maze lined up row by column
	 */
	public String toString()
	{
		// Result String
		String result = "";

		// Loops Traverses entire maze
		for (int row = 0; row < size(); row++)
		{
			for (int col = 0; col < size(); col++)
				result += maze[row][col] + " ";

			// Adds a new line for formatting
			result += "\n";
		}
		return result;
	}

	/**
	 * checks for equality between two Maze objects
	 * 
	 * @param anotherMaze
	 *            The other Maze that we are checking with
	 * @return A boolean representing whether or not this Maze is equal to anotherMoint
	 */
	@SuppressWarnings("unchecked")
	public boolean equals(Object other)
	{
		// Temporary Storage
		Maze<D> otherMaze;

		// Checks Object isn't null and verifies it's a Maze(-ing, badum-tsh)
		if (other != null && (other instanceof Maze))
			otherMaze = (Maze<D>) other;

		// If it's null or not a Maze then return false by default
		else
			return false;

		// checks the String versions of each maze, since that goes through every spot in the maze
		return toString().equals(otherMaze.toString());
	}
}
