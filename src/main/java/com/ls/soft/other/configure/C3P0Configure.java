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
            dataSource.setAcquireIncrement(environment.getProperty("c3p0.acquireIncrement", int.class, 5));
            dataSource.setInitialPoolSize(environment.getProperty("c3p0.initialPoolSize", int.class, 5));
            dataSource.setMaxPoolSize(environment.getProperty("c3p0.maxPoolSize", int.class, 200));
            dataSource.setMinPoolSize(environment.getProperty("c3p0.minPoolSize", int.class, 5));
            dataSource.setMaxIdleTime(environment.getProperty("c3p0.maxIdleSize", int.class, 1800));
            dataSource.setMaxIdleTimeExcessConnections(environment.getProperty("c3p0.maxIdleTimeExcessConnections", int.class, 1200));
            dataSource.setMaxConnectionAge(environment.getProperty("c3p0.maxConnectionAge", int.class, 1000));
            dataSource.setPreferredTestQuery(environment.getProperty("c3p0.preferredTestQuery", "select 1 from dual"));
            dataSource.setIdleConnectionTestPeriod(environment.getProperty("c3p0.idleConnectionTestPeriod", int.class, 120));
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
