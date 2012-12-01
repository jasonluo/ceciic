package org.ceciic.jsf;

import java.io.Serializable;
import java.util.List;

import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;


public class Tweets implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List <Tweet> ceciicTweets;
	private List <Tweet> alumniTweets;
	private SearchResults trendTweets;
	private int currentPage = 1;

	
	public List<Tweet> getCeciicTweets() {
		return ceciicTweets;
	}

	public void setCeciicTweets(List<Tweet> ceciicTweets) {
		this.ceciicTweets = ceciicTweets;
	}

	public List<Tweet> getAlumniTweets() {
		return alumniTweets;
	}

	public void setAlumniTweets(List<Tweet> alumniTweets) {
		this.alumniTweets = alumniTweets;
	}

	public SearchResults getTrendTweets() {
		return trendTweets;
	}

	public void setTrendTweets(SearchResults trendTweets) {
		this.trendTweets = trendTweets;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
}
