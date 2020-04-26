package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bean.User;

@Mapper
public interface UserMapper {

	public List<User> selectList();
	public int deleteByPrimaryKey(Long id);
	public User selectByPrimaryKey(Long id);
	public int insert(User u);
	public int insertSelective(User user);
	public int updateByPrimaryKeySelective(User u);
	public int updateByPrimaryKey(User u);
	
}
