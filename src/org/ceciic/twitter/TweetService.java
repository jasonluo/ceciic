package org.ceciic.twitter;

import java.io.*;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ceciic.jsf.Tweets;
import org.springframework.social.twitter.api.DirectMessage;
import org.springframework.social.twitter.api.SearchOperations;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("tweetService")
@Transactional
public class TweetService {
	private String consumerKey = "TNpleMsYW7OeMxIGQmC1eQ"; // The application's consumer key
	private String consumerSecret = "IkFIrxeO2o3gk6qlRd71U0ANBuTQP57HbjSysFSrb5E"; // The application's consumer secret
	private String accessToken = "58879279-oDioYdXfAY4imZICeS3r7SHUpbZEs5iZosFNZDjBH"; // The access token granted after OAuth authorization
	private String accessTokenSecret = "PpAh57BeFj23pBfG1zxYI8rW5cB1dqLhofSnVqqFQ"; // The access token secret granted after OAuth authorization
	private Twitter twitter = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
	
	private static final Log log = LogFactory.getLog(TweetService.class);
			
	public void postTweet(String text){
		this.twitter.timelineOperations().updateStatus(text);
	}
	
	public List <Tweet> findCeciicTweets(){
		List <Tweet> ceciicTweets = twitter.timelineOperations().getUserTimeline();
		
		return ceciicTweets; 
	}
	
	public List <Tweet> findAlumniTweets(){
		List <Tweet> alumniTweets = twitter.timelineOperations().getHomeTimeline(); 
	
		return alumniTweets;
	}
	
	public SearchResults findTrendTweets(Tweets tweets){
		SearchOperations searchOperations  = twitter.searchOperations();
		
		SearchResults results = null;
		
		if(tweets.getTrendTweets() == null){
			 results = searchOperations.search("耶稣 OR 基督 OR 圣经 OR 圣灵 -基督徒余 -つ -い -て -ま -で", 1, 10);
		}
		else if(tweets.getCurrentPage() <=10){
			tweets.setCurrentPage(tweets.getCurrentPage()+1);
			results = searchOperations.search("耶稣 OR 基督 OR 圣经 OR 圣灵 -基督徒余 -つ -い -て -ま -で", tweets.getCurrentPage(), 10);
		}
		else{
			 tweets.setCurrentPage(1);
			 results = searchOperations.search("耶稣 OR 基督 OR 圣经 OR 圣灵 -基督徒余 -つ -い -て -ま -で", 1, 10);
		}
		
		
		return results;
	}
	
	public static void mainCXX(String [] args){
		String consumerKey = "TNpleMsYW7OeMxIGQmC1eQ"; // The application's consumer key
		String consumerSecret = "IkFIrxeO2o3gk6qlRd71U0ANBuTQP57HbjSysFSrb5E"; // The application's consumer secret
		String accessToken = "58879279-oDioYdXfAY4imZICeS3r7SHUpbZEs5iZosFNZDjBH"; // The access token granted after OAuth authorization
		String accessTokenSecret = "PpAh57BeFj23pBfG1zxYI8rW5cB1dqLhofSnVqqFQ"; // The access token secret granted after OAuth authorization The access token secret granted after OAuth authorization
		Twitter twitter = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
		
		String s ="耶稣基督";
		
		SearchOperations searchOperations  = twitter.searchOperations();
		SearchResults results = searchOperations.search("from:ceciic");
		
		List <Tweet> l = results.getTweets();
		for (Tweet t : l){
			System.out.println(""+ t.getText()+ "   hello=="+t.getFromUser());
		}
		
		List <DirectMessage> l2  = twitter.directMessageOperations().getDirectMessagesReceived();
		
		for (DirectMessage d : l2){
			System.out.println("--"+ d.getText()+ "   hello=="+d.getSender());
		}
		
		l = twitter.timelineOperations().getHomeTimeline();
		for (Tweet t : l){
			System.out.println(""+ t.getText()+ "   hello=="+t.getFromUser());
		}
		System.out.println("=======================================");
		l=twitter.timelineOperations().getUserTimeline();
		for (Tweet t : l){
			System.out.println(""+ t.getText()+ "   hello=="+t.getFromUser());
		}
	}
	
	public static void main(String [] args){
		
		String infile="C:\\Cloud Foundry\\GB-today.txt"; 
		String outfile="C:\\Cloud Foundry\\GB-today-8.txt"; 
		String from="GB2312"; 
		String to="UTF-8";
		 // set up byte streams
	    InputStream in;
	    try{
	    if (infile != null) in = new FileInputStream(infile);
	    else in = System.in;
	    OutputStream out;
	    if (outfile != null) out = new FileOutputStream(outfile);
	    else out = System.out;

	    // Use default encoding if no encoding is specified.
	    if (from == null) from = System.getProperty("file.encoding");
	    if (to == null) to = System.getProperty("file.encoding");

	    // Set up character stream
	    Reader r = new BufferedReader(new InputStreamReader(in, from));
	    Writer w = new BufferedWriter(new OutputStreamWriter(out, to));

	    // Copy characters from input to output.  The InputStreamReader
	    // converts from the input encoding to Unicode,, and the OutputStreamWriter
	    // converts from Unicode to the output encoding.  Characters that cannot be
	    // represented in the output encoding are output as '?'
	    char[] buffer = new char[4096];
	    int len;
	    while((len = r.read(buffer)) != -1) 
	      w.write(buffer, 0, len);
	    r.close();
	    w.flush();
	    w.close();
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }

	}

}
