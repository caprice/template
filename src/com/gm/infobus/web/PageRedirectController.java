package com.gm.infobus.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gm.infobus.entity.User;
import com.gm.infobus.service.UserService;


/**
 * 
 * 菜单跳转controller
 *
 */
@Controller
@RequestMapping(value = "/page")
public class PageRedirectController extends BaseController {
	@Autowired
	private UserService userService;
	/**
	 * 首页.
	 * 
	 * @return the string
	 */
	@RequestMapping(value = "show")
	public String showHomePage(User user) {
		userService.addUser(user);
		List<User> list= userService.findAllUsers();
		System.out.print("the total user:"+list.size());
		return "index";
	}
}
