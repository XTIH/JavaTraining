package com.bookstorewebapp.BookstoreWebApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstorewebapp.BookstoreWebApplication.model.BookAuthorMapping;

public interface BookAuthorMappingRepository extends JpaRepository<BookAuthorMapping, Integer>
{

}
