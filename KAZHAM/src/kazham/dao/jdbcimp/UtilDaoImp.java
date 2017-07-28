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
import kazham.bean.ListaParametro;
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
import kazham.bean.RegUsuarioxOpcion;
import kazham.bean.TipoCambio;
import kazham.bean.Usuario;
import kazham.bean.ValCfgTippar;
import kazham.commons.Constants;
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

public class UtilDaoImp implements UtilDao {

  private Logger logger = Logger.getLogger(UtilDaoImp.class.getName());
  private static UtilDaoImp instance;
  private static final ResourceBundle resources = ResourceBundle.getBundle("configuracion");
  private static final String OWNER = resources.getString("APP_IAA_COMUNES")+"."; //Constants.OWNER_ESQUEMA_COMUNES
  private static final String OWNERGES = resources.getString("APP_IAA_GESTION")+"."; //Constants.OWNER_ESQUEMA_GESTION
  private static final String OWNERREP = resources.getString("APP_IAA_REPORTE")+"."; //Constants.OWNER_ESQUEMA_REPORTE

  public static UtilDaoImp getInstance() {
    if (instance == null) {
	  instance = new UtilDaoImp();
    }
    return instance;
  }

  public void init(Connection conn, JSONObject jo){
	    CallableStatement cs=null;
	    ResultSet rs01=null;
	    ResultSet rs02=null;
	    ResultSet rs03=null;
	    ResultSet rs04=null;
	    ResultSet rs05=null;
	    ResultSet rs06=null;
	    ResultSet rs07=null;
	    ResultSet rs08=null;
	    ResultSet rs09=null;
	    ResultSet rs10=null;
	    ResultSet rs11=null;

	    try {
	        cs=conn.prepareCall("{call " + OWNER + "PQ_IAA_COMUN.sp_obt_constantes_general(?,?,?,?,?,?,?,?,?,?,?)}");
	        cs.registerOutParameter(1, OracleTypes.CURSOR);
	        cs.registerOutParameter(2, OracleTypes.CURSOR);
	        cs.registerOutParameter(3, OracleTypes.CURSOR);
	        cs.registerOutParameter(4, OracleTypes.CURSOR);
	        cs.registerOutParameter(5, OracleTypes.CURSOR);
	        cs.registerOutParameter(6, OracleTypes.CURSOR);
	        cs.registerOutParameter(7, OracleTypes.CURSOR);
	        cs.registerOutParameter(8, OracleTypes.CURSOR);
	        cs.registerOutParameter(9, OracleTypes.CURSOR);
	        cs.registerOutParameter(10, OracleTypes.CURSOR);
	        cs.registerOutParameter(11, OracleTypes.CURSOR);
	        cs.execute();        
	        rs01 = (ResultSet)cs.getObject(1);		//	a_cursor_sas 
	        rs02 = (ResultSet)cs.getObject(2);		//	a_cursor_tercero 
	        rs03 = (ResultSet)cs.getObject(3);		//	a_cursor_acuerdo 	
	        rs04 = (ResultSet)cs.getObject(4);		//	a_cursor_finanzas 	
	        rs05 = (ResultSet)cs.getObject(5);		//	a_cursor_gestion 	
	        rs06 = (ResultSet)cs.getObject(6);		//	a_cursor_interfaz 	
	        rs07 = (ResultSet)cs.getObject(7);		//	a_cursor_migracion 
	        rs08 = (ResultSet)cs.getObject(8);		//	a_cursor_producto 	
	        rs09 = (ResultSet)cs.getObject(9);		//	a_cursor_reporte 	
	        rs10 = (ResultSet)cs.getObject(10);		//	a_cursor_siniestro 	
	        rs11 = (ResultSet)cs.getObject(11);		//	a_cursor_soporte 

	        jo.put("sas", commons.mapper.Utils.getJSONObject(rs01));
	        jo.put("ter", commons.mapper.Utils.getJSONObject(rs02));
	        jo.put("acu", commons.mapper.Utils.getJSONObject(rs03));
	        jo.put("fin", commons.mapper.Utils.getJSONObject(rs04));
	        jo.put("ges", commons.mapper.Utils.getJSONObject(rs05));
	        jo.put("int", commons.mapper.Utils.getJSONObject(rs06));
	        jo.put("mig", commons.mapper.Utils.getJSONObject(rs07));
	        jo.put("pro", commons.mapper.Utils.getJSONObject(rs08));
	        jo.put("rep", commons.mapper.Utils.getJSONObject(rs09));
	        jo.put("sin", commons.mapper.Utils.getJSONObject(rs10));
	        jo.put("sop", commons.mapper.Utils.getJSONObject(rs11));


	    } catch (SQLException e) {
	      logger.error("[init] : " + "Conexion a BD");
	      throw new RuntimeException("" + e, e);

	    } finally {
		      JdbcHelper.close(null, cs, null, null, null);
		      JdbcHelper.close(null, null, null, null, rs01);
		      JdbcHelper.close(null, null, null, null, rs02);
		      JdbcHelper.close(null, null, null, null, rs03);
		      JdbcHelper.close(null, null, null, null, rs04);
		      JdbcHelper.close(null, null, null, null, rs05);
		      JdbcHelper.close(null, null, null, null, rs06);
		      JdbcHelper.close(null, null, null, null, rs07);
		      JdbcHelper.close(null, null, null, null, rs08);
		      JdbcHelper.close(null, null, null, null, rs09);
		      JdbcHelper.close(null, null, null, null, rs10);
		      JdbcHelper.close(null, null, null, null, rs11);

	    }
  }

