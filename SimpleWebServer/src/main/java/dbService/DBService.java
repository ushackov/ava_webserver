package dbService;

import dao.DAO;
import dao.HibernateDAO;
import dao.JdbcDAO;
import model.UsersDataSet;
import org.hibernate.HibernateException;

import java.sql.SQLException;

public class DBService {
  private final DAO dao;

  public DBService() {
//    this.dao = new JdbcDAO();
    this.dao = new HibernateDAO();
  }


//  public UsersDataSet getUser(String login) throws DBException {
//    try {
//      return dao.get(login);
//    } catch (SQLException | HibernateException e) {
//      throw new DBException(e);
//    }
//  }

  public UsersDataSet getUserByLogin(String login) throws DBException {
    try {
      return dao.get(login);
    } catch (SQLException | HibernateException e) {
      throw new DBException(e);
    }
  }

  public boolean addUser(String name, String password) throws DBException {
    try {
      return dao.insertUser(name, password);
    } catch (SQLException | HibernateException e) {
      throw new DBException(e);
    }
  }
}


