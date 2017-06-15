package service.impl;

import mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;

import po.User;
import po.UserExample;
import service.UserService;


public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public boolean check(User user) {
		UserExample userExample = new UserExample();
		UserExample.Criteria criteria =  userExample.createCriteria();
		criteria.andIdEqualTo(user.getId());
		criteria.andPasswordEqualTo(user.getPassword());
		return !(userMapper.selectByExample(userExample).isEmpty());
	}

	

}
