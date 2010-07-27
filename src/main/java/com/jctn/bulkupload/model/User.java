/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.model;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

/**
 * POJO representing a single user. This will likely be a row in some CSV input file.
 * @author martin
 */
public class User {

	private String firstName;
	private String lastName;
	private String email;
	private Integer extension;
	private boolean addVoicemail;
	private String password;
	private boolean userAdded;
	private Long userId;
	private String error;
	private boolean extensionAdded;
	private Long vmMailBoxId;
	private Integer vmPassword;
	private boolean vmBoxAdded;
	private boolean vmBoxLinked;
	private String username;
	private String authUsername;

	public String getError() {
		return error;
	}

	public Long getUserId() {
		return userId;
	}

	public boolean isUserAdded() {
		return userAdded;
	}

	public void setUserAdded(boolean userAdded) {
		this.userAdded = userAdded;
	}

	public boolean isAddVoicemail() {
		return addVoicemail;
	}

	public void setAddVoicemail(boolean addVoicemail) {
		this.addVoicemail = addVoicemail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getExtension() {
		return extension;
	}

	public void setExtension(Integer extension) {
		this.extension = extension;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "User{" + "error=" + (StringUtils.isEmpty(error) ? "NONE" : error) + ": firstName=" + firstName + ";lastName=" + lastName + ";email=" + email + ";extension=" + extension + ";addVoicemail=" + addVoicemail + ";password=" + password + ";userAdded=" + userAdded + ";userId=" + userId + ";extensionAdded=" + extensionAdded + ";vmMailBoxId=" + vmMailBoxId + ";vmPassword=" + vmPassword + ";vmBoxAdded=" + vmBoxAdded + ";vmBoxLinked=" + vmBoxLinked + ";username=" + username + ";authUsername=" + authUsername + '}';
	}

	public String getUsername() {
		return username;
	}

	public String getAuthUsername() {
		return authUsername;
	}

	public void setAuthUsername(String authUsername) {
		this.authUsername = authUsername;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		if (password == null) {
			password = RandomStringUtils.randomAlphanumeric(8);
		}
		return password;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setError(String errorString) {
		this.error = errorString;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}

	public void setExtensionAdded(boolean extAdded) {
		this.extensionAdded = extAdded;
	}

	public boolean isExtensionAdded() {
		return extensionAdded;
	}

	public String getVmUsername() {
		return "vm." + getUsername();
	}

	public Long getVmMailBoxId() {
		return vmMailBoxId;
	}

	public void setVmMailBoxId(Long vmMailBoxId) {
		this.vmMailBoxId = vmMailBoxId;
	}

	public boolean isVmBoxAdded() {
		return vmBoxAdded;
	}

	public void setVmBoxAdded(boolean vmBoxAdded) {
		this.vmBoxAdded = vmBoxAdded;
	}

	public boolean isVmBoxLinked() {
		return vmBoxLinked;
	}

	public void setVmBoxLinked(boolean vmBoxLinked) {
		this.vmBoxLinked = vmBoxLinked;
	}

	public Integer getVmPassword() {
		return vmPassword;
	}

	public void setVmPassword(Integer vmPassword) {
		this.vmPassword = vmPassword;
	}
}
