/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.service.ws;

import com.jctn.bulkupload.model.json.AbstractJSONResponse;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author martin
 */
public abstract class AbstractWebservice<T extends AbstractJSONResponse> implements JsonMapper<T> {

	protected T parseError(Class<T> returnClass, String jsonString, JSONParser parser) throws ParseException, InstantiationException, IllegalAccessException {
		T retValue = returnClass.newInstance();

		JSONObject jsonObject = null;
		jsonObject = (JSONObject) parser.parse(jsonString);
		JSONObject response = (JSONObject) jsonObject.get("Response");
		JSONObject context = (JSONObject) response.get("Context");
		JSONObject request = (JSONObject) context.get("Request");
		//Errors
		JSONObject errors = (JSONObject) request.get("Errors");
		if (errors != null) {
			Map errMap = (Map) errors.get("Error");
			retValue.setErrors(errMap);
		}

		return retValue;
	}
}
