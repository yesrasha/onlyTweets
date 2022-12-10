package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import javafx.scene.Node;

public class Post implements Serializable,Comparable<Post>, Subject {

	private String text;
	private User ownerAccount;
	private String date;
	private String ownerUsername;
	private Date dateObj;
	private LinkedList<Post> replies;
	private List<Observer> observers;

	public Post(String text, User ownerAccount) {
		this.text = text;
		this.ownerAccount = ownerAccount;
		this.dateObj = new Date();
		this.date = dateObj.toString();
		this.replies = new LinkedList<>();
		this.observers = new ArrayList<>();
	}
	
	public void addReply(Post post) {
		replies.add(post);
		notifyObservers(this);
	}
	
	public Date getDateObj() {
		return dateObj;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String getCreationDate() {
		return date;
	}
	
	public String getOwnerUsername() {
		return ownerUsername;
	}
	
	public User getOwnerAccount() {
		return ownerAccount;
	}
	
	public static void main(String[] args) {
		
		Date date = new Date();
		String dateString = date.toString();
		
		TreeSet<Post> set = new TreeSet<>();
		User u = new User("user","passr");
		
		Post p1 = new Post("p1",u);
		Post p2 = new Post("p2",u);
		Post p3 = new Post("p3",u);
		
		set.add(p3);
		set.add(p2);
		set.add(p1);
		
		System.out.println(set);
		
		
	}

	@Override
	public int compareTo(Post o) {
		int comparison = o.getDateObj().compareTo(dateObj);
		if(comparison == 0) return -1;
		return comparison;
		
	}

	@Override
	public String toString() {
		return "Post [text=" + text +  ", date=" + date + ", ownerUsername="
				+ ownerUsername + ", dateObj=" + dateObj + "]";
	}

	@Override
	public void registerObserver(Observer o) {
		this.observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		this.observers.remove(o);
	}

	@Override
	public void notifyObservers(Post newPost) {
		for(Observer ob: observers) {
			ob.update(newPost);
		}
	}
	
	public void clearSubscribers() {
		observers.clear();
	}

	public LinkedList<Post> getReplies() {
		// TODO Auto-generated method stub
		return this.replies;
	}

	
	

}
