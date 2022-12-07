package view;

import controller.MainViewController;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import model.User;

public class UserItemView {

	private User user;

	private HBox root;
	private Button followBtn;
	private Label usernameLabel;

	public UserItemView(User user, User loggedIn, MainViewController mainViewController) {
		this.user = user;
		usernameLabel = new Label(user.getUsername());
		usernameLabel.setStyle("-fx-text-fill:white;");
		boolean follows = user.getFollowers().contains(loggedIn);
		followBtn = new Button(follows ? "unfollow" : "follow");
		followBtn.setOnAction(e -> {
			if (!follows) {
				loggedIn.followUser(user);
			}
			else user.removeObserver(loggedIn);
			
			mainViewController.update();
			
			
		});

		root = new HBox();
		root.getChildren().addAll(usernameLabel, followBtn);
		root.setSpacing(5);

	}

	public HBox getRoot() {
		return this.root;
	}
}
