package com.ucloudlink.sap.example;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sap.conn.jco.AbapException;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;
import com.ucloudlink.configuration.sap.SapConn;
import com.ucloudlink.sap.example.domain.CompanyBO;

@Component
public class SapService 
{
	private final org.apache.logging.log4j.Logger logger = LogManager.getLogger(SapService.class);
	
	@Autowired
	private SapConn sapConn;
	
	public List<CompanyBO> queyCompTable() throws JCoException
	{
		JCoDestination destination = sapConn.getDestination();
		
		if(destination == null)
		{
			return null;
		}
		
		JCoFunction function = destination.getRepository().getFunction("BAPI_COMPANYCODE_GETLIST");

		logger.debug("===================FUNCTIONS=================================");
		String[] functions = destination.getRepository().getCachedFunctionTemplateNames();
		for (String s : functions) {
			logger.debug(s);
		}

		if (function == null)
			throw new RuntimeException("BAPI_COMPANYCODE_GETLIST not found in SAP.");

		try {
			function.execute(destination);
		} catch (AbapException e) {
			logger.error(e.toString());
			return null;
		}

		JCoStructure returnStructure = function.getExportParameterList().getStructure("RETURN");
		if (!(returnStructure.getString("TYPE").equals("") || returnStructure.getString("TYPE").equals("S"))) {
			throw new RuntimeException(returnStructure.getString("MESSAGE"));
		}

		logger.debug("===================BAPI_COMPANYCODE_GETLIST=================================");
		
		List<CompanyBO> result = new ArrayList<CompanyBO>();
		
		JCoTable codes = function.getTableParameterList().getTable("COMPANYCODE_LIST");
		for (int i = 0; i < codes.getNumRows(); i++) {
			codes.setRow(i);
			logger.debug(codes.getString("COMP_CODE") + '\t' + codes.getString("COMP_NAME"));
			
			CompanyBO company = new CompanyBO();
			company.setCode(codes.getString("COMP_CODE"));
			company.setName(codes.getString("COMP_NAME"));
			result.add(company);
			
		}
		
		return result;
	}

}
