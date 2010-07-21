/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.model.json;

import java.io.Serializable;
import java.util.Map;

/**
 * Represents an abstract JSON-formatted response.
 *
 * @author martin
 */
public abstract class AbstractJSONResponse implements Serializable {

	static final String NAME_ERRORS = "Errors";
	static final String NAME_ERROR = "Error";
	static final String NAME_PARAMETER = "Parameter";
	static final String NAME_CODE = "Code";
	static final String NAME_MESSAGE = "Message";
	private Map errors;

	public Map getErrors() {
		return errors;
	}

	public void setErrors(Map errors) {
		this.errors = errors;
	}

	public boolean hasError() {
		return errors != null && !errors.isEmpty();
	}
}
