package ca.utoronto.utm.paint;
import java.util.ArrayList;

public class SquiggleCommand extends PaintCommand {
	private ArrayList<Point> points=new ArrayList<Point>();
	
	public void add(Point p){ 
		this.points.add(p); 
		this.setChanged();
		this.notifyObservers();
	}
	
	public ArrayList<Point> getPoints(){ return this.points; }
	
	/**
	 * Accept a Visitor object and perform Visitor-specific actions
	 * 
	 * @param visitor 
	 */
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
}
	

