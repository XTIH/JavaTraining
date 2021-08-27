package com.bookstorewebapp.BookstoreWebApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstorewebapp.BookstoreWebApplication.model.Book;
import com.bookstorewebapp.BookstoreWebApplication.repository.BookRepository;
import com.bookstorewebapp.BookstoreWebApplication.exception.ResourceNotFoundException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/book")
public class BookController 
{
	@Autowired BookRepository br;
	
	// API to fetch all books.
	@GetMapping("/book")
	public List<Book> getAllBooks()
	{
		return (List<Book>) br.findAll();
	}
	
	// API to insert new book
	@PostMapping("/addBook")
	public Book insertBook(@RequestBody Book book)
	{
		return br.save(book);
	}
	
	/* API to fetch book by isbn. If there is an error while retrieving the book, a custom error, 
	 * ResourceNotFoundException will be thrown */
	@GetMapping("/book/{isbn}")
	public ResponseEntity<Book> getBookById(@PathVariable int isbn)
	{
		Book book = br.findById(isbn)
				.orElseThrow(() -> new ResourceNotFoundException("Book does not exist in the database"));
		return ResponseEntity.ok(book);
	}
	

	/* API to fetch book by title. If there is an error while retrieving the book, a custom error, 
	 * ResourceNotFoundException will be thrown */
	@GetMapping("/bookTitleLike/{title}")
	public ResponseEntity<List<Book>> getEmployeeByName(@PathVariable String title)
	{
		List<Book> bl = br.findByName(title);
		return ResponseEntity.ok(bl);
	}
	
	/* API to update book details. Book is first retrieved from database; and if it does not exist in 
	the database, the custom ResourceNotFoundException will be thrown. */
	@PutMapping("/updateBook/{isbn}")
	public ResponseEntity<Book> updateBook(@PathVariable int isbn, @RequestBody Book book)
	{
		Book tempBook = br.findById(isbn)
				.orElseThrow(() -> new ResourceNotFoundException("Book does not exist in the database"));
		System.out.println(tempBook.getTitle());
		
		tempBook.setTitle(book.getTitle());
		tempBook.setYear(book.getYear());
		tempBook.setPrice(book.getPrice());

		Book updatedBook = br.save(tempBook);
		return ResponseEntity.ok(updatedBook);
	}
}
