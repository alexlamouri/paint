package paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

/**
 * An Polyline Strategy that implements the Strategy interface that contains all mouse events. The strategy contains a 
 * copy of the current paint panel, a line shape, a click count, a graphics context and a polyline shape. 
 * It also contains methods that can handle the mouse events, determines what each mouse event should do for 
 * a polyline, and adds those new creations to the command stack in Paint panel. This strategy is an enhanced 
 * version of the listeners from Bug3.
 * @author Alexandre (gagneal1) & edited by Jessica (ander710)
 */
public class PolylineStrategy implements Strategy {

	private Polyline polyline;
	private int click = 0;
	private Line line;
	
	private PaintPanel paintpanel;
	private GraphicsContext gui;
	
	/**
	 * Construct a new Polyline Strategy with the given paint panel and obtains the 
	 * graphics context from the panel.
	 * @param paintpanel
	 */
	PolylineStrategy(PaintPanel paintpanel) {
		this.paintpanel = paintpanel;
		this.gui = paintpanel.getG();
	}
	
	/**
	 * Creates a new point based on the position of where the mouse moved on the canvas of the Paint Panel.
	 * This new point represents the new end point of the polyline. A new draw command is added to the temp
	 * commands stack and the paint panel is refreshed.
	 * @param MouseEvent e
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		click = 0;			
		if (this.polyline != null) {
			Point point = new Point((int) e.getX(), (int) e.getY());
			this.polyline.setEnd(point);
			this.paintpanel.addTempCommands(new DrawPolylineCommand(this.polyline, gui));
			this.paintpanel.repaint();
			}
		}
	
	/**
	 * Creates a new point based on the position of where the mouse clicked on the canvas of the Paint Panel.
	 * For a single click, the click count is set to one and a new polyline is created if there was no pre-existing 
	 * one existing. A new line is created to connect the two polyline points together. If there was a pre-existing line, 
	 * then the new point is set to be the final end point and another line is created for the polyline. The 
	 * temporary commands are reset, and the polyline is added to the commands stack.The start point is indicated
	 * to be the end point to stop drawing a polyline.
	 * If the click count is greater than one, the polyline creation will end.
	 * @param MouseEvent e
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		click += 1;
		Point point = new Point((int) e.getX(), (int) e.getY());

		if (click == 1) {
			if (this.polyline == null) {
				this.polyline = new Polyline(point, null);
				this.line = new Line(this.polyline.getStart(),this.polyline.getEnd());
			} else {
				this.polyline.setEnd(point);
				this.line = new Line(this.polyline.getStart(),this.polyline.getEnd());
				this.paintpanel.clearTempCommands();
				this.paintpanel.addCommands(new DrawLineCommand(this.line, gui));				
				this.polyline.setStart(this.polyline.getEnd());				
			}
		} else {
			this.polyline = new Polyline(null, null);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * These following mouse events do not do anything for the PolyLine strategy, but they
	 * exist to make sure that nothing can happen accidentally when the events do occur.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
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
