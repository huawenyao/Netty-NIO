package com.common.controller;

import javax.annotation.Resource;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.common.reposity.page.Page;
import com.common.service.UserService;
import com.xiaoleilu.hutool.http.HttpResponse;

public class UserCtrl {
	@Resource 
	UserService  userService;
	@RequestMapping(value = "/user/users")  
	 public String list(  
	   @RequestParam(required = false, defaultValue = "1") int pageNo,  
	   @RequestParam(required = false, defaultValue = "5") int pageSize, 
	   HttpResponse reponse,
	   @ModelAttribute("name") String userName,  
	   Model model) {  
	   // 这里是“信使”诞生之地，一出生就加载了很多重要信息！  
	   Page page = Page.newBuilder(pageNo, pageSize, "users");  
	   page.getParams().put("userName", userName);           //这里再保存查询条件  
	       
	   model.addAttribute("users",userService.selectByName(  
			   userName, page));  
	   model.addAttribute("page", page);             //这里将page返回前台  
	   return "/";  
	}

}
