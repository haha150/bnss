package org.bnss.domain;

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
	
	@Column(name = "sender", length = 100, nullable = false)
	private String from;
	
	@Column(name = "recipient", length = 100, nullable = false)
	private String recipient;
	
	@Column(name = "file", columnDefinition = "LONGBLOB", nullable = false)
	private byte[] file;
	
	@Column(name = "symmetrickey", columnDefinition = "TINYBLOB", nullable = false)
	private byte[] key;
	
	@Column(name = "hash", columnDefinition = "TINYBLOB", nullable = false)
	private byte[] hash;

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

	public byte[] getKey() {
		return key;
	}

	public void setKey(byte[] key) {
		this.key = key;
	}

	public byte[] getHash() {
		return hash;
	}

	public void setHash(byte[] hash) {
		this.hash = hash;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}
	

}
