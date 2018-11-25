package com.camp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sap.conn.jco.JCoException;
import com.camp.sap.example.StepByStepClient;

@SpringBootApplication
public class FbaApplication 
{
	public static void main(String args[]) throws JCoException
	{
		SpringApplication.run(FbaApplication.class, args);	
		
		System.out.println("===================================");
		//StepByStepClient.step4WorkWithTable();
	}

}
