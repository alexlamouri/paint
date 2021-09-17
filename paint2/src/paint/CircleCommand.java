package ca.utoronto.utm.paint;

public class CircleCommand extends PaintCommand implements Visitable {
	private Point center;
	private int radius;
	
	public CircleCommand(Point center, int radius){
		this.center = center;
		this.radius = radius; 
	}
	public Point getCenter() { return center; }
	
	public void setCenter(Point center) { 
		this.center = center; 
		this.setChanged();
		this.notifyObservers();
	}
	
	public int getRadius() { return radius; }
	
	public void setRadius(int radius) { 
		this.radius = radius; 
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Accept a Visitor object and perform Visitor-specific actions
	 * 
	 * @param visitor 
	 */
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
