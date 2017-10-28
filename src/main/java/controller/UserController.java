package controller;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;

import helper.Passwords;
import model.DbManager;

public class UserController {

	public UserController() {

	}

	public String checkLoginData(String username, String password) {
		boolean result = false;
		ResultSet data = new DbManager().getLoginData(username);
		if (data != null) {
			String salt = null;
			String hash = null;

			try {
				while (data.next()) {
					hash = data.getString(1);
					salt = data.getString(2);
				}

				result = Passwords.checkPasswordHash(password, salt, hash);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return String.valueOf(result);
	}

	private boolean updatePassword(String username, String password) {
		boolean result = false;
		try {
			String salt = "1";
			byte[] saltArray = Passwords.getNextSalt();
			salt = new String(saltArray, "UTF-16");
			result = new DbManager().updatePassword(salt, Passwords.getSecurePassword(password, salt), username);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
}
