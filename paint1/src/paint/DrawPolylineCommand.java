package paint;


import paint.DrawingCommands;
import javafx.scene.canvas.GraphicsContext;

/**
 * DrawPolylineCommand class that implements DrawingCommands that draws a Polyline on the canvas.
 * It contains a drawPolyline method and an execute method that runs drawPolyline when prompted.
 *  
 * @author Jessica (ander710)
 */
public class DrawPolylineCommand implements DrawingCommands{
	
	private Polyline polyline;
	private GraphicsContext g;
	
	/**
	 * Construct a new drawPolyline Command with the given polyline from the panel.
	 * @param p
	 * @param panel
	 */	
	public DrawPolylineCommand(Polyline p, GraphicsContext g) {
		this.polyline = p;
		this.g= g;
	}
	/**
	 * Draws the given polyline on the canvas using the GraphicsContext.
	 * @param p
	 * @param g
	 */
	public void drawPolyline (Polyline p, GraphicsContext g) {
		if (p.getStart() != null && p.getEnd() != null) {
			int startX = p.getStart().getX();
			int startY = p.getStart().getY();
			int endX = p.getEnd().getX();
			int endY = p.getEnd().getY();
			g.strokeLine(startX, startY, endX, endY);
		}	
	}
	/**
	 * Executes the drawPolyline method in the Drawing Command stack when prompted.
	 */
	@Override
	public void execute() {
		this.drawPolyline(this.polyline, this.g);	
	}
}