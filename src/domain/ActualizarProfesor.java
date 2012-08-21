package domain;

import display.FrontCommand;
import data.ProfesorFinder;
import data.ProfesorRowGateway;

import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import javax.servlet.ServletException;

public class ActualizarProfesor extends FrontCommand {

  public void process()
    throws ServletException, IOException {
      ProfesorFinder profs =
        (ProfesorFinder) context.getBean("profesorFinder");
      String id = request.getParameter("id");
    ProfesorRowGateway prof = profs.find(id);
    if (prof!=null) {
      String cedula = request.getParameter("cedula");
      if (cedula!=null) prof.setCedula(cedula);
      String nombre = request.getParameter("nombre");
      if (nombre!=null) prof.setNombre(nombre);
      String titulo = request.getParameter("titulo");
      if (titulo!=null) prof.setTitulo(titulo);
      String area = request.getParameter("area");
      if (area!=null) prof.setArea(area);
      String telefono = request.getParameter("telefono");
      if (telefono!=null) prof.setTelefono(telefono);
      prof.update();
    }
    response.sendRedirect("universidad/domain.ListaProfesores");
  }
}