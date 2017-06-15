package service;

import java.util.ArrayList;
import java.util.List;

import po.CustomUser;
import po.User;



public interface UserGradeService {
	
	boolean addGrade(CustomUser cuser);
	
	int deleteGrade(String userId);
	
	List<CustomUser> selectGrade();
	
	int alterGrade(CustomUser cuser);
    
}
