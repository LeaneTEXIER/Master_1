package car.car_tp1_texier;

import java.util.HashMap;

/**
 * Class to simulate a database for logins and passwords
 * 
 * @authors Marine, Celine et Leane
 *
 */
public class UsersPasswords {

	private static HashMap<String, String> userPwd = createUsersPasswords();

	/**
	 * Create a hashmap with logins and passwords
	 * 
	 * @return a hashmap with logins and passwords
	 */
	private static HashMap<String, String> createUsersPasswords() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("user", "password");
		map.put("texierl", "pltYujhs10");
		map.put("celine", "test");
		map.put("marine", "mdp");
		return map;
	}

	/**
	 * Check if user is a key in the map
	 * 
	 * @param login the login of the user
	 * @return true if the user is in the map, false otherwise
	 */
	public static boolean userInBDD(String login) {
		return userPwd.containsKey(login);
	}

	/**
	 * Check if the password entered is the password of the user
	 * 
	 * @param login    the login of the user
	 * @param password the password entered
	 * @return true if the password is the value of the login, false otherwise
	 */
	public static boolean passwordIsOk(String login, String password) {
		return password.equals(userPwd.get(login));
	}
}
