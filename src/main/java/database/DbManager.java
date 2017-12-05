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
				+ bean.getTitle() + "', '" + bean.getMaxCapacity() + "', '" + bean.getStartTime() + "', '"
				+ bean.getEndTime() + "', '" + bean.getAddress() + "', '" + bean.isOpenEvent() + "','"
				+ bean.getDescription() + "', " + bean.getSportId() + ", " + bean.getLocationId() + ", '"
				+ bean.getCreatorEmail() + "');";

		try {
			con.createStatement().executeUpdate(query);
			con.close();
		} catch (SQLException e) {
			result = "Error occured while executing query. Please try again or contact admin.";
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

	public ResultSet getEvents() {
		ResultSet result = null;
		String query= "SELECT n1.*,\r\n" + 
				"       n2.sudionici\r\n" + 
				"FROM\r\n" + 
				"  (SELECT d.id_dogadaj,\r\n" + 
				"          d.naziv,\r\n" + 
				"          kapacitet,\r\n" + 
				"          od,\r\n" + 
				"          \"do\",\r\n" + 
				"          adresa,\r\n" + 
				"          otvoreno,\r\n" + 
				"          d.opis,\r\n" + 
				"          s.naziv sport,\r\n" + 
				"          gr.naziv grad,\r\n" + 
				"          ko.kor_ime kreator,\r\n" + 
				"          gr.id_grad,\r\n" + 
				"          s.id_sport\r\n" + 
				"   FROM public.dogadaj d\r\n" + 
				"   INNER JOIN public.grad gr ON gr.id_grad=d.id_grad\r\n" + 
				"   INNER JOIN public.sport s ON s.id_sport=d.id_sport\r\n" + 
				"   INNER JOIN public.korisnik ko ON ko.email=d.kreator\r\n" + 
				"   GROUP BY d.id_dogadaj,\r\n" + 
				"            s.naziv,\r\n" + 
				"            gr.naziv,\r\n" + 
				"            ko.kor_ime,\r\n" + 
				"            gr.id_grad,\r\n" + 
				"            s.id_sport) AS n1\r\n" + 
				"LEFT JOIN\r\n" + 
				"  (SELECT count(s.id_dogadaj) AS sudionici,\r\n" + 
				"          d.id_dogadaj\r\n" + 
				"   FROM public.dogadaj d\r\n" + 
				"   INNER JOIN public.sudionik s ON s.id_dogadaj=d.id_dogadaj\r\n" + 
				"   WHERE s.status='accepted'\r\n" + 
				"   GROUP BY s.id_dogadaj,\r\n" + 
				"            d.id_dogadaj) AS n2 ON n2.id_dogadaj=n1.id_dogadaj;";
		try {

			result = getConnection().createStatement().executeQuery(query);
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}

}
