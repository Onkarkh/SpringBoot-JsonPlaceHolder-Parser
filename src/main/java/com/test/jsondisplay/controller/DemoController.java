package com.test.jsondisplay.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.jsondisplay.model.Post;
import com.test.jsondisplay.model.User;

@Controller
public class DemoController {
	
	private List<Post> posts;
	
	private List<User> users;
	
	private ObjectMapper objectMapper;
	
	private void getPosts() {
		
		posts = new ArrayList<Post>();
		
		objectMapper = new ObjectMapper();
		
		try {
			Post[] retrivedPosts = objectMapper.readValue(new URL("https://jsonplaceholder.typicode.com/posts"), Post[].class);
			
			for(Post temp : retrivedPosts) {
				posts.add(temp);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void getUsers() {
		
		users = new ArrayList<User>();

		objectMapper = new ObjectMapper();

		try {
			User[] retrivedUsers = objectMapper.readValue(new URL("http://jsonplaceholder.typicode.com/users"),
					User[].class);

			for (User temp : retrivedUsers) {
				users.add(temp);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/users")
	public String showUsers(Model model) {
		
		getUsers();
		
		model.addAttribute("users", users);
		
		return "show-users";
	}
	
	@GetMapping("/all-posts")
	public String showPosts(Model model) {
		
		getPosts();
		
		model.addAttribute("posts", posts);
		
		return "show-posts";
	}
	
}
