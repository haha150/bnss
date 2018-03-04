package org.bnss.domain;

import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Data")
@Table(name = "datas")
public class Data {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name", length = 100, nullable = false)
	private String name;
	
	@Column(name = "recipient", length = 100, nullable = false)
	private String recipient;
	
	@Column(name = "file", columnDefinition = "LONGBLOB", nullable = false)
	private byte[] file;
	
	@Column(name = "symmetrickey", length = 100, nullable = false)
	private String key;
	
	@Column(name = "hash", length = 100, nullable = false)
	private String hash;

	public Data() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}
	

}
