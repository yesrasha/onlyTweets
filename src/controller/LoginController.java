package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Button signInBtn;

    @FXML
    private TextField usernameTextField;
    
    @FXML
    private Label signUpBtn;
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		signInBtn.setOnAction(e ->{
			String username = usernameTextField.getText(),
				   password = passwordTextField.getText();
			
			boolean success = Main.bag.login(username, password);
			
			if(success) {
				Parent registerView = loadResource("../view/MainView.fxml");
		    	Main.stage.setTitle("Home");
				Scene scene = new Scene(registerView);
				scene.getStylesheets().add(getClass().getResource("../styles/application.css").toExternalForm());
				Main.stage.setScene(scene);
				Main.stage.show();
			}
		});
		
		signUpBtn.setOnMouseClicked( e ->{
			Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("../view/RegisterScreen.fxml"));
				Main.stage.setTitle("Register");
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("../styles/application.css").toExternalForm());
				Main.stage.setScene(scene);
				Main.stage.show();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		
		
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
