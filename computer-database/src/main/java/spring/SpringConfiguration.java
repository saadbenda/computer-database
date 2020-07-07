package spring;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;



@Configuration
public class SpringConfiguration implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		webContext.register(SpringConfiguration.class, SpringMvcConfiguration.class);
		webContext.setServletContext(servletContext);
		DispatcherServlet dispatcherServlet = new DispatcherServlet(webContext);
		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcherServlet",dispatcherServlet);
		//dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
	}

}
