/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.controller;

import com.jctn.bulkupload.model.User;
import com.jctn.bulkupload.model.json.AbstractJSONResponse;
import com.jctn.bulkupload.model.json.SessionCreateResponse;
import com.jctn.bulkupload.service.ws.SessionCreate;
import com.jctn.bulkupload.service.ws.UserAdd;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
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

	public BulkUserAddController(String adminUsername, String adminPassword, String adminDomain) {
		this.adminUsername = adminUsername;
		this.adminPassword = adminPassword;
		this.adminDomain = adminDomain;
		this.sessionId = createSession(adminUsername, adminPassword, adminDomain);
	}

	public String getSessionId() {
		return sessionId;
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
	public Collection<User> parseCsv(File csvFile) {
		return new ArrayList<User>(0);
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
			}
		}
	}

	private void uploadSingleUser(User user) {
		//add user--UserAdd
		UserAdd userAdd = new UserAdd();
		Map params = new HashMap();
		params.put(UserAdd.PARAM_SESSION_ID, this.sessionId);
		//add extension--UserAliasAdd
		//add vm box--VoicemailoboxAdd
		//link user to vm--UserAddressEdit.
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
		}
		return sessionId_;
	}

	private <T extends AbstractJSONResponse> boolean validateResponse(T response) {
		//TODO implement based on type of T. E.g. is T instanceof SessionCreateResponse, check the role, etc

		return true;
	}

	private void handleError(String string, Exception e) {
		logger.error(string, e);
		//throw new UnsupportedOperationException("Not yet implemented");
	}
}
