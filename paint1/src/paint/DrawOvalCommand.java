package paint;

import paint.DrawingCommands;
import javafx.scene.canvas.GraphicsContext;

/**
 * DrawOvalCommand class that implements DrawingCommands that draws a oval on the canvas.
 * It contains a copy of the current paint panel and an oval shape. It also contains a drawOval method 
 * and an execute method that runs drawOval when prompted.
 *  
 * @author Alexandre (gagneal1)
 */
public class DrawOvalCommand implements DrawingCommands{
	
	private Oval oval;
	private GraphicsContext g;
	private PaintPanel panel;
	
	/**
	 * Construct a new drawOval Command with the given line from the panel.
	 * @param o
	 * @param panel
	 */
	public DrawOvalCommand(Oval o, PaintPanel panel) {
		this.oval = o;
		this.g = panel.getG();
		this.panel = panel;
	}
	/**
	 * Draws the given oval on the canvas using the GraphicsContext.
	 * @param o
	 * @param g
	 */	
	public void drawOval(Oval o, GraphicsContext g) {
		int x = o.getCentre().getX();
		int y = o.getCentre().getY();
		int width = Math.abs(o.getWidth());
		int height = Math.abs(o.getHeight());

		if (panel.getFill()) {
			g.fillOval(x - (width/2), y - (height/2), width, height);
			} 
		else {
			g.strokeOval(x - (width/2), y - (height/2), width, height);
		}		
	}
	/**
	 * Executes the drawOval method in the Drawing Command stack when prompted.
	 */
	@Override
	public void execute() {
		this.drawOval(this.oval, this.g);	
	}
	
}