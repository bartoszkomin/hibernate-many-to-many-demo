package com.blogspot.bartoszkomin.hibernate_many_to_many_demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.blogspot.bartoszkomin.hibernate_many_to_many_demo.model.Book;
import com.blogspot.bartoszkomin.hibernate_many_to_many_demo.repository.BookRepository;

/**
 * @author Bartosz Komin
 * Book rest controller
 */
@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	BookRepository bookRepository;
	
	/**
	 * Save new book
	 * @param bookRequestBody - JAVA representation of JSON request body
	 * @return Book entity
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(code = HttpStatus.CREATED)
	public Book addNewBook(@Valid @RequestBody Book bookRequest) {
		
		return bookRepository.save(bookRequest);
	}
	
	/**
	 * Get book list
	 * @return List of Book entity
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.OK)
	public List<Book> getBooks() {
		
		List<Book> returnBookList = new ArrayList<>();
		bookRepository.findAll().iterator().forEachRemaining(returnBookList::add);
		
		return returnBookList;
	}
}
