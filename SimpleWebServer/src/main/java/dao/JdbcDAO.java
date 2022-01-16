package dao;

import dbService.DBException;
import model.UsersDataSet;
import dao.executor.Executor;
import dao.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcDAO implements DAO {

  private final Executor executor;
  Connection connection;
  private final String sql = "SELECT * FROM example WHERE login=?";


  public JdbcDAO() {
    this.connection = ConnectionUtils.getPostgresConnection();
    this.executor = new Executor(connection);
    ConnectionUtils.printConnectInfo(connection);
  }

  @Override
  public UsersDataSet get(String login) throws DBException {
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setString(1, login);
      return executor.execQuery(statement, result -> {
        if (!result.next()) {

          return null;
        }
        return new UsersDataSet(result.getLong(1), result.getString(2), result.getString(3));
      });
    } catch (SQLException e) {
      throw new DBException(e);
    }
  }

  public long getUserId(String login) throws DBException {
    try {
      PreparedStatement statement = connection.prepareStatement(sql);
      statement.setString(1, login);
      return executor.execQuery(statement, result -> {
        result.next();
        return result.getLong(1);
      });
    } catch (SQLException e) {
      throw new DBException(e);
    }
  }

  public boolean insertUser(String name, String pass) throws DBException {
    try {
      connection.setAutoCommit(false);
      createTable();
      String updSql = "INSERT INTO example (id, login, password) VALUES (DEFAULT, ?, ?)";
      PreparedStatement statement = connection.prepareStatement(updSql);
      statement.setString(1, name);
      statement.setString(2, pass);
      boolean count = executor.execUpdate(statement);
      connection.commit();
      return count;
    } catch (SQLException e) {
      try {
        connection.rollback();
      } catch (SQLException ignore) {
      }
      throw new DBException(e);
    } finally {
      try {
        connection.setAutoCommit(true);
      } catch (SQLException ignore) {
      }
    }
  }

  public void createTable() throws SQLException {
    String createTable = "CREATE TABLE IF NOT EXISTS example " +
        "(id SERIAL PRIMARY KEY, login varchar(256), password varchar(256))";
    PreparedStatement statement = connection.prepareStatement(createTable);
    executor.execUpdate(statement);
  }

  public void dropTable() throws SQLException {
    PreparedStatement statement = connection.prepareStatement("drop table example");
    executor.execUpdate(statement);
  }
}
