package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory {

  private static String dbUrl;
  
  public DAOFactory() {
    dbUrl = System.getenv("JDBC_DATABASE_URL");
  }

  public Connection getConnection() {
    Connection con = null;
    try {
      con = DriverManager.getConnection(dbUrl);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return con;
  }
  
  public UserDAO getUserDao() {
    return new UserDaoImpl(this);
  }

  public EventDao getEventDao() {
    return new EventDaoImpl(this);
  }
  
}
