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
@Table(name="author")
public class Author 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", precision=4)
	private int id;
	
	@Column(name="name", length=30)
	private String name;
	
	@OneToMany(mappedBy="author",cascade=CascadeType.ALL)
	private List<BookAuthorMapping> bam;
		
	public Author() {};
	
	public Author(String name)
	{
		super();
		this.name = name;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}
}
