package paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

/**
 * A Line Strategy that implements the Strategy interface that contains all mouse events. The strategy contains a 
 * copy of the current paint panel and a line shape. It also contains methods that can handle the mouse events, 
 * determines what each mouse event should do for a line, and adds those new creations to the command stack in 
 * Paint panel. This strategy is an enhanced version of the listeners from Bug3.
 * @author Alexandre (gagneal1)
 */
public class LineStrategy implements Strategy {

	private Line line;

	private PaintPanel paintpanel;
	private GraphicsContext g;

	/**
	 * Construct a new LineStrategy with the given PaintPanel and retrieve the graphics context from panel
	 * @param paintpanel
	 */
	LineStrategy(PaintPanel paintpanel) {
		this.paintpanel = paintpanel;
		this.g = paintpanel.getG();
	}
	
	/**
	 * Creates a new point based on the position of where the mouse moved on the canvas of the Paint Panel.
	 * This new point represents the new end point of the line. A new draw command is added to the temp
	 * commands stack and the paint panel is refreshed.
	 * @param MouseEvent e
	 */
	@Override
	public void mouseMoved(MouseEvent e) {		
		if (this.line != null) {
			Point point = new Point((int) e.getX(), (int) e.getY());
			this.line.setEnd(point);
			this.paintpanel.addTempCommands(new DrawLineCommand(this.line, this.g));
			this.paintpanel.repaint();
			}
		}
	
	/**
	 * Creates a new point based on where the mouse clicked on the canvas. If there is no pre-existing line, create
	 * a new line with the point as a start point. If there is a line, set the point as a new end point, clear the 
	 * temporary commands, add the line to the commands stack and set the given line to null.
	 * @param MouseEvent e
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		Point point = new Point((int) e.getX(), (int) e.getY());
		if (this.line == null) {
			this.line = new Line(point, null);
		} else {
			this.line.setEnd(point);
			this.paintpanel.clearTempCommands();
			this.paintpanel.addCommands(new DrawLineCommand(this.line, g));
			this.line = null;			
			}
	}
	
	/*
	 * (non-Javadoc)
	 * These following mouse events do not do anything for the Line strategy, but they
	 * exist to make sure that nothing can happen accidentally when the events do occur.
	 */
	@Override
	public void mousePressed(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	
	/**
	 * Determines the mouse event type and runs the appropriate mouse event method for the shape.
	 * @param MouseEvent e
	 */
	@Override
	public void handleEvent(MouseEvent e) {
		if (e.getEventType() == MouseEvent.MOUSE_MOVED) {
			mouseMoved(e);
		} else if (e.getEventType() == MouseEvent.MOUSE_CLICKED) {
			mouseClicked(e);
		} else {}
	}
}
