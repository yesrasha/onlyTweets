package model;

import java.util.List;

public interface FeedSubject {
	

	public void registerObserver(FeedObserver o);

	public void removeObserver(FeedObserver o);

	public void notifyObservers();		
	

}
