/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.controller;

import com.jctn.bulkupload.model.User;
import com.jctn.bulkupload.model.json.AbstractJSONResponse;
import com.jctn.bulkupload.model.json.OrganizationReadResponse;
import com.jctn.bulkupload.model.json.SessionCreateResponse;
import com.jctn.bulkupload.model.json.UserAddResponse;
import com.jctn.bulkupload.model.json.UserAddressEditResponse;
import com.jctn.bulkupload.model.json.UserAliasAddResponse;
import com.jctn.bulkupload.model.json.VoicemailboxAddResponse;
import com.jctn.bulkupload.service.ws.OrganizationRead;
import com.jctn.bulkupload.service.ws.SessionCreate;
import com.jctn.bulkupload.service.ws.UserAdd;
import com.jctn.bulkupload.service.ws.UserAddressEdit;
import com.jctn.bulkupload.service.ws.UserAliasAdd;
import com.jctn.bulkupload.service.ws.VoicemailboxAdd;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrTokenizer;
import org.apache.log4j.Logger;

/**
 * Controller class that delegates to the service layer for interacting with the
 * Jctn webservices. UI classes should make method calls directly to this class.
 *
 * @author Martin Constantine
 */
public class BulkUserAddController {

	private static final Logger logger = Logger.getLogger(BulkUserAddController.class);
	private String adminUsername;
	private String adminPassword;
	private String adminDomain;
	private String sessionId;
	private Long organizationId;

	public BulkUserAddController(String adminUsername, String adminPassword, String adminDomain, boolean createSession) {
		this.adminUsername = adminUsername;
		this.adminPassword = adminPassword;
		this.adminDomain = adminDomain;
		if (createSession) {
			initSession();
		}
	}

	/**
	 * Initializes the session by fetching a new session id and the current organization ID.
	 */
	public final void initSession() {
		this.sessionId = createSession(adminUsername, adminPassword, adminDomain);
		this.organizationId = fetchOrganizationId(adminDomain);
	}

