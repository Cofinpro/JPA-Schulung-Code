package de.cofinpro.training.jpa.model;

import java.util.Date;

/**
 * @author gtudan
 *
 */
public class Book {
	private String title;
	private String author;
	private int pages;
	private double price;
	private Date published;
	
	
	public Book(String title) {
		super();
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public int getPages() {
		return pages;
	}
	
	public void setPages(int pages) {
		this.pages = pages;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getPublished() {
		return published;
	}

	public void setPublished(Date published) {
		this.published = published;
	}
}
