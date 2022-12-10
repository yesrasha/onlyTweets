package view;

import java.util.LinkedList;

import application.Main;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Observer;
import model.Post;

public class PostItemView implements Observer {
	private Post post;
	private VBox root;
	private Text usernameField, postField, timeStamp;
	private Separator sep;
	private Accordion accordion;
	private VBox repliesContainer;
	private TextField commentTextField;

	public PostItemView(Post post) {
		this.post = post;

		usernameField = new Text(post.getOwnerAccount().getUsername());
		usernameField.setStyle("-fx-font-size:20px;-fx-fill:white;-fx-cursor:hand;-fx-font-weight:bold;");
		accordion = new Accordion();
		repliesContainer = new VBox();
		repliesContainer.setPadding(new Insets(0, 0, 0, 20));
		repliesContainer.setStyle("-fx-background-color:black;");
		commentTextField = new TextField();
		commentTextField.setPromptText("Add comment...");
		repliesContainer.getChildren().add(commentTextField);
		TitledPane replies = new TitledPane("Replies", repliesContainer);
		replies.setStyle("-fx-fill:black;-fx-cursor:hand;-fx-font-weight:bold;");
		accordion.getPanes().add(replies);
		post.registerObserver(this);
		
		commentTextField.setOnKeyPressed(e ->{
			if(e.getCode() == KeyCode.ENTER) {
				post.addReply( new Post(commentTextField.getText(),Main.bag.getLoggedInUser()));
			}
		});

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
		update(post);
	}

	public VBox getRoot() {
		return root;
	}

	@Override
	public void update(Post newPost) {
		LinkedList<Post> replies = newPost.getReplies();
		Node[] replyPostItems = new Node[replies.size()];
		int i = 0;
		for(Post post: replies) {
			replyPostItems[i++] = new ReplyPostItem(post).getRoot();
		}
		repliesContainer.getChildren().setAll(replyPostItems);
		repliesContainer.getChildren().add(commentTextField);
		
	}

}
