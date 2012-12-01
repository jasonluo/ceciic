package org.ceciic.mongo;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

import org.springframework.data.annotation.Id;

@ManagedBean(name="blogBean")
@RequestScoped
@XmlRootElement(name="Blog")
@XmlAccessorType(XmlAccessType.FIELD) 
public class Blog {
	@Id
	private String id;
	private String author = "co-worker";
	private String text="";
	private Date postDate;
	
	//0 is least important, 5 is most important;
	private int severity = 1;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
