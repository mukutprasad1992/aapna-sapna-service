package com.aapnasapna.AapnaSapna.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "username")
	@NotBlank(message = "Name is mandatory")
	private String userName;
	@Column(name = "password")
	private String password;
	@Column(name = "role")
	private String role;
	@Column(name = "full_name")
	private String fullName;
	@Column(name = "country")
	private String country;
	@Column(name = "enabled")
	private short enabled;

	public String getCountry() {
		return country;
	}

	public short getEnabled() {
		return enabled;
	}

	public String getFullName() {
		return fullName;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public String getUserName() {
		return userName;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setEnabled(short enabled) {
		this.enabled = enabled;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}