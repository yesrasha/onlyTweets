package controller;

import java.io.IOException;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.FeedObserver;
import model.Observer;
import model.Post;
import model.User;
import model.UserBag;
import utils.BackUpRestoreTools;
import view.PostItemView;
import view.UserItemView;

public class MainViewController implements Initializable, FeedObserver {

	@FXML
	private VBox home;

	@FXML
	private TextArea textArea;

	@FXML
	private Button homeBtn;

	@FXML
	private SplitPane splitPane;

	@FXML
	private Font x1;

	@FXML
	private Color x2;

	@FXML
	private Font x3;

	@FXML
	private Color x4;

	@FXML
	private Button postBtn;

	@FXML
	private VBox postContainer;

	@FXML
	private VBox usersVBox;

	@FXML
	public void handleFollowersClick(MouseEvent event) {

	}

	@FXML
	public void handleHomeClick(MouseEvent event) {
		splitPane.getItems().set(1, home);

	}

	@FXML
	public void handleProfileClick(MouseEvent event) {
		loadPage("ProfileScreen");
	}

	public void loadPage(String page) {

		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("../view/" + page + ".fxml"));
			splitPane.getItems().set(1, root);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		textArea.setStyle("-fx-font-fill:black;");
		// lets call initial update
		update(Main.bag.getLoggedInUser().getFeed());

		Main.bag.getLoggedInUser().registerObserver(this);
		update();
		postBtn.setOnAction(e -> {
			String text = textArea.getText();
			if (text.isBlank())
				return;

			List<String> misspelled = Main.dict.getWrongWords(text.split(" "));
			System.out.println(misspelled);
			if (misspelled.size() > 0) {
				// OPEN MODAL pass list of words
				try {
					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(getClass().getResource("../view/SpellCheckDialog.fxml"));
					DialogPane dialogPane = fxmlLoader.load();
					SpellCheckDialogController controller = fxmlLoader.getController();
					controller.setMisspelledWords(misspelled);

					Dialog<ButtonType> dialog = new Dialog<>();
					dialog.setDialogPane(dialogPane);
					dialog.setTitle("Misspelled Button");

					Optional<ButtonType> clickedButton = dialog.showAndWait();

					if (clickedButton.get() == ButtonType.OK) {
						Post post = new Post(text, Main.bag.getLoggedInUser());
						Main.bag.getLoggedInUser().addPost(post);
						textArea.clear();
					}

				}

				catch (Exception ed) {
					ed.printStackTrace();
				}
				return;
			}

			Post post = new Post(text, Main.bag.getLoggedInUser());
			Main.bag.getLoggedInUser().addPost(post);
			textArea.clear();

		});
	}

	@Override
	public void update(Set<Post> newFeed) {
		Node[] postItems = new Node[newFeed.size()];
		int i = 0;
		for (Post post : newFeed) {
			postItems[i++] = (new PostItemView(post).getRoot());
		}
		postContainer.getChildren().setAll(postItems);

	}

	public void update() {
		UserBag bag = Main.bag;
		Node[] userItems = new Node[bag.size()];
		int i = 0;
		for (User user : bag) {
			userItems[i++] = new UserItemView(user, Main.bag.getLoggedInUser(), this).getRoot();
		}
		usersVBox.getChildren().setAll(userItems);
	}

	@FXML
	public void logout(ActionEvent event) {
		Main.bag.getLoggedInUser().clearUIObservers();
		BackUpRestoreTools.backupUserBag(Main.bag);
		
		//move to splash screen
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("../view/SplashScreen.fxml"));
			Main.stage.setTitle("Login");
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../styles/application.css").toExternalForm());
			Main.stage.setScene(scene);
			Main.stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
