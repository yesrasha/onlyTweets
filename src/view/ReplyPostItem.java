package view;

import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Post;

public class ReplyPostItem {
	private Post post;
	private VBox root;
	private Text usernameField, postField, timeStamp;
	private Separator sep;

	public ReplyPostItem(Post post) {
		this.post = post;

		usernameField = new Text(post.getOwnerAccount().getUsername());
		usernameField.setStyle("-fx-font-size:20px;-fx-fill:white;-fx-cursor:hand;-fx-font-weight:bold;");


		postField = new Text(post.getText());
		postField.setStyle("-fx-fill:white;");
		timeStamp = new Text(post.getCreationDate());
		timeStamp.setStyle("-fx-fill:white;");
		sep = new Separator();
		root = new VBox();
		root.setStyle("-fx-background-color:black;");
		root.setPadding(new Insets(10));
		root.setSpacing(3);
		root.getChildren().addAll(usernameField, postField, timeStamp, sep);
	}

	public VBox getRoot() {
		return root;
	}

}
