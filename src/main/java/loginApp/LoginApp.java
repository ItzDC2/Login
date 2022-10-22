package loginApp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import login.LoginController;
import login.LoginView;

public class LoginApp extends Application {

	private LoginController controller = new LoginController();
	
	public static Stage primaryStage; //Para obtener la referencia del stage
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		LoginApp.primaryStage = primaryStage;
		primaryStage.setTitle("Login");
		primaryStage.setScene(new Scene(controller.getView(), 250, 200));
		primaryStage.show();
	
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
