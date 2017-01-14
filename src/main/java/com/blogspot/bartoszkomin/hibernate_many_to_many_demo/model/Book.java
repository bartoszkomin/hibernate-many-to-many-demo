package com.blogspot.bartoszkomin.hibernate_many_to_many_demo.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Bartosz Komin
 * Book entity
 */
@Entity
@Table(name = "books")
public class Book {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Column(nullable = false)
	private String name;

	@ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "UserBook", 
    	joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"), 
    	inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
    	uniqueConstraints={@UniqueConstraint(columnNames={"book_id", "user_id"})})
	@JsonIgnoreProperties("books")
	private List<User> users;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Integer getId() {
		return id;
	}
	
}
