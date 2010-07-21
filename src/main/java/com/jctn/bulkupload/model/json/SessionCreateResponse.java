/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.model.json;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author martin
 */
public class SessionCreateResponse extends AbstractJSONResponse {

    public static final String ROLE_ACCOUNT_ADMIN = "Account Admin";
    private String sessionId;
    private List<String> roles = new ArrayList<String>(2);

    public void setSessionId(String data) {
        this.sessionId = data;
    }

    public String getSessionId() {
        return sessionId;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void addRole(String role) {
        this.roles.add(role);
    }
}
