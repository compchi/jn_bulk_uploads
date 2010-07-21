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

	/**
	 * Parameter name for organization ID.
	 */
	public static final String PARAM_ORGANIZATION_ID = "OrganizationId";
	/**
	 * Parameter name for domain.
	 */
	public static final String PARAM_DOMAIN = "Domain";
	/**
	 * Parameter name for username. The value of this parameter is normally vm.&lt;authusername&gt;.
	 */
	public static final String PARAM_USERNAME = "Username";
	/**
	 * Parameter name for mailbox. The value of this parameter is normally the same as the extension.
	 */
	public static final String PARAM_MAILBOX = "Mailbox";
	/**
	 * Parameter name for mailbox id. This is usually assigned by the service, so it's unclear why it is needed.
	 * Using the same number as {@linkplain #PARAM_MAILBOX} for the value should be enough.
	 */
	public static final String PARAM_VMBOX_ID = "VoicemailboxId";
	/**
	 * Parameter name for full name.
	 */
	public static final String PARAM_FULLNAME = "Fullname";

	public VoicemailboxAdd() {
		super.action = "VoicemailboxAdd";
	}

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
