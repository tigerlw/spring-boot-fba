package com.camp.configuration.sap;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.ext.DestinationDataProvider;
import com.camp.configuration.sap.CustomDestinationDataProvider.MyDestinationDataProvider;

@Component
public class SapConn implements ApplicationListener<ContextRefreshedEvent>
{
	static String ABAP_AS = "ABAP_AS_WITHOUT_POOL";
    static String ABAP_AS_POOLED = "ABAP_AS_WITH_POOL";
    static String ABAP_MS = "ABAP_MS_WITHOUT_POOL";
    
    private final org.apache.logging.log4j.Logger logger = LogManager.getLogger(SapConn.class);
    
    @Autowired
    private Environment env;
    
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event)
	{
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		Properties connectProperties = new Properties();
		connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST, env.getProperty("sap.host"));
		connectProperties.setProperty(DestinationDataProvider.JCO_SYSNR, env.getProperty("sap.sysnr"));
		connectProperties.setProperty(DestinationDataProvider.JCO_CLIENT, env.getProperty("sap.client"));
		connectProperties.setProperty(DestinationDataProvider.JCO_USER, env.getProperty("sap.user"));
		connectProperties.setProperty(DestinationDataProvider.JCO_PASSWD, env.getProperty("sap.pwd"));
		connectProperties.setProperty(DestinationDataProvider.JCO_LANG, env.getProperty("sap.lang"));

		MyDestinationDataProvider myProvider = new MyDestinationDataProvider();
		try {
			com.sap.conn.jco.ext.Environment.registerDestinationDataProvider(myProvider);
		} catch (IllegalStateException providerAlreadyRegisteredException) {
			logger.error(providerAlreadyRegisteredException);
		}
		myProvider.changeProperties(ABAP_AS, connectProperties);
		myProvider.changeProperties(ABAP_AS_POOLED, connectProperties);
		myProvider.changeProperties(ABAP_MS, connectProperties);

	}
	
	public JCoDestination getDestination()
	{
		JCoDestination destination = null;
		try {
			destination = JCoDestinationManager.getDestination(ABAP_AS_POOLED);
		} catch (JCoException e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
		
		return destination;
	}
    
  
	

}
