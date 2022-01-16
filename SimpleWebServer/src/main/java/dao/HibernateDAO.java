package dao;

import model.UsersDataSet;
import dao.utils.SessionFactoryUtils;
import org.hibernate.*;

public class HibernateDAO extends SessionFactoryUtils implements DAO {

  private final SessionFactory sessionFactory;

  public HibernateDAO() {
    this.sessionFactory = createSessionFactory(getPostgresConfiguration());
  }

  @Override
  public UsersDataSet get(String login) throws HibernateException {
    long id = getUserId(login);
    if (id == -1) {
      return null;
    }
    Session session = sessionFactory.openSession();
    UsersDataSet dataSet = (UsersDataSet) session.get(UsersDataSet.class, id);
    session.close();
    return dataSet;
  }

  @Override
  public long getUserId(String login) throws HibernateException {
    Session session = sessionFactory.openSession();
    String sql = "FROM UsersDataSet WHERE login= :name";
    Query query = session.createQuery(sql);
    query.setParameter("name", login);
    UsersDataSet example = (UsersDataSet) query.uniqueResult();
    session.close();
    return example == null ? -1 : example.getId();
  }

  @Override
  public boolean insertUser(String login, String password) throws HibernateException {
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();
    boolean isAdded = (Long) session.save(new UsersDataSet(login, password)) != 0;
    transaction.commit();
    session.close();
    return isAdded;
  }
}
