package paint;

/**
 * An Oval class that inherits from Shape that contains a centre point, int width and int height.
 * It contains methods that can get and set the centre point, width and height.
 * @author Alexandre (gagneal1)
 */
public class Oval extends Shape{
	
	private Point centre;
	private int width;
	private int height;
	
	/**
	 * Construct a new Oval with the given centre point, int width and height
	 * @param centre
	 * @param width
	 * @param height
	 */
	public Oval(Point centre, int width, int height) {
		this.centre = centre;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Return the current centre point of given Oval.
	 * @return centre
	 */
	public Point getCentre() {
		return centre;
	}
	
	/**
	 * Change the current centre point of the given Oval to the new one.
	 * @param centre
	 */
	public void setCentre(Point centre) {
		this.centre = centre;
	}

	/**
	 * Return the current int width of the given Oval.
	 * @return width
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Change the current width of the given Oval to the new int value.
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * Return the current height of the given Oval
	 * @return height
	 */
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * Change the current height of the given Oval to the new int value.
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}
}


