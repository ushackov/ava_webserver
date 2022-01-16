package servlets;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {

  private final AccountService accountService;

  public SignInServlet(AccountService accountService) {
    this.accountService = accountService;
  }

  public void doPost(HttpServletRequest request,
                     HttpServletResponse response) throws IOException {
    String login = request.getParameter("login");
    UserProfile user = accountService.getUserByLogin(login);
    String pass = request.getParameter("password");
    if (login == null || pass == null) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    } else if (user == null) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().println("User does not exist");
    } else if (!pass.equals(user.getPass())) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getWriter().println("Incorrect password");
    } else {
      response.getWriter().println(user.getLogin() + " "
          + user.getPass());
    }
  }
}
