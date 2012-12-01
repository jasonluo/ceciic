package org.ceciic.jsf;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.ceciic.mongo.Blog;

@ManagedBean(name="blogListBean")
@RequestScoped()
public class Blogs {
	
	List <Blog> allBlogs;

	public List<Blog> getAllBlogs() {
		return allBlogs;
	}

	public void setAllBlogs(List<Blog> allBlogs) {
		this.allBlogs = allBlogs;
	}

}
