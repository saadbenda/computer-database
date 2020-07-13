package spring;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;



@Configuration

public class SpringConfiguration extends AbstractAnnotationConfigDispatcherServletInitializer
implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		webContext.register(SpringConfiguration.class, SpringMvcConfiguration.class, SpringSecurityConfiguration.class);
		webContext.setServletContext(servletContext);
		DispatcherServlet dispatcherServlet = new DispatcherServlet(webContext);
		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcherServlet",dispatcherServlet);
		//dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
	}
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SpringSecurityConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { SpringMvcConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/*" };
	}

}
