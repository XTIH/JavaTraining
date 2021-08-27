package com.bookstorewebapp.BookstoreWebApplication.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="book")
public class Book 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="isbn", precision=4)
	private int isbn;
	
	@Column(name="title", length=100)
	private String title;
	
	@Column(name="year", precision=4)
	private int year;
	
	@Column(name="price", precision=4)
	private int price;
	
	@OneToMany(mappedBy="book",cascade=CascadeType.ALL)
	private List<BookAuthorMapping> bam;
	
	public Book() {}
	
	public Book(String title, int year, int price) 
	{
		super();
		this.title = title;
		this.year = year;
		this.price = price;
	}

	public int getIsbn() 
	{
		return isbn;
	}
	
	public void setIsbn(int isbn) 
	{
		this.isbn = isbn;
	}
	
	public String getTitle() 
	{
		return title;
	}
	
	public void setTitle(String title) 
	{
		this.title = title;
	}

	public int getYear() 
	{
		return year;
	}
	
	public void setYear(int year) 
	{
		this.year = year;
	}

	public int getPrice() 
	{
		return price;
	}

	public void setPrice(int price) 
	{
		this.price = price;
	}
}
