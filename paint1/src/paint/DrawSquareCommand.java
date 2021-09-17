package paint;

import paint.DrawingCommands;

import javafx.scene.canvas.GraphicsContext;


/**
 * DrawSquareCommand class that implements DrawingCommands that draws a square on the canvas.
 * It contains a copy of the current paint panel and a square shape. It also contains drawSquare method
 * and an execute method that runs drawSquare when prompted.
 *  
 * @author Jessica (ander710)
 */
public class DrawSquareCommand implements DrawingCommands{
	
	private Square square;
	private GraphicsContext g;
	private PaintPanel panel;
	
	/**
	 * Construct a new drawSquare Command with the given square from the panel.
	 * @param s
	 * @param panel
	 */
	public DrawSquareCommand(Square s, PaintPanel panel) {
		this.square = s;
		this.panel = panel;
		this.g= panel.getG();
	}
	
	/**
	 * Draws the given square on the canvas using the GraphicsContext.
	 * @param s
	 * @param g
	 */
	public void drawSquare (Square s, GraphicsContext g) {

		int x1 = s.getCorner1().getX();
		int y1 = s.getCorner1().getY();
		int x2 = s.getCorner2().getX();
		int y2 = s.getCorner2().getY();

		int w = Math.abs(x2-x1);
		int h = Math.abs(y2-y1);
		
		//Drawing square from bottom right corner to top left corner
		if (x2 < x1 && y2 < y1) {

			if (panel.getFill()) {
				g.fillRect(x2, y2, w, h);
			} else {
				g.strokeRect(x2, y2, w, h);
			}
		}
		
		//Drawing square from top right corner to bottom left corner
		else if (x2 < x1) {
			if (panel.getFill()) {
				g.fillRect(x2, y1, w, h);
			} else {
				g.strokeRect(x2, y1, w, h);
			}
		}
		
		//Drawing square from bottom left corner to top right corner
		else if (y2 < y1) {
			if (panel.getFill()) {
				g.fillRect(x1, y2, w, h);
			} else {
				g.strokeRect(x1, y2, w, h);
			}
		}
		
		//Drawing square from top left corner to bottom right corner.
		else {
			if (panel.getFill()) {
				g.fillRect(x1, y1, w, h);
			}
			else {
				g.strokeRect(x1, y1, w, h);
			}
		}
	}

	/**
	 * Executes the drawSquare method in the Drawing Command stack when prompted.
	 */
	@Override
	public void execute() {
		this.drawSquare(this.square, this.g);	
	}
	
}