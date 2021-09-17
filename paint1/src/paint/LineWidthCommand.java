package paint;

import javafx.scene.canvas.GraphicsContext;

/**
 * LineWidthCommand class that implements DrawingCommands that changes the 
 * chosen line width in the paint panel by adding the change to the 
 * commands stack in the panel. It contains a int thickness that changes the line width of the
 * graphics context. It also contains methods that change the line width of the canvas and an 
 * execute method that runs the linewidth change when prompted.
 * @author Danica (navaret3)
 */
public class LineWidthCommand implements DrawingCommands {
	
	private int thickness;
	private GraphicsContext g;
	
	/**
	 * Construct a new LineWidth Command with the target thickness from the line width button and
	 * the given paint panel.
	 * @param thickness
	 * @param panel
	 */
	public LineWidthCommand(int thickness, PaintPanel panel) {
		this.thickness = thickness;
		this.g = panel.getG();
	}
	
	/**
	 * Sets the current line width of the graphics context with the given thickness.
	 * @param thickness
	 * @param g
	 */
	public void setLineWidth(int thickness, GraphicsContext g) {
		g.setLineWidth(thickness);
	}
	
	/**
	 * Executes the line width method in the Drawing Command stack when prompted.
	 */
	@Override
	public void execute() {
		this.setLineWidth(this.thickness, this.g);	
	}
}
