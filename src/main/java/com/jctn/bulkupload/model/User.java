/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.model;

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
		return "User{" + "firstName=" + firstName + "lastName=" + lastName + "email=" + email + "extension=" + extension + "addVoicemail=" + addVoicemail + '}';
	}
}
