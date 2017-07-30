package kazham.dao.jdbcimp;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import kazham.bean.ListaCorreoGenerico;
import kazham.bean.ListaLogAuditoria;
import kazham.bean.Periodo;
import kazham.bean.LstConstante;
import kazham.bean.LstConstanteCursor;
import kazham.bean.LstLog;
import kazham.bean.LstParametro;
import kazham.bean.LstTipparametro;
import kazham.bean.MntArchivoblob;
import kazham.bean.MntCasillaCorreo;
import kazham.bean.MntConstante;
import kazham.bean.MntMailLogCursor;
import kazham.bean.MntParametro;
import kazham.bean.MntTipparametro;
import kazham.bean.PeriodoMes;
import kazham.bean.RegUsuarioxOpcion;
import kazham.bean.TipoCambio;
import kazham.bean.Usuario;
import kazham.bean.ValCfgTippar;
import kazham.commons.Constants;
import kazham.dao.KazhamDao;
import kazham.dao.UtilDao;

import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;


import commons.util.JdbcHelper;

/**
 * Funcion: clase dao de la clase util 
 * Creación:  - 
 * Modificaciones:
 * 					1.0								Version Inicial del Componente
 * SCASTANEDM		2.0			31/12/2014			Se creo el método: 
 * 													- obtTipoCambio
 */

public class KazhamDaoImp implements KazhamDao {

//  private Logger logger = Logger.getLogger(KazhamDaoImp.class.getName());
  private static KazhamDaoImp instance;
//  private static final ResourceBundle resources = ResourceBundle.getBundle("configuracion");
  private static final String OWNER = "APP_IAA_COMUNES";
  
  public static KazhamDaoImp getInstance() {
    if (instance == null) {
	  instance = new KazhamDaoImp();
    }
    return instance;
  }

  public void guardarDato(Connection conn, Usuario param){
	    CallableStatement cs=null;

		    try {
			      cs=conn.prepareCall("{call " + OWNER + ".PQ_KAZ_USUARIO.sp_mnt_usuario(?,?,?,?)}");
			      
			      commons.util.JdbcHelper.setString(cs, 1, param.getIdeusuario());
			      commons.util.JdbcHelper.setString(cs, 2, param.getName());
			      commons.util.JdbcHelper.setString(cs, 3, param.getEmail());
			      commons.util.JdbcHelper.setString(cs, 4, param.getPassword());
			      cs.execute();
			      //param.setIdetippar(cs.getString(1));

			    } catch (SQLException e) {
//			      logger.error("[mntConstante] : " + "Conexion a BD");
			      throw new RuntimeException("" + e, e);

			    } finally {
			      JdbcHelper.close(null, cs, null, null, null);

			    }
	    	    
  }
    	
	
  public void listarPeriodo(Connection conn, PeriodoMes param){
	    CallableStatement cs=null;

	    try {
		      cs=conn.prepareCall("{call " + OWNER + ".PQ_KAZ_USUARIO.sp_mnt_usuario(?,?,?,?)}");
		      
//		      commons.util.JdbcHelper.setString(cs, 1, param.getIdeusuario());
//		      commons.util.JdbcHelper.setString(cs, 2, param.getName());
//		      commons.util.JdbcHelper.setString(cs, 3, param.getEmail());
//		      commons.util.JdbcHelper.setString(cs, 4, param.getPassword());
		      cs.execute();
		      //param.setIdetippar(cs.getString(1));

		    } catch (SQLException e) {
		      throw new RuntimeException("" + e, e);

		    } finally {
		      JdbcHelper.close(null, cs, null, null, null);
		    }
  	}
}


