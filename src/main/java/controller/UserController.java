package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Map;

import beans.UserBean;
import helper.MailSender;
import helper.Passwords;
import model.DbManager;

public class UserController {

	public UserController() {

	}

	public boolean checkLoginData(Map<String, String[]> params) {
		boolean result;
		if (params != null && !params.isEmpty() && params.containsKey("username") && params.containsKey("password")) {
			result = false;
		}
		result = checkLoginData(params.get("username")[0], params.get("password")[0]);
		return result;
	}

	public boolean checkLoginData(String username, String password) {
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

	@SuppressWarnings("unused")
	private boolean updatePassword(String username, String password) {
		boolean result = false;
		String salt = Passwords.getNextSalt();
		result = new DbManager().updatePassword(salt, Passwords.getSecurePassword(password, salt), username);
		return result;
	}

	public boolean registerUser(UserBean user) {
		boolean result = false;
		user.setSalt(Passwords.getNextSalt());
		user.setPassword(Passwords.getSecurePassword(user.getPassword(), user.getSalt()));
		if (user.getImage() != null) {
			user.setImage(new String(Base64.getDecoder().decode(user.getImage())));
		}
		result = new DbManager().insertUser(user);
		if (user.getEmail() != null) {
			new MailSender().sendEmail(user.getEmail());
		}
		return result;

	}
}