  public void initUser(Connection conn, String usuario, JSONObject jo){
	    CallableStatement cs=null;
	    ResultSet rsUser=null;

	    try {
	        cs=conn.prepareCall("{call " + OWNER + "PQ_IAA_SEGURIDAD.sp_obt_valores_usuario(?,?)}");
	        commons.util.JdbcHelper.setString(cs, 1, usuario);
	        cs.registerOutParameter(2, OracleTypes.CURSOR);
	        cs.execute();        
	        rsUser = (ResultSet)cs.getObject(2);		//	a_cursor_

	        jo.put("usr", commons.mapper.Utils.getJSONObject(rsUser));


	    } catch (SQLException e) {
	      logger.error("[init] : " + "Conexion a BD");
	      throw new RuntimeException("" + e, e);

	    } finally {
		      JdbcHelper.close(null, cs, null, null, null);
		      JdbcHelper.close(null, null, null, null, rsUser);

	    }
  }
  
  public void listaParametro(Connection conn, ListaParametro param) {
    CallableStatement cs=null;
    ResultSet rs=null;

    try {
      cs=conn.prepareCall("{call " + OWNER + "PQ_IAA_COMUN.SP_LISTA_PARAMETRO(?,?)}");
      commons.util.JdbcHelper.setString(cs, 1, param.getIdetippar());
      cs.registerOutParameter(2, OracleTypes.CURSOR);
      cs.execute();
      rs = (ResultSet)cs.getObject(2);
      param.setCursor(commons.mapper.Utils.populateListFromResultSet("sas.bean.ListaParametroCursor", rs));


    } catch (SQLException e) {
      logger.error("[listaParametro] : " + "Conexion a BD");
      throw new RuntimeException("" + e, e);

    } finally {
      JdbcHelper.close(null, cs, null, null, rs);

    }
  }

  public void lstTipparametro(Connection conn, LstTipparametro param) {
    CallableStatement cs=null;
    ResultSet rs=null;

    try {
      cs=conn.prepareCall("{call " + OWNER + "PQ_IAA_COMUN.sp_lst_tipparametro(?,?,?,?,?,?)}");
      commons.util.JdbcHelper.setString(cs, 1, param.getIdetippar());
      commons.util.JdbcHelper.setString(cs, 2, param.getDescripcion());
      commons.util.JdbcHelper.setString(cs, 3, param.getCodexterno());
      commons.util.JdbcHelper.setString(cs, 4, param.getIndactivo());
      commons.util.JdbcHelper.setString(cs, 5, param.getUsuario());
      cs.registerOutParameter(6, OracleTypes.CURSOR);
      cs.execute();
      rs=(ResultSet)cs.getObject(6);
      param.setCursor(commons.mapper.Utils.populateListFromResultSet("sas.bean.LstTipparametroCursor", rs));


    } catch (SQLException e) {
      logger.error("[lstTipparametro] : " + "Conexion a BD");
      throw new RuntimeException("" + e, e);

    } finally {
      JdbcHelper.close(null, cs, null, null, rs);

    }
  }

  public void mntTipparametro(Connection conn, MntTipparametro param) {
    CallableStatement cs=null;

    try {
      cs=conn.prepareCall("{call " + OWNER + "PQ_IAA_COMUN.sp_mnt_tipparametro(?,?,?,?,?,?,?,?)}");
      
      commons.util.JdbcHelper.setString(cs, 1, param.getIdetippar());
      cs.registerOutParameter(1, Types.VARCHAR);
      commons.util.JdbcHelper.setString(cs, 2, param.getDescripcion());
      commons.util.JdbcHelper.setString(cs, 3, param.getCodexterno());
      commons.util.JdbcHelper.setString(cs, 4, param.getIndactivo());
      commons.util.JdbcHelper.setInt(cs, 5, param.getLongitudcampo());
      commons.util.JdbcHelper.setString(cs, 6, param.getTipodato());
      commons.util.JdbcHelper.setString(cs, 7, param.getUsuario());
      commons.util.JdbcHelper.setString(cs, 8, param.getFgnew());
      
      cs.execute();
      param.setIdetippar(cs.getString(1));

    } catch (SQLException e) {
      logger.error("[mntParametro] : " + "Conexion a BD");
      throw new RuntimeException("" + e, e);

    } finally {
      JdbcHelper.close(null, cs, null, null, null);

    }
  }
	  
  public void lstParametro(Connection conn, LstParametro param) {
    CallableStatement cs=null;
    ResultSet rs=null;

    try {
      cs=conn.prepareCall("{call " + OWNER + "PQ_IAA_COMUN.SP_LST_PARAMETRO2(?,?,?,?,?,?,?,?,?)}");
      commons.util.JdbcHelper.setString(cs, 1, param.getIdetippar());
      commons.util.JdbcHelper.setString(cs, 2, param.getIdetipparpadre());
      commons.util.JdbcHelper.setString(cs, 3, param.getCodigo());
      commons.util.JdbcHelper.setString(cs, 4, param.getAbreviatura());
      commons.util.JdbcHelper.setString(cs, 5, param.getDescripcion());
      commons.util.JdbcHelper.setString(cs, 6, param.getDescripcion2());
      commons.util.JdbcHelper.setString(cs, 7, param.getIndactivo());
      commons.util.JdbcHelper.setString(cs, 8, param.getMasterdetail());
      cs.registerOutParameter(9, OracleTypes.CURSOR);
      cs.execute();
      rs=(ResultSet)cs.getObject(9);
      param.setCursor(commons.mapper.Utils.populateListFromResultSet("sas.bean.LstParametroCursor", rs));


    } catch (SQLException e) {
      logger.error("[lstParametro] : " + "Conexion a BD");
      throw new RuntimeException("" + e, e);

    } finally {
      JdbcHelper.close(null, cs, null, null, rs);

    }
  }

