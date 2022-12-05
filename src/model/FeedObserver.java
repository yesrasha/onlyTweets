package model;

import java.util.List;
import java.util.Set;

public interface FeedObserver {
	
	public void update(Set<Post> feed);
}
