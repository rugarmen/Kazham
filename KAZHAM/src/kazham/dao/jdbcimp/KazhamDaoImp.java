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

import kazham.bean.ComponentePlantilla;
import kazham.bean.DetallePlantilla;
import kazham.bean.Informacion;
import kazham.bean.LstConstante;
import kazham.bean.LstConstanteCursor;
import kazham.bean.LstParametro;
import kazham.bean.MntArchivoblob;
import kazham.bean.MntConstante;
import kazham.bean.MntParametro;
import kazham.bean.PeriodoMes;
import kazham.bean.Plantilla;
import kazham.bean.Usuario;
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
	    ResultSet rs=null;

	    try {
		      cs=conn.prepareCall("{call " + OWNER + ".PQ_MIN_LISTADO.SP_CBO_PERIODO(?)}");
		      cs.registerOutParameter(1, OracleTypes.CURSOR);
		      cs.execute();
		      
		      rs=(ResultSet)cs.getObject(1);
		      param.setCursor(commons.mapper.Utils.populateListFromResultSet(PeriodoMes.class.getName(), rs));


		    } catch (SQLException e) {
		      throw new RuntimeException("" + e, e);

		    } finally {
		      JdbcHelper.close(null, cs, null, null, null);
		    }
  	}
  
  	public void grabarInformacion(Connection conn, Informacion param){
	    CallableStatement cs=null;

		    try {
			      cs=conn.prepareCall("{call " + OWNER + ".PQ_KAZ_USUARIO.sp_mnt_usuario(?,?,?,?)}");
			      
//			      commons.util.JdbcHelper.setString(cs, 1, param.getIdeusuario());
//			      commons.util.JdbcHelper.setString(cs, 2, param.getName());
//			      commons.util.JdbcHelper.setString(cs, 3, param.getEmail());
//			      commons.util.JdbcHelper.setString(cs, 4, param.getPassword());
			      cs.execute();
			      //param.setIdetippar(cs.getString(1));

			    } catch (SQLException e) {
//			      logger.error("[mntConstante] : " + "Conexion a BD");
			      throw new RuntimeException("" + e, e);

			    } finally {
			      JdbcHelper.close(null, cs, null, null, null);

			    }
	    	    
  	}
  
    public void listarInformacion(Connection conn, Informacion param){
	    CallableStatement cs=null;
	    ResultSet rs=null;

	    try {
		      cs=conn.prepareCall("{call " + OWNER + ".PQ_MIN_LISTADO.SP_LST_INFORMACION(?)}");
		      cs.registerOutParameter(1, OracleTypes.CURSOR);
		      cs.execute();
		      
		      rs=(ResultSet)cs.getObject(1);
		      param.setCursor(commons.mapper.Utils.populateListFromResultSet(Informacion.class.getName(), rs));


		    } catch (SQLException e) {
		      throw new RuntimeException("" + e, e);

		    } finally {
		      JdbcHelper.close(null, cs, null, null, null);
		    }
  	}
    
    public void listarPlantilla(Connection conn, Plantilla param){
	    CallableStatement cs=null;
	    ResultSet rs=null;

	    try {
		      cs=conn.prepareCall("{call " + OWNER + ".PQ_MIN_LISTADO.SP_LST_PLANTILLA(?,?)}");
		      commons.util.JdbcHelper.setBigDecimal(cs, 1, param.getIdeempresa());
		      cs.registerOutParameter(2, OracleTypes.CURSOR);
		      cs.execute();
		      
		      rs=(ResultSet)cs.getObject(2);
		      param.setCursor(commons.mapper.Utils.populateListFromResultSet(Plantilla.class.getName(), rs));


		    } catch (SQLException e) {
		      throw new RuntimeException("" + e, e);

		    } finally {
		      JdbcHelper.close(null, cs, null, null, null);
		    }
  	}
    
    public void listarComponentePlantilla(Connection conn, ComponentePlantilla param){
	    CallableStatement cs=null;
	    ResultSet rs=null;

	    try {
		      cs=conn.prepareCall("{call " + OWNER + ".PQ_MIN_LISTADO.SP_LST_COMPLANTILLA(?,?)}");
		      commons.util.JdbcHelper.setBigDecimal(cs, 1, param.getIdeplantilla());
		      cs.registerOutParameter(2, OracleTypes.CURSOR);
		      cs.execute();
		      
		      rs=(ResultSet)cs.getObject(2);
		      param.setCursor(commons.mapper.Utils.populateListFromResultSet(ComponentePlantilla.class.getName(), rs));


		    } catch (SQLException e) {
		      throw new RuntimeException("" + e, e);

		    } finally {
		      JdbcHelper.close(null, cs, null, null, null);
		    }
  	}
    
    public void listarDetallePlantilla(Connection conn, DetallePlantilla param){
	    CallableStatement cs=null;
	    ResultSet rs=null;

	    try {
		      cs=conn.prepareCall("{call " + OWNER + ".PQ_MIN_LISTADO.SP_LST_DETPLANTILLA(?,?)}");
		      commons.util.JdbcHelper.setBigDecimal(cs, 1, param.getIdecomplantilla());
		      cs.registerOutParameter(2, OracleTypes.CURSOR);
		      cs.execute();
		      
		      rs=(ResultSet)cs.getObject(2);
		      param.setCursor(commons.mapper.Utils.populateListFromResultSet(DetallePlantilla.class.getName(), rs));


		    } catch (SQLException e) {
		      throw new RuntimeException("" + e, e);

		    } finally {
		      JdbcHelper.close(null, cs, null, null, null);
		    }
  	}
}


