package paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * ShapeChooserPanel class that contains the buttons of the shape types for the view.
 * It contains the view and a paintpanel, and seven buttons corresponding to seven shapes
 * defined in a shapeLabel array. Each button handles assigns the corresponding strategy to the
 * Paint panel.
 */
public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {

	private View view; // So we can talk to our parent or other components of the view
	
	/**
	 * Constructs a new ShapeChooserPanel with the given view. It adds all the different
	 * shape buttons to the left of the canvas.
	 * @param view
	 */
	public ShapeChooserPanel(View view) {

		this.view = view;
		String[] shapeLabel = { "Oval", "Circle", "Rectangle", "Square", "Squiggle", "Line", "Polyline"};
	
		// Shape Panel
		int row = 3;
		ToggleGroup shapePanel = new ToggleGroup();
		for (String label : shapeLabel) { 
			ToggleButton shapeButton = new ToggleButton(label);
			Image icon = new Image("file:src/paint/icons/" + label + ".png");
			shapeButton.setGraphic(new ImageView(icon));
			shapeButton.setMaxSize(50, 50);
			shapeButton.setMinSize(50, 50);
			shapeButton.setOnAction(this);
			shapeButton.setToggleGroup(shapePanel);
			this.add(shapeButton, 0, row);
			row++;
		}
	}
	/**
	 * Handles the click events of the button and assigns the appropriate ShapeStrategy to the
	 * paint panel.
	 */
	@Override
	public void handle(ActionEvent event) {
		
		StrategyFactory ShapeStrategyFactory = new StrategyFactory(this.view.getPaintPanel());
		ToggleButton selectedButton = (ToggleButton) event.getSource();
		String selectedShape = (selectedButton.getText());
			
		if (selectedButton.isSelected()) {
			Strategy shapeStrategy = ShapeStrategyFactory.getShapeStrategy(selectedShape);
			this.view.getPaintPanel().setShapeStrategy(shapeStrategy);
			System.out.println("Selected: " + selectedShape);
		} else {
			this.view.getPaintPanel().setShapeStrategy(null);
			System.out.println("Unselected: " + selectedShape);
		}	
	}
}
	




