package com.bookstorewebapp.BookstoreWebApplication.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="book_author_map")
public class BookAuthorMapping 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="book_isbn", nullable=false)
	private Book book;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="author_id", nullable=false)
	private Author author;
	
	public BookAuthorMapping() {}
	
	public BookAuthorMapping(Book book, Author author)
	{
		this.book = book;
		this.author = author;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public Book getBook() 
	{
		return book;
	}

	public void setBook(Book book) 
	{
		this.book = book;
	}

	public Author getAuthor() 
	{
		return author;
	}

	public void setAuthor(Author author) 
	{
		this.author = author;
	}
}
