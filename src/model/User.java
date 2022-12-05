package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class User implements Serializable,Subject,Observer,FeedSubject{

	private String username, password;
	private List<User> subscriptions;
	private List<Observer> followers;
	private LinkedList<Post> posts;
	private Set<Post> feed;
	private List<FeedObserver> feedObservers;
	

	public User(String username, String password) {
		this.username = username.toLowerCase();
		this.password = password;
		this.subscriptions = new LinkedList<>();
		this.posts = new LinkedList<>();
		this.feed = new TreeSet<>();
		this.followers = new LinkedList<>();
		this.followers.add(this);
		feedObservers = new LinkedList<>();
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<User> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<User> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public LinkedList<Post> getPosts() {
		return posts;
	}

	public void setPosts(LinkedList<Post> posts) {
		this.posts = posts;
	}
	
	public Set<Post> getFeed(){
		return this.feed;
	}

	@Override
	public void update(Post newPost) {
		// one of the people we follow just made new post lets add it to our feed
		// this post was just made so its the latest post in our feed
		feed.add(newPost);
		
		//notify UI subscriber that the feed changed!
		notifyObservers();
		
	}
	
	public void addPost(Post newPost) {
		System.out.println("adding post...");
		this.posts.addFirst(newPost);
		notifyObservers(newPost);
	}

	@Override
	public void registerObserver(Observer o) {
		// bascially follow me
		followers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		followers.remove(o);
	}
	
	public void followUser(User user) {
		// follow this other user
		user.registerObserver(this);
		
		for(Post post : user.getFeed()) {
			this.feed.add(post);
		}
		
		// feed has changed
		notifyObservers();
	}

	@Override
	public void notifyObservers(Post newPost) {
		// lets notify all my followers that I just made a new post
		System.out.println("notifying followers...");
				for(Observer follower : followers) {
					follower.update(newPost);
				}
	}

	@Override
	public void registerObserver(FeedObserver o) {
		feedObservers.add(o);
	}

	@Override
	public void removeObserver(FeedObserver o) {
		feedObservers.remove(o);
		
	}

	@Override
	public void notifyObservers() {
		
		for(FeedObserver ui : feedObservers) {
			ui.update(this.feed);
		}
	}
	
	public void clearUIObservers(){
		this.feedObservers.clear();
	}

	

}
