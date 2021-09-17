package paint;

/**
 * A rectangle class that inherits from Shape that contains two corner points that represent
 * the opposite diagonal corners. It contains methods that can get and set the corners of 
 * rectangle. The first corner represents the starting drawing point of the rectangle.
 * @author Jessica (ander710)
 */
public class Rectangle extends Shape{
	
	private Point corner1;
	private Point corner2;

	
	/**
	 * Construct a new Rectangle with the given opposite diagonal corners
	 * @param corner1
	 * @param corner2
	 */
	public Rectangle(Point corner1, Point corner2) {
		//super(color, thickness, fill);
		this.corner1 = corner1;
		this.corner2 = corner2;
	}
	
	/**
	 * Return the current corner 1 of the rectangle. This is the starting corner.
	 * @return corner1
	 */
	public Point getCorner1() {
		return corner1;
	}
	
	/**
	 * Change the current corner1 of the given Rectangle to the new point.
	 * @param corner1
	 */
	public void setCorner1(Point corner1) {
		this.corner1 = corner1;
	}
	
	/**
	 * Return the current corner2 of the given Rectangle that is dynamically determined.
	 * @return corner2
	 */
	public Point getCorner2() {
		return corner2;
	}
	
	/**
	 * Change the current corner2 of the given Rectangle to the new point.
	 * @param corner2
	 */
	public void setCorner2(Point corner2) {
		this.corner2 = corner2;
	}
}

