package paint;

import paint.DrawingCommands;
import javafx.scene.canvas.GraphicsContext;

/**
 * DrawRectangleCommand class that implements DrawingCommands that draws a rectangle on the canvas.
 * It contains a copy of the current paint panel and a rectangle shape. It also contains a 
 * drawRectangle method and an execute method that runs drawRectangle when prompted.
 *  
 * @author Jessica (ander710)
 */
public class DrawRectangleCommand implements DrawingCommands{
	
	private Rectangle rectangle;
	private GraphicsContext g;
	private PaintPanel panel;
	
	/**
	 * Construct a new drawRectangle Command with the given rectangle from the panel.
	 * @param r
	 * @param panel
	 */
	public DrawRectangleCommand(Rectangle r, PaintPanel panel) {
		this.rectangle = r;
		this.panel = panel;
		this.g= panel.getG();
	}
	
	/**
	 * Draws the given rectangle on the canvas using the GraphicsContext.
	 * @param r
	 * @param g
	 */
	public void drawRectangle(Rectangle r, GraphicsContext g) {
		
		int x1 = r.getCorner1().getX();
		int y1 = r.getCorner1().getY();
		int x2 = r.getCorner2().getX();
		int y2 = r.getCorner2().getY();
				
		int w = Math.abs(x2-x1);
		int h = Math.abs(y2-y1);

		//Drawing rectangle from bottom right corner to top left corner
		if (x2 < x1 && y2 < y1) {
			if (panel.getFill()) {
				g.fillRect(x2, y2, w, h);
			} else {					
				g.strokeRect(x2, y2, w, h);
			}
		}
		
		//Drawing rectangle from bottom right corner to top left corner
		else if (x2 < x1) {				
			if (panel.getFill()) {				
				g.fillRect(x2, y1, w, h);
			} else {
				g.strokeRect(x2, y1, w, h);
			}
		}	
						
		//Drawing rectangle from bottom right corner to top left corner
		else if (y2 < y1) {
					
			if (panel.getFill()) {
				g.fillRect(x1, y2, w, h);
			} else {
				g.strokeRect(x1, y2, w, h);
			}		
		}	
				
		//Drawing rectangle from bottom right corner to top left corner
		else {
			if (panel.getFill()) {
				g.fillRect(x1, y1, w, h);
			} else {
				g.strokeRect(x1, y1, w, h);
			}
		}
	}
	
	/**
	 * Executes the drawRectangle method in the Drawing Command stack when prompted.
	 */
	@Override
	public void execute() {
		this.drawRectangle(this.rectangle, this.g);	
	}
}