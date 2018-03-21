package dao;

import java.sql.ResultSet;

import beans.UserBean;

public interface UserDAO {

  /**
   * Returns users credentials neccessary for password verification based on username
   * 
   * @param username
   * @return Result set with hashed password and salt
   */
  ResultSet getLoginData(String username);
  /**
   * Updates user password and salt in db
   * 
   * @param salt salt used to hash password
   * @param hash hashed user password
   * @param email identification for a user
   * @return true if successful or false if not
   */
  boolean updatePassword(String salt, String hash, String email);
  
  /**
   * 
   * @param bean bean containing users data
   * @return true if successful or false if not
   */
  boolean insertUser(UserBean bean);
  
}
