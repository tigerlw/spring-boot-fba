package com.camp.dubbox.service.impl;

import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;

import com.camp.cc.Request;
import com.camp.cc.Respone;
import com.camp.dubbox.service.UserService;
import com.camp.endport.SapController;

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
