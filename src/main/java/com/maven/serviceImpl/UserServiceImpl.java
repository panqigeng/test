package com.maven.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maven.mapper.UserMapper;
import com.maven.model.User;
import com.maven.service.UserService;

@Service("userService")
@Transactional
// 此处不再进行创建SqlSession和提交事务，都已交由spring去管理了�?
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper mapper;

	/**
	 * 根据 id 删除 数据
	 */
	public boolean delete(int id) {
		return mapper.delete(id);
	}

	/**
	 * 查询User的全部数�?
	 */
	public List<User> findAll() {
		List<User> findAllList = mapper.findAll();
		return findAllList;
	}

	/**
	 * 根据 id 查询 对应数据
	 */
	public User findById(int id) {
		User user = mapper.findById(id);
		return user;
	}

	/**
	 * 新增数据
	 */
	public void save(User user) {
		mapper.save(user);
	}

	/**
	 * 根据 id 修改对应数据
	 */
	public boolean update(User user) {
		return mapper.update(user);
	}

	

	// 查询账号是否存在
	
	public String selAccountIsExist(String account) {

		return mapper.selAccountIsExist(account);
	}

	// 查询账号的密�?
	
	public String selAccountPassword(String account) {

		return mapper.selAccountPassword(account);
	}
}