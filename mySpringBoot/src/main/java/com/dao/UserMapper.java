package com.dao;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;

import com.pojo.User;

@CacheConfig
public interface UserMapper {
	
	public List<User> getUserList(User user);
	
}
