package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.UserBean;

public class DbManager {

	private static Connection con;

	private static Connection getConnection() {
		String dbUrl = System.getenv("JDBC_DATABASE_URL");
		try {
			return DriverManager.getConnection(dbUrl);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public DbManager() {
		con = getConnection();
	}

	public ResultSet getLoginData(String username) {
		ResultSet result = null;
		try {

			result = getConnection().createStatement()
					.executeQuery("SELECT lozinka, sol FROM korisnik WHERE kor_ime='" + username + "'");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean updatePassword(String salt, String hash, String username) {
		boolean result = false;

		String query = "UPDATE korisnik SET sol = '" + salt + "', lozinka = '" + hash + "'\r\n" + "WHERE kor_ime='"
				+ username + "';";
		try {
			con.createStatement().executeUpdate(query);
			con.close();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean insertUser(UserBean bean) {
		boolean result = false;
		String query = "INSERT INTO public.korisnik(\r\n"
				+ "	ime, prezime, kor_ime, email, lozinka, sol, spol, godina, opis, slika)\r\n" + "	VALUES ('"
				+ bean.getFirstName() + "', '" + bean.getLastName() + "', '" + bean.getUserName() + "', '" + bean.getEmail()
				+ "', '" + bean.getPassword() + "', '" + bean.getSalt() + "','" + bean.getSex() + "', " + bean.getAge() + ", '"
				+ bean.getDescription() + "', " + bean.getImage() + ");";

		try {
			con.createStatement().executeUpdate(query);
			con.close();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
