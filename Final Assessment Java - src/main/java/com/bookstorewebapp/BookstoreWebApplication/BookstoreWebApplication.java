package com.bookstorewebapp.BookstoreWebApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookstoreWebApplication 
{
	public static void main(String[] args) 
	{
		SpringApplication.run(BookstoreWebApplication.class, args);
		System.out.println("Bookstore Web Application is running!");
	}
}
