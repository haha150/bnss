package org.bnss.domain;

import javax.persistence.*;

import org.jboss.aerogear.security.otp.api.Base32;

@Entity(name = "User")
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "radcheck_id", length = 100)
	private Long rad;
	
	@Column(name = "secret", length = 100)
	private String secret;
	
	@Column(name = "cert", length = 100)
	private String cert;

	public User(Long id, String username, String password, String secret) {
		this.id = id;
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

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getCert() {
		return cert;
	}

	public void setCert(String cert) {
		this.cert = cert;
	}

	public Long getRad() {
		return rad;
	}

	public void setRad(Long rad) {
		this.rad = rad;
	}

	

}
