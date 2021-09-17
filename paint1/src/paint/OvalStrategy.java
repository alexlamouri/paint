package paint;

import javafx.scene.input.MouseEvent;

/**
 * An Oval Strategy that implements the Strategy interface that contains all mouse events. The strategy contains a 
 * copy of the current paint panel and an oval shape. It also contains methods that can handle the mouse events, 
 * determines what each mouse event should do for an oval, and adds those new creations to the command stack in 
 * Paint panel. This strategy is an enhanced version of the listeners from Bug3.
 * @author Alexandre (gagneal1)
 */
public class OvalStrategy implements Strategy {

	private Oval oval;
	private PaintPanel paintpanel;
	
	/**
	 * Construct a new OvalStrategy with the given PaintPanel
	 * @param paintpanel
	 */
	OvalStrategy(PaintPanel paintpanel) {
		this.paintpanel = paintpanel;
	}
	
	/*
	 * (non-Javadoc)
	 * These following mouse events do not do anything for the Oval strategy, but they
	 * exist to make sure that nothing can happen accidentally when the events do occur.
	 */
	@Override
	public void mouseMoved(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	/**
	 * Creates a new point based on the position of where the mouse pressed on the canvas of the Paint Panel.
	 * This new point represents the centre point of the new oval to be drawn.
	 * @param MouseEvent e
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		Point centre = new Point((int) e.getX(), (int) e.getY());
		int width = 0;
		int height = 0;
		this.oval = new Oval(centre, width, height);
	}
	
	/**
	 * Dynamically creates new widths and heights based on the mouse drag movements and assigns them to the oval.
	 * The oval and panel are assigned to the Draw Oval command and is added to the temporary command stack.
	 * Repaint is called to refresh the current canvas and display the dynamic changes.
	 * @param MouseEvent e
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		int width = 2 * Math.abs((int) e.getX() - (int) this.oval.getCentre().getX());
		this.oval.setWidth(width);
		int height = 2 * Math.abs((int) e.getY() - (int) this.oval.getCentre().getY());
		this.oval.setHeight(height);
		this.paintpanel.addTempCommands(new DrawOvalCommand(this.oval, this.paintpanel));		
		this.paintpanel.repaint();
	}
	
	/**
	 * Indicates the end of the creation of the oval instance. Clear the temporary command stack and 
	 * add the final oval to the proper command stack. Clear the original oval and set it to null.
	 * @param MouseEvent e
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (this.oval != null) {
			this.paintpanel.clearTempCommands();
			this.paintpanel.addCommands(new DrawOvalCommand(this.oval, this.paintpanel));
			this.oval = null;
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
