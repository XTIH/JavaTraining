package com.bookstorewebapp.BookstoreWebApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstorewebapp.BookstoreWebApplication.exception.ResourceNotFoundException;
import com.bookstorewebapp.BookstoreWebApplication.model.Author;
import com.bookstorewebapp.BookstoreWebApplication.repository.AuthorRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/author")
public class AuthorController 
{
	@Autowired AuthorRepository ar;
	
	// API to fetch all books.
	@GetMapping("/author")
	public List<Author> getAllBooks()
	{
		return (List<Author>) ar.findAll();
	}
	
	// API to insert a new author
	@PostMapping("/addAuthor")
	public Author insertBook(@RequestBody Author au)
	{
		return ar.save(au);
	}
	
	/* API to fetch author by id. If there is an error while retrieving the book, a custom error, 
	 * ResourceNotFoundException will be thrown */
	@GetMapping("/author/{id}")
	public ResponseEntity<Author> getAuthorById(@PathVariable int id)
	{
		Author au = ar.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book does not exist in the database"));
		return ResponseEntity.ok(au);
	}
	
	/* API to fetch author by name. If there is an error while retrieving the book, a custom error, 
	 * ResourceNotFoundException will be thrown */
	@GetMapping("/authorNameLike/{name}")
	public ResponseEntity<List<Author>> getAuthorByName(@PathVariable String name)
	{
		List<Author> al = ar.findByName(name);
		return ResponseEntity.ok(al);
	}
}
