package ca.utoronto.utm.paint;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;

/**
 * Implementation of the Visitor design pattern
 * 
 * DrawVisitor is given a GraphicsContext
 * 
 * SaveVisitor visits a PaintCommand and draws the command to the GraphicsContext
 * 
 * @author gagneal1
 */
public class DrawVisitor implements Visitor {
	
	private GraphicsContext graphic;

	DrawVisitor(GraphicsContext graphicContext) {
		this.graphic = graphicContext;
	}

	/**
	 * Visit a CircleCommand and draw the CircleCommand in the GraphicsContext
	 * 
	 * @param CircleCommand 
	 */
	public void visit(CircleCommand command) {
		
		int x = command.getCenter().x;
		int y = command.getCenter().y;
		int radius = command.getRadius();
		
		if (command.isFill() ) {
			graphic.setFill(command.getColor());
			graphic.fillOval(x - radius, y- radius, 2 * radius, 2 * radius);
		}
		
		else {
			graphic.setFill(command.getColor());
			graphic.strokeOval(x - radius, y - radius, 2 * radius, 2 * radius);
		}

	}

	/**
	 * Visit a RectangleCommand and draw the RectangleCommand in the GraphicsContext
	 * 
	 * @param RectangleCommand 
	 */
	public void visit(RectangleCommand command) {
		
		Point topLeft = command.getTopLeft();
		Point dimensions = command.getDimensions();
		
		if(command.isFill()){
			graphic.setFill(command.getColor());
			graphic.fillRect(topLeft.x, topLeft.y, dimensions.x, dimensions.y);
		} 
		
		else {
			graphic.setStroke(command.getColor());
			graphic.strokeRect(topLeft.x, topLeft.y, dimensions.x, dimensions.y);
		}

	}

	/**
	 * Visit a SquiggleCommand and draw the SquiggleCommand in the GraphicsContext
	 * 
	 * @param SquiggleCommand 
	 */
	public void visit(SquiggleCommand command) {
		
		ArrayList<Point> points = command.getPoints();
		
		graphic.setStroke(command.getColor());
		
		for(int i=0;i<points.size()-1;i++){
			Point p1 = points.get(i);
			Point p2 = points.get(i+1);
			graphic.strokeLine(p1.x, p1.y, p2.x, p2.y);
		}

	}

}
