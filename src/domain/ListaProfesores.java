package domain;

import display.FrontCommand;
import data.ProfesorRowGateway;
import data.ProfesorFinder;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;

public class ListaProfesores extends FrontCommand {

  public void process()
    throws ServletException, IOException {
      ProfesorFinder profs = (ProfesorFinder) context.getBean("profesorFinder");
    List<ProfesorRowGateway> data = profs.findAll();
    List param = new ArrayList();
    for (int i=0;i<data.size();i++) {
      ProfesorRowGateway prof = data.get(i);
      Map item = new HashMap();
      item.put("id",prof.getId()+"");
      item.put("cedula",prof.getCedula());
      item.put("nombre",prof.getNombre());
      item.put("titulo",prof.getTitulo());
      item.put("area",prof.getArea());
      item.put("telefono",prof.getTelefono());
      param.add(item);
    }
    request.setAttribute("profesores",param);
    forward("/listaProfesores.jsp");
  }
}