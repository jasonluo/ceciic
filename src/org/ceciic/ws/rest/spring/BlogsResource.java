package org.ceciic.ws.rest.spring;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ceciic.mongo.Blog;
import org.ceciic.mongo.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/blogs")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Component
@Scope("session")
public class BlogsResource {
	private static final Log log = LogFactory.getLog(BlogsResource.class);
	@Context
    UriInfo uriInfo;

    @Autowired
    private BlogService blogService;
    
    @GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Blog> getContacts() {
    	System.out.println("called blogService...");
		return blogService.findAllBlogs();
	}
    
    @GET
    @Path("{author}")
    public List <Blog> getBlogsByAuthor(@PathParam("author") String author) {
    	log.error("author==="+author);
    	return blogService.findAllBlogs();
    }
    
    @GET
    @Path("delete/{blogId}")
    @Produces({MediaType.TEXT_PLAIN})
    public String deleteBlog(@PathParam("blogId") String blogId) {
    	log.error("blogId==="+blogId);
    	Blog blog = blogService.findOne(blogId);
    	log.error("found blog==="+blog);
    	if(blog != null){
    		blogService.delete(blogId);
    		return "Success";
    	}
    	else{
    		return "Cannot find blog requested.";
    	}
    }
}
