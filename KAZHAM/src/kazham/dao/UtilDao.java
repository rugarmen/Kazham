package kazham.dao;

import java.sql.Connection;
import java.util.List;

import kazham.bean.LstConstante;
import kazham.bean.LstParametro;
import kazham.bean.MntArchivoblob;
import kazham.bean.MntConstante;
import kazham.bean.MntParametro;
import kazham.bean.Usuario;
import org.json.simple.JSONObject;



//import sas.seguridad.bean.ObtUsuarioCursor;

/**
 * Funcion: clase dao de la clase util 
 * Creación:  - 
 * Modificaciones:
 * 					1.0								Version Inicial del Componente
 * SCASTANEDM		2.0			31/12/2014			Se creo el método: 
 * 													- obtTipoCambio
 */

public interface UtilDao  {

  void init(Connection conn, JSONObject jo);
  
  void initUser(Connection conn, String usuario, JSONObject jo);
  
  void lstParametro(Connection conn, LstParametro param);

  void mntParametro(Connection conn, MntParametro param);

  void mntArchivoblob(Connection conn, MntArchivoblob param);
  
  String viewFile(Connection conn, String idArchivo, String ruta);

  void lstConstante(Connection conn, LstConstante param);

  void mntConstante(Connection conn, MntConstante param);

  void mntArchivoblobGes(Connection conn, MntArchivoblob param);

  void lstParametroGes(Connection conn, LstParametro param);

  void mntParametroGes(Connection conn, MntParametro param);

  void obtConstante(Connection conn, LstConstante param);
  
  void obtAcercade(Connection conn, LstConstante param);

  Usuario obtUsuario(Connection conn,Usuario usuario);

  String getIndicadorTrabajadorRimac(Connection conn, String username);

}


