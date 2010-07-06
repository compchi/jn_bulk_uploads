/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.service.ws;

import com.jctn.bulkupload.model.json.VoicemailboxAddResponse;
import org.json.simple.JSONObject;

/**
 * Representes API for VoicemailboxAdd service. This just adds the mailbox to the organization,
 * making it available for use. The UserAddressEdit API must be subsequently leveraged to actually
 * map a user to this mailbox.
 * 
 * @author martin
 */
public class VoicemailboxAdd extends AbstractWebservice<VoicemailboxAddResponse> {

	@Override
	public VoicemailboxAddResponse mapJson(String jsonString) {
		VoicemailboxAddResponse addResponse = new VoicemailboxAddResponse();
		try {
			JSONObject serviceObj = parseResultAndError(addResponse, jsonString, "VoicemailboxAdd");
			if (serviceObj == null) {
				return addResponse;
			}

			JSONObject vmBox = (JSONObject) serviceObj.get("Voicemailbox");
			addResponse.setMailBox(new Integer((String) vmBox.get("Mailbox")));
			addResponse.setVmBoxId(new Long((String) vmBox.get("VoicemailboxId")));
			addResponse.setPassword(new Integer((String) vmBox.get("Password")));
		} catch (Exception e) {
			throw new IllegalStateException("Error processing VoicemailboxAdd response:", e);
		}
		return addResponse;
	}
}
