package accounts;

import dbService.DBException;
import dbService.DBService;
import model.UsersDataSet;

public class AccountService {
  private final DBService data;

  public AccountService() {
    data = new DBService();
  }

  public boolean addNewUser(UserProfile userProfile) {
    try {
      return data.addUser(userProfile.getLogin(), userProfile.getPass());
    } catch (DBException e) {
      e.printStackTrace();
    }
    return false;
  }

  public UserProfile getUserByLogin(String login) {
    try {
      UsersDataSet user = data.getUserByLogin(login);
      if (user == null) {
        return null;
      }
      return new UserProfile(user.getLogin(), user.getPassword());
    } catch (DBException e) {
      e.printStackTrace();
    }
    return null;
  }
}
