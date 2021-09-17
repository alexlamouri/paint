package paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * ShapeColourCommand class that implements DrawingCommands and changes the
 * shape colour regardless of fill to the paint panel by adding the change to
 * the command stack in the panel. It contains a boolean fill, string color, a graphics context 
 * and a paint panel. It also contains a method that changes the set color of the canvas and fill
 * if the boolean holds true, and an execute method that runs the color change when prompted.
 * @author Danica (navaret3)
 */
public class ShapeColourCommand implements DrawingCommands {
	
	private String color;
	private GraphicsContext g;
	private boolean fill;
	private PaintPanel panel;
	
	/**
	 * Constructs a new ShapeColourCommand that initializes the chosen colour
	 * and the graphics context.
	 * @param color
	 * @param g
	 */
	public ShapeColourCommand(boolean fill, String color, PaintPanel panel) {
		this.g = panel.getG();
		this.color = color;
		this.fill = fill;
		this.panel = panel;
	}
	
	/**
	 * Pick color method that changes the drawing color in the canvas. If
	 * fill shape was picked, that the drawing method changes to create a 
	 * filled in shape. If fill is false, the shape is an colored outline.
	 * @param color
	 * @param g
	 */
	public void pickColour(String color, GraphicsContext g) {
		if(this.fill) {
			g.setFill(Color.web(color, 1.0));
			this.panel.setFill(true);
		} else {
			g.setStroke(Color.web(color, 1.0));
			this.panel.setFill(false);
		}
	}
	
	/**
	 * Executes the pick color method in the Drawing Command stack when prompted.
	 */
	@Override
	public void execute() {
		this.pickColour(this.color, this.g);	
	}
}
