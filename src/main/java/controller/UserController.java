package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import beans.UserBean;
import helper.MailSender;
import helper.Passwords;
import model.DbManager;

public class UserController {

	public UserController() {

	}

	public boolean checkLoginData(Map<String, String[]> params) {
		boolean result = false;
		if (params != null && !params.isEmpty() && params.containsKey("username") && params.containsKey("password")) {
			result = checkLoginData(params.get("username")[0], params.get("password")[0]);
		}
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

	public boolean registerUser(UserBean user) {
		boolean result = false;
		if (user.getEmail() != null) {

			user.setSalt(Passwords.getNextSalt());
			user.setPassword(Passwords.getSecurePassword(user.getPassword(), user.getSalt()));
			// if (user.getImage() != null) {
			// user.setImage(new String(Base64.getDecoder().decode(user.getImage())));
			// }
			result = new DbManager().insertUser(user);
			if (result) {
				new MailSender().sendEmail(user.getEmail(), "Registracija SportLoc", "Registracija uspjesna");
			}
		}
		return result;
	}

	public boolean resetPassword(String email) {
		boolean result = false;
		if (email != null) {
			String password = Passwords.generateRandomPassword(8);
			String salt = Passwords.getNextSalt();
			result = new DbManager().updatePassword(salt, Passwords.getSecurePassword(password, salt), email);
			if (result) {
				new MailSender().sendEmail(email, "Lozinka SportLoc", "Vasa nova lozinka je: " + password);
			}
		}
		return result;
	}
}
