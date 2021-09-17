
package paint;

import javafx.event.EventHandler;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import java.util.Observable;
import java.util.Observer;


class PaintPanel extends StackPane implements Observer, EventHandler<MouseEvent> {

	private View view; // So we can talk to our parent or other components of the view
	private String backgroundcolor;
	
	// Modifiers
	private boolean fill; 
	
	// Graphic interface
	private GraphicsContext g;
	
	// Commands
	private DrawingCommandsInvoker commands;
	private DrawingCommandsInvoker tempCommands;
	private DrawingCommandsInvoker undoCommands;

	private Canvas canvas;
	
	private Strategy ShapeStrategy;

	/**
	 * Constructs a new PaintPanel with the given view. Sets all the
	 * default parameters for the canvas.
	 * @param view
	 */
	public PaintPanel(View view) {
		
		this.backgroundcolor = "WHITE";
		this.ShapeStrategy = null; // Equivalent to this.mode = null
		this.view = view;
		
		this.canvas = new Canvas(400, 400);
		this.getChildren().add(this.canvas);
		// The canvas is transparent, so the background color of the
		// containing pane serves as the background color of the canvas.
		this.setStyle("-fx-background-color: " + backgroundcolor);
		this.addEventHandler(MouseEvent.ANY, this);
		
		g = this.canvas.getGraphicsContext2D();
		commands = new DrawingCommandsInvoker();
		tempCommands = new DrawingCommandsInvoker();
		undoCommands = new DrawingCommandsInvoker();

		this.addCommands(new ShapeColourCommand(false, "BLACK", this));
		this.addCommands(new LineWidthCommand(1, this));
	}

	public void repaint() {
		GraphicsContext g = this.canvas.getGraphicsContext2D();
		
		// Clear the Canvas
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		// Execute Commands
		commands.executeAll();
		tempCommands.executeAll();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// Not exactly how MVC works, but similar.
		this.repaint();
	}
	
	/**
	 * Set the current fill of the paint panel to the new boolean.
	 * @param fill
	 */
	public void setFill(boolean fill) {
		this.fill = fill;
	}
	
	/**
	 * Return the current fill boolean of the paint panel.
	 * @return fill
	 */
	public boolean getFill() {
		return this.fill;
	}
	
	/**
	 * Set the current shapestrategy of the paintpanel to the given shapestrategy
	 * @param ShapeStrategy
	 */
	public void setShapeStrategy(Strategy ShapeStrategy) {
		this.ShapeStrategy = ShapeStrategy;
	}
	
	/**
	 * Set the canvas color of the paintpanel to the given string background color.
	 * @param backgroundcolor
	 */
	public void setBackgroundColor(String backgroundcolor) {
		this.backgroundcolor = backgroundcolor;
		this.setStyle("-fx-background-color: " + backgroundcolor);
	}
	
	/**
	 * Return the current background color of the paint panel.
	 * @return
	 */
	public String getBackgroundColor() {
		return this.backgroundcolor;
	}
	
	/**
	 * Add drawing commands to the commands invoker in paint panel.
	 * @param command
	 */
	public void addCommands(DrawingCommands command) {
		commands.add(command);
	}
	
	/**
	 * Set the current temporary commands invoker to the given commands invoker
	 * in the Paint panel.
	 * @param commands
	 */
	public void setTempCommands(DrawingCommandsInvoker commands) {
		tempCommands = commands;
	}
	
	/**
	 * Add a drawing command to the temporary commands invoker in paint panel.
	 * @param command
	 */
	public void addTempCommands(DrawingCommands command) {
		tempCommands.add(command);
	}
	
	/**
	 * Clear all entries in the current temporary commands invoker in paint panel.
	 */
	public void clearTempCommands() {
		this.tempCommands.clear();
	}
	
	/**
	 * Clear all entries in the current commands invoker in paint panel.
	 */
	public void clearCommands() {
		this.commands.clear();
	}
	
	/**
	 * Return the current graphics context of paint panel.
	 * @return g
	 */
	public GraphicsContext getG() {
		return g;
	}
	
	/**
	 * Removes latest drawing command from commands invoker and adds it to 
	 * undo commands invoker in paint panel.
	 */
	public void addtoUndoCommands() {
		if(! commands.getCommands().empty()) {
			this.undoCommands.add(this.commands.remove());
		}
	}
	
	/**
	 * Removes latest undo command from undoCommands invoker and re-adds it
	 * to the commands invoker in paint panel.
	 */
	public void redoCommands() {
		if(! undoCommands.getCommands().empty()) {
			this.commands.add(this.undoCommands.remove());
		}
	}
	
	/**
	 * Handle the current mouse event on the paint panel and assigns the 
	 * appropriate ShapeStrategy to deal with the event.
	 * @param MouseEvent event
	 */
	@Override
	public void handle(MouseEvent event) {
		if (this.ShapeStrategy == null) {
			return;
		} else {
			this.ShapeStrategy.handleEvent(event);
		}	
	}
}