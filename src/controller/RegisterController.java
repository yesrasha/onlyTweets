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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import utils.BackUpRestoreTools;

public class RegisterController implements Initializable {
	@FXML
    private PasswordField passwordTextField;
	
	@FXML
	private PasswordField verifyPasswordTextField;
	
    @FXML
    private Button signUpBtn;

    @FXML
    private TextField usernameTextField;
    
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		signUpBtn.setOnAction(e ->{
			String username = usernameTextField.getText(),
				   password = passwordTextField.getText(),
				   verifyPassword = verifyPasswordTextField.getText();
			
			if(Main.bag.isUsernameTaken(username)) {
				System.out.println("Username is taken !");
				return;
			}
			
			if(!password.equals(verifyPassword)) {
				System.out.println("Passwords must match");
				return;
			}
			
			boolean success = Main.bag.addUser(username, password);
			
			BackUpRestoreTools.backupUserBag(Main.bag);
			
			if(success) {
				Parent loginView = loadResource("../view/LoginScreen.fxml");
		    	Main.stage.setTitle("Login");
				Scene scene = new Scene(loginView);
				scene.getStylesheets().add(getClass().getResource("../styles/application.css").toExternalForm());
				Main.stage.setScene(scene);
				Main.stage.show();
			}
			

		});
	}

}
