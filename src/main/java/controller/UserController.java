package controller;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import beans.UserBean;
import helper.Passwords;
import model.DbManager;

public class UserController {

	public UserController() {

	}

	public boolean checkLoginData(Map<String, String[]> params) {
		String username = params.get("username")[0];
		String password = params.get("password")[0];

		boolean result = false;
		ResultSet data = new DbManager().getLoginData(username);
		if (data != null) {
			String salt = null;
			String hash = null;

			try {
				while (data.next()) {
					hash = data.getString(1);
					salt = data.getString(2);
					result = Passwords.checkPasswordHash(password, salt, hash);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	private boolean updatePassword(String username, String password) {
		boolean result = false;
		try {
			String salt = "";
			byte[] saltArray = Passwords.getNextSalt();
			salt = new String(saltArray, "UTF-16");
			result = new DbManager().updatePassword(salt, Passwords.getSecurePassword(password, salt), username);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean registerUser(UserBean user) {
		return false;

	}
}
