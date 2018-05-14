package com.maven.mapper;

import java.util.List;

import com.maven.model.User;

/**
 * 做为 DAO的接�?
 * 
 * @author
 */

public interface UserMapper {
	void save(User user);

	boolean update(User user);

	boolean delete(int id);

	User findById(int id);

	List<User> findAll();
	
	// 查询账号是否存在
	public String selAccountIsExist(String account);

	// 查询账号的密�?
	public String selAccountPassword(String account);

}