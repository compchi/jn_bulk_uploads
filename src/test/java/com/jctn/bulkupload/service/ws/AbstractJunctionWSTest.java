/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.service.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import junit.framework.TestCase;

/**
 *
 * @author martin
 */
public abstract class AbstractJunctionWSTest extends TestCase {

	public AbstractJunctionWSTest(String name) {
		super(name);
	}

	public AbstractJunctionWSTest() {
		super();
	}

	/**
	 * Loads the contents of the given resource into a string.
	 * @param resourcePath Path to text resource representing a request/response to be used for testing.
	 * @return String with JSON content
	 * @throws IOException
	 */
	protected String loadTestResource(String resourcePath) throws IOException {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(
				this.getClass().getResourceAsStream(resourcePath)));

		String jsonString = "";

		while (reader.ready()) {
			jsonString += reader.readLine();
		}
		reader.close();
		return jsonString;
	}
}
