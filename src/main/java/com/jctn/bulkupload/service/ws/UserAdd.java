/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.service.ws;

import com.jctn.bulkupload.model.json.UserAddResponse;
import com.jctn.bulkupload.util.PronouncableRandomString;
import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONObject;

/**
 *
 * @author martin
 */
public class UserAdd extends AbstractWebservice<UserAddResponse> {

	public static final String PARAM_ORGANIZATION_ID = "OrganizationId";
	public static final String PARAM_USERNAME = "Username";
	public static final String PARAM_DOMAIN = "Domain";
	public static final String PARAM_NAME = "Name";
	public static final String PARAM_EMAIL = "Email";
	public static final String PARAM_AUTH_USERNAME = "AuthUsername";
	public static final String PARAM_PASSWORD = "Password";
	public static final String PARAM_PASSWORD_CONFIRM = "PasswordConfirm";
	private PronouncableRandomString randUsernameGenerator;

	public UserAdd() {
		super.action = "UserAdd";
		this.randUsernameGenerator = new PronouncableRandomString();
	}

	public void setRandUsernameGenerator(PronouncableRandomString randUsernameGenerator) {
		this.randUsernameGenerator = randUsernameGenerator;
	}

	/**
	 * Creates an auth username per the algo described in the technical specs.
	 * @param emailAddress
	 * @param onsipDomain
	 * @return
	 */
	public String createAuthUsername(String emailAddress, String onsipDomain) {
		//1. get onsip username
		String domainUsername = onsipDomain.split("\\.")[0];
		domainUsername = replaceNonalphaNumeric(domainUsername);
		//2. get email username
		String emailUsername = emailAddress.split("@")[0];
		emailUsername = replaceNonalphaNumeric(emailUsername);
		//3. join 1) and 2) with underscore
		String authUsername = domainUsername + "_" + emailUsername;
		//4.
		if (authUsername.length() >= 32 || !authUsername.matches("^[a-z]([a-z0-9_])*[a-z0-9]$")) {
			//5.
			authUsername = this.randUsernameGenerator.getRandomPronouncableString();
		}

		return authUsername;
	}

	/**
	 * Replaces all non alphanumeric lowercase chars with an underscore. If the resulting string ends up starting
	 * or ending with an underscore, then those are stripped respectively.
	 * @param input
	 * @return
	 */
	private String replaceNonalphaNumeric(String input) {
		input = input.replaceAll("[^a-z0-9_]", "_");
		input = input.startsWith("_") ? StringUtils.stripStart(input, "_") : input;
		input = input.endsWith("_") ? StringUtils.stripEnd(input, "_") : input;
		return input;
	}

	/**
	 * Creates a username based on the algorithm described in the tech spec.
	 * @param emailUsername
	 * @return
	 */
	public String createUsername(String emailUsername) {
		emailUsername = emailUsername.trim();
		emailUsername = emailUsername.replaceAll("[^a-z0-9_\\-\\.]+", "_");
		String legalExpression = "^[a-z][_a-z0-9-]*(\\.[_a-z0-9-]+)*$";

		if (!emailUsername.matches(legalExpression) || emailUsername.length() >= 32) {
			emailUsername = "user";
		}

		return emailUsername;
	}

	@Override
	public UserAddResponse mapJson(String jsonString) {
		UserAddResponse userAddResponse = new UserAddResponse();
		try {

			JSONObject jUserAdd = parseResultAndError(userAddResponse, jsonString, "UserAdd");
			if (jUserAdd == null) {
				return userAddResponse;
			}

			JSONObject jUser = (JSONObject) jUserAdd.get("User");
			userAddResponse.setUsername((String) jUser.get("Username"));
			userAddResponse.setUserId(Long.parseLong((String) jUser.get("UserId")));
		} catch (Exception e) {
			throw new IllegalStateException("Error processing response for UserAdd:", e);
		}
		return userAddResponse;
	}
}
