package org.bnss.security;

import java.util.ArrayList;
import java.util.List;

import org.bnss.domain.User;
import org.bnss.service.UserService;
import org.jboss.aerogear.security.otp.Totp;
import org.jboss.aerogear.security.otp.api.Base32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder encoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String verificationCode = ((CustomWebAuthenticationDetails) authentication.getDetails()).getVerificationCode();

		List<User> users = userService.getAllUsers();
		User user = null;
		for (User u : users) {
			if (u.getUsername().equals(authentication.getName())
					&& encoder.matches(authentication.getCredentials().toString(), u.getPassword())) {
				user = u;
				break;
			}
		}

		if ((user == null)) {
			throw new BadCredentialsException("Invalid username or password");
		}

		Totp totp = new Totp(user.getSecret());
		if (!isValidLong(verificationCode) || !totp.verify(verificationCode)) {
			throw new BadCredentialsException("Invalid verfication code");
		}

		return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(),
				AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
	}

	private boolean isValidLong(String code) {
		try {
			Long.parseLong(code);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	public void create() {
		User u = new User();
		u.setPassword(encoder.encode("david"));
		u.setUsername("ChunHeng Jen");
		u.setSecret(Base32.random());
		
		User u2 = new User();
		u2.setPassword(encoder.encode("ali"));
		u2.setUsername("Ali Symeri");
		u2.setSecret(Base32.random());
		
		User u3 = new User();
		u3.setPassword(encoder.encode("farhad"));
		u3.setUsername("Farhad Zareafifi");
		u3.setSecret(Base32.random());
		
		User u4 = new User();
		u4.setPassword(encoder.encode("samie"));
		u4.setUsername("Samie Mostafavi");
		u4.setSecret(Base32.random());
		
		userService.addUser(u);
		userService.addUser(u2);
		userService.addUser(u3);
		userService.addUser(u4);

	}
}