package paint;

/**
 * A line class that inherits from Shape that contains a start point and an end point. 
 * The class contains methods that can get and set the start and end points.
 * @author Jessica (ander710)
 */
public class Line extends Shape {
	
	private Point start;
	private Point end;

	/**
	 * Construct a new line with the given start and end points.
	 * @param start
	 * @param end
	 */
	public Line(Point start, Point end) {	
		this.start = start;
		this.end = end;
	}
	
	/**
	 * Change the current start point of the given Line to the new point
	 * @param start
	 */
	public void setStart(Point start) {
		this.start = start;
	}
	
	/**
	 * Change the current end point of the given Line to the new point
	 * @param end
	 */
	public void setEnd(Point end) {
		this.end = end;
	}
	
	/**
	 * Return the current start point of the given Line
	 * @return start
	 */
	public Point getStart() {
		return start;
	}
	
	/**
	 * Return the current end point of the given Line
	 * @return end
	 */
	public Point getEnd() {
		return end;
	}
}