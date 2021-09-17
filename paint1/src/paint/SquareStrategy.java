package paint;

import javafx.scene.input.MouseEvent;

/**
 * A Square Strategy that implements the Strategy interface that contains all mouse events. The strategy contains a 
 * copy of the current paint panel and a square shape. It also contains methods that can handle the mouse events, 
 * determines what each mouse event should do for a square, and adds those new creations to the command stack in 
 * Paint panel. This strategy is an enhanced version of the listeners from Bug3.
 * @author Alexandre (gagneal1)
 */
public class SquareStrategy implements Strategy {

	private Square square;	
	private PaintPanel paintpanel;

	/**
	 * Construct a new Square Strategy with the given paint panel.
	 * @param paintpanel
	 */
	SquareStrategy(PaintPanel paintpanel) {
		this.paintpanel = paintpanel;
	}
	
	/**
	 * Creates a new point based on the position of where the mouse pressed on the canvas of the Paint Panel.
	 * This new point represents the first corner of the square. The second corner is equal to the first corner
	 * and a new square is created.
	 * @param MouseEvent e
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		Point corner1 = new Point((int) e.getX(), (int) e.getY());
		Point corner2 = corner1;
		this.square = new Square(corner1, corner2);
	}
	
	/**
	 * Dynamically creates a new corner2 based on the mouse drag movements and assigns them to the square. Based on the
	 * positions of the two corners, the width and height are dynamically created so that only a square is created.
	 * The square and panel are assigned to the Draw Rectangle command and is added to the temporary command stack.
	 * Repaint is called to refresh the current canvas and display the dynamic changes.
	 * @param MouseEvent e
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		Point corner2 = new Point((int) e.getX(), (int) e.getY());

		int x1 = this.square.getCorner1().getX();
		int y1 = this.square.getCorner1().getY();
		int x2 = corner2.getX();
		int y2 = corner2.getY();
		
		int w = Math.abs(x2 - x1);
		int h = Math.abs(y2 - y1);
		int l = (int)(w+h)/2;
		
		//Drawing square from bottom right corner to top left corner
		if (x2 < x1 && y2 < y1) {
			corner2.setX(x1 - l);
			corner2.setY(y1-l);
		}
		//Drawing square from top right corner to bottom left corner
		else if (x2 < x1) {
			corner2.setX(x1 - l);
			corner2.setY(y1 + l);
		}
		//Drawing square from bottom left corner to top right corner
		else if (y2 < y1) {
			corner2.setX(x1 + l);
			corner2.setY(y1 - l);
		}
		//Drawing square from top left corner to bottom right corner.
		else {
			corner2.setX(x1 + l);
			corner2.setY(y1 + l);
		}
		
		this.square.setCorner2(corner2);
		this.paintpanel.addTempCommands(new DrawSquareCommand(this.square, this.paintpanel));
		this.paintpanel.repaint();	
	}
	
	/**
	 * Indicates the end of the creation of the square instance. Clear the temporary command stack and 
	 * add the final square to the proper command stack. Clear the original square and set it to null.
	 * @param MouseEvent e
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (this.square != null) {	
			this.paintpanel.clearTempCommands();
			this.paintpanel.addCommands(new DrawSquareCommand(this.square, this.paintpanel));
			this.square = null;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * These following mouse events do not do anything for the Square strategy, but they
	 * exist to make sure that nothing can happen accidentally when the events do occur.
	 */
	@Override
	public void mouseMoved(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	/**
	 * Determines the mouse event type and runs the appropriate mouse event method for the shape.
	 * @param MouseEvent e
	 */
	@Override
	public void handleEvent(MouseEvent e) {
		if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
			mouseDragged(e);
		} else if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
			mousePressed(e);
		} else if (e.getEventType() == MouseEvent.MOUSE_RELEASED) {
			mouseReleased(e);
		} else {}
	}
}
