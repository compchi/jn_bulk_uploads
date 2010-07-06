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

	protected void parseError(T responseObj, JSONObject parsedFile) {

		JSONObject response = (JSONObject) parsedFile.get("Response");
		JSONObject context = (JSONObject) response.get("Context");
		JSONObject request = (JSONObject) context.get("Request");
		//Errors
		JSONObject errors = (JSONObject) request.get("Errors");
		if (errors != null) {
			Map errMap = (Map) errors.get("Error");
			responseObj.setErrors(errMap);
		}
	}

	protected JSONObject parseResultAndError(T responseObj, String jsonString, String serviceName) throws ParseException {
		if (responseObj == null) {
			throw new IllegalArgumentException("responseObj cannot be null");
		}

		JSONObject resultObj = null;

		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
		parseError(responseObj, jsonObject);
		JSONObject response = (JSONObject) jsonObject.get("Response");
		JSONObject jresult = (JSONObject) response.get("Result");
		if (jresult != null) {
			resultObj = (JSONObject) jresult.get(serviceName);
		}

		return resultObj;
	}
}
