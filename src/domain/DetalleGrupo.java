package domain;

import display.FrontCommand;
import data.GrupoFinder;
import data.GrupoRowGateway;

import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import javax.servlet.ServletException;

public class DetalleGrupo extends FrontCommand {

  public void process()
    throws ServletException, IOException {
      GrupoFinder grupos =
        (GrupoFinder) context.getBean("grupoFinder");
      String id = request.getParameter("id");
    GrupoRowGateway grupo = grupos.find(id);
    Map params = new HashMap();
    params.put("id",grupo.getId()+"");
    params.put("numero",grupo.getNumero());
    params.put("sigla",grupo.getSigla());
    params.put("nombre",grupo.getNombre());
    params.put("horario",grupo.getHorario());
    params.put("aula",grupo.getAula());
	params.put("id_profesor",grupo.getId_profesor());
    request.setAttribute("grupo",params);
    forward("/detalleGrupo.jsp");
  }
}