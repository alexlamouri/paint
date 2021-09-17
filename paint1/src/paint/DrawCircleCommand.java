package paint;

import paint.DrawingCommands;
import javafx.scene.canvas.GraphicsContext;

/**
 * DrawCircleCommand class that implements DrawingCommands that draws a circle on the canvas.
 * It contains a copy of the current paint panel and a circle shape. It also contains a drawCircle method 
 * and an execute method that runs drawCircle when prompted.
 *  
 * @author Jessica (ander710)
 */

public class DrawCircleCommand implements DrawingCommands{
	
	private Circle circle;
	private GraphicsContext g;
	private PaintPanel panel;
	
	/**
	 * Construct a new drawCircle Command with the given circle from the panel.
	 * @param c
	 * @param panel
	 */
	public DrawCircleCommand(Circle c, PaintPanel panel) {
		this.circle = c;
		this.panel = panel;
		this.g = panel.getG();
	}
	
	/**
	 * Draws the given circle on the canvas using the GraphicsContext.
	 * @param c
	 * @param g
	 */
	public void drawCircle(Circle c, GraphicsContext g) {	
		int x = c.getCentre().getX();
		int y = c.getCentre().getY();
		int radius = c.getRadius();

		if (panel.getFill()) {
			g.fillOval(x - radius, y-radius , 2*radius, 2*radius);
			} else {
				g.strokeOval(x - radius , y - radius , 2 * radius, 2 * radius);
			}	
	}
	
	/**
	 * Executes the drawCircle method in the Drawing Command stack when prompted.
	 */
	@Override
	public void execute() {
		this.drawCircle(this.circle, this.g);	
	}
	
}