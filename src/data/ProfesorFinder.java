package universidad;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ProfesorService extends HttpServlet {
  private static final String PAGE_HEADER =
    "<HTML><HEAD><TITLE>Sistema Universitario" +
    "</TITLE></HEAD><BODY>";

  private static final String PAGE_FOOTER =
    "</BODY></HTML>";

  private static String DRIVER ="org.sqlite.JDBC";

  private static String URL = "jdbc:sqlite:C:\\Users\\Ronald\\Downloads\\tutorial1\\universidad\\database\\universidad.sqlite";

  private Connection con;

  public void init(ServletConfig config) throws ServletException {

    try {
      Class.forName(DRIVER);
      con = DriverManager.getConnection(URL);
    } catch (Exception e) {
      System.out.println("Database initialization error");
    }
  }

  public void doGet(HttpServletRequest request,
                  HttpServletResponse response)
  throws ServletException,IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String id = request.getParameter("id");
    String service = request.getParameter("service");
    ProfesorGateway profs = new ProfesorGateway(con);
    out.println(PAGE_HEADER);
    if (service.equals("DETAIL"))
      detailProfesor(out,profs,id);
    else if (service.equals("LIST"))
      listProfesor(out,profs);
    out.println(PAGE_FOOTER);
    out.close();
  }

  public void listProfesor(PrintWriter out, ProfesorGateway profs) {
    out.println("<H2>Detalle de profesor</H2>");
    out.println("<TABLE>");
    try {
      ResultSet rs = profs.findAll();
      while (rs.next()) {
		String identificacion = rs.getString(1);
        String dept = rs.getString(2);
        String name = rs.getString(3);
        String title = rs.getString(4);
        String email = rs.getString(5);
        String phone = rs.getString(6);
		out.println("<TR><TD>"+identificacion+"</TD>");
		out.println("<TD>"+dept+"</TD>");
        out.println("<TD>"+name+"</TD>");
        out.println("<TD>"+title+"</TD>");
        out.println("<TD>"+email+"</TD>");
        out.println("<TD>"+phone+"</TD>");
		out.println("<TD><a href=http://localhost:8089/profesor?service=DETAIL&id="+identificacion+">DETALLE</a></TD></TR>");
      }
      rs.close();
    } catch (Exception e) {}
    out.println("</TABLE>");
  }

  public void detailProfesor(PrintWriter out, ProfesorGateway profs,String id) {
    out.println("<TABLE><TR><TH>Depart.</TH><TH>Nombre</TH>");
    out.println("<TH>Titulo</TH><TH>Correo</TH><TH>Telefono</TH></TR>");
    try {
      ResultSet rs = profs.findProfesor(id);
      while (rs.next()) {
		String identificacion = rs.getString(1);
        String dept = rs.getString(2);
        String name = rs.getString(3);
        String title = rs.getString(4);
        String email = rs.getString(5);
        String phone = rs.getString(6);
		out.println("<TR><TD><B>ID:</B></TD><TD>"+identificacion+"</TD></TR>");
        out.println("<TR><TD><B>Dept.:</B></TD><TD>"+dept+"</TD></TR>");
        out.println("<TR><TD><B>Nombre:</B></TD><TD>"+name+"</TD></TR>");
        out.println("<TR><TD><B>Titulo:</B></TD><TD>"+title+"</TD></TR>");
        out.println("<TR><TD><B>Correo:</B></TD><TD>"+email+"</TD></TR>");
        out.println("<TR><TD><B>Telefono:</B></TD><TD>"+phone+"</TD></TR>");
      }
      rs.close();
    } catch (Exception e) {}
    out.println("</TABLE>");
  }
}