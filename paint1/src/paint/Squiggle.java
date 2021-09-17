package paint;

import java.util.ArrayList;

/**
 * A Squiggle class that extends from Shape that represents a free form non-straight line.
 * It contains an array list of all the points that are in the line. It also contains methods that
 * can return the array list of points and add a new point to the array.
 */
public class Squiggle extends Shape {
	
	private ArrayList<Point> points;
	
	/**
	 * Construct a new Squiggle that contains an array list of points and adds the given point to the array.
	 * This point represents the start point of the Squiggle.
	 * @param point
	 */
	public Squiggle(Point point) {
		points = new ArrayList<Point>();
		points.add(point);
	}
	
	/**
	 * Return the current array list of points of the given Squiggle.
	 * @return points
	 */
	public ArrayList<Point> getPoints() {
		return points;
	}
	
	/**
	 * Add a new point to the current array list of points for the Squiggle.
	 * @param point
	 */
	public void addPoint(Point point) {
		points.add(point);
	}
}