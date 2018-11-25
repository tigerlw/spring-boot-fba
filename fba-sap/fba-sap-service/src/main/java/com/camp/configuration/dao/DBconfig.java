package com.camp.configuration.dao;

import java.beans.PropertyVetoException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * Created by yangyibo on 17/1/18.
 */
@Configuration
public class DBconfig {
    @Autowired
    private Environment env;

    @Bean(name="dataSource")
    public ComboPooledDataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(env.getProperty("jdbc.driverClassName"));
        dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        dataSource.setUser(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        dataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("jdbc.maxPoolSize")));
        dataSource.setMinPoolSize(Integer.parseInt(env.getProperty("jdbc.miniPoolSize")));
        dataSource.setInitialPoolSize(Integer.parseInt(env.getProperty("jdbc.initialPoolSize")));
        dataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("jdbc.maxIdleTime")));
        dataSource.setAcquireIncrement(5);
        dataSource.setIdleConnectionTestPeriod(60);
        return dataSource;
    }
}
