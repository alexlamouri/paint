package paint;

import java.util.ArrayList;


import ca.utoronto.utm.paint.DrawingCommands;
import javafx.scene.canvas.GraphicsContext;

/**
 * DrawSquiggleCommand class that implements DrawingCommands that draws a free form non-straight line
 * on the canvas. It contains an execute method that runs drawSquiggle when prompted.
 *  
 * @author Jessica (ander710)
 */
public class DrawSquiggleCommand implements DrawingCommands{
	
	private Squiggle squiggle;
	private GraphicsContext g;
	
	/**
	 * Construct a new drawSquiggle Command with the given squiggle from the panel.
	 * @param s
	 * @param panel
	 */
	public DrawSquiggleCommand(Squiggle s, GraphicsContext g) {
		this.squiggle = s;
		this.g= g;
	}
	
	/**
	 * Draws the given squiggle on the canvas using the GraphicsContext.
	 * @param c
	 * @param g
	 */
	public void drawSquiggle(Squiggle s, GraphicsContext g) {

		ArrayList<Point> points = s.getPoints();
		for (int j = 0; j < points.size() - 1; j++) {
			Point p1 = points.get(j);
			Point p2 = points.get(j + 1);
			if (p1 != null && p2 !=null) { 
			g.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());	
			}
		}
	}

	/**
	 * Executes the drawSquiggle method in the Drawing Command stack when prompted.
	 */
	@Override
	public void execute() {
		this.drawSquiggle(this.squiggle, this.g);	
	}
}