package com.ls.soft.configuree;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.ls.soft.other.configure.C3P0Configure;
import com.ls.soft.other.configure.MybatisConfig;

@Configuration
// / 启用切面自动代理，用于AOP
@EnableAspectJAutoProxy
// 启用注解事务，即可以使用@Transactional注解来控制事务
@EnableTransactionManagement
@ComponentScan(basePackages = "com.*", excludeFilters = { @Filter(value = Controller.class) })
 @Import({ C3P0Configure.class,MybatisConfig.class })
 @MapperScan(basePackages = { "com.***.dao" })
 @PropertySource("classpath:db.properties")
public class AppConfigure {

	@Bean
	public static HandlerExceptionResolver getExceptionHandlerExceptionResolver() {
		ExceptionHandlerExceptionResolver resolver = new ExceptionHandlerExceptionResolver();
		return resolver;
	}

	/**
	 * 必须为static
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer loadProperties() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		return configurer;
	}

	@Bean
	public static CommonsMultipartResolver commonsMultipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		resolver.setMaxUploadSize(2048 * 2048);
		resolver.setMaxInMemorySize(2048);
		return resolver;
	}
	/**
	 * jsp视图解析器的bean
	 * 
	 * @return
	 */
	// @Bean
	// public UrlBasedViewResolver setupViewResolver() {
	// UrlBasedViewResolver resolver = new UrlBasedViewResolver();
	// resolver.setPrefix("/WEB-INF/");
	// resolver.setSuffix(".jsp");
	// resolver.setViewClass(JstlView.class);
	// return resolver;
	// }

	/**
	 * 配置数据源
	 * 
	 * @return
	 */
	// @Bean(name = "dataSource")
	// public ComboPooledDataSource getDataSource() {
	// try {
	//
	// ComboPooledDataSource dataSource = new ComboPooledDataSource();
	// dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/mfdb");
	// dataSource.setDriverClass("com.mysql.jdbc.Driver");
	// dataSource.setUser("root");
	// dataSource.setPassword("zp1228");
	// dataSource.setMaxPoolSize(75);
	// return dataSource;
	// } catch (Exception e) {
	// return null;
	// }
	// }

}
