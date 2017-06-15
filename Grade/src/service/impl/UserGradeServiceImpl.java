package service.impl;

import java.util.ArrayList;
import java.util.List;

import mapper.UserGradeMapper;
import mapper.UserInfoMapper;
import mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;

import po.CustomUser;
import service.UserGradeService;


public class UserGradeServiceImpl implements UserGradeService {
	@Autowired
	private UserGradeMapper userGradeMapper;
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private UserMapper userMapper; 
	
	@Override
	public boolean addGrade(CustomUser cuser) {
		if (userMapper.selectByPrimaryKey(cuser.getId())==null)
		{
			userMapper.insertSelective(cuser.getUser());
			if (userInfoMapper.selectByPrimaryKey(cuser.getId()) == null)
				userInfoMapper.insertSelective(cuser);
			else
				userInfoMapper.updateByPrimaryKeySelective(cuser);
			if (userGradeMapper.selectByPrimaryKey(cuser.getId())== null)
				userGradeMapper.insertSelective(cuser.getUserGrade());
			else
				userGradeMapper.updateByPrimaryKeySelective(cuser.getUserGrade());
			return true;
		}
		return false;
	}

	@Override
	public List<CustomUser> selectGrade() {

		return userMapper.selectAllGrade();
	}

	@Override
	public int deleteGrade(String userId) {
		// TODO Auto-generated method stub
		
		return userMapper.deleteByPrimaryKey(userId)*
				userInfoMapper.deleteByPrimaryKey(userId)*
					userGradeMapper.deleteByPrimaryKey(userId);
	}

	@Override
	public int alterGrade(CustomUser cuser) {
		return userInfoMapper.updateByPrimaryKeySelective(cuser)*
				userGradeMapper.updateByPrimaryKeySelective(cuser.getUserGrade());
	}

}
