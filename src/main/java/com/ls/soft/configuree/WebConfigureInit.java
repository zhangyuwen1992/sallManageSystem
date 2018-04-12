package com.ls.soft.configuree;

import java.nio.charset.StandardCharsets;
import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class WebConfigureInit implements WebApplicationInitializer {
	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		FilterRegistration.Dynamic encodingFilter = servletContext.addFilter(
				"encodingFilter", CharacterEncodingFilter.class);
		// 配置Spring提供的字符编码过滤器
		encodingFilter.setInitParameter("encoding",
				String.valueOf(StandardCharsets.UTF_8));
		encodingFilter.setInitParameter("forceEncoding", "true");
		// 配置过滤器的过滤路径
		// encodingFilter.addMappingForUrlPatterns(null, false, "/*");
		encodingFilter.addMappingForUrlPatterns(
				EnumSet.of(DispatcherType.REQUEST), true, "/");
		//

		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(AppConfigure.class);

		servletContext.addListener(new ContextLoaderListener(ctx));

		// 基于注解配置的Web容器上下文
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		// 注册Web容器配置类
		context.register(WebConfigure.class);
		ServletRegistration.Dynamic servlet = servletContext.addServlet(
				"dispatcher", new DispatcherServlet(context));
		servlet.addMapping("/");
		servlet.setLoadOnStartup(1);
	}
}
