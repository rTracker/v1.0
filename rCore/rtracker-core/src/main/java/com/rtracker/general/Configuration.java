package com.rtracker.general;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import com.rtracker.entries.IUser;

/**
 * All system settings
 * 
 * @author dantonov
 *
 */
public class Configuration {

	public static Configuration getInstance() {
		synchronized (Configuration.class) {
			if (instance == null) {
				instance = new Configuration();
			}
		}
		return instance;
	}

	public void addUser(IUser user, String password) {
		users.put(user, getHash(password));
	}

	public Map<IUser, String> getUsers() {
		return new HashMap<IUser, String>(users);
	}

	public static String getHash(String password) {
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			return password;
		}
		byte[] hashBytes = messageDigest.digest(password.getBytes());
		String hash = String.valueOf(hashBytes);
		return hash;
	}

	private Configuration() {
		users = new HashMap<>();
	}

	private static Configuration instance = null;
	private Map<IUser, String> users;
}
