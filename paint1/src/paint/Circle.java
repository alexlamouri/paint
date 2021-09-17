package paint;

/**
 * A Circle class that inherits from shape that contains a centre point and a radius.
 * It contains methods that can set and get the centre point and the radius.
 */
public class Circle extends Shape{
	
	private Point centre;
	private int radius;
	
	/**
	 * Constructs a Circle with a center Point and int radius.
	 * @param centre
	 * @param radius
	 */
	public Circle(Point centre, int radius) {
		this.centre = centre;
		this.radius = radius;
	}
	
	/**
	 * Return the current center point of the given Circle
	 * @return centre
	 */
	public Point getCentre() {
		return centre;
	}
	
	/**
	 * Change the centre point of the given Circle to a new point
	 * @param Point centre
	 */
	public void setCentre(Point centre) {
		this.centre = centre;
	}
	
	/**
	 * Return the current int radius of the given Gircle
	 * @return radius
	 */
	public int getRadius() {
		return this.radius;
	}
	
	/**
	 * Change the radius of the given Circle to a new value
	 * @param radius
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}
}


