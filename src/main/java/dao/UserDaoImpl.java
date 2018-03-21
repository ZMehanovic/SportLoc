package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.UserBean;

class UserDaoImpl implements UserDAO {

  DAOFactory daoFactory;

  public UserDaoImpl(DAOFactory daoFactory) {
    this.daoFactory = daoFactory;
  }

  @Override
  public ResultSet getLoginData(String username) {
    ResultSet result = null;
    Connection con = daoFactory.getConnection();
    try {
      result = con.createStatement()
          .executeQuery("SELECT lozinka, sol FROM korisnik WHERE kor_ime='" + username + "'");
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }

  @Override
  public boolean updatePassword(String salt, String hash, String email) {
    boolean result = false;
    Connection con = daoFactory.getConnection();
    
    String query = "UPDATE korisnik SET sol = '" + salt + "', lozinka = '" + hash + "'\r\n"
        + "WHERE email='" + email + "';";
    
    try {
      result = con.createStatement().executeUpdate(query) != 0 ? true : false;
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return result;
  }
  
  public boolean insertUser(UserBean bean) {
    boolean result = false;
    Connection con = daoFactory.getConnection();
    
    String query = "INSERT INTO public.korisnik(\r\n"
        + " ime, prezime, kor_ime, email, lozinka, sol, spol, datum_rodenja)\r\n" + " VALUES ('"
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

}
