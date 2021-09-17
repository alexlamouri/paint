package paint;


import paint.DrawingCommands;

import javafx.scene.canvas.GraphicsContext;


/**
 * DrawLineCommand class that implements DrawingCommands that draws a line on the canvas.
 * It contains a drawLine method and an execute method that runs drawLine when prompted.
 *  
 * @author Jessica (ander710)
 */
public class DrawLineCommand implements DrawingCommands{
	
	private Line line;
	private GraphicsContext g;
	
	/**
	 * Construct a new drawLine Command with the given line from the panel.
	 * @param l
	 * @param panel
	 */
	public DrawLineCommand(Line l, GraphicsContext g) {
		this.line = l;
		this.g= g;
	}
	
	/**
	 * Draws the given line on the canvas using the GraphicsContext.
	 * @param l
	 * @param g
	 */
	public void drawLine (Line l, GraphicsContext g) {
		
		if (l.getStart() != null && l.getEnd() != null) {
			int startX = l.getStart().getX();
			int startY = l.getStart().getY();
			int endX = l.getEnd().getX();
			int endY = l.getEnd().getY();
			g.strokeLine(startX, startY, endX, endY);
		}
	}
	/**
	 * Executes the drawLine method in the Drawing Command stack when prompted.
	 */
	@Override
	public void execute() {
		this.drawLine(this.line, this.g); 
}