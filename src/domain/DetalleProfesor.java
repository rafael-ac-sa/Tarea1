package domain;

import display.FrontCommand;
import data.ProfesorFinder;
import data.ProfesorRowGateway;

import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import javax.servlet.ServletException;

public class DetalleProfesor extends FrontCommand {

  public void process()
    throws ServletException, IOException {
      ProfesorFinder profs =
        (ProfesorFinder) context.getBean("profesorFinder");
      String id = request.getParameter("id");
    ProfesorRowGateway prof = profs.find(id);
    Map params = new HashMap();
    params.put("id",prof.getId()+"");
    params.put("cedula",prof.getCedula());
    params.put("nombre",prof.getNombre());
    params.put("titulo",prof.getTitulo());
    params.put("area",prof.getArea());
    params.put("telefono",prof.getTelefono());
    request.setAttribute("profesor",params);
    forward("/detalleProfesor.jsp");
  }
}