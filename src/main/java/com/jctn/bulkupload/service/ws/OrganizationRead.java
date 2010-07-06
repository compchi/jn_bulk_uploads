/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.service.ws;

import com.jctn.bulkupload.model.json.OrganizationReadResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author martin
 */
public class OrganizationRead extends AbstractWebservice<OrganizationReadResponse> {

	@Override
	public OrganizationReadResponse mapJson(String jsonString) {
		OrganizationReadResponse result = new OrganizationReadResponse();

		try {

			JSONObject jOrgRead = parseResultAndError(result, jsonString, "OrganizationRead");

			if(jOrgRead == null){
				return result;
			}
			
			JSONObject jOrg = (JSONObject) jOrgRead.get("Organization");
			Long orgId = Long.parseLong((String) jOrg.get("OrganizationId"));
			result.setOrgId(orgId);

		} catch (Exception e) {
			throw new IllegalStateException("Error processing response:", e);
		}

		return result;
	}
}
