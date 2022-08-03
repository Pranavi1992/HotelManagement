package com.project.hm.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class security extends WebSecurityConfigurerAdapter {
	  @Autowired
	    private UserDetailsService userDetailsService;

		/*
		 * @Bean AuthenticationProvider authenticationProvider() {
		 * DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		 * provider.setUserDetailsService(userDetailsService);
		 * provider.setPasswordEncoder(encoder()); return provider; }
		 */
         public void configure(AuthenticationManagerBuilder authenBuilder) throws Exception
         {
        	 authenBuilder.userDetailsService(userDetailsService).passwordEncoder(encoder());
         }
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	    	http.csrf().disable();
	    	http.authorizeRequests()
	                .antMatchers("/**")
	                .permitAll()
					/*
					 * .antMatchers("/getAllUsers") .hasAuthority("ADMIN")
					 */
	                .anyRequest()
	                .authenticated()
	               
	                ;
	    }
	    @Bean
	    PasswordEncoder encoder()
	    {
	    	return NoOpPasswordEncoder.getInstance();
	    }
	    
	   @Bean(name=BeanIds.AUTHENTICATION_MANAGER)
	   public  AuthenticationManager authManger() throws Exception {
	    	return super.authenticationManager();
	    }

}
