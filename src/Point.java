/**
 * Point.java - represents a point(x, y) on the maze
 */

public class Point
{
	// Instance Variables
	private int	x;
	private int	y;

	/**
	 * default constructor that sets x and y to 0
	 */
	public Point()
	{
		setX(0);
		setY(0);
	}

	/**
	 * constructor that takes an x and a y value as input
	 * 
	 * @param x
	 * @param y
	 */
	public Point(int x, int y)
	{
		setX(x);
		setY(y);
	}

	/**
	 * gets the x value of this point
	 * 
	 * @return The x value of this point
	 */
	public int getX()
	{
		return x;
	}

	/**
	 * gets the y value of this point
	 * 
	 * @return The y value of this point
	 */
	public int getY()
	{
		return y;
	}

	/**
	 * sets the x value of this point
	 */
	public void setX(int x)
	{
		this.x = x;
	}

	/**
	 * sets the x value of this point
	 */
	public void setY(int y)
	{
		this.y = y;
	}

	/**
	 * toString method
	 * 
	 * returns a String that contains the x and y values of this point formatted as (x, y)
	 */
	public String toString()
	{
		return "(" + x + ", " + y + ")";
	}

	/**
	 * checks for equality between two Point objects
	 * 
	 * @param anotherPoint
	 *            The other Point that we are checking with
	 * @return A boolean representing whether or not this Point is equal to anotherPoint
	 */
	public boolean equals(Object other)
	{
		// Temporary Storage
		Point otherPoint;

		// Checks Object isn't null and verifies it's a Point
		if (other != null && (other instanceof Point))
			otherPoint = (Point) other;

		// If it's null or not a Point then return false by default
		else
			return false;

		// Returns a comparison of the Points
		return (x == otherPoint.getX() && y == otherPoint.getY());
	}

	// Hash Code
	public int hashCode()
	{
		return Integer.valueOf(y).hashCode();
	}
}
