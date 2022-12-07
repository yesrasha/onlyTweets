package view;

import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Post;

public class PostItemView {
	private Post post;
	private VBox root;
	private Text usernameField, postField, timeStamp;
	private Separator sep;
	Accordion accordion;

	public PostItemView(Post post) {
		this.post = post;

		usernameField = new Text(post.getOwnerAccount().getUsername());
		usernameField.setStyle("-fx-font-size:20px;-fx-fill:white;-fx-cursor:hand;-fx-font-weight:bold;");
		accordion = new Accordion();
		TitledPane replies = new TitledPane("Replies", new Label("Replies"));
		replies.setStyle("-fx-fill:black;-fx-cursor:hand;-fx-font-weight:bold;");
		accordion.getPanes().add(replies);

		postField = new Text(post.getText());
		postField.setStyle("-fx-fill:white;");
		timeStamp = new Text(post.getCreationDate());
		timeStamp.setStyle("-fx-fill:white;");
		sep = new Separator();
		root = new VBox();
		root.setStyle("-fx-background-color:black;");
		root.setPadding(new Insets(10));
		root.setSpacing(3);
		root.getChildren().addAll(usernameField, postField, timeStamp, accordion, sep);
	}

	public VBox getRoot() {
		return root;
	}

}
