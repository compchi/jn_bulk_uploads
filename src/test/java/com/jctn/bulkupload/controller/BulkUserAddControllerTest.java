/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.controller;

import java.io.File;
import com.jctn.bulkupload.model.json.UserAddressEditResponse;
import com.jctn.bulkupload.model.json.UserAliasAddResponse;
import com.jctn.bulkupload.service.ws.UserAliasAdd;
import com.jctn.bulkupload.model.User;
import com.jctn.bulkupload.model.json.VoicemailboxAddResponse;
import com.jctn.bulkupload.service.ws.AbstractJunctionWSTest;
import com.jctn.bulkupload.service.ws.HttpConnector;
import com.jctn.bulkupload.service.ws.UserAddressEdit;
import com.jctn.bulkupload.service.ws.VoicemailboxAdd;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.lang.StringUtils;
import static org.mockito.Mockito.*;

/**
 *
 * @author martin
 */
public class BulkUserAddControllerTest extends AbstractJunctionWSTest {

	private BulkUserAddController controller;
	private User testUser;

	public BulkUserAddControllerTest(String testName) {
		super(testName);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		controller = new BulkUserAddController(null, "mccomputerconsulting", "ons1pAcc3s5", "mccomputerconsulting.onsip.com", false);
		//controller.setAdminUsername("mccomputerconsulting");
		//controller.setAdminPassword("ons1pAcc3s5");
		//controller.setAdminDomain("mccomputerconsulting.onsip.com");
		testUser = mock(User.class);
		testUser.setEmail("testuser1@mccomputerconsulting.onsip.com");
		testUser.setFirstName("Test2");
		testUser.setLastName("User2");
		testUser.setExtension(7002);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		controller = null;
		testUser = null;
	}

	public void testInitialize() throws Exception {
		System.out.println("testInitialize");
		controller.initSession();
		assertNotNull(controller.getSessionId());
		assertTrue(StringUtils.isNotBlank(controller.getSessionId()));
	}

	/**
	 * Test of parseCsv method, of class BulkUserAddController.
	 */
	public void testParseCsvGood() throws URISyntaxException, FileNotFoundException, IOException {
		System.out.println("parseCsvGood");
		File csvFile = new File(this.getClass().getResource("/goodline.csv").toURI());

		Collection<User> result = controller.parseCsv(csvFile);
		assertNotNull(result);
		assertEquals(1, result.size());
		User user = result.iterator().next();
		assertEquals("Test", user.getFirstName());
		assertEquals("User", user.getLastName());
		assertEquals(new Integer(1234), user.getExtension());
		assertEquals("test_user@domain.com", user.getEmail());
		assertEquals(false, user.isAddVoicemail());
	}

	public void testParseCsvBad() throws URISyntaxException, FileNotFoundException, IOException {
		System.out.println("parseCsvBad");
		File csvFile = new File(this.getClass().getResource("/badline.csv").toURI());

		Collection<User> result = controller.parseCsv(csvFile);
		assertNotNull(result);
		assertEquals(0, result.size());
	}

	/**
	 * Test of bulkUpload method, of class BulkUserAddController.
	 */
	public void testBulkUpload() {
		System.out.println("bulkUpload");
		Collection<User> users = new ArrayList<User>(1);
		users.add(testUser);

		//This is really an integration test.
		//controller.bulkUpload(users);
	}

	public void testExecUserAliasAddGood() throws Exception {
		System.out.println("execUserAliasAddGood");
		String goodUserAliasResponse = loadTestResource("/useraliasadd_good_response.json");
		HttpConnector connector = mock(HttpConnector.class);
		when(connector.sendRequest(anyString(), anyMap())).thenReturn(goodUserAliasResponse);

		UserAliasAdd realUserAliasAdd = new UserAliasAdd();
		UserAliasAddResponse response = realUserAliasAdd.mapJson(goodUserAliasResponse);

		UserAliasAdd mockUserAliasAdd = mock(UserAliasAdd.class);
		mockUserAliasAdd.setHttpConnector(connector);
		when(mockUserAliasAdd.sendRequest(anyMap())).thenReturn(response);

		controller.execUserAliasAdd(testUser, mockUserAliasAdd);
		verify(testUser, times(2)).setExtension(7002);


	}

