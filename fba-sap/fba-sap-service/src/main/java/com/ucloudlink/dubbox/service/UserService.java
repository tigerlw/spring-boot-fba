package com.ucloudlink.dubbox.service;

import com.ucloudlink.cc.Request;
import com.ucloudlink.cc.Respone;

public interface UserService 
{
	public String queryUser(String userName);
	
	public Respone queryUserDomain(Request req);

}
