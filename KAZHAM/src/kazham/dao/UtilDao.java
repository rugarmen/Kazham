package kazham.dao;

import java.sql.Connection;
import java.util.List;

import kazham.bean.ListaCorreoGenerico;
import kazham.bean.ListaLogAuditoria;
import kazham.bean.ListaParametro;
import kazham.bean.LstConstante;
import kazham.bean.LstLog;
import kazham.bean.LstParametro;
import kazham.bean.LstTipparametro;
import kazham.bean.MntArchivoblob;
import kazham.bean.MntCasillaCorreo;
import kazham.bean.MntConstante;
import kazham.bean.MntMailLogCursor;
import kazham.bean.MntParametro;
import kazham.bean.MntTipparametro;
import kazham.bean.RegUsuarioxOpcion;
import kazham.bean.TipoCambio;
import kazham.bean.Usuario;
import kazham.bean.ValCfgTippar;

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
  
  void listaParametro(Connection conn, ListaParametro param);

  void lstParametro(Connection conn, LstParametro param);

  void mntParametro(Connection conn, MntParametro param);

  void valCfgTippar(Connection conn, ValCfgTippar param);
    
  void lstTipparametro(Connection conn, LstTipparametro param);

  void mntTipparametro(Connection conn, MntTipparametro param);

  void mntArchivoblob(Connection conn, MntArchivoblob param);
  
  String viewFile(Connection conn, String idArchivo, String ruta);

  void lstConstante(Connection conn, LstConstante param);

  void mntConstante(Connection conn, MntConstante param);

  void mntArchivoblobGes(Connection conn, MntArchivoblob param);

  void lstParametroGes(Connection conn, LstParametro param);

  void mntParametroGes(Connection conn, MntParametro param);

  void valCfgTipparGes(Connection conn, ValCfgTippar param);
    
  void lstTipparametroGes(Connection conn, LstTipparametro param);

  void mntTipparametroGes(Connection conn, MntTipparametro param);

  void regUsuarioxOpcion(Connection conn, RegUsuarioxOpcion param);
  
  void obtConstante(Connection conn, LstConstante param);
  
  void obtAcercade(Connection conn, LstConstante param);

void mntAcuseRecibo(Connection conn,MntMailLogCursor mntMailLog);

void obtDatosCasillaMail(Connection conn, ListaCorreoGenerico lstCasilla);

void mntCasillaCorreo(Connection conn, MntCasillaCorreo param);

void lstCasillaCorreo(Connection conn, MntCasillaCorreo param);

List lstLog(Connection conn, LstLog lstlog);

Usuario obtUsuario(Connection conn,Usuario usuario);

void obtTipoCambio(Connection conn, TipoCambio tipocambio); //SCASTANEDM 2014.12.31

String getIndicadorTrabajadorRimac(Connection conn, String username); //SCASTANEDM 2014.12.31

void listarLogAuditoria(Connection conn, ListaLogAuditoria param); 	// RCR 04/09/2015 GC 21989 

}


