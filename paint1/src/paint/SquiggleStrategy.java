package paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

/**
 * An Squiggle Strategy that implements the Strategy interface that contains all mouse events. The strategy contains a 
 * copy of the current paint panel, a graphics context and a squiggle shape. It also contains methods that can handle 
 * the mouse events, determines what each mouse event should do for a squiggle, and adds those new creations 
 * to the command stack in Paint panel. This strategy is an enhanced version of the listeners from Bug3.
 * @author Alexandre (gagneal1)
 */
public class SquiggleStrategy implements Strategy {

	private Squiggle squiggle;
	private PaintPanel paintpanel;
	private GraphicsContext g;

	/**
	 * Construct a new Squiggle Strategy with the given paint panel and retrieve the graphics context from the panel.
	 * @param paintpanel
	 */
	SquiggleStrategy(PaintPanel paintpanel) {
		this.paintpanel = paintpanel;
		this.g = paintpanel.getG();
	}

	/*
	 * (non-Javadoc)
	 * These following mouse events do not do anything for the Squiggle strategy, but they
	 * exist to make sure that nothing can happen accidentally when the events do occur.
	 */
	@Override
	public void mouseMoved(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	/**
	 * Creates a new point based on the position of where the mouse pressed on the canvas of the Paint Panel.
	 * This new point represents the start point of the new squiggle to be drawn. A new squiggle is created.
	 * @param MouseEvent e
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		Point point = new Point((int) e.getX(), (int) e.getY());
		this.squiggle = new Squiggle(point);
	}

	/**
	 * Dynamically new points to the Squiggle based on the mouse drag movements and assigns them to the squiggle.
	 * The sqiuggle and panel are assigned to the Draw Squiggle command and is added to the temporary command stack.
	 * Repaint is called to refresh the current canvas and display the dynamic changes.
	 * @param MouseEvent e
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		this.squiggle.addPoint(new Point((int) e.getX(), (int) e.getY()));
		this.paintpanel.addTempCommands(new DrawSquiggleCommand(this.squiggle, this.g));	
		this.paintpanel.repaint();
	}

	/**
	 * Indicates the end of the creation of the squiggle instance. Clear the temporary command stack and 
	 * add the final squiggle to the proper command stack. Clear the original squiggle and set it to null.
	 * @param MouseEvent e
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (this.squiggle != null) {
			this.paintpanel.setTempCommands(new DrawingCommandsInvoker());
			this.paintpanel.addCommands(new DrawSquiggleCommand(this.squiggle, this.g));
			this.squiggle = null;
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
