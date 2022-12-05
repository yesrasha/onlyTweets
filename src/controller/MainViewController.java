package controller;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.FeedObserver;
import model.Observer;
import model.Post;
import view.PostItemView;


public class MainViewController implements Initializable,FeedObserver {

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
    public void handleFollowersClick(MouseEvent event) {

    }

    @FXML
    public void handleHomeClick(MouseEvent event) {
    	splitPane.getItems().set(1,home);
    	
    }

    @FXML
    public void handleProfileClick(MouseEvent event) {
    	loadPage("ProfileScreen");
    }
    
    public void loadPage(String page) {
    	
    	Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("../view/" + page +".fxml"));
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
		postBtn.setOnAction( e ->{
			Post post = new Post(textArea.getText(),Main.bag.getLoggedInUser());
			Main.bag.getLoggedInUser().addPost(post);
			textArea.clear();
			
		});
	}

	@Override
	public void update(Set<Post> newFeed) {
		Node[] postItems = new Node[newFeed.size()];
		int i = 0;
		for(Post post : newFeed) {
			postItems[i++]=(new PostItemView(post).getRoot());
		}
		postContainer.getChildren().setAll(postItems);
	}

	
    

}
