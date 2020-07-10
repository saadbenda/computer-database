package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan({ "service", "persistence", "mapper", "validation", "controllers","model" })
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
 
    /*
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) 
      throws Exception {
        auth.inMemoryAuthentication().withUser("user")
          .password(passwordEncoder().encode("password")).roles("USER");
    }
    */
    @Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
			throws Exception {
    	 auth.inMemoryAuthentication().withUser("mkyong").password("123456").roles("USER");
   	  auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");
   	  auth.inMemoryAuthentication().withUser("dba").password("123456").roles("DBA");
   	 auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("password")).roles("ADMIN");
	}
    
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return rawPassword.toString().equals(encodedPassword);
            }
        };
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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
    
    @Override
	protected void configure(HttpSecurity http) throws Exception {
    	//http.cors().and().csrf().disable();
    
    	
    	
    	 http
         .csrf().disable()
         .authorizeRequests()
         .antMatchers("/admin/**").hasRole("ADMIN")
         .antMatchers("/anonymous*").anonymous()
         .antMatchers("/login*").permitAll()
         .anyRequest().authenticated()
         .and()
         .formLogin()
         .loginPage("/login")
         .loginProcessingUrl("/login_o")
         .defaultSuccessUrl("/homepage.html", true)
         //.failureUrl("/login.html?error=true")
        
         .and()
         .logout()
         .logoutUrl("/perform_logout")
         .deleteCookies("JSESSIONID")
         ;
    	/*
    	http.authorizeRequests()
		.antMatchers("/login/**").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/dba/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
		.and().formLogin();
    	
    	
    	http.authorizeRequests().anyRequest().permitAll();
    	*/
	
	}
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