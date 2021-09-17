package paint;

import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;

/**
 * DesignChooserPanel class that contains the buttons of the shape modifiers for the view.
 * It contains the view and a paintpanel, and four buttons: the shape color, fill shape, 
 * canvas color, and line width. Each button handles its own events and adds the appropriate values
 * to the paintpanel that is part of the view.
 * @author Jessica (ander710), Danica (navaret3) & Alexandre (gagneal1)
 */
public class DesignChooserPanel extends GridPane implements EventHandler<ActionEvent> {

	private View view; // So we can talk to our parent or other components of the view
	private PaintPanel panel;
	
	/**
	 * Construct a new DesignChooserPanel with the given view and adds all the shape modifier
	 * buttons to the right of the canvas.
	 * @param view
	 */
	public DesignChooserPanel(View view) {

		this.view = view;
		this.panel = view.getPaintPanel();
		
		// Combobox buttons for shape color, canvas color, and line thickness
		ComboBox<String> colorPanel = new ComboBox<String>();
		ComboBox<String> backgroundcolorPanel = new ComboBox<String>();
		ComboBox<String> thicknessPanel = new ComboBox<String>();
		
		// Color List containing strings of the names and the web codes & hashmap to connect the two for corresponding codes and colors.
		String[] colorlist = {"WHITE", "CRIMSON", "DARK ORANGE", "GOLD", "FOREST GREEN", "DEEP SKY BLUE", "DARK VIOLET", "DEEP PINK", "BLACK"};
		String[] codelist = {"#FFFFFF", "#DC143C", "#FF8C00", "#FFD700", "#228B22", "#00BFFF", "#9400D3", "#FF1493", "#000000"};
		HashMap<String, String> colorCodes = new HashMap<String, String>();
		
		for(int i=0; i<codelist.length; i++)  {
			colorCodes.put(colorlist[i], codelist[i]);
			colorPanel.getItems().add(colorlist[i]);
			backgroundcolorPanel.getItems().add(colorlist[i]);
		}
		
		// Thickness List
		for(int i=1; i<10; i++) {
			thicknessPanel.getItems().add(Integer.toString(i));
		}
		
		// Background color Panel Title
		Label backgroundcolorTitle = new Label("Canvas color :");
		this.add(backgroundcolorTitle, 0, 0);
		
		// Background color Panel
	 	String defaultBackgroundColor = panel.getBackgroundColor();
		backgroundcolorPanel.getSelectionModel().select(defaultBackgroundColor); 
		backgroundcolorPanel.setMaxSize(150, 25);
		this.add(backgroundcolorPanel, 0, 1);
		
		// Background color EventHandler
		backgroundcolorPanel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				String color = backgroundcolorPanel.getValue().toString();
				view.getPaintPanel().setBackgroundColor(colorCodes.get(color));
				System.out.println("Canvas color: " + color);
			}});
		
		// Color Panel Title
		Label colorTitle = new Label("Shape color:");
		this.add(colorTitle, 0, 2);
		
		// Color Panel
		colorPanel.getSelectionModel().select("BLACK"); 
		colorPanel.setMaxSize(150, 25);
		this.add(colorPanel, 0, 3);
		
		// Color Panel EventHandler
		colorPanel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				String color = colorPanel.getValue().toString();
				panel.addCommands(new ShapeColourCommand(panel.getFill(), colorCodes.get(color), panel));
				System.out.println("Color: " + color);	
			}});
		
		// Thickness Panel Title
		Label thicknessTitle = new Label("Line Thickness:");
		thicknessTitle.setMinSize(100, 25);
		//thicknessTitle.setMaxSize(100, 25);
		this.add(thicknessTitle, 0, 4);
		
		// Thickness Panel
		thicknessPanel.getSelectionModel().select(0); 
		thicknessPanel.setMinSize(60, 25);
		this.add(thicknessPanel, 0, 5);
		
		// Thickness Panel EventHandler
		thicknessPanel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				int thickness = Integer.parseInt(thicknessPanel.getValue().toString());
				panel.addCommands(new LineWidthCommand(thickness, panel));
				System.out.println("Thickness: " + thickness);		
			}});
		
		// Fill Panel
		CheckBox fillPanel = new CheckBox("Fill Shape");
		fillPanel.setMinSize(100,  50);
		this.add(fillPanel, 0, 7);
		
		// Fill Panel EventHandler
		fillPanel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				boolean Fill = fillPanel.isSelected();
				String color = colorPanel.getValue().toString();
				panel.addCommands(new ShapeColourCommand(Fill, colorCodes.get(color), panel));
				System.out.println("Fill: " + Fill);
			}});
		}

	@Override
	public void handle(ActionEvent event) {}
}
	





