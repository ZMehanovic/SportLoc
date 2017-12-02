package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.EventBean;
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

	public boolean updatePassword(String salt, String hash, String email) {
		boolean result = false;

		String query = "UPDATE korisnik SET sol = '" + salt + "', lozinka = '" + hash + "'\r\n" + "WHERE email='"
				+ email + "';";
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
				+ "	ime, prezime, kor_ime, email, lozinka, sol, spol, datum_rodenja)\r\n" + "	VALUES ('"
				+ bean.getFirstName() + "', '" + bean.getLastName() + "', '" + bean.getUserName() + "', '"
				+ bean.getEmail() + "', '" + bean.getPassword() + "', '" + bean.getSalt() + "','" + bean.getGender()
				+ "', '" + bean.getDob() + "');";

		try {
			con.createStatement().executeUpdate(query);
			con.close();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String addEvent(EventBean bean) {
		String result = null;
		String query = "INSERT INTO public.dogadaj(\r\n"
				+ "	naziv, kapacitet, od, \"do\", adresa, otvoreno, opis, id_sport, id_grad, kreator)	VALUES ('"
				+ bean.getTitle() + "', '" + bean.getCapacity() + "', '" + bean.getStartTime() + "', '"
				+ bean.getEndTime() + "', '" + bean.getAddress() + "', '" + bean.isOpenEvent() + "','"
				+ bean.getDescription() + "', " + bean.getSportId() + ", " + bean.getLocationId() + ", '"
				+ bean.getCreatorEmail() + "');";

		try {
			con.createStatement().executeUpdate(query);
			con.close();
		} catch (SQLException e) {
			result = "Error occured while executing query. \nPlease try again or contact admin.";
			e.printStackTrace();
		}
		return result;
	}

	public ResultSet getCities() {
		ResultSet result = null;
		try {

			result = getConnection().createStatement().executeQuery("SELECT id_grad, naziv FROM public.grad");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ResultSet getSports() {
		ResultSet result = null;
		try {

			result = getConnection().createStatement().executeQuery("SELECT id_sport, naziv FROM public.sport;");
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
