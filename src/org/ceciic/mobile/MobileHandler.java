package org.ceciic.mobile;

import java.io.IOException;
import java.util.Date;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ceciic.jsf.Blogs;
import org.ceciic.jsf.Tweets;
import org.ceciic.mongo.Blog;
import org.ceciic.mongo.BlogService;
import org.ceciic.twitter.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.oauth1.OAuth1Operations;
import org.springframework.social.oauth1.OAuth1Parameters;
import org.springframework.social.oauth1.OAuthToken;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

public class MobileHandler {
	private static final Log log = LogFactory.getLog(MobileHandler.class);
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private TweetService tweetService;
			
	ConnectionFactoryRegistry connectionFactoryRegistry;
	
	public String postAnnouncement() {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ValueBinding binding = application.createValueBinding("#{blogBean}");
        Blog blog = (Blog)binding.getValue(facesContext);
        blog.setPostDate(new Date(System.currentTimeMillis()));
        blog.setSeverity(5);
        this.blogService.saveBlog(blog);
        
        this.tweetService.postTweet(blog.getText());
        
        log.debug("==="+blog.getText());
        log.debug("author==="+blog.getAuthor());
        
        return "success";
	}
	
	public String blog() {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ValueBinding binding = application.createValueBinding("#{blogBean}");
        Blog blog = (Blog)binding.getValue(facesContext);
        blog.setPostDate(new Date(System.currentTimeMillis()));
        
        this.blogService.saveBlog(blog);
        
        this.tweetService.postTweet(blog.getText());
        
        return "success";
	}
	
	public void initPage() {
		//setup ceciic blogs
		FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ValueBinding binding = application.createValueBinding("#{blogListBean}");
        Blogs blogList = (Blogs)binding.getValue(facesContext);
        blogList.setAllBlogs(blogService.findAllBlogs());
        log.debug("==="+blogList.getAllBlogs());
        
        //setup ceciic tweets
        ValueBinding binding2 = application.createValueBinding("#{tweetsBean}");
        Tweets tweets = (Tweets)binding2.getValue(facesContext);
        tweets.setCeciicTweets(this.tweetService.findCeciicTweets());
        tweets.setAlumniTweets(this.tweetService.findAlumniTweets());
        tweets.setTrendTweets(this.tweetService.findTrendTweets(tweets));
	}
	
	public String deleteAllBlogs() {
		this.blogService.dropAll();
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
        Application application = facesContext.getApplication();
        ValueBinding binding = application.createValueBinding("#{blogListBean}");
        Blogs blogList = (Blogs)binding.getValue(facesContext);
        blogList.setAllBlogs(null);
        
        return "success";
	}
	
	public void setUpAuthenticationFailureMessage() {
		//setup ceciic blogs
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		HttpServletRequest request = (HttpServletRequest)facesContext.getExternalContext().getRequest();
		
		
        String authenticationFailure = (String) request.getParameter("authenticationFailure");
         
        if(authenticationFailure!=null && authenticationFailure.equalsIgnoreCase("true")){
        	FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Invalid user name and/or passord!");
        	facesContext.addMessage("j_login", facesMessage);
        }
      
	}
	
	//This method is for future reference to Oauth dance to acquire token. should be in a filter
	public String postTweetXXX(){
		TwitterConnectionFactory connectionFactory = (TwitterConnectionFactory)connectionFactoryRegistry.getConnectionFactory(Twitter.class);
		//this is the first half to send authorization request
		OAuth1Operations oauthOperations = connectionFactory.getOAuthOperations();
		OAuthToken requestToken = oauthOperations.fetchRequestToken("http://ceciic.cloudfoundry.com/twitter", null);
		String authorizeUrl = oauthOperations.buildAuthorizeUrl(requestToken.getValue(), OAuth1Parameters.NONE);
		System.out.println("==="+authorizeUrl);
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(authorizeUrl);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//need second part to retrieve authorization token

		return "success";
	}

	public ConnectionFactoryRegistry getConnectionFactoryRegistry() {
		return connectionFactoryRegistry;
	}

	public void setConnectionFactoryRegistry(
			ConnectionFactoryRegistry connectionFactoryRegistry) {
		this.connectionFactoryRegistry = connectionFactoryRegistry;
	}
	
	
}
