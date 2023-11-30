package com.jackpot.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@ComponentScan(basePackages = { "com.jackpot.controller", "com.jackpot.exception" })
public class ServletConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("/resources/");
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/views/");
		bean.setSuffix(".jsp");
		registry.viewResolver(bean);
	}
	
	@Bean(name="multipartResolver")
	public CommonsMultipartResolver getResolver() throws IOException {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();

//		resolver.setMaxUploadSize(1024*1024*40); //40MB, -1 : 무제한
//		resolver.setMaxUploadSizePerFile(1024*1024*20); //20MB, -1 : 무제한

		resolver.setMaxUploadSize(-1); //-1 : 무제한
		resolver.setMaxUploadSizePerFile(-1); //-1 : 무제한

		resolver.setMaxInMemorySize(1024*1024); //1MB

		resolver.setUploadTempDir(new FileSystemResource("e:\\Temp\\upload\\tmp"));
		resolver.setDefaultEncoding("UTF-8");

		return resolver;
	}

}
