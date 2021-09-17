package paint;

import javafx.application.Application;
import javafx.stage.Stage;

public class Paint extends Application {

	View view; // View + Controller

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		this.view = new View(stage);
	}
}