  public void mntParametro(Connection conn, MntParametro param) {
    CallableStatement cs=null;

    try {
      cs=conn.prepareCall("{call " + OWNER + "PQ_IAA_COMUN.SP_MNT_PARAMETRO2(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
      commons.util.JdbcHelper.setInt(cs, 1, param.getIdepar());
      cs.registerOutParameter(1, Types.NUMERIC);
      commons.util.JdbcHelper.setString(cs, 2, param.getIdetippar());
      commons.util.JdbcHelper.setInt(cs, 3, param.getCodigon());
      commons.util.JdbcHelper.setString(cs, 4, param.getCodigoc());
      commons.util.JdbcHelper.setString(cs, 5, param.getAbreviatura());
      commons.util.JdbcHelper.setString(cs, 6, param.getDescripcion());
      commons.util.JdbcHelper.setString(cs, 7, param.getDescripcion2());
      commons.util.JdbcHelper.setString(cs, 8, param.getIndactivo());
      commons.util.JdbcHelper.setString(cs, 9, param.getUsucreacion());
      commons.util.JdbcHelper.setString(cs, 10, param.getUsumodif());
      commons.util.JdbcHelper.setString(cs, 11, param.getCodexterno());
      commons.util.JdbcHelper.setString(cs, 12, param.getIdetipparpadre());
      commons.util.JdbcHelper.setString(cs, 13, param.getRefmigracion());
      commons.util.JdbcHelper.setString(cs, 14, param.getRefmigracionrs());
      cs.execute();
      param.setIdepar(new Integer(cs.getInt(1)));


    } catch (SQLException e) {
      logger.error("[mntParametro] : " + "Conexion a BD");
      throw new RuntimeException("" + e, e);

    } finally {
      JdbcHelper.close(null, cs, null, null, null);

    }
  }

  public void valCfgTippar(Connection conn, ValCfgTippar param) {
    CallableStatement cs=null;
    
    try {
      cs=conn.prepareCall("{call " + OWNER + "PQ_IAA_COMUN.SP_VAL_CFGTIPPAR(?,?,?)}");
      commons.util.JdbcHelper.setString(cs, 1, param.getCodusuario() );
      commons.util.JdbcHelper.setString(cs, 2, param.getIdetippar());
      cs.registerOutParameter(3, Types.VARCHAR);
      cs.execute();
      param.setResult(cs.getString(3));

    } catch (SQLException e) {
      logger.error("[valCfgTippar] : " + "Conexion a BD");
      throw new RuntimeException("" + e, e);

    } finally {
      JdbcHelper.close(null, cs, null, null, null);

    }
  }

  public void mntArchivoblob(Connection conn, MntArchivoblob param) {
    CallableStatement cs=null;
    
    try {
      cs=conn.prepareCall("{call " + OWNER + "PQ_IAA_COMUN.SP_MNT_ARCHIVOBLOB(?,?,?,?,?,?,?)}");
      commons.util.JdbcHelper.setInt(cs, 1, param.getIdearchivoblob());
      cs.registerOutParameter(1, Types.INTEGER);
      cs.setBlob(2, param.getArchivoblob());
      commons.util.JdbcHelper.setString(cs, 3, param.getNomarchivo());
      commons.util.JdbcHelper.setString(cs, 4, param.getNomarchivo2());
      commons.util.JdbcHelper.setString(cs, 5, param.getExtension());
      commons.util.JdbcHelper.setString(cs, 6, param.getBytes());
      commons.util.JdbcHelper.setString(cs, 7, param.getDescripcion());
      cs.execute();
      param.setIdearchivoblob(new Integer(cs.getInt(1)));

    } catch (SQLException e) {
      logger.error("[mntArchivoblob] : " + "Conexion a BD");
      throw new RuntimeException("" + e, e);

    } finally {
      JdbcHelper.close(null, cs, null, null, null);

    }
  }

  public String viewFile(Connection conn, String idArchivo, String ruta) {
	    CallableStatement cs=null;
	    ResultSet rs = null ;
	    String fileName = null;

	    try {
	      cs=conn.prepareCall("{call APP_IAA_COMUNES.PQ_IAA_COMUN.SP_OBT_ARCHIVOBLOB(?,?)}");
	      commons.util.JdbcHelper.setString(cs, 1, idArchivo);
	      cs.registerOutParameter(2, OracleTypes.CURSOR);
	      cs.execute();
	      rs = (ResultSet)cs.getObject(2);
	      if(rs.next()){	    	  
	    	  fileName = rs.getString("nomarchivo");	    	  
	    	  
	    	  writeBlobToFile(ruta + 
	    			          "temp" + 
	    			          System.getProperty("file.separator") + 
	    			          fileName, 
	    			          rs.getBlob("archivoblob"));
	      }

		} catch(Exception e) {
			logger.error("Error en obtener el archivo blob en DB: " + e);
			throw new RuntimeException("Error en obtener el archivo blob en DB", e);
		} finally {
			JdbcHelper.close(null, cs, null, null, rs);
		}
	    return fileName;
	    
	  }  
  
	public static void writeBlobToFile(String filename, Blob outBytes){
		try {
			
			File file =new File(filename);
	   	 
			//if file doesnt exists, then create it
			if(!file.exists()){
			   file.createNewFile();
			}
	
			FileOutputStream fileOutputStream=new FileOutputStream(file);
			//	get the content in bytes
			fileOutputStream.write(outBytes.getBytes(1, (int) outBytes.length()));
			fileOutputStream.flush();
			fileOutputStream.close();
			
		} catch (Exception e) {
			throw new RuntimeException("" + e, e);
		}
	}  


	  public void lstConstante(Connection conn, LstConstante param) {
	    CallableStatement cs=null;
	    ResultSet rs=null;

	    try {
	      cs=conn.prepareCall("{call " + OWNER + "PQ_IAA_COMUN.sp_lst_constante(?,?,?,?,?,?)}");
	      commons.util.JdbcHelper.setString(cs, 1, param.getIdeconstante());
	      commons.util.JdbcHelper.setString(cs, 2, param.getDscconstante());
	      commons.util.JdbcHelper.setString(cs, 3, param.getIdptipodato());
	      commons.util.JdbcHelper.setString(cs, 4, param.getValor());
	      commons.util.JdbcHelper.setString(cs, 5, param.getUsuario());
	      cs.registerOutParameter(6, OracleTypes.CURSOR);
	      cs.execute();
	      rs=(ResultSet)cs.getObject(6);
	      param.setCursor(commons.mapper.Utils.populateListFromResultSet("sas.bean.LstConstanteCursor", rs));


	    } catch (SQLException e) {
	      logger.error("[lstConstante] : " + "Conexion a BD");
	      throw new RuntimeException("" + e, e);

	    } finally {
	      JdbcHelper.close(null, cs, null, null, rs);

	    }
	  }

	  public void mntConstante(Connection conn, MntConstante param) {
	    CallableStatement cs=null;

	    try {
	      cs=conn.prepareCall("{call " + OWNER + "PQ_IAA_COMUN.sp_mnt_constante(?,?,?,?,?,?)}");
	      
	      commons.util.JdbcHelper.setString(cs, 1, param.getIdeconstante());
	      commons.util.JdbcHelper.setString(cs, 2, param.getDscconstante());
	      commons.util.JdbcHelper.setString(cs, 3, param.getIdptipodato());
	      commons.util.JdbcHelper.setString(cs, 4, param.getValor());
	      commons.util.JdbcHelper.setString(cs, 5, param.getUsuario());
	      commons.util.JdbcHelper.setString(cs, 6, param.getValorclob());
	      cs.execute();
	      //param.setIdetippar(cs.getString(1));

	    } catch (SQLException e) {
	      logger.error("[mntConstante] : " + "Conexion a BD");
	      throw new RuntimeException("" + e, e);

	    } finally {
	      JdbcHelper.close(null, cs, null, null, null);

	    }
	  }

	  public void mntArchivoblobGes(Connection conn, MntArchivoblob param) {
	    CallableStatement cs=null;
	    
	    try {
	      cs=conn.prepareCall("{call " + OWNERGES + "PQ_IAA_COMUN_GES.SP_MNT_ARCHIVOBLOB(?,?,?,?,?,?,?)}");
	      commons.util.JdbcHelper.setInt(cs, 1, param.getIdearchivoblob());
	      cs.registerOutParameter(1, Types.INTEGER);
	      cs.setBlob(2, param.getArchivoblob());
	      commons.util.JdbcHelper.setString(cs, 3, param.getNomarchivo());
	      commons.util.JdbcHelper.setString(cs, 4, param.getNomarchivo2());
	      commons.util.JdbcHelper.setString(cs, 5, param.getExtension());
	      commons.util.JdbcHelper.setString(cs, 6, param.getBytes());
	      commons.util.JdbcHelper.setString(cs, 7, param.getDescripcion());
	      cs.execute();
	      param.setIdearchivoblob(new Integer(cs.getInt(1)));

	    } catch (SQLException e) {
	      logger.error("[mntArchivoblob] : " + "Conexion a BD");
	      throw new RuntimeException("" + e, e);

	    } finally {
	      JdbcHelper.close(null, cs, null, null, null);

	    }
	  }


	  public void lstTipparametroGes(Connection conn, LstTipparametro param) {
	    CallableStatement cs=null;
	    ResultSet rs=null;

	    try {
	      cs=conn.prepareCall("{call " + OWNERGES + "PQ_IAA_COMUN_GES.sp_lst_tipparametro(?,?,?,?,?,?)}");
	      commons.util.JdbcHelper.setString(cs, 1, param.getIdetippar());
	      commons.util.JdbcHelper.setString(cs, 2, param.getDescripcion());
	      commons.util.JdbcHelper.setString(cs, 3, param.getCodexterno());
	      commons.util.JdbcHelper.setString(cs, 4, param.getIndactivo());
	      commons.util.JdbcHelper.setString(cs, 5, param.getUsuario());
	      cs.registerOutParameter(6, OracleTypes.CURSOR);
	      cs.execute();
	      rs=(ResultSet)cs.getObject(6);
	      param.setCursor(commons.mapper.Utils.populateListFromResultSet("sas.bean.LstTipparametroCursor", rs));


	    } catch (SQLException e) {
	      logger.error("[lstTipparametro] : " + "Conexion a BD");
	      throw new RuntimeException("" + e, e);

	    } finally {
	      JdbcHelper.close(null, cs, null, null, rs);

	    }
	  }

	  public void mntTipparametroGes(Connection conn, MntTipparametro param) {
	    CallableStatement cs=null;

	    try {
	      cs=conn.prepareCall("{call " + OWNERGES + "PQ_IAA_COMUN_GES.sp_mnt_tipparametro(?,?,?,?,?,?,?,?)}");
	      
	      commons.util.JdbcHelper.setString(cs, 1, param.getIdetippar());
	      cs.registerOutParameter(1, Types.VARCHAR);
	      commons.util.JdbcHelper.setString(cs, 2, param.getDescripcion());
	      commons.util.JdbcHelper.setString(cs, 3, param.getCodexterno());
	      commons.util.JdbcHelper.setString(cs, 4, param.getIndactivo());
	      commons.util.JdbcHelper.setInt(cs, 5, param.getLongitudcampo());
	      commons.util.JdbcHelper.setString(cs, 6, param.getTipodato());
	      commons.util.JdbcHelper.setString(cs, 7, param.getUsuario());
	      commons.util.JdbcHelper.setString(cs, 8, param.getFgnew());
	      
	      cs.execute();
	      param.setIdetippar(cs.getString(1));

	    } catch (SQLException e) {
	      logger.error("[mntParametro] : " + "Conexion a BD");
	      throw new RuntimeException("" + e, e);

	    } finally {
	      JdbcHelper.close(null, cs, null, null, null);

	    }
	  }
		  
	  public void lstParametroGes(Connection conn, LstParametro param) {
	    CallableStatement cs=null;
	    ResultSet rs=null;

	    try {
	      cs=conn.prepareCall("{call " + OWNERGES + "PQ_IAA_COMUN_GES.SP_LST_PARAMETRO2(?,?,?,?,?,?,?,?,?)}");
	      commons.util.JdbcHelper.setString(cs, 1, param.getIdetippar());
	      commons.util.JdbcHelper.setString(cs, 2, param.getIdetipparpadre());
	      commons.util.JdbcHelper.setString(cs, 3, param.getCodigo());
	      commons.util.JdbcHelper.setString(cs, 4, param.getAbreviatura());
	      commons.util.JdbcHelper.setString(cs, 5, param.getDescripcion());
	      commons.util.JdbcHelper.setString(cs, 6, param.getDescripcion2());
	      commons.util.JdbcHelper.setString(cs, 7, param.getIndactivo());
	      commons.util.JdbcHelper.setString(cs, 8, param.getMasterdetail());
	      cs.registerOutParameter(9, OracleTypes.CURSOR);
	      cs.execute();
	      rs=(ResultSet)cs.getObject(9);
	      param.setCursor(commons.mapper.Utils.populateListFromResultSet("sas.bean.LstParametroCursor", rs));


	    } catch (SQLException e) {
	      logger.error("[lstParametro] : " + "Conexion a BD");
	      throw new RuntimeException("" + e, e);

	    } finally {
	      JdbcHelper.close(null, cs, null, null, rs);

	    }
	  }

	  public void mntParametroGes(Connection conn, MntParametro param) {
	    CallableStatement cs=null;

	    try {
	      cs=conn.prepareCall("{call " + OWNERGES + "PQ_IAA_COMUN_GES.SP_MNT_PARAMETRO2(?,?,?,?,?,?,?,?,?,?,?,?)}");
	      commons.util.JdbcHelper.setInt(cs, 1, param.getIdepar());
	      cs.registerOutParameter(1, Types.NUMERIC);
	      commons.util.JdbcHelper.setString(cs, 2, param.getIdetippar());
	      commons.util.JdbcHelper.setInt(cs, 3, param.getCodigon());
	      commons.util.JdbcHelper.setString(cs, 4, param.getCodigoc());
	      commons.util.JdbcHelper.setString(cs, 5, param.getAbreviatura());
	      commons.util.JdbcHelper.setString(cs, 6, param.getDescripcion());
	      commons.util.JdbcHelper.setString(cs, 7, param.getDescripcion2());
	      commons.util.JdbcHelper.setString(cs, 8, param.getIndactivo());
	      commons.util.JdbcHelper.setString(cs, 9, param.getUsucreacion());
	      commons.util.JdbcHelper.setString(cs, 10, param.getUsumodif());
	      commons.util.JdbcHelper.setString(cs, 11, param.getCodexterno());
	      commons.util.JdbcHelper.setString(cs, 12, param.getIdetipparpadre());
	      cs.execute();
	      param.setIdepar(new Integer(cs.getInt(1)));


	    } catch (SQLException e) {
	      logger.error("[mntParametro] : " + "Conexion a BD");
	      throw new RuntimeException("" + e, e);

	    } finally {
	      JdbcHelper.close(null, cs, null, null, null);

	    }
	  }

	  public void valCfgTipparGes(Connection conn, ValCfgTippar param) {
	    CallableStatement cs=null;
	    
	    try {
	      cs=conn.prepareCall("{call " + OWNERGES + "PQ_IAA_COMUN_GES.SP_VAL_CFGTIPPAR(?,?,?)}");
	      commons.util.JdbcHelper.setString(cs, 1, param.getCodusuario() );
	      commons.util.JdbcHelper.setString(cs, 2, param.getIdetippar());
	      cs.registerOutParameter(3, Types.VARCHAR);
	      cs.execute();
	      param.setResult(cs.getString(3));

	    } catch (SQLException e) {
	      logger.error("[valCfgTippar] : " + "Conexion a BD");
	      throw new RuntimeException("" + e, e);

	    } finally {
	      JdbcHelper.close(null, cs, null, null, null);

	    }
	  }	  

	  public void regUsuarioxOpcion(Connection conn, RegUsuarioxOpcion param) {
		CallableStatement cs=null;
		    
	    try {

	      cs=conn.prepareCall("{call " + OWNER + "pq_iaa_seguridad.sp_registro_opcion(?,?,?,?,?,?)}");
	      commons.util.JdbcHelper.setString(cs, 1, param.getCodusuario());
	      commons.util.JdbcHelper.setInt(cs, 2, param.getIdelog());
	      commons.util.JdbcHelper.setInt(cs, 3, param.getCodaplicacion());
	      commons.util.JdbcHelper.setString(cs, 4, param.getDscaplicacion());
	      commons.util.JdbcHelper.setInt(cs, 5, param.getCodopcion());
	      commons.util.JdbcHelper.setString(cs, 6, param.getDscopcion());
	      
	      cs.execute();

	    } catch (SQLException e) {
	      logger.error("[regUsuarioxOpcion] : " + "Conexion a BD");
	      throw new RuntimeException("" + e, e);

	    } finally {
	      JdbcHelper.close(null, cs, null, null, null);

	    }
	  }	  
	  
	  public void obtConstante(Connection conn, LstConstante param) {
		    CallableStatement cs = null;
		    ResultSet rs	     = null;
		    
		    ArrayList<LstConstanteCursor> arrLstConstante = null;
		    LstConstanteCursor lstConstante = null;
		    
		    try {
		      cs=conn.prepareCall("{call " + OWNER + "PQ_IAA_COMUN.sp_obt_constante(?,?)}");
		      commons.util.JdbcHelper.setString(cs, 1, param.getIdeconstante());
		      cs.registerOutParameter(2, OracleTypes.CURSOR);
		      cs.execute();
		      rs=(ResultSet)cs.getObject(2);
		      
		      Integer index = 1;
		      arrLstConstante = new ArrayList<LstConstanteCursor>();
		      
		      while(rs.next()) {
		    	  lstConstante = new LstConstanteCursor();

		    	  lstConstante.setIndexrow(index.toString());
		    	  
		    	  lstConstante.setIdeconstante(rs.getString("ideconstante"));
		    	  lstConstante.setDscconstante(rs.getString("dscconstante"));
		    	  lstConstante.setIdptipodato(rs.getString("idptipodato"));
		    	  lstConstante.setValor(rs.getString("valor"));
//		    	  lstConstante.setValorclob(kazham.commons.CommonUtil.clobToString(rs.getClob("valorclob")));
		    	  
		    	  arrLstConstante.add(lstConstante);
		    	  index++;
		      }
		      
		      param.setCursor(arrLstConstante);
	
		    } catch (SQLException e) {
		      logger.error("[obtConstante] : " + "Conexion a BD");
		      throw new RuntimeException("" + e, e);
	
		    } finally {
		      JdbcHelper.close(null, cs, null, null, null);
	
		    }
	  }
	  
	  public void obtAcercade(Connection conn, LstConstante param) {
		    CallableStatement cs = null;
		    ResultSet rs	     = null;
		    
		    ArrayList<LstConstanteCursor> arrLstConstante = null;
		    LstConstanteCursor lstConstante = null;
		    
		    try {
		      cs=conn.prepareCall("{call " + OWNER + "PQ_IAA_COMUN.sp_obt_acercade(?)}");
		      
		      cs.registerOutParameter(1, OracleTypes.CURSOR);
		      cs.execute();
		      rs=(ResultSet)cs.getObject(1);
		      
		      Integer index = 1;
		      arrLstConstante = new ArrayList<LstConstanteCursor>();
		      
		      while(rs.next()) {
		    	  lstConstante = new LstConstanteCursor();

		    	  lstConstante.setIndexrow(index.toString());
		    	  
		    	  lstConstante.setIdeconstante(rs.getString("ideconstante"));
		    	  lstConstante.setDscconstante(rs.getString("dscconstante"));
		    	  lstConstante.setIdptipodato(rs.getString("idptipodato"));
		    	  lstConstante.setValor(rs.getString("valor"));
//		    	  lstConstante.setValorclob(kazham.commons.CommonUtil.clobToString(rs.getClob("valorclob")));
		    	  
		    	  arrLstConstante.add(lstConstante);
		    	  index++;
		      }
		      
		      param.setCursor(arrLstConstante);
	
		    } catch (SQLException e) {
		      logger.error("[obtAcercade] : " + "Conexion a BD");
		      throw new RuntimeException("" + e, e);
	
		    } finally {
		      JdbcHelper.close(null, cs, null, null, null);
	
		    }
	  }

	public void mntAcuseRecibo(Connection conn,MntMailLogCursor mntMailLog) {
	    CallableStatement cs=null;
	    ResultSet rs=null;

	    try {
	      cs=conn.prepareCall("{call " + OWNER + "PQ_IAA_COMUN_MAIL.sp_mnt_acuse_recibo(?,?)}");
	      commons.util.JdbcHelper.setString(cs, 1, mntMailLog.getToken());
	      cs.registerOutParameter(2, OracleTypes.CURSOR);
	      cs.execute();
	      rs = (ResultSet)cs.getObject(2);
	      mntMailLog.setCursor(commons.mapper.Utils.populateListFromResultSet("sas.bean.MntMailLogCursor", rs));


	    } catch (SQLException e) {
	      logger.error("[mntAcuseRecibo] : " + "Conexion a BD");
	      throw new RuntimeException("" + e, e);

	    } finally {
	      JdbcHelper.close(null, cs, null, null, rs);

	    }
		
	}

	public void obtDatosCasillaMail(Connection conn,
			ListaCorreoGenerico lstCasilla) {
		CallableStatement cs=null;
	    ResultSet rs=null;

	    try {
	      cs=conn.prepareCall("{call " + OWNER + "PQ_IAA_COMUN_MAIL.sp_obt_datosmail(?,?)}");
	      commons.util.JdbcHelper.setInt(cs, 1, lstCasilla.getIdetercero());
	      cs.registerOutParameter(2, OracleTypes.CURSOR);
	      cs.execute();
	      rs = (ResultSet)cs.getObject(2);
	      lstCasilla.setCursor(commons.mapper.Utils.populateListFromResultSet("sas.bean.ListaCorreoGenericoCursor", rs));


	    } catch (SQLException e) {
	      logger.error("[obtDatosCasillaMail] : " + "Conexion a BD");
	      throw new RuntimeException("" + e, e);

	    } finally {
	      JdbcHelper.close(null, cs, null, null, rs);

	    }
		
	}
	public void mntCasillaCorreo(Connection conn,
			MntCasillaCorreo param) {
		CallableStatement cs=null;

	    try {
	    	cs=conn.prepareCall("{call " + OWNERREP + "PQ_IAA_REPORTE_CRM.sp_mnt_crm_casilla_correo_ter(?,?,?,?,?,?)}");
	    	commons.util.JdbcHelper.setInt(cs, 1, param.getIdetercero());
//	    	cs.registerOutParameter(1, Types.INTEGER);
	    	commons.util.JdbcHelper.setString(cs, 2, param.getCorreo());
	    	commons.util.JdbcHelper.setString(cs, 3, param.getClave());
	    	commons.util.JdbcHelper.setString(cs, 4, param.getNombre());
	    	commons.util.JdbcHelper.setString(cs, 5, param.getStscasillacorreo());
	    	commons.util.JdbcHelper.setString(cs, 6, param.getIndgenerico());
	    	
	    	cs.execute();
//		    param.setIdemail(cs.getInt(1));

	    } catch (SQLException e) {
	    	logger.error("[mntCasillaCorreo] : " + "Conexion a BD");
		    throw new RuntimeException("" + e, e);
	    } finally {
	    	JdbcHelper.close(null, cs, null, null, null);
	    }
	}
	
	public void lstCasillaCorreo(Connection conn,
			MntCasillaCorreo param) {
		CallableStatement cs=null;
	    ResultSet rs=null;

	    try {
	    	cs=conn.prepareCall("{call " + OWNERREP + "PQ_IAA_REPORTE_CRM.sp_obt_crm_casilla_correo_ter(?,?,?)}");
	    	commons.util.JdbcHelper.setInt(cs, 1, param.getIdetercero());
	    	commons.util.JdbcHelper.setString(cs, 2, param.getNombre());
	    	cs.registerOutParameter(3, OracleTypes.CURSOR);
	    	cs.execute();
	    	rs = (ResultSet)cs.getObject(3);
	    	param.setCursor(commons.mapper.Utils.populateListFromResultSet("sas.bean.MntCasillaCorreoCursor", rs));

	    } catch (SQLException e) {
	    	logger.error("[lstCasillaCorreo] : " + "Conexion a BD");
	    	throw new RuntimeException("" + e, e);
	    } finally {
	    	JdbcHelper.close(null, cs, null, null, rs);
	    }
	}

	public List lstLog(Connection conn, LstLog lstlog) {
		CallableStatement cs = null;
		ResultSet rs = null;
		List listaLog = null;
		JSONObject object = null;
		
		
		try {
//			cs=conn.prepareCall("{call " +
//					resources.getString(Constants.OWNER_ESQUEMA_INTERFAZ)+"."+
//					"PQ_IAA_OBTENER_WS.sp_lst_log" +
//					"(?,?,?,?,?)}");
//			
//			JdbcHelper.setInt(cs, 1, lstlog.getIdelog());
//			JdbcHelper.setInt(cs, 2, lstlog.getIdtransac());
//			JdbcHelper.setString(cs, 3, lstlog.getFecini());
//			JdbcHelper.setString(cs, 4, lstlog.getFecfin());
//			cs.registerOutParameter(5, OracleTypes.CURSOR);
//			cs.execute();
			
			rs=(ResultSet)cs.getObject(5);
			listaLog = new ArrayList();
//			listaLog = commons.mapper.Utils.populateListFromResultSet("sas.bean.LstLog",rs);
			

		} catch (Exception e) {
			logger.error("Error al listar remesas de DB: " + e);
			throw new RuntimeException("[UtilDaoImp]: Error al listar log en DB" + e, e);
		} finally {
			logger.info("[UtilDaoImp]: ejecución finalizada listar log.");
			JdbcHelper.close(null, cs, null, null, rs);
		}
		return listaLog;
	}
	
	  public Usuario obtUsuario(Connection conn,Usuario usuario) {
		  CallableStatement cs = null;
			ResultSet rs = null;
			
			try {
//				cs=conn.prepareCall("{call " +
//						resources.getString(Constants.OWNER_ESQUEMA_FINANZAS)+"."+
//						"pq_iaa_transaccion.sp_obt_usuario_cotizacion" +
//						"(?,?)}");
//				
//				JdbcHelper.setInt(cs, 1, usuario.getIdeacuerdo());
//				cs.registerOutParameter(2, OracleTypes.VARCHAR);
//				cs.execute();
				
				usuario.setIdeusuario("RGARCIAM");
				
			} catch (Exception e) {
				logger.error("Error al obtUsuario de DB: " + e);
				throw new RuntimeException("[UtilDaoImp]: Error al listar log en DB" + e, e);
			} finally {
				logger.info("[UtilDaoImp]: ejecución finalizada listar log.");
				JdbcHelper.close(null, cs, null, null, rs);
			}
			return usuario;
	  }
		
	  

	  //ini SCASTANEDM 2014.12.31
	  public void obtTipoCambio(Connection conn, TipoCambio tipocambio) {
		  CallableStatement cs = null;
			ResultSet rs = null;
			
			try {

//				System.out.println("ini obtTipoCambio");
//				
//			    String call = "{call " +
//						resources.getString(Constants.OWNER_ESQUEMA_FINANZAS)+"."+
//						"pq_iaa_recaudo.sp_tipo_cambio" +
//						"(?,?,?,?,?,?)}";
//
//				
//	            CallableStatement cstmt = conn.prepareCall(call);
//	            
//	            cstmt.setString(1, tipocambio.getMonedaini());
//	            cstmt.setString(2, tipocambio.getMonedafin());
//	            cstmt.setString(3, tipocambio.getTipotasa());
//	            cstmt.setString(4, tipocambio.getFechacambio());
//	            cstmt.registerOutParameter(5, oracle.jdbc.OracleTypes.NUMBER);
//	            cstmt.registerOutParameter(6, oracle.jdbc.OracleTypes.NUMBER);
//	            
//				cstmt.executeQuery();
//				
//				tipocambio.setTipocambio(cstmt.getDouble(5));
//				tipocambio.setRespuesta(cstmt.getInt(6));
	            	            
			} catch (Exception e) {
				logger.error("Error al obtTipoCambio de DB: " + e);
				throw new RuntimeException("[UtilDaoImp]: Error al listar log en DB" + e, e);
			} finally {
				logger.info("[UtilDaoImp]: ejecución finalizada listar log.");
				JdbcHelper.close(null, cs, null, null, rs);
			}

	  }
	  //fin SCASTANEDM 2014.12.31
	  
	  
	  public String getIndicadorTrabajadorRimac(Connection conn, String username) {
		  CallableStatement cs = null;
			ResultSet rs = null;
			String salida = "";
			try {

//				System.out.println("ini getIndicadorTrabajadorRimac");
//				
//			    String call = "{call " +
//						resources.getString(Constants.OWNER_ESQUEMA_ACUERDO)+"."+
//						"pq_iaa_cotizar.sp_obt_ind_trabajadorrimac" +
//						"(?,?)}";
//
//				
//	            CallableStatement cstmt = conn.prepareCall(call);
//	            
//	            cstmt.setString(1, username);
//	            cstmt.registerOutParameter(2, Types.VARCHAR);
//	            
//				cstmt.executeQuery();
//				
//				salida = cstmt.getString(2);
					            	            
			} catch (Exception e) {
				logger.error("Error al getIndicadorTrabajadorRimac de DB: " + e);
				throw new RuntimeException("[UtilDaoImp]: Error al listar log en DB" + e, e);
			} finally {
				logger.info("[UtilDaoImp]: ejecución finalizada listar log.");
				JdbcHelper.close(null, cs, null, null, rs);
			}
			
			return salida;

	  }
	  
		// RCR 04/09/2015 GC 21989 INI	
	public void listarLogAuditoria(Connection conn, ListaLogAuditoria param) {
		// TODO Auto-generated method stub
		  CallableStatement cs=null;
	      ResultSet rs=null;			
			try {
//				cs=conn.prepareCall("{call " +
//						resources.getString(Constants.OWNER_ESQUEMA_COMUNES)+"."+
//						"PQ_IAA_COMUN.sp_lst_logauditoria" +
//						"(?,?,?,?)}");
//				
//				commons.util.JdbcHelper.setString(cs, 1, param.getNombretabla());				
//				commons.util.JdbcHelper.setString(cs, 2, param.getFechaini());
//				commons.util.JdbcHelper.setString(cs, 3, param.getFechafin());
//				cs.registerOutParameter(4, OracleTypes.CURSOR);
//				cs.execute();
//				
//				rs=(ResultSet)cs.getObject(4);
//				  param.setCursor(commons.mapper.Utils.populateListFromResultSet("sas.bean.ListaLogAuditoria", rs));
//				 commons.mapper.Utils.populateListFromResultSet("sas.bean.ListaLogAuditoria",rs);
//				
			  
			} catch (Exception e) {
				logger.error("Error al listar remesas de DB: " + e);
				throw new RuntimeException("[UtilDaoImp]: Error al listar log de auditoria en DB" + e, e);
			} finally {
				logger.info("[UtilDaoImp]: ejecución finalizada listar log  de auditoria.");
				JdbcHelper.close(null, cs, null, null, rs);
			}
		
		}
	// RCR 04/09/2015 GC 21989 FIN		
	  
	  	
	  
}


