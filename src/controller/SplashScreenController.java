package controller;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class SplashScreenController {

    @FXML
    private Button loginBtn;

    @FXML
    private Button registerBtn;

    @FXML
    void handleLoginClick(ActionEvent event) {
    	Parent loginView = loadResource("../view/LoginScreen.fxml");
    	Main.stage.setTitle("Login");
		Scene scene = new Scene(loginView);
		scene.getStylesheets().add(getClass().getResource("../styles/application.css").toExternalForm());
		Main.stage.setScene(scene);
		Main.stage.show();
    }

    @FXML
    void handleRegisterClick(ActionEvent event) {
    	Parent registerView = loadResource("../view/RegisterScreen.fxml");
    	Main.stage.setTitle("Register");
		Scene scene = new Scene(registerView);
		scene.getStylesheets().add(getClass().getResource("../styles/application.css").toExternalForm());
		Main.stage.setScene(scene);
		Main.stage.show();
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
