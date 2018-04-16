package com.ls.soft.other.configure;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@PropertySource("classpath:db.properties")  
public class C3P0Configure {

	@Inject
    Environment environment;
    
    @Bean(destroyMethod = "close")  
    public DataSource dataSource() {
        try {
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass(environment.getProperty("db.c3p0.driverClassName"));
            dataSource.setJdbcUrl(environment.getProperty("db.c3p0.url"));
            dataSource.setUser(environment.getProperty("db.c3p0.username"));
            dataSource.setPassword(environment.getProperty("db.c3p0.password"));
            dataSource.setAcquireIncrement(5);
            dataSource.setInitialPoolSize(5);
            dataSource.setMaxPoolSize(200);
            dataSource.setMinPoolSize(5);
            dataSource.setMaxIdleTime(1800);
            dataSource.setMaxIdleTimeExcessConnections(1200);
            dataSource.setMaxConnectionAge(1000);
            dataSource.setPreferredTestQuery("select 1 from dual");
            dataSource.setIdleConnectionTestPeriod(120);
            //当一个连接关闭时，如果有未提交的事务, C3P0默认的策略是回滚任何未提交的事务
            dataSource.setAutoCommitOnClose(false);
            return dataSource;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(dataSource);
		return dataSourceTransactionManager;
	}
}
