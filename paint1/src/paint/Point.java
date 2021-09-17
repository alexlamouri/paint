package paint;

/**
 * A point class that extends shape and contains an x and y variable indicating its position.
 * It contains methods that can set and get the current x and y positions.
 */
public class Point extends Shape{
	int x, y; 
	
	/**
	 * Constructs a new point with the given int x and y positions
	 * @param x
	 * @param y
	 */
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Return the current x position of the point
	 * @return x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Change the current x position of the given Point to the new int value
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Return the current y position of the given Point
	 * @return y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Change the current y position of the given point to the new int value
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
}
