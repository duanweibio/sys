package com.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.User;
import com.service.UserService;

@RestController
@RequestMapping("/user/v1/")
@EnableScheduling
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("list")
	public Object selectList(String accountId){
		return userService.selectList(accountId);
	}
	
	@RequestMapping("findById")
	public Object findById(String accountId){
		return userService.selectByPrimaryKey(accountId);
	}
	@RequestMapping("save")
	//@ResponseBody
	public Object saverUser(@RequestBody User user){
		return userService.saveUser(user);
	}
	
	@RequestMapping("update")
	public Object updateUser(@RequestBody User user){
		return userService.updateUser(user);
	}
	
	//每分钟启动
	//@Scheduled(cron="0 0 2 * * ?")
	@Scheduled(cron="0 0 2 * * ?")//凌晨两点
	public void uodatejiageandfangjianshuliang(){
		Date now = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String oneDay = sf.format(now);
		 List<User> list = userService.selectList(oneDay);
		 for (User user : list) {
			User u = new User();
			u.setId(user.getId());
			u.setAge("23");
			u.setSex("男");
			userService.updateUser(user);
		}
		 System.out.println("更新结束!");
	}
	
}
