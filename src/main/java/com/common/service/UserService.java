package com.common.service;

import javax.annotation.Resource;

import com.common.dao.UserBasicInfoMapper;
import com.common.model.UserBasicInfo;
import com.common.model.UserBasicInfoExample;
import com.common.reposity.page.Page;

public class UserService {
	@Resource
	private UserBasicInfoMapper userBasicInfoMapper;

	public java.util.List<UserBasicInfo> selectByName(String name, Page page) {  
//		   Map<String, Object> map = Maps.newHashMap(); 
		   UserBasicInfoExample  userExample=new UserBasicInfoExample();
		        
		   return userBasicInfoMapper.selectLimitByExample(userExample,page); 
	}
}
