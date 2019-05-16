package com.tp2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @authors Celine, Marine et Leane
 *
 */
public class MyResourceTest {

	private HttpServer server;
	private WebTarget target;

	@Before
	public void setUp() throws Exception {
		ParamConf.parse();
		// start the server
		server = Main.startServer();

		// create the client
		Client c = ClientBuilder.newClient();

		target = c.target(Main.BASE_URI);
	}

	@Test
	public void testRequestFail() {
		Response response = target.path("requestNotExists").request().post(null);
		assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void testPwdOK() {
		String responseMsg = target.path("myresource/pwd").request().get(String.class);
		assertEquals("/", responseMsg);
	}

	@Test
	public void testAddDirectoryOK() {
		String nameNewDir = "test";
		Response response = target.path("myresource/addDirectory").queryParam("directory", nameNewDir).request()
				.post(null);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		String responseMsg = target.path("myresource/ls").request().get(String.class);
		List<String> listFiles = getListFiles(responseMsg);
		boolean find = false;
		for (String s : listFiles) {
			if (s.equals(nameNewDir)) {
				find = true;
			}
		}
		assertTrue(find);
	}

	@Test
	public void testDeleteFileFail() {
		String response = target.path("myresource/deleteFile").queryParam("file", "notexist.txt").request()
				.delete(String.class);
		assertEquals("Could not delete file", response);
	}

	public List<String> getListFiles(String responseMsg) {
		List<String> res = new ArrayList<>();
		responseMsg = responseMsg.substring(responseMsg.indexOf("<li>"), responseMsg.lastIndexOf("</li>"));
		String pattern = "[\\s]*</a>[\\s]*";
		Scanner scResponse = new Scanner(responseMsg);
		Scanner sc = scResponse.useDelimiter(pattern);
		String s, next;
		while (sc.hasNext()) {
			next = sc.next();
			s = next.substring(next.lastIndexOf("\">") + 2);
			res.add(s);
		}
		scResponse.close();
		return res;
	}

	// @Test
	// public void testDeleteDirectory() {
	// // Ajout dossier
	// String nameNewDir = "test";
	// Response response =
	// target.path("myresource/addDirectory").queryParam("directory",
	// nameNewDir).request()
	// .post(null);
	// // Suppression du dossier
	// response = target.path("myresource/deleteDirectory").queryParam("directory",
	// nameNewDir).request().delete();
	// assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	//
	// String responseMsg =
	// target.path("myresource/ls").request().get(String.class);
	// List<String> listFiles = getListFiles(responseMsg);
	// boolean find = false;
	// for (String s : listFiles) {
	// if (s.equals(nameNewDir)) {
	// find = true;
	// }
	// }
	// assertFalse(find);
	// }

	@After
	public void tearDown() throws Exception {
		server.stop();
	}

}
