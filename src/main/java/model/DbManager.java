package model;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbManager {

	public ResultSet getLoginData(String username) {
		ResultSet result = null;
		try {
			Connection con = getConnection();

			result = con.createStatement()
					.executeQuery("SELECT lozinka, sol FROM korisnik WHERE kor_ime='" + username + "'");
		} catch (URISyntaxException | SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	private static Connection getConnection() throws URISyntaxException, SQLException {
		String dbUrl = System.getenv("JDBC_DATABASE_URL");
		return DriverManager.getConnection(dbUrl);
	}

	public boolean updatePassword(String salt, String hash, String username) {
		boolean result = false;

		String query = "UPDATE korisnik SET sol = '" + salt + "', lozinka = '" + hash + "'\r\n"
						+ "WHERE kor_ime='" + username + "';";
		try {
			Connection con = getConnection();
			con.createStatement().executeUpdate(query);
			result = true;
		} catch (URISyntaxException | SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
