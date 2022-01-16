package servlets;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
  private final AccountService accountService;

  public SignUpServlet(AccountService accountService) {
    this.accountService = accountService;
  }

  public void doPost(HttpServletRequest request,
                     HttpServletResponse response) throws IOException {
    String login = request.getParameter("login");
    String pass = request.getParameter("password");
    if (login == null || pass == null) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    } else if (accountService.getUserByLogin(login) == null) {
      accountService.addNewUser(new UserProfile(login, pass));
    } else if (login.equals(accountService.getUserByLogin(login).getLogin())) {
      response.getWriter().println("User already exists");
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
  }
}
