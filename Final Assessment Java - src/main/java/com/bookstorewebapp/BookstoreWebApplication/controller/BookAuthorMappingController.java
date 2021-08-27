package com.bookstorewebapp.BookstoreWebApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstorewebapp.BookstoreWebApplication.model.Book;
import com.bookstorewebapp.BookstoreWebApplication.model.BookAuthorMapping;
import com.bookstorewebapp.BookstoreWebApplication.repository.BookAuthorMappingRepository;

@RestController
@RequestMapping("/map")
public class BookAuthorMappingController 
{
	@Autowired BookAuthorMappingRepository bamr;
	
	// API to retrieve all mappings
	@GetMapping("map")
	public List<BookAuthorMapping> getAllMapping()
	{
		return (List<BookAuthorMapping>) bamr.findAll();
	}
	
	// API to add new author
	@PostMapping("/addMap")
	public BookAuthorMapping addMap(@RequestBody BookAuthorMapping bam)
	{
		return bamr.save(bam);
	}
}
