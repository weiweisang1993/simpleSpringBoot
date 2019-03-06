package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.dao.UserMapper;
import com.pojo.User;
import com.service.UserService;

@Service
@CacheConfig(cacheNames="userCache")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	@Cacheable(value="user",key="#user.userId") 
	public List<User> getUserList(User user) {
		return userMapper.getUserList(user);
	}
}
