/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.model.json;

/**
 *
 * @author martin
 */
public class VoicemailboxAddResponse extends AbstractJSONResponse {

	private Long vmBoxId;
	private Integer mailBox;
	private Integer password;

	public Integer getMailBox() {
		return mailBox;
	}

	public void setMailBox(Integer mailBox) {
		this.mailBox = mailBox;
	}

	public Integer getPassword() {
		return password;
	}

	public void setPassword(Integer password) {
		this.password = password;
	}

	public Long getVmBoxId() {
		return vmBoxId;
	}

	public void setVmBoxId(Long vmBoxId) {
		this.vmBoxId = vmBoxId;
	}
}
