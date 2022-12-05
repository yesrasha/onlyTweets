package view;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Post;

public class PostItemView {
	private Post post;
	private VBox root;
	private Text usernameField,postField,timeStamp;
	
	public PostItemView(Post post) {
		this.post = post;
		
		usernameField = new Text(post.getOwnerAccount().getUsername());
		postField = new Text(post.getText());
		timeStamp = new Text(post.getCreationDate());
		root = new VBox();
		root.getChildren().addAll(usernameField,postField,timeStamp);
	}
	
	public VBox getRoot() {
		return root;
	}
	
}
