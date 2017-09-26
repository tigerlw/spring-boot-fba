package com.ucloudlink.dubbox.service.impl;

import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;

import com.ucloudlink.cc.Request;
import com.ucloudlink.cc.Respone;
import com.ucloudlink.dubbox.service.UserService;
import com.ucloudlink.endport.SapController;

@Component
public class UserServiceImpl implements UserService
{
	private final org.apache.logging.log4j.Logger logger = LogManager.getLogger(UserServiceImpl.class);

	public String queryUser(String userName) {
		// TODO Auto-generated method stub
		logger.info("===================="+userName);
		
		return "Dao:"+userName;
	}

	@Override
	public Respone queryUserDomain(Request req) {
		// TODO Auto-generated method stub
		return null;
	}

}
