package com.ls.soft.other.configure;

import java.util.Properties;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.github.pagehelper.PageInterceptor;


public class MybatisConfig {
    @Inject
    Environment environment;

    // use mybatis
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        sessionFactory.setTypeAliasesPackage("com.ls.soft.dao");// "com.lh.tss.dao");
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:com/ls/**/dao/*Mapper.xml"));// ));
        sessionFactory.setConfiguration(configuration());
        return sessionFactory;
    }

    private Configuration configuration() {
        Configuration configuration = new Configuration();
        configuration.addInterceptor(pageInterceptor());
        return configuration;
    }

    private PageInterceptor pageInterceptor() {
        PageInterceptor pageHelper = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("pageSizeZero", "true");
        properties.setProperty("reasonable", "false");
        properties.setProperty("supportMethodsArguments", "false");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
