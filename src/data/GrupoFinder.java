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
    GrupoGateway grupos = new GrupoGateway(con);
    out.println(PAGE_HEADER);
    if (service.equals("DETAIL"))
      detailGrupo(out,grupos,id);
    else if (service.equals("LIST"))
      listGrupo(out,grupos);
    out.println(PAGE_FOOTER);
    out.close();
  }

  public void listGrupo(PrintWriter out, GrupoGateway grupos) {
    out.println("<H2>Detalle de grupo</H2>");
    out.println("<TABLE>");
    try {
      ResultSet rs = grupos.findAll();
      while (rs.next()) {
		String id = rs.getString(1);
        String numero = rs.getString(2);
        String sigla = rs.getString(3);
        String nombre = rs.getString(4);
        String horario = rs.getString(5);
        String aula = rs.getString(6);
		String id_profesor = rs.getString(7);
		out.println("<TR><TD>"+id+"</TD>");
		out.println("<TD>"+numero+"</TD>");
        out.println("<TD>"+sigla+"</TD>");
        out.println("<TD>"+nombre+"</TD>");
        out.println("<TD>"+horario+"</TD>");
        out.println("<TD>"+aula+"</TD>");
		out.println("<TD>"+id_profesor+"</TD>");
		out.println("<TD><a href=http://localhost:8089/grupo?service=DETAIL&id="+id+">DETALLE</a></TD></TR>");
      }
      rs.close();
    } catch (Exception e) {}
    out.println("</TABLE>");
  }

  public void detailGrupo(PrintWriter out, GrupoGateway grupos,String id) {
    out.println("<TABLE><TR><TH>Depart.</TH><TH>Nombre</TH>");
    out.println("<TH>Titulo</TH><TH>Correo</TH><TH>Telefono</TH></TR>");
    try {
      ResultSet rs = grupos.findGrupo(id);
      while (rs.next()) {
		String id = rs.getString(1);
        String numero = rs.getString(2);
        String sigla = rs.getString(3);
        String nombre = rs.getString(4);
        String horario = rs.getString(5);
        String aula = rs.getString(6);
		String id_profesor = rs.getString(6);
		out.println("<TR><TD><B>ID:</B></TD><TD>"+id+"</TD></TR>");
        out.println("<TR><TD><B>Numero.:</B></TD><TD>"+numero+"</TD></TR>");
        out.println("<TR><TD><B>Sigla:</B></TD><TD>"+sigla+"</TD></TR>");
        out.println("<TR><TD><B>Nombre:</B></TD><TD>"+nombre+"</TD></TR>");
        out.println("<TR><TD><B>Horario:</B></TD><TD>"+horario+"</TD></TR>");
        out.println("<TR><TD><B>Aula:</B></TD><TD>"+aula+"</TD></TR>");
		out.println("<TR><TD><B>ID_profesor:</B></TD><TD>"+id_profesor+"</TD></TR>");
      }
      rs.close();
    } catch (Exception e) {}
    out.println("</TABLE>");
  }
}