package com.camp.dubbox.service;

import com.camp.cc.Request;
import com.camp.cc.Respone;

public interface UserService 
{
	public String queryUser(String userName);
	
	public Respone queryUserDomain(Request req);

}
