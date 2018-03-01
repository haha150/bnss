package org.bnss.security;

import java.util.ArrayList;
import java.util.List;

import org.bnss.domain.User;
import org.bnss.service.UserService;
import org.jboss.aerogear.security.otp.Totp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String verificationCode = ((CustomWebAuthenticationDetails) authentication.getDetails()).getVerificationCode();

		/*if (authentication.getName().equals("admin") && authentication.getCredentials().equals("admin")) {
			List<GrantedAuthority> grantedAuths = new ArrayList<>();
			grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
			return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(),
					grantedAuths);
		} else {
			return null;
		}*/

		List<User> users = userService.getAllUsers();
		User user = null;
		for (User u : users) {
			if (u.getUsername().equals(authentication.getName())
					&& u.getPassword().equals(authentication.getCredentials())) {
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
}