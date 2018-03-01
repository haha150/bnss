package org.bnss.security;

import java.util.ArrayList;
import java.util.List;

import org.bnss.service.UserService;
import org.jboss.aerogear.security.otp.Totp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;
	
	@Autowired
	UserService userService;
	
	@Autowired
    private CustomWebAuthenticationDetailsSource authenticationDetailsSource;
	
	 @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        super.configure(auth);
	    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/user").hasAnyRole("USER")
				.antMatchers("/logged").hasAnyRole("USER")
				.anyRequest().authenticated()
			.and()
				.formLogin().loginPage("/login").authenticationDetailsSource(authenticationDetailsSource).permitAll()
			.and()
				.x509()
					.subjectPrincipalRegex("CN=(.*?)(?:,|$)")
					.userDetailsService(userDetailsService());
	}
	
	@Bean
    public UserDetailsService userDetailsService() {
        return (username -> {
        	List<org.bnss.domain.User> users = userService.getAllUsers();
    		for (org.bnss.domain.User u : users) {
    			if (u.getUsername().equals(username)) {
    				return new User(username, "", AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
    			}
    		}
    		throw new BadCredentialsException("Invalid common name");
        });
    }
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.customAuthenticationProvider);
    }
	
}