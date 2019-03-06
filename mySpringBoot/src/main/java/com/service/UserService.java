package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pojo.User;

@Service
public interface UserService {
	
	public List<User> getUserList(User user);
	
}
