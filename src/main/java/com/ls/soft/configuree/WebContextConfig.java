package com.ls.soft.configuree;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


public class WebContextConfig  extends WebMvcConfigurerAdapter {
	
	 @Inject
	 Environment environment;
	
	 /**
    * 静态资源过滤
    */
   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry.addResourceHandler( "/static/**")
               .addResourceLocations("/WEB-INF/static/")
               .setCachePeriod(31556926);
   }
   
   /**
    * 将对于静态资源的请求转发到Servlet容器的默认处理静态资源的servlet
    * 因为将spring的拦截模式设置为"/"时会对静态资源进行拦截
    */
   @Override
   public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
       configurer.enable();
   }
   
   /**
    * JSP视图解析器
    */
   @Bean
   public ViewResolver internalResourceViewResolver() {
       InternalResourceViewResolver resolver = new InternalResourceViewResolver();
       resolver.setViewClass(JstlView.class);
       resolver.setPrefix("/WEB-INF/jsp/");
       resolver.setSuffix(".jsp");
       resolver.setOrder(99);
       resolver.setExposeContextBeansAsAttributes(true);
       return resolver;
   }
   
   @Override
   public void addViewControllers(ViewControllerRegistry registry) {
     registry.addViewController("/").setViewName("index");
   }

   /**                                                            
    * 描述 : <文件上传处理器>                                                                                                                                                                                                                                           
    * @return                                                                                                        
    */    
   @Bean(name="multipartResolver")  
    public CommonsMultipartResolver commonsMultipartResolver(){  
   	 CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver();
   	 multipartResolver.setResolveLazily(true);
   	 multipartResolver.setMaxUploadSize(1048576000L);
        return multipartResolver;  
    }

//    @Bean
//    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() throws Exception {
//        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
//        Resource[] resources = new PathMatchingResourcePatternResolver()
//                .getResources("classpath*:**/*.properties");
//        propertyPlaceholderConfigurer.setLocations(resources);
//        return propertyPlaceholderConfigurer;
//    }

}