package spring;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import service.Service;
import servlets.AddComputerServlet;


@Configuration
public class SpringConfiguration implements  WebApplicationInitializer  {
	
/*
	 @Override
	 protected WebApplicationContext createRootApplicationContext() {
	 AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
	 rootContext.register(SpringConfiguration.class);
	 return rootContext;
	 } */
	
	
	 @Override
		public void onStartup(ServletContext servletContext) throws ServletException {
			AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
			webContext.register(SpringConfiguration.class, SpringMvcConfiguration.class);
			webContext.setServletContext(servletContext);
			ServletRegistration.Dynamic servlet = servletContext.addServlet("dynamicServlet", new DispatcherServlet(webContext));
			servlet.setLoadOnStartup(1);
			servlet.addMapping("/");
		} 
/*
	private static AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);

	public static AnnotationConfigApplicationContext getContext() {
		return appContext;
	}*/
	
	/*
	 @Override
		public void onStartup(ServletContext servletContext) throws ServletException {
		  AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
	        appContext.register(SpringConfiguration.class,SpringMvcConfiguration.class);
	 
	        // Dispatcher Servlet
	        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("SpringDispatcher",
	                new DispatcherServlet(appContext));
	        dispatcher.setLoadOnStartup(1);
	        dispatcher.addMapping("/");
	         
	        dispatcher.setInitParameter("contextClass", appContext.getClass().getName());
	 
	        servletContext.addListener(new ContextLoaderListener(appContext));
	         
	      
	    } */
	        
	        
	        
	        
		
		
	


	
	/*
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        registerDispatcherServlet(servletContext);
    }*/
	
	/*
	 * @Bean public ServletRegistrationBean<HttpServlet> countryServlet() {
	 * ServletRegistrationBean<HttpServlet> servRegBean = new
	 * ServletRegistrationBean<>(); servRegBean.setServlet(new
	 * HelloCountryServlet()); servRegBean.addUrlMappings("/country/*");
	 * servRegBean.setLoadOnStartup(1); return servRegBean; }
	 */
}
