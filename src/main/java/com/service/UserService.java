package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bean.User;
import com.dao.UserMapper;


@Service
@Cacheable(cacheNames="user")
public class UserService {
	
	@Autowired
	private UserMapper usermapper;
	
	@Cacheable(key="'userList'+#accountId")
	public List<User> selectList(String accountId){
		return usermapper.selectList();
		
	}
	@CacheEvict(key="#id")
	public Object deleteByPrimaryKey(String id){
		return usermapper.deleteByPrimaryKey(Long.parseLong(id));
		
	}
	@Cacheable(key = "#id")
	public User selectByPrimaryKey(String id){
		return usermapper.selectByPrimaryKey(Long.parseLong(id));
		
	}
	
	//@CachePut(key="#user.id")
	@Cacheable(cacheNames = {"user","user"},key = "#user.id")
	public Object saveUser(User user){
		return usermapper.insert(user);
		
	}
	
	@CachePut(key="#user.id")
	public Object updateUser(User u){
		return usermapper.updateByPrimaryKey(u);
		
	}

}
