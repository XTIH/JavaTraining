package com.bookstorewebapp.BookstoreWebApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bookstorewebapp.BookstoreWebApplication.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>
{
	// Custom query to search book by title
	@Query("SELECT b FROM Book b WHERE b.title LIKE %:title%")
	List<Book> findByName(@Param("title") String title);
}
