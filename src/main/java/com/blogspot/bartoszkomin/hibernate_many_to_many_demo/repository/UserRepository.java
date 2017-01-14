package com.blogspot.bartoszkomin.hibernate_many_to_many_demo.repository;

import java.io.Serializable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.blogspot.bartoszkomin.hibernate_many_to_many_demo.model.User;

/**
 * @author Bartosz Komin
 * 
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Serializable> {
	
}
