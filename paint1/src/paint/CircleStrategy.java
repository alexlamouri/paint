package paint;

import javafx.scene.input.MouseEvent;

/**
 * A Circle Strategy that implements the Strategy interface that contains all mouse events. The strategy contains a 
 * copy of the current paint panel and a circle shape. It also contains methods that can handle the mouse events, 
 * determines what each mouse event should do for a circle, and adds those new creations to the command stack in 
 * Paint panel. This strategy is an enhanced version of the listeners from Bug3.
 * @author Alexandre (gagneal1)
 */
public class CircleStrategy implements Strategy {

	private Circle circle;
	private PaintPanel paintpanel;
	
	/**
	 * Construct a new Circle Strategy with the given paint panel.
	 * @param paintpanel
	 */
	CircleStrategy(PaintPanel paintpanel) {
		this.paintpanel = paintpanel;
	}
	
	/*
	 * (non-Javadoc)
	 * These following mouse events do not do anything for the Circle strategy, but they
	 * exist to make sure that nothing can happen accidentally when the events do occur.
	 */
	@Override
	public void mouseMoved(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}

	/**
	 * Creates a new point based on the position of where the mouse pressed on the canvas of the Paint Panel.
	 * This new point represents the centre point of the new circle to be drawn.
	 * @param MouseEvent e
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		Point centre = new Point((int) e.getX(), (int) e.getY());
		int radius = 0;
		this.circle = new Circle(centre, radius);
	}
	
	/**
	 * Dynamically creates new radius based on the mouse drag movements and assigns them to the circle.
	 * The circle and panel are assigned to the Draw Circle command and is added to the temporary command stack.
	 * Repaint is called to refresh the current canvas and display the dynamic changes.
	 * @param MouseEvent e
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		int delta_x = Math.abs((int) e.getX() - (int) this.circle.getCentre().getX());
		int delta_y = Math.abs((int) e.getY() - (int) this.circle.getCentre().getY());
		int radius = (int) Math.sqrt(Math.pow(delta_x, 2) + Math.pow(delta_y, 2));
		this.circle.setRadius(radius);
		this.paintpanel.addTempCommands(new DrawCircleCommand(this.circle, this.paintpanel));		
		this.paintpanel.repaint();
	}
	
	/**
	 * Indicates the end of the creation of the circle instance. Clear the temporary command stack and 
	 * add the final circle to the proper command stack. Clear the original circle and set it to null.
	 * @param MouseEvent e
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (this.circle != null) {
			this.paintpanel.setTempCommands(new DrawingCommandsInvoker());
			this.paintpanel.addCommands(new DrawCircleCommand(this.circle, this.paintpanel));
			this.circle = null;
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
