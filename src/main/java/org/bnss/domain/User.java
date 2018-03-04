package org.bnss.domain;

import javax.persistence.*;

import org.jboss.aerogear.security.otp.api.Base32;

@Entity(name = "User")
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "username", unique = true, length = 20, nullable = false)
	private String username;
	
	@Column(name = "password", length = 100, nullable = false)
	private String password;
	
	@Column(name = "secret", length = 100)
	private String secret;
	
	@Column(name = "cert", columnDefinition = "TINYBLOB")
	private byte[] cert;

	public User(Long id, String username, String password, String secret) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.secret = secret;
	}

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
	
	public byte[] getCert() {
		return cert;
	}

	public void setCert(byte[] cert) {
		this.cert = cert;
	}

}
