package org.bnss.security;

import java.util.ArrayList;
import java.util.List;

import org.bnss.domain.RadCheck;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/user").hasAnyRole("USER")
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
	    			RadCheck rad = userService.findRadById(u.getRad());
	    			String usr = username.replaceAll(" ", "");
	    			if (rad != null && rad.getUsername().equals(usr)) {
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