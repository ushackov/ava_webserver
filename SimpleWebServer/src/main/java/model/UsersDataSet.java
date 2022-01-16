package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "example")
public class UsersDataSet implements Serializable {
  private static final long serialVersionUID = -8706689714326132798L;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "login", unique = true, updatable = false)
  private String login;

  @Column(name = "password")
  private String password;

  @SuppressWarnings("UnusedDeclaration")
  public UsersDataSet() {
  }

  @SuppressWarnings("UnusedDeclaration")
  public UsersDataSet(long id, String login, String pass) {
    this.id = id;
    this.login = login;
    this.password = pass;
  }

  @SuppressWarnings("UnusedDeclaration")
  public UsersDataSet(String login, String pass) {
    this.setId(-1);
    this.login = login;
    this.password = pass;
  }

  @SuppressWarnings("UnusedDeclaration")
  public UsersDataSet(String name) {
    this.setId(-1);
    this.setLogin(name);
  }

  @SuppressWarnings("UnusedDeclaration")
  public void setId(long id) {
    this.id = id;
  }

  @SuppressWarnings("UnusedDeclaration")
  public void setLogin(String login) {
    this.login = login;
  }

  public long getId() {
    return id;
  }

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public String toString() {
    return "UsersDataSet{" +
        "id=" + id +
        ", login='" + login +
        ", password='" + password + '\'' +
        '}';
  }
}

