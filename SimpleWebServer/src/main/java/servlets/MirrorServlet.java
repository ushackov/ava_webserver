package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MirrorServlet extends HttpServlet {
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response) throws IOException {
    response.getWriter().println(request.getParameter("key"));
  }
}
