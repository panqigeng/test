package com.maven.service;

import java.util.List;

import com.maven.model.User;

public interface UserService {
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