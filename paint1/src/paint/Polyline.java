package paint;

/**
 * A polyline class that inherits from Shape that contains a start and end point.
 * It also contains methods that can set and get the current start and end points of the polyline.
 * @author Jessica (ander710)
 */
public class Polyline extends Shape {
	
	private Point start;
	private Point end;
	
	/**
	 * Constructs a new polyline with the given start and end points
	 * @param start
	 * @param end
	 */
	public Polyline(Point start, Point end) {
		this.start = start;
		this.end = end;
	}
	
	/**
	 * Change the current start point of the given Polyline to the new point.
	 * @param start
	 */
	public void setStart(Point start) {
		this.start = start;
	}
	
	/**
	 * Change the current end point of the given Polyline to the new point.
	 * @param end
	 */
	public void setEnd(Point end) {
		this.end = end;
	}
	
	/**
	 * Return the current start point of the given Polyline
	 * @return start
	 */
	public Point getStart() {
		return start;
	}
	
	/**
	 * Return the given end point of the given Polyline
	 * @return end
	 */
	public Point getEnd() {
		return end;
	}
}