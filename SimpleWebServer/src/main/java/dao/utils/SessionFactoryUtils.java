package dao.utils;

import org.hibernate.SessionFactory;
import model.UsersDataSet;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionFactoryUtils {

  protected SessionFactory createSessionFactory(Configuration configuration) {
    StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
    builder.applySettings(configuration.getProperties());
    ServiceRegistry serviceRegistry = builder.build();
    return configuration.buildSessionFactory(serviceRegistry);
  }

  protected Configuration getPostgresConfiguration() {
    Configuration configuration = new Configuration().configure();
    configuration.addAnnotatedClass(UsersDataSet.class);
    return configuration;
  }
}
