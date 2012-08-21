package data;

import java.util.*;
import java.sql.*;
import javax.sql.*;
import org.springframework.jdbc.core.JdbcTemplate;

public class ProfesorRowGateway {

  private int id;
  private String cedula;
  private String nombre;
  private String titulo;
  private String area;
  private String telefono;
  private JdbcTemplate jdbcTemplate;
  private DataSource dataSource;

  ProfesorRowGateway() {};

  ProfesorRowGateway(int id, String ced, String nomb,
                String tit, String area, String tel) {
    this.id=id; this.cedula=ced; this.nombre=nomb;
    this.titulo=tit;this.area=area;this.telefono=tel;
  }

  public void setId(int id) {this.id = id;}
  public int getId() {return id;}

  public void setCedula(String ced) {this.cedula=ced;}
  public String getCedula() {return cedula;}

  public void setNombre(String nomb) {this.nombre=nomb;}
  public String getNombre() {return nombre;}

  public void setTitulo(String tit) {this.titulo=tit;}
  public String getTitulo() {return titulo;}

  public void setArea(String area) {this.area=area;}
  public String getArea() {return area;}

  public void setTelefono(String tel) {this.telefono=tel;}
  public String getTelefono() {return telefono;}

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  private void createJdbcTemplate() {
    jdbcTemplate = new JdbcTemplate(dataSource);
  }

  private static final String insertStatement =
    "INSERT INTO profesor "+
    "VALUES (?,?,?,?,?,?)";

  public int insert() {
    Random generator = new Random();
    int id = generator.nextInt();
    if (jdbcTemplate==null) createJdbcTemplate();
      jdbcTemplate.update(insertStatement,
         id,cedula,nombre,titulo,area,telefono);
    return id;
  }

  private static final String updateStatement =
    "UPDATE profesor "+
    "SET cedula = ?, nombre = ?, titulo = ?, "+
    "area = ?, telefono = ? WHERE id = ?";

  public void update() {
    if (jdbcTemplate==null) createJdbcTemplate();
      jdbcTemplate.update(updateStatement,
          cedula,nombre,titulo,area,telefono,id);
  }

  private static final String deleteStatement =
    "DELETE FROM profesor "+
    "WHERE id = ?";

  public void delete() {
    if (jdbcTemplate==null) createJdbcTemplate();
      jdbcTemplate.update(deleteStatement,id);
  }

  public static ProfesorRowGateway load(DataSource ds, Map map) {
    ProfesorRowGateway prof=null;
    if (map==null)
      prof = new ProfesorRowGateway();
    else {
      int id = ((Integer)map.get("id")).intValue();
      String cedula = (String)map.get("cedula");
      String nombre = (String)map.get("nombre");
      String titulo = (String)map.get("titulo");
      String area = (String)map.get("area");
      String telefono = (String)map.get("telefono");
      prof = new ProfesorRowGateway(id,cedula,nombre,titulo,area,telefono);
    }
    prof.setDataSource(ds);
    return prof;
  }
}