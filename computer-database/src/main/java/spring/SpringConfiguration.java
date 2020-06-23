package spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import service.Service;

@Configuration
@ComponentScan({"service","persistence","servlets","mapper"})


public class SpringConfiguration {
	
	
	
	private static AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);

	public static AnnotationConfigApplicationContext getContext() {
		return appContext;
	}

	@Bean
	public HikariConfig hikariConfig() {
		return new HikariConfig("/datasource.properties");
	}
	
	

	@Bean
	@Scope("singleton")
	public HikariDataSource getHikariDataSource() {
		return new HikariDataSource(hikariConfig());
	}
	
	/* @Bean	
	   public ServletRegistrationBean<HttpServlet> countryServlet() {
		   ServletRegistrationBean<HttpServlet> servRegBean = new ServletRegistrationBean<>();
		   servRegBean.setServlet(new HelloCountryServlet());
		   servRegBean.addUrlMappings("/country/*");
		   servRegBean.setLoadOnStartup(1);
		   return servRegBean;
	   }
	*/
}
