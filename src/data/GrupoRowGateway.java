package data;

import java.util.*;
import java.sql.*;
import javax.sql.*;
import org.springframework.jdbc.core.JdbcTemplate;

public class GrupoRowGateway {

  private int id;
  private int numero;
  private String sigla;
  private String nombre;
  private String horario;
  private String aula;
  private int id_profesor;
  private JdbcTemplate jdbcTemplate;
  private DataSource dataSource;

  GrupoRowGateway() {};

  GrupoRowGateway(int id, int numero, String sigla,
                String nombre, String horario, String aula, int id_profesor) {
    this.id=id; this.numero=numero; this.sigla=sigla;
    this.nombre=nombre;this.horario=horario;this.aula=aula;this.id_profesor=profesor;
  }

  public void setId(int id) {this.id = id;}
  public int getId() {return id;}

  public void setNumero(int numero) {this.numero=numero;}
  public String getNumero() {return numero;}

  public void setSigla(String sigla) {this.sigla=sigla;}
  public String getSigla() {return sigla;}

  public void setNombre(String nombre) {this.nombre=nombre;}
  public String getNombre() {return nombre;}

  public void setHorario(String horario) {this.horario=horario;}
  public String getHorario() {return horario;}

  public void setAula(String aula) {this.aula=aula;}
  public String getAula() {return aula;}
  
  public void setId_profesor(int id_profesor) {this.id_profesor = id_profesor;}
  public int getId_profesor() {return id_profesor;}

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  private void createJdbcTemplate() {
    jdbcTemplate = new JdbcTemplate(dataSource);
  }

  private static final String insertStatement =
    "INSERT INTO grupo "+
    "VALUES (?,?,?,?,?,?,?)";

  public int insert() {
    Random generator = new Random();
    int id = generator.nextInt();
    if (jdbcTemplate==null) createJdbcTemplate();
      jdbcTemplate.update(insertStatement,
         id,numero,sigla,nombre,horario,aula,id_profesor);
    return id;
  }

  private static final String updateStatement =
    "UPDATE grupo "+
    "SET numero = ?, sigla = ?, nombre = ?, "+
    "horario = ?, aula = ?, id_profesor = ? WHERE id = ?";

  public void update() {
    if (jdbcTemplate==null) createJdbcTemplate();
      jdbcTemplate.update(updateStatement,
          numero,sigla,nombre,horario,aula,id_profesor,id);
  }

  private static final String deleteStatement =
    "DELETE FROM grupo "+
    "WHERE id = ?";

  public void delete() {
    if (jdbcTemplate==null) createJdbcTemplate();
      jdbcTemplate.update(deleteStatement,id);
  }

  public static GrupoRowGateway load(DataSource ds, Map map) {
    GrupoRowGateway grupo=null;
    if (map==null)
      grupo = new GrupoRowGateway();
    else {
      int id = ((Integer)map.get("id")).intValue();
      int numero = (Integer)map.get("numero");
      String sigla = (String)map.get("sigla");
      String nombre = (String)map.get("nombre");
      String horario = (String)map.get("horario");
      String aula = (String)map.get("aula");
	  int id_profesor = (Integer)map.get("id_profesor");
      grupo = new GrupoRowGateway(id,numero,sigla,nombre,horario,aula,id_profesor);
    }
    grupo.setDataSource(ds);
    return grupo;
  }
}