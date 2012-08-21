package domain;

import display.FrontCommand;
import data.GrupoFinder;
import data.GrupoRowGateway;

import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import javax.servlet.ServletException;

public class ActualizarGrupo extends FrontCommand {

  public void process()
    throws ServletException, IOException {
      GrupoFinder grupos =
        (GrupoFinder) context.getBean("grupoFinder");
      String id = request.getParameter("id");
    GrupoRowGateway grupo = grupos.find(id);
    if (grupo!=null) {
      int numero = request.getParameter("numero");
      if (numero!=null) grupo.setNumero(numero);
      String sigla = request.getParameter("sigla");
      if (sigla!=null) grupo.setSigla(sigla);
      String nombre = request.getParameter("nombre");
      if (nombre!=null) prof.setNombre(nombre);
      String horario = request.getParameter("horario");
      if (horario!=null) prof.setHorario(horario);
      String aula = request.getParameter("aula");
      if (aula!=null) prof.setAula(aula);
	  int id_profesor = request.getParameter("id_profesor");
      if (id_profesor!=null) grupo.setId_profesor(id_profesor);
      grupo.update();
    }
    response.sendRedirect("universidad/domain.ListaGrupos");
  }
}