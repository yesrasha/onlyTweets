package controller;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import model.User;

public class SpellCheckDialogController {
	
	private String postText;
	
	@FXML
	private TextArea mispelledTextArea;
	
	
	public void setMisspelledWords(List<String> s) {
		StringBuilder sb = new StringBuilder();
		for(String word: s) sb.append(word).append(" ");
		
		mispelledTextArea.setText(sb.toString());
	}

}
