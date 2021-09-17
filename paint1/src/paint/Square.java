package paint;

/**
 * A Square class that is a special kind of rectangle with equal sides.
 * It inherits all methods and attributes from Rectangle.
 * @author Jessica (ander710)
 */
public class Square extends Rectangle{
	
	/**
	 * Construct a new square with the given opposite diagonal corners
	 * @param corner1
	 * @param corner2
	 */
	public Square(Point corner1, Point corner2) {
		super(corner1, corner2);
	}
}

