package paint;

import javafx.scene.input.MouseEvent;

/**
 * A Rectangle Strategy that implements the Strategy interface that contains all mouse events. The strategy contains a 
 * copy of the current paint panel and a rectangle shape. It also contains methods that can handle the mouse events, 
 * determines what each mouse event should do for a rectangle, and adds those new creations to the command stack in 
 * Paint panel. This strategy is an enhanced version of the listeners from Bug3.
 * @author Alexandre (gagneal1)
 */
public class RectangleStrategy implements Strategy {

	private Rectangle rectangle;
	private PaintPanel paintpanel;

	/**
	 * Construct a new RectangleStrategy with the given PaintPanel
	 * @param paintpanel
	 */
	RectangleStrategy(PaintPanel paintpanel) {
		this.paintpanel = paintpanel;
	}
	
	/*
	 * (non-Javadoc)
	 * These following mouse events do not do anything for the Rectangle strategy, but they
	 * exist to make sure that nothing can happen accidentally when the events do occur.
	 */
	@Override
	public void mouseMoved(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}

	/**
	 * Creates a new point based on the position of where the mouse pressed on the canvas of the Paint Panel.
	 * This new point represents the first corner of the rectangle. The second corner is equal to the first corner
	 * and a new rectangle is created.
	 * @param MouseEvent e
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		Point corner1 = new Point((int) e.getX(), (int) e.getY());
		Point corner2 = corner1;
		this.rectangle = new Rectangle(corner1, corner2);
	}
	
	/**
	 * Dynamically creates a new corner2 based on the mouse drag movements and assigns them to the rectangle.
	 * The rectangle and panel are assigned to the Draw Rectangle command and is added to the temporary command stack.
	 * Repaint is called to refresh the current canvas and display the dynamic changes.
	 * @param MouseEvent e
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		Point corner2 = new Point((int) e.getX(), (int) e.getY());
		this.rectangle.setCorner2(corner2);
		this.paintpanel.addTempCommands(new DrawRectangleCommand(this.rectangle, this.paintpanel));
		this.paintpanel.repaint();
	}
	
	/**
	 * Indicates the end of the creation of the rectangle instance. Clear the temporary command stack and 
	 * add the final rectangle to the proper command stack. Clear the original rectangle and set it to null.
	 * @param MouseEvent e
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (this.rectangle != null) {
			this.paintpanel.clearTempCommands();
			this.paintpanel.addCommands(new DrawRectangleCommand(this.rectangle, this.paintpanel));
			this.rectangle = null;	
		}
	}
	
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
