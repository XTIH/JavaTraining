package com.bookstorewebapp.BookstoreWebApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookstorewebapp.BookstoreWebApplication.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer>
{
	// Custom query to search author by name
	@Query("SELECT a FROM Author a WHERE a.name LIKE %:name%")
	List<Author> findByName(@Param("name") String name);
}
