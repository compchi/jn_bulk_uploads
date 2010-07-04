/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.service.ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 *
 * @author martin
 */
public class HttpConnector {

    public String sendRequest(String url, Map<String, String> stringParams) throws IOException {
        String username = "mccomputerconsulting";
        String password = "ons1pAcc3s5";

//        String params = "Username=" + username
//                + "&Output=json"
//                + "&Action=SessionCreate"
//                + "&password=" + password;

        HttpClient client = new HttpClient();
        //clientParams.set
        PostMethod method = new PostMethod(url);
        byte[] responseBody = null;

        try {
            //Header
            Header header = new Header("Accept", "application/json");
            method.setRequestHeader(header);
            //Parameters
            HttpMethodParams methodParams = new HttpMethodParams();
            methodParams.setContentCharset("UTF-8");
            //Body
//            NameValuePair[] body = new NameValuePair[]{
//                new NameValuePair("Username", username),
//                new NameValuePair("Output", "json"),
//                new NameValuePair("Action", "SessionCreate"),
//                new NameValuePair("password", password)
//            };
            List<NameValuePair> body = new ArrayList(stringParams.size());
            for (String paramName : stringParams.keySet()) {
                body.add(new NameValuePair(paramName, stringParams.get(paramName)));
            }

            method.setRequestBody(body.toArray(new NameValuePair[]{}));

            int response = client.executeMethod(method);
            if (response != HttpStatus.SC_OK) {
                throw new IllegalStateException("Http request failed: " + response);
            }
            responseBody = method.getResponseBody();
        } finally {
            method.releaseConnection();
        }

        return new String(responseBody, "UTF-8");
    }
}
