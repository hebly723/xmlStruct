package service;

import po.User;



public interface UserService {
	/**
	 * ����û���������ȷ������true
	 * @param id
	 * @param password
	 * @return
	 */
	boolean check(User user);
    
}
