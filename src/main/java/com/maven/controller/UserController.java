package com.maven.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.maven.model.User;
import com.maven.serviceImpl.UserServiceImpl;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserServiceImpl userServiceImpl;

	/**
	 * 获取�?有用户列�?
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getAllUser")
	public String getAllUser(HttpServletRequest request, Model model) {
		List<User> user = userServiceImpl.findAll();
		model.addAttribute("userList", user);
		request.setAttribute("userList", user);
		return "/allUser";
	}

	/**
	 * 跳转到添加用户界�?
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/toAddUser")
	public String toAddUser() {
		return "/addUser";
	}

	/**
	 * 添加用户并重定向
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/addUser")
	public String addUser(User user, Model model) {
		userServiceImpl.save(user);
		return "redirect:/user/getAllUser";
	}

	/**
	 * 编辑用户
	 * 
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateUser")
	public String updateUser(User user, HttpServletRequest request, Model model) {
		if (userServiceImpl.update(user)) {
			user = userServiceImpl.findById(user.getId());
			request.setAttribute("user", user);
			model.addAttribute("user", user);
			return "redirect:/user/getAllUser";
		} else {
			return "/error";
		}
	}

	/**
	 * 根据id查询单个用户
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/getUser")
	public String getUser(int id, HttpServletRequest request, Model model) {
		request.setAttribute("user", userServiceImpl.findById(id));
		model.addAttribute("user", userServiceImpl.findById(id));
		return "/editUser";
	}

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @param request
	 * @param response
	 */
	@RequestMapping("/delUser")
	public void delUser(int id, HttpServletRequest request,
			HttpServletResponse response) {
		String result = "{\"result\":\"error\"}";
		if (userServiceImpl.delete(id)) {
			result = "{\"result\":\"success\"}";
		}
		response.setContentType("application/json");
		try {
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String login(String account, String password) {
		Map<Object, String> result = new HashMap<Object, String>();
		String ac = userServiceImpl.selAccountIsExist(account);
		if (ac == null) {
			result.put("id", "Username Or Password is not correct");
			String json = JSON.toJSONString(result);
			return json;
		}

		String pa = userServiceImpl.selAccountPassword(account);

		if (!password.equals(pa)) {
			result.put("id", "Username Or Password is not correct");
			String json = JSON.toJSONString(result);
			return json;
			// return "Username Or Password is not correct";

		} else {
			result.put("id", "login_success");
			String json = JSON.toJSONString(result);
			return json;
			// return "login_success";

		}

	}

}
