package org.bnss.security;

import java.util.ArrayList;
import java.util.List;

import org.bnss.domain.RadCheck;
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
			RadCheck rad = userService.findRadById(u.getRad());
			String usr = authentication.getName().replaceAll(" ", "");
			if (rad != null && rad.getUsername().equals(usr)
					&& authentication.getCredentials().toString().equals(rad.getValue())) {
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
		List<RadCheck> users = userService.getAllRad();
		for (RadCheck r : users) {
			if (r.getId() == 11) {
				User u = new User();
				u.setRad(r.getId());
				u.setSecret("ODHXFPOXRTPVTXSE");
				u.setCert("/Users/Ali/Downloads/bnss-2/certs/employee3.der");
				userService.addUser(u);
			} else if (r.getId() == 10) {
				User u2 = new User();
				u2.setRad(r.getId());
				u2.setSecret("6DCE7ZG6O4TCLBIR");
				u2.setCert("/Users/Ali/Downloads/bnss-2/certs/employee1.der");
				userService.addUser(u2);
			} else if (r.getId() == 12) {
				User u3 = new User();
				u3.setRad(r.getId());
				u3.setSecret("VIT3XFTNFDBTRZXK");
				u3.setCert("/Users/Ali/Downloads/bnss-2/certs/employee2.der");
				userService.addUser(u3);
			} else if (r.getId() == 13) {
				User u4 = new User();
				u4.setRad(r.getId());
				u4.setSecret("GYOY3ZZTLWH4K3R4");
				u4.setCert("/Users/Ali/Downloads/bnss-2/certs/employee4.der");
				userService.addUser(u4);
			}
		}

	}
}