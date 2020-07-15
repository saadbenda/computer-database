package spring;

import java.util.Locale;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import service.UserService;

@Configuration
@EnableWebSecurity
@ComponentScan({ "service", "persistence", "mapper", "validation", "controllers","model" })
@EnableJpaRepositories({ "service", "persistence", "mapper", "validation", "controllers", "model" })
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
    DigestAuthenticationFilter digestAuthenticationFilter;

    @Autowired
    DigestAuthenticationEntryPoint entryPoint;
    /*
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) 
      throws Exception {
        auth.inMemoryAuthentication().withUser("user")
          .password(passwordEncoder().encode("password")).roles("USER");
    }
    */
	
	  @Autowired
	    UserService userService;
	
    @Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
			throws Exception {
    	
    	auth.userDetailsService(userService);
auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("password")).roles("ADMIN");
    }
   	 
 	/*
   	 List<UserDTO> users = ServiceUser.getAllCompany();
	
	for (UserDTO u : users) {
		auth.inMemoryAuthentication().passwordEncoder(passwordEncoder).withUser(u.getUsername())
				.password(passwordEncoder.encode(u.getPassword())).roles(u.getRole());
	} */
   	 
   	 
	
    
    
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence rawPassword) {
//                return rawPassword.toString();
//            }
//            @Override
//            public boolean matches(CharSequence rawPassword, String encodedPassword) {
//                return rawPassword.toString().equals(encodedPassword);
//            }
//        };
//    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        //return NoOpPasswordEncoder.getInstance();
    }
    
    
    @Bean
    public DigestAuthenticationEntryPoint digestEntryPoint() {
        DigestAuthenticationEntryPoint bauth = new DigestAuthenticationEntryPoint();
        bauth.setRealmName("Digest WF Realm");
        bauth.setKey("CDBKey");
        return bauth;
    }

    @Bean
    public DigestAuthenticationFilter digestAuthenticationFilter(UserService userDetailsService) throws Exception {
        DigestAuthenticationFilter digestAuthenticationFilter = new DigestAuthenticationFilter();
        digestAuthenticationFilter.setUserDetailsService(userDetailsService);
        digestAuthenticationFilter.setAuthenticationEntryPoint(digestEntryPoint());
        return digestAuthenticationFilter;
    }
    
    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }
    
    
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//          .anyRequest().authenticated()
//          .and().httpBasic();
//        http
//        .authorizeRequests()
//        .antMatchers("/admin/**").hasRole("ADMIN")
//        .anyRequest().authenticated();
//    }
    
    
//    @Override
//    public void configure(AuthenticationManagerBuilder auth)
//            throws Exception {
//        //builder.userDetailsService();
//        
//        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
//        auth.inMemoryAuthentication().withUser("mkyong").password("123456").roles("USER");
//     	  auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");
//     	  auth.inMemoryAuthentication().withUser("dba").password("123456").roles("DBA");
//     	 auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("password")).roles("ADMIN");
//        
//    }

    protected void configure(final HttpSecurity http) throws Exception {
    	
    	http
		.csrf()
		.disable();
	
	http
	.authorizeRequests()
		.antMatchers("/login").permitAll()
		.antMatchers("/dashboard").access("hasAnyRole('USER', 'ADMIN')")
		.antMatchers("/editComputer", "/addComputer").access("hasRole('ADMIN')");
//		.and()
//        .exceptionHandling(e -> e.authenticationEntryPoint(entryPoint)).addFilter(digestAuthenticationFilter);
		
	
	http
	.authorizeRequests().and().formLogin()
	//.loginPage("/login")
		.loginProcessingUrl("/perform_login")
		.defaultSuccessUrl("/dashboard")
		.failureUrl("/failure")
		.usernameParameter("username")
		.passwordParameter("password")
		.permitAll()
	.and()
		.logout()
		.logoutUrl("/logout")
		.permitAll();
    }
    
    
    
    
    
//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
////        http
////          .csrf().disable()
////          .authorizeRequests()
////          .antMatchers("/admin/**").hasRole("ADMIN")
////          .antMatchers("/anonymous*").anonymous()
////          .antMatchers("/login*").permitAll()
////          .anyRequest().authenticated()
////          .and()
////          .formLogin()
////          .loginPage("/login.html")
////          .loginProcessingUrl("/perform_login")
////          .defaultSuccessUrl("/homepage.html", true);
//          //.failureUrl("/login.html?error=true")
//          
//        http
//        .csrf().disable()
//        .authorizeRequests()
//            .anyRequest().authenticated()
//            .and()
//        .formLogin()
//            .loginPage("/login")
//            .permitAll();
//    
//    	 
//    	/*
//    	http.authorizeRequests()
//		.antMatchers("/login/**").access("hasRole('ROLE_ADMIN')")
//		.antMatchers("/dba/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
//		.and().formLogin();
//    	
//    	
//    	http.authorizeRequests().anyRequest().permitAll();
//    	*/
//	
//}
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//          .anyRequest().authenticated()
//          .and().formLogin()
//          .loginPage("/login").permitAll();
//    }
    
    
    
   
    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) 
      throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
          .withUser("spring")
          .password(encoder.encode("secret"))
          .roles("USER");
    }
    */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/resources/**");
    }
    
    
}