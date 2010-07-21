/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.model.json;

/**
 * Wrapper for user-alias aka, the user's extension. Currently only stores the
 * extension number/string.
 *
 * @author martin
 */
public class UserAliasAddResponse extends AbstractJSONResponse {

	private String extension;

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
}
