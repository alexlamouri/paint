package ca.utoronto.utm.paint;

/**
 * Visitor design pattern
 * 
 * Every object inheriting Visitor can visit a Visitable object
 * 
 * @author gagneal1
 */
public interface Visitor {
	
	void visit(CircleCommand circleCommand);

	void visit(RectangleCommand rectangleCommand);
	
	void visit(SquiggleCommand squiggleCommand);

}
