package paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class View implements EventHandler<ActionEvent> {


	private PaintPanel paintPanel;
	private ShapeChooserPanel shapeChooserPanel;
	private DesignChooserPanel designChooserPanel;


	public View(Stage stage) {

		initUI(stage);
	}

	private void initUI(Stage stage) {

		this.paintPanel = new PaintPanel(this);
		this.shapeChooserPanel = new ShapeChooserPanel(this);
		this.designChooserPanel = new DesignChooserPanel(this);
		

		BorderPane root = new BorderPane();
		root.setTop(createMenuBar());
		root.setCenter(this.paintPanel);
		root.setRight(this.designChooserPanel);
		root.setLeft(this.shapeChooserPanel);
	

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Paint");
		stage.show();
	}

	public PaintPanel getPaintPanel() {
		return paintPanel;
	}

	public ShapeChooserPanel getShapeChooserPanel() {
		return shapeChooserPanel;
	}
	
	public DesignChooserPanel getDesignChooserPanel() {
		return designChooserPanel;
	}

	private MenuBar createMenuBar() {

		MenuBar menuBar = new MenuBar();
		Menu menu;
		MenuItem menuItem;

		// A menu for File

		menu = new Menu("File");

		menuItem = new MenuItem("New");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Open");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Save");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menu.getItems().add(new SeparatorMenuItem());

		menuItem = new MenuItem("Exit");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuBar.getMenus().add(menu);

		// Another menu for Edit

		menu = new Menu("Edit");

		menuItem = new MenuItem("Cut");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Copy");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Paste");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menu.getItems().add(new SeparatorMenuItem());

		menuItem = new MenuItem("Undo");
		menuItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				paintPanel.addtoUndoCommands();
				paintPanel.repaint();
			}
		});
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Redo");
		menuItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				paintPanel.redoCommands();
				paintPanel.repaint();
			}
		});
		menu.getItems().add(menuItem);
		
		menuItem = new MenuItem("Clear");
		menuItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent a) {
				paintPanel.clearCommands();
				paintPanel.repaint();
			}
		});
		menu.getItems().add(menuItem);
		
		menuBar.getMenus().add(menu);

		return menuBar;
	}

	@Override
	public void handle(ActionEvent event) {
		System.out.println(((MenuItem)event.getSource()).getText());
	}
}
