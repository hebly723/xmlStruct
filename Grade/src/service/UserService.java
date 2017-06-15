package service;

import po.User;



public interface UserService {
	/**
	 * 如果用户名密码正确，返回true
	 * @param id
	 * @param password
	 * @return
	 */
	boolean check(User user);
    
}
