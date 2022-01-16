package dao;

import dbService.DBException;
import model.UsersDataSet;

import java.sql.SQLException;

public interface DAO {

  boolean insertUser(String login, String password) throws SQLException, DBException;

  long getUserId(String login) throws SQLException, DBException;

  UsersDataSet get(String login) throws SQLException, DBException;

}
