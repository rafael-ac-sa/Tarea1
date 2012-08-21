package display;
import java.util.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.springframework.web.context.*;

public abstract class FrontCommand {

  public WebApplicationContext context;
  public HttpServletRequest request;
  public HttpServletResponse response;

  public void init(WebApplicationContext ctx,
                   HttpServletRequest req,
                   HttpServletResponse resp) {
    this.context = ctx;
    this.request = req;
    this.response = resp;
  }

  protected void forward(String target)
    throws ServletException, IOException {
    RequestDispatcher dispatcher =
      context.getServletContext().getRequestDispatcher(target);
    dispatcher.forward(request,response);
  }

  public abstract void process()
    throws ServletException, IOException;

}