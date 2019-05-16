/**
 * 
 */
package com.tp2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Celine, Marine et Leane
 *
 */
public class ParamConf {

	public static String adress = "";
	public static Integer port = 0;
	public static String username = "";
	public static String password = "";
	public static String mode = "";

	/**
	 * Parses the config.properties file
	 */
	public static void parse() {
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
			adress = prop.getProperty("adress");
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			mode = prop.getProperty("mode");
			port = Integer.parseInt(prop.getProperty("port"));
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
