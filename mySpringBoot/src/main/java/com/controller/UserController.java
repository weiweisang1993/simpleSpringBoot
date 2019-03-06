package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pojo.User;
import com.service.UserService;
import com.util.RedisUtil;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@GetMapping
	public List<User> getUser(User requestUser){
		List<User> userList = new ArrayList<User>();
		//userList = userService.getUserList(requestUser);
		Map<String,Object> params = new HashMap<String,Object>();
		return userList;
	}
	
	@GetMapping("/session")
	public String getRequestSession(){
		
		return session.getId();
	}
}