	public String getSessionId() {
		return sessionId;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setAdminDomain(String adminDomain) {
		this.adminDomain = adminDomain;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}

	/**
	 * Parse the given CSV file. Columns expected:
	 * <ol>
	 * <li>First name</li>
	 * <li>Last name</li>
	 * <li>Extension (int)</li>
	 * <li>Email address</li>
	 * <li>Voicemail (Y/N)</li>
	 * </ol>
	 * @param csvFile
	 * @return
	 */
	public Collection<User> parseCsv(File csvFile) throws FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(
				new FileInputStream(csvFile)));
		String line = null;
		StrTokenizer tokenizer = StrTokenizer.getCSVInstance();
		tokenizer.setIgnoreEmptyTokens(false);
		Collection<User> results = new ArrayList<User>(20);
		//skip the first line
		line = reader.readLine();
		while ((line = reader.readLine()) != null) {
			tokenizer.reset(line);
			try {
				String[] tokens = tokenizer.getTokenArray();
				if (tokens.length != 5) {
					logger.error("Unprocessable line (does not have 5 values): " + line);
					continue;
				}
				results.add(constructUserFromLineParts(tokens));
			} catch (Exception e) {
				logger.error("Parsing failed for line: " + line, e);
			}
		}
		reader.close();
		return results;
	}

	/**
	 * Builds a user object from the given array.
	 * @param tokens
	 * @return
	 */
	private User constructUserFromLineParts(String[] tokens) {
		User user = new User();
		user.setFirstName(tokens[0]);
		user.setLastName(tokens[1]);
		if (StringUtils.isEmpty(user.getFirstName()) && StringUtils.isEmpty(user.getLastName())) {
			throw new IllegalArgumentException("Neither first name nor last name are available.");
		}

		try {
			user.setExtension(new Integer(tokens[2]));
		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentException("Extension must be a valid integer: " + tokens[2]);
		}

		if (!tokens[3].matches(".+@.+\\.[a-z]+")) {
			throw new IllegalArgumentException("Email address is invalid: " + tokens[3]);
		}

		user.setEmail(tokens[3]);
		user.setAddVoicemail("y".equalsIgnoreCase(tokens[4]));
		return user;
	}

	/**
	 * Uploads a collection of users to the Jctn service.
	 * @param users
	 */
	public void bulkUpload(Collection<User> users) {
		for (User user : users) {
			logger.info("Uploading user: " + user);
			try {
				uploadSingleUser(user);
			} catch (Exception e) {
				logger.error("Error uploading user: " + e.toString(), e);
			} finally {
				logUser(user);
			}
		}
	}

	private void uploadSingleUser(User user) throws IOException {

		//add user--UserAdd
		logger.info("Adding new user: " + user.getEmail());
		logger.debug("Step 1: UserAdd");
		boolean usenamesGood = false;
		int suffix = 1;
		String originalAuthUsername = null;
		String originalUsername = null;
		while (!usenamesGood) {
			execUserAdd(user);
			if(originalAuthUsername == null){
				originalAuthUsername = user.getAuthUsername();
			}
			if(originalUsername == null){
				originalUsername = user.getUsername();
			}

			if (StringUtils.isEmpty(user.getError())) {
				break;
			}

			//@TODO: see what kind of error message is retuned for existing username and auth_username
			//and leverage here
			if (user.getError().contains("username") || user.getError().contains("auth_username")) {
				String suffixStr = suffix + "";
				user.setUsername(originalUsername);
				user.setAuthUsername(originalAuthUsername);
				{
					String currentAuthName = user.getAuthUsername();
					if ((suffixStr + currentAuthName).length() > 32) {
						currentAuthName = currentAuthName.substring(0, (32 - suffixStr.length()));
					}

					user.setAuthUsername(currentAuthName + suffixStr);
				}
				{
					String currentUsername = user.getUsername();
					if ((suffixStr + currentUsername).length() > 32) {
						currentUsername = currentUsername.substring(0, (32 - suffixStr.length()));
					}
					user.setUsername(currentUsername + suffixStr);
				}
				suffix++;
				user.setError(null);
			} else {
				usenamesGood = true;
			}
		}

		if (!StringUtils.isEmpty(user.getError())) {
			return;
		}
		//add extension--UserAliasAdd
		logger.debug("Step 2: UserAliasAdd (extension)");
		execUserAliasAdd(user, new UserAliasAdd());
		if (!StringUtils.isEmpty(user.getError())) {
			return;
		}

		//If the user doesn't want voice mail, we're done.
		if (!user.isAddVoicemail()) {
			logger.debug("User does not want voicemail. All done.");
			return;
		}
		//add vm box--VoicemailoboxAdd
		logger.debug("Step 3: VoicemailBoxAdd");
		execVoicemailBoxAdd(user, new VoicemailboxAdd());
		if (!StringUtils.isEmpty(user.getError())) {
			return;
		}

		//link user to vm--UserAddressEdit.
		logger.debug("Step 4: UserAddressEdit (link voicemail)");
		execUserAddressEdit(user, new UserAddressEdit());
	}

	protected final String createSession(String adminUsername, String adminPassword, String adminDomain) {
		SessionCreate sessionCreate = new SessionCreate();
		Map params = new HashMap();
		params.put("Username", this.adminUsername);
		params.put("Password", this.adminPassword);
		String sessionId_ = null;
		try {
			SessionCreateResponse response = sessionCreate.sendRequest(params);
			//validate the response
			if (!validateResponse(response)) {
				return sessionId_;
			}

			sessionId_ = response.getSessionId();
		} catch (Exception e) {
			handleError("Error creating session.", e);
			throw new IllegalStateException(e);
		}
		return sessionId_;
	}

	private <T extends AbstractJSONResponse> boolean validateResponse(T response) {
		if (response == null) {
			handleError("Null response", new IllegalStateException("Response object is null"));
			return false;
		}

		if (!response.hasError()) {
			//check the role if this is a sessioncreateresponse. This is a logical error check
			if (response instanceof SessionCreateResponse) {
				SessionCreateResponse sessionCreateResponse = (SessionCreateResponse) response;
				List<String> roles = sessionCreateResponse.getRoles();
				if (!roles.contains(SessionCreateResponse.ROLE_ACCOUNT_ADMIN)) {
					handleError("Permission denied", new IllegalArgumentException("User does not have sufficient priviliges."));
					return false;
				}
			}
			return true;
		}

		//We know we have an error here so we'll just let the user know what it was.
		String errorStr = constructErrorString(response.getErrors());
		handleError("Error in response: " + errorStr, new IllegalStateException("Response error"));
		return false;
	}

	private void handleError(String string, Exception e) {
		logger.error(string, e);

		//throw new UnsupportedOperationException("Not yet implemented");
	}

	private Long fetchOrganizationId(String adminDomain) {
		Map params = new HashMap();
		params.put(UserAdd.PARAM_SESSION_ID, getSessionId());
		//get the organization ID via OrgRead
		OrganizationRead orgRead = new OrganizationRead();
		params.put(OrganizationRead.PARAM_DOMAIN, adminDomain);

		OrganizationReadResponse orgReadResponse = null;

		try {
			orgReadResponse = orgRead.sendRequest(params);
			if (!validateResponse(orgReadResponse)) {
				throw new IllegalStateException("Response validation failed");
			}

		} catch (Exception e) {
			handleError("Error reading organization", e);
			throw new IllegalStateException(e);
		}

		return orgReadResponse.getOrgId();
	}

	private String constructErrorString(Map<String, String> errorMap) {
		String errorStr = "";
		for (String errorKey : errorMap.keySet()) {
			errorStr += errorKey + "=>" + errorMap.get(errorKey) + ",";
		}

		if (errorStr.endsWith(",")) {
			errorStr = errorStr.substring(0, errorStr.length() - 1);
		}
		return errorStr;
	}

	/**
	 * 
	 * @param user
	 * @throws IOException
	 */
	private void execUserAdd(User user) throws IOException {
		Map params = new HashMap();
		params.put(UserAdd.PARAM_SESSION_ID, getSessionId());
		UserAdd userAdd = new UserAdd();
		//Username and authusername
		if (user.getAuthUsername() == null) {
			user.setAuthUsername(userAdd.createAuthUsername(user.getEmail(), adminDomain));
		}

		if (user.getUsername() == null) {
			user.setUsername(userAdd.createUsername(user.getEmail().split("@")[0]));
		}
		params.put(UserAdd.PARAM_ORGANIZATION_ID, getOrganizationId() + "");
		params.put(UserAdd.PARAM_USERNAME, user.getUsername());
		params.put(UserAdd.PARAM_AUTH_USERNAME, user.getAuthUsername());
		params.put(UserAdd.PARAM_PASSWORD, user.getPassword());
		params.put(UserAdd.PARAM_PASSWORD_CONFIRM, user.getPassword());
		params.put(UserAdd.PARAM_EMAIL, user.getEmail());
		params.put(UserAdd.PARAM_DOMAIN, this.adminDomain);
		params.put(UserAdd.PARAM_NAME, user.getFullName());
		UserAddResponse response = userAdd.sendRequest(params);
		if (validateResponse(response)) {
			user.setUserAdded(true);
			user.setUserId(response.getUserId());
		} else {
			user.setUserAdded(false);
			user.setError(constructErrorString(response.getErrors()));
		}
	}

	/**
	 * 
	 * @param user
	 */
	void execUserAliasAdd(User user, UserAliasAdd userAliasAdd) throws IOException {
		Map params = new HashMap();
		params.put(UserAliasAdd.PARAM_SESSION_ID, getSessionId());
		params.put(UserAliasAdd.PARAM_ORGANIZATION_ID, getOrganizationId() + "");
		params.put(UserAliasAdd.PARAM_ALIAS_USERNAME, user.getExtension() + "");
		params.put(UserAliasAdd.PARAM_ADDRESS_USERNAME, user.getUsername());
		UserAliasAddResponse response = userAliasAdd.sendRequest(params);
		if (validateResponse(response)) {
			logger.debug("Adding user alias: " + response.getExtension());
			user.setExtension(Integer.parseInt(response.getExtension()));
		} else {
			logger.debug("Failed to retrieve user alias.");
			user.setExtensionAdded(false);
			user.setError(constructErrorString(response.getErrors()));
		}
	}

	/**
	 * Adds a voicemail box to the current organization. The mailbox is intended to be linked to the given user.
	 * This method does NOT link the mail box. That must be done in a separate operation via {@linkplain UserAddressEdit}
	 * @param user
	 * @param voicemailboxAdd
	 * @throws IOException
	 */
	void execVoicemailBoxAdd(User user, VoicemailboxAdd voicemailboxAdd) throws IOException {
		Map params = new HashMap();
		params.put(VoicemailboxAdd.PARAM_SESSION_ID, getSessionId());
		params.put(VoicemailboxAdd.PARAM_ORGANIZATION_ID, getOrganizationId() + "");
		params.put(VoicemailboxAdd.PARAM_USERNAME, user.getVmUsername());
		params.put(VoicemailboxAdd.PARAM_FULLNAME, user.getFullName());
		params.put(VoicemailboxAdd.PARAM_MAILBOX, user.getExtension() + "");
		params.put(VoicemailboxAdd.PARAM_VMBOX_ID, user.getExtension() + "");
		params.put(VoicemailboxAdd.PARAM_DOMAIN, adminDomain);
		VoicemailboxAddResponse response = voicemailboxAdd.sendRequest(params);
		if (validateResponse(response)) {
			logger.debug("Mailbox added: " + response.getVmBoxId());
			user.setVmMailBoxId(response.getVmBoxId());
			user.setVmPassword(response.getPassword());
			user.setVmBoxAdded(true);
		} else {
			logger.debug("Failed to add voicemail box.");
			user.setVmBoxAdded(false);
			user.setError(constructErrorString(response.getErrors()));
		}
	}

	void execUserAddressEdit(User user, UserAddressEdit userAddressEdit) throws IOException {
		Map params = new HashMap();
		params.put(UserAddressEdit.PARAM_SESSION_ID, getSessionId());
		params.put(UserAddressEdit.PARAM_ADDRESS, user.getUsername() + "@" + this.adminDomain);
		params.put(UserAddressEdit.PARAM_DEFAULT_ADDRESS, user.getVmUsername() + "@" + this.adminDomain);
		params.put(UserAddressEdit.PARAM_USERNAME, user.getUsername());
		params.put(UserAddressEdit.PARAM_USER_ID, user.getUserId() + "");
		UserAddressEditResponse response = userAddressEdit.sendRequest(params);
		if (validateResponse(response)) {
			logger.debug("Mailbox successfully linked.");
			user.setVmBoxLinked(true);
		} else {
			logger.debug("Failed to link voicemail box.");
			user.setVmBoxLinked(false);
			user.setError(constructErrorString(response.getErrors()));
		}
	}

	private void logUser(User user) {
		return;
	}
}
