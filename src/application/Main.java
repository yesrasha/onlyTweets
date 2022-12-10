package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Dictionary;
import model.UserBag;
import utils.BackUpRestoreTools;

public class Main extends Application {
	

	public static UserBag bag = BackUpRestoreTools.restoreUserBag();
	public static Stage stage;
	public static Dictionary dict = new Dictionary();
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		stage = primaryStage;
		
		Parent root = FXMLLoader.load(getClass().getResource("../view/SplashScreen.fxml"));
		primaryStage.setTitle("Login");
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("../styles/application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	@Override
	public void stop() throws Exception {
		bag.getLoggedInUser().clearUIObservers();
		
		BackUpRestoreTools.backupUserBag(bag);
		super.stop();
	}
	 public Parent loadResource(String resourcePath) {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(resourcePath));
			Parent view;
			try {
				view = (Parent) loader.load();
				return view;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
	
	
}


