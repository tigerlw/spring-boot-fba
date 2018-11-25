package com.camp.endport;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sap.conn.jco.JCoException;
import com.camp.dubbox.service.UserService;
import com.camp.sap.example.SapService;
import com.camp.sap.example.domain.CompanyBO;

@RestController
public class SapController 
{
	private final org.apache.logging.log4j.Logger logger = LogManager.getLogger(SapController.class);
	
	@Autowired
	private SapService sapService;
	
	/*@Autowired
	private UserService userService;*/
	
	 @RequestMapping(value="/queryTable", method=RequestMethod.GET)
	 public List<CompanyBO> queryTable()
	 {
		 List<CompanyBO> result = null;
		 try {
			 
			result = sapService.queyCompTable();
			
		} catch (JCoException e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
		}
		 return result;
	 }
	 
	 @RequestMapping(value="/queryUser", method=RequestMethod.GET)
	 public String queryUser(@RequestParam String name)
	 {
		 /*String result = userService.queryUser(name);*/
		 logger.info("========================"+name);
		 return name;
	 }

}
