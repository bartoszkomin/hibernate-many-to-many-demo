package com.blogspot.bartoszkomin.hibernate_many_to_many_demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.blogspot.bartoszkomin.hibernate_many_to_many_demo.App;
import com.blogspot.bartoszkomin.hibernate_many_to_many_demo.model.Book;
import com.blogspot.bartoszkomin.hibernate_many_to_many_demo.model.User;
import com.blogspot.bartoszkomin.hibernate_many_to_many_demo.repository.BookRepository;
import com.blogspot.bartoszkomin.hibernate_many_to_many_demo.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class UserControllerTest {
	
	@Autowired
	private WebApplicationContext ctx;
	
	@Autowired
	UserRepository userReposiotry;
	
	@Autowired
	BookRepository bookReposiotry;
	
	private MockMvc mockMvc;

	private ObjectMapper mapper = new ObjectMapper();
	
	@Before
	public void setUp() {
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	@Transactional
	public void addNewUserOkNoLinkTest() throws Exception {
		
		String postJson = "{\"name\": \"Albert Einstein\"}";

		ResultActions resultAction = this.mockMvc.perform(post("/users")
				.contentType(MediaType.APPLICATION_JSON).content(postJson))
				.andDo(print())
				.andExpect(status().isCreated());
		
		String responseBody = resultAction.andReturn().getResponse().getContentAsString();
		
		User user = mapper.readValue(responseBody, User.class);
		
		assertNotNull(user);
		assertNotNull(user.getId());
		assertNotNull(user.getName());
		
	}
	
	@Test
	public void getUsersOkTest() throws Exception {
		prepareUserStartDataForTest(); //to make sure we have something in db
		
		ResultActions resultAction  = this.mockMvc.perform(get("/users"))
				.andDo(print())
				.andExpect(status().isOk());
		
		String responseBody = resultAction.andReturn().getResponse().getContentAsString();
		
		List<User> outputUserListObject = mapper.readValue(responseBody, new TypeReference<List<User>>(){});
		
		assertNotNull(outputUserListObject);
		assertTrue(outputUserListObject.size() > 0);
	}
	
	@Test
	public void getUserByIdOkTest() throws Exception {
		User insertedUser = prepareUserStartDataForTest(); //to make sure we have something in db
		
		ResultActions resultAction  = this.mockMvc.perform(get("/users/"+insertedUser.getId()))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("{\"id\":"+insertedUser.getId()+",\"name\":\""+insertedUser.getName()+"\",\"books\":[]}")));;
	}
	
	@Test
	public void addNewUserOkLinkWithBookTest() throws Exception {
		Book insertedBook = prepareBookStartDataForTest();
		
		String postJson = "{\"name\": \"Albert Einstein\", \"books\":[{\"id\":"+insertedBook.getId()+"}]}";

		ResultActions resultAction = this.mockMvc.perform(post("/users")
				.contentType(MediaType.APPLICATION_JSON).content(postJson))
				.andDo(print())
				.andExpect(status().isCreated());
		
		String responseBody = resultAction.andReturn().getResponse().getContentAsString();
		
		User user = mapper.readValue(responseBody, User.class);
		
		assertNotNull(user);
		assertNotNull(user.getId());
		assertNotNull(user.getName());
		assertNotNull(user.getBooks());
		assertTrue(user.getBooks().size() == 1);
		
	}
	
	private Book prepareBookStartDataForTest() {
		Book bookInit = new Book();
		bookInit.setName("The Meaning of Relativity");

		return bookReposiotry.save(bookInit);
	}
	
	private User prepareUserStartDataForTest() {
		User userInit = new User();

		userInit.setName("Albert Einstein");
		
		return userReposiotry.save(userInit);
	}
}
