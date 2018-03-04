package org.bnss.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DataDTO {

	private Long id;
	private String name;
	private String from;
	private String recipient;
	private byte[] file;
	private byte[] key;
	private byte[] hash;

	@JsonCreator
	public DataDTO(@JsonProperty("id") Long id, @JsonProperty("name") String name, @JsonProperty("from") String from,
			@JsonProperty("recipient") String recipient, @JsonProperty("file") byte[] file,
			@JsonProperty("key") byte[] key, @JsonProperty("hash") byte[] hash) {
		this.id = id;
		this.name = name;
		this.recipient = recipient;
		this.file = file;
		this.key = key;
		this.hash = hash;
		this.from = from;
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
