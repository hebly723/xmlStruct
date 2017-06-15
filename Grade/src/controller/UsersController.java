package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import po.User;
import service.UserService;

@Controller
public class UsersController {
	@Autowired
	private UserService userservice;
	@RequestMapping("/login")
	public @ResponseBody Map<String, String> loginCheck(HttpServletRequest request, @RequestBody User user )
			throws Exception {
		System.out.println("In");
		Map<String, String> map = new HashMap<String, String>();
		boolean  answer = userservice.check(user);
		map.put("success", answer+"");
		if (answer){
			HttpSession session = request.getSession();
			session.setAttribute("isLogin", true);
//			session.setAttribute("username", user.);
			session.setAttribute("userId", user.getId());
		}
		return map;
	}
}
