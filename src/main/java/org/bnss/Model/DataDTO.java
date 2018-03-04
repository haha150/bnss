package org.bnss.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DataDTO {

	private Long id;
	private String name;
	private String recipient;
	private byte[] file;
	private String key;
	private String hash;

	@JsonCreator
	public DataDTO(@JsonProperty("id") Long id, @JsonProperty("name") String name,
			@JsonProperty("recipient") String recipient, @JsonProperty("file") byte[] file,
			@JsonProperty("key") String key, @JsonProperty("hash") String hash) {
		this.id = id;
		this.name = name;
		this.recipient = recipient;
		this.file = file;
		this.key = key;
		this.hash = hash;
	}

	public DataDTO() {

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
