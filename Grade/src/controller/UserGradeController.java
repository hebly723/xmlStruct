package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import po.CustomUser;
import po.User;
import service.UserGradeService;
import service.UserService;

@Controller
public class UserGradeController {
	@Autowired
	private UserGradeService userGradeServicee;
	@RequestMapping("/selectGrade")
	public @ResponseBody List<CustomUser> selectGrade(HttpServletRequest request)
			throws Exception {
//		System.out.println("In");
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("success", userservice.check(user)+"");
		Object answer = request.getSession().getAttribute("isLogin");
//		System.out.println(answer.toString()+"???");
		if (answer.equals(true))
			return userGradeServicee.selectGrade();
		else
			return null;	
	}
	@RequestMapping("/addGrade")
	public @ResponseBody boolean addGrade(HttpServletRequest request,
			@RequestBody CustomUser cuser)
			throws Exception {
//		System.out.println("In");
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("success", userservice.check(user)+"");
		Object answer = request.getSession().getAttribute("isLogin");
//		System.out.println(answer.toString()+"???");
		System.out.println(cuser);
		if (answer.equals(true))
			return userGradeServicee.addGrade(cuser);
		else
			return false;	
	}
	@RequestMapping("/deleteGrade")
	public @ResponseBody int deleteGrade(HttpServletRequest request,
			@RequestBody CustomUser cuser)
			throws Exception {
//		System.out.println("In");
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("success", userservice.check(user)+"");
		Object answer = request.getSession().getAttribute("isLogin");
//		System.out.println(answer.toString()+"???");
		if (answer.equals(true))
			return userGradeServicee.deleteGrade(cuser.getId());
		else
			return 0;	
	}
	@RequestMapping("/alterGrade")
	public @ResponseBody int alterGrade(HttpServletRequest request,
			@RequestBody CustomUser cuser)
			throws Exception {
		Object answer = request.getSession().getAttribute("isLogin");
//		System.out.println(answer.toString()+"???");
		if (answer.equals(true))
			return userGradeServicee.alterGrade(cuser);
		else
			return 0;	
	}
	
}
