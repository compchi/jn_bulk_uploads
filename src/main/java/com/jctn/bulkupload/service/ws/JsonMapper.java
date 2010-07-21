package com.jctn.bulkupload.service.ws;

import com.jctn.bulkupload.model.json.AbstractJSONResponse;

/**
 * Interface for converting JSON strings to Java POJOs. This is designed to
 * work with children of {@link AbstractJSONResponse}.
 * @author Martin Constantine
 */
public interface JsonMapper<T extends AbstractJSONResponse> {

	/**
	 * Given a JSON string, this method constructs an object of type T from
	 * that string.
	 * @param jsonString
	 * @return
	 */
	T mapJson(String jsonString);
}
