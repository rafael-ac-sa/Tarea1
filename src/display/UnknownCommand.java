package display;
import java.io.*;
import javax.servlet.*;

public class UnknownCommand extends FrontCommand {

  public void process()
    throws ServletException, IOException {
    forward("/unknown.jsp");
  }