	public void testExecUserAliasAddError() throws Exception {
		System.out.println("execUserAliasAddError");
		String badResponseString = loadTestResource("/useraliasadd_error_response.json");
		HttpConnector connector = mock(HttpConnector.class);
		when(connector.sendRequest(anyString(), anyMap())).thenReturn(badResponseString);

		UserAliasAdd realUserAliasAdd = new UserAliasAdd();
		UserAliasAddResponse response = realUserAliasAdd.mapJson(badResponseString);

		UserAliasAdd userAliasAdd = mock(UserAliasAdd.class);
		userAliasAdd.setHttpConnector(connector);
		when(userAliasAdd.sendRequest(anyMap())).thenReturn(response);

		controller.execUserAliasAdd(testUser, userAliasAdd);
		verify(testUser).setExtensionAdded(false);
		verify(testUser, atLeastOnce()).setError(anyString());
	}

	public void testExecVoicemailBoxAddGood() throws Exception {
		System.out.println("execVoicemailBoxAddGood");
		String goodResponseStr = loadTestResource("/voicemailadd_good_response.json");
		HttpConnector connector = mock(HttpConnector.class);
		when(connector.sendRequest(anyString(), anyMap())).thenReturn(goodResponseStr);

		VoicemailboxAdd service = new VoicemailboxAdd();
		VoicemailboxAddResponse response = service.mapJson(goodResponseStr);

		VoicemailboxAdd mockService = mock(VoicemailboxAdd.class);
		mockService.setHttpConnector(connector);
		when(mockService.sendRequest(anyMap())).thenReturn(response);

		controller.execVoicemailBoxAdd(testUser, mockService);
		verify(testUser).setVmMailBoxId(11452L);
		verify(testUser).setVmPassword(4978);
		verify(testUser).setVmBoxAdded(true);
	}

	public void testExecVoicemailBoxAddError() throws Exception {
		System.out.println("execVoicemailBoxAddError");
		String errResponse = loadTestResource("/voicemailadd_error_response.json");
		HttpConnector connector = mock(HttpConnector.class);
		when(connector.sendRequest(anyString(), anyMap())).thenReturn(errResponse);

		VoicemailboxAdd service = new VoicemailboxAdd();
		VoicemailboxAddResponse response = service.mapJson(errResponse);

		VoicemailboxAdd mockService = mock(VoicemailboxAdd.class);
		mockService.setHttpConnector(connector);
		when(mockService.sendRequest(anyMap())).thenReturn(response);

		controller.execVoicemailBoxAdd(testUser, mockService);
		verify(testUser).setVmBoxAdded(false);
		verify(testUser, atLeastOnce()).setError(anyString());
	}

	public void testexecUserAddressEditGood() throws Exception {
		System.out.println("execVoicemailBoxAddGood");
		String goodResponseStr = loadTestResource("/useraddressedit_good_response.json");
		HttpConnector connector = mock(HttpConnector.class);
		when(connector.sendRequest(anyString(), anyMap())).thenReturn(goodResponseStr);

		UserAddressEdit service = new UserAddressEdit();
		UserAddressEditResponse response = service.mapJson(goodResponseStr);

		UserAddressEdit mockService = mock(UserAddressEdit.class);
		mockService.setHttpConnector(connector);
		when(mockService.sendRequest(anyMap())).thenReturn(response);

		controller.execUserAddressEdit(testUser, mockService);
		verify(testUser).setVmBoxLinked(true);
	}

	public void testexecUserAddressEditError() throws Exception {
		System.out.println("execVoicemailBoxAddError");
		String badResponseStr = loadTestResource("/useraddressedit_error_response.json");
		HttpConnector connector = mock(HttpConnector.class);
		when(connector.sendRequest(anyString(), anyMap())).thenReturn(badResponseStr);

		UserAddressEdit service = new UserAddressEdit();
		UserAddressEditResponse response = service.mapJson(badResponseStr);

		UserAddressEdit mockService = mock(UserAddressEdit.class);
		mockService.setHttpConnector(connector);
		when(mockService.sendRequest(anyMap())).thenReturn(response);

		controller.execUserAddressEdit(testUser, mockService);
		verify(testUser).setVmBoxLinked(false);
		verify(testUser, atLeastOnce()).setError(anyString());
	}
}
