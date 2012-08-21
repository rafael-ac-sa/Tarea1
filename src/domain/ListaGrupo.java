package domain;

import display.FrontCommand;
import data.GrupoRowGateway;
import data.GrupoFinder;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;

public class ListaGrupos extends FrontCommand {

  public void process()
    throws ServletException, IOException {
      GrupoFinder grupos = (GrupoFinder) context.getBean("grupoFinder");
    List<GrupoRowGateway> data = grupos.findAll();
    List param = new ArrayList();
    for (int i=0;i<data.size();i++) {
      GrupoRowGateway grupo = data.get(i);
      Map item = new HashMap();
      item.put("id",grupo.getId()+"");
      item.put("numero",grupo.getNumero());
      item.put("sigla",grupo.getSigla());
      item.put("nombre",grupo.getNombre());
      item.put("horario",grupo.getHorario());
      item.put("aula",grupo.getAula());
	  item.put("id_profesor",grupo.getId_profesor());
      param.add(item);
    }
    request.setAttribute("grupos",param);
    forward("/listaGrupos.jsp");
  }
}