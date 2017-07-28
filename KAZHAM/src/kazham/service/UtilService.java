package kazham.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.ResourceBundle;

import oracle.sql.BLOB;
import commons.util.JdbcHelper;
import commons.framework.BaseService;



import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.ibm.websphere.rsadapter.WSCallHelper;

//import sas.seguridad.bean.ObtUsuarioCursor;
import oracle.jdbc.pool.OracleDataSource;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;

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
import kazham.commons.Constants;
import kazham.dao.UtilDao;
import kazham.dao.jdbcimp.UtilDaoImp;
/**
 * Funcion: clase service de la clase util 
 * Creación:  - 
 * Modificaciones:
 * 					1.0								Version Inicial del Componente
 * SCASTANEDM		2.0			05/01/2015			Se creo el método: 
 * 													- obtTipoCambio
 */
public class UtilService extends BaseService {

	/**
  *
  **/
	private Logger logger = Logger.getLogger(UtilService.class.getName());
	private static UtilService instance;
	UtilDao utilDao = UtilDaoImp.getInstance();

	ResourceBundle resources = ResourceBundle.getBundle("configuracion");

	private UtilService() {
	}

	public static UtilService getInstance() {
		if (instance == null) {
			instance = new UtilService();
		}
		return instance;
	}

	public void init(JSONObject jo) {
		java.sql.Connection conn = super.getConnection();
		try {
			utilDao.init(conn, jo);

		} catch (RuntimeException e) {
			logger.error("[init] : " + "Init");
			JdbcHelper.rollback(conn);
			throw new RuntimeException("" + e, e);

		} finally {
			JdbcHelper.commit(conn);
			JdbcHelper.close(conn, null, null, null, null);

		}
	}

	public void initUser(String usuario, JSONObject jo) {
		java.sql.Connection conn = super.getConnection();
		try {
			utilDao.initUser(conn, usuario, jo);

		} catch (RuntimeException e) {
			logger.error("[initUser] : " + "Init User");
			JdbcHelper.rollback(conn);
			throw new RuntimeException("" + e, e);

		} finally {
			JdbcHelper.commit(conn);
			JdbcHelper.close(conn, null, null, null, null);

		}
	}

	public void listaParametro(ListaParametro param) {
		java.sql.Connection conn = super.getConnection();
		try {
			utilDao.listaParametro(conn, param);

		} catch (RuntimeException e) {
			logger.error("[listaParametro] : " + "Consulta servicio");
			JdbcHelper.rollback(conn);
			throw new RuntimeException("" + e, e);

		} finally {
			JdbcHelper.commit(conn);
			JdbcHelper.close(conn, null, null, null, null);

		}

	}

	public void lstTipparametro(LstTipparametro param) {
		java.sql.Connection conn = super.getConnection();
		try {
			utilDao.lstTipparametro(conn, param);

		} catch (RuntimeException e) {
			logger.error("[lstTipparametro] : " + "Consulta servicio");
			JdbcHelper.rollback(conn);
			throw new RuntimeException("" + e, e);

		} finally {
			JdbcHelper.commit(conn);
			JdbcHelper.close(conn, null, null, null, null);

		}
	}

	public void mntTipparametro(MntTipparametro param) {
		java.sql.Connection conn = super.getConnection();
		try {
			utilDao.mntTipparametro(conn, param);

		} catch (RuntimeException e) {
			logger.error("[mntTipparametro] : " + "Consulta servicio");
			JdbcHelper.rollback(conn);
			throw new RuntimeException("" + e, e);

		} finally {
			JdbcHelper.commit(conn);
			JdbcHelper.close(conn, null, null, null, null);

		}

	}

	public void lstParametro(LstParametro param) {
		java.sql.Connection conn = super.getConnection();
		try {
			utilDao.lstParametro(conn, param);

		} catch (RuntimeException e) {
			logger.error("[lstParametro] : " + "Consulta servicio");
			JdbcHelper.rollback(conn);
			throw new RuntimeException("" + e, e);

		} finally {
			JdbcHelper.commit(conn);
			JdbcHelper.close(conn, null, null, null, null);

		}

	}

	public void mntParametro(MntParametro param) {
		java.sql.Connection conn = super.getConnection();
		try {
			utilDao.mntParametro(conn, param);

		} catch (RuntimeException e) {
			logger.error("[mntParametro] : " + "Consulta servicio");
			JdbcHelper.rollback(conn);
			throw new RuntimeException("" + e, e);

		} finally {
			JdbcHelper.commit(conn);
			JdbcHelper.close(conn, null, null, null, null);

		}

	}

	public void valCfgTippar(ValCfgTippar param) {
		java.sql.Connection conn = super.getConnection();
		try {
			utilDao.valCfgTippar(conn, param);

		} catch (RuntimeException e) {
			logger.error("[valCfgTippar] : " + "Consulta servicio");
			JdbcHelper.rollback(conn);
			throw new RuntimeException("" + e, e);

		} finally {
			JdbcHelper.commit(conn);
			JdbcHelper.close(conn, null, null, null, null);

		}

	}

	public void mntArchivoblob(MntArchivoblob param) {

		java.sql.Connection conn = null;
		try {
			logger.info("[mntArchivoblob 01] ");

			String server = resources.getString(Constants.SERVIDOR);
			String tipoconexion = resources.getString(Constants.TIPOCONEXION);
			BLOB blob = null;
			conn = super.getConnection();
			if (server.equals("WebSphere")) {
				blob = (BLOB) WSCallHelper.jdbcPass(BLOB.class,
						"createTemporary", new Object[] { conn,
								new Boolean(true),
								new Integer(BLOB.DURATION_SESSION) },
						new Class[] { java.sql.Connection.class, boolean.class,
								int.class }, new int[] {
								WSCallHelper.CONNECTION, WSCallHelper.IGNORE,
								WSCallHelper.IGNORE });
			} else {
				conn = this.getConnectionOracleDS();
				//org.apache.tomcat.dbcp.dbcp.DelegatingConnection del = new org.apache.tomcat.dbcp.dbcp.DelegatingConnection(conn);
		//		OracleConnection con = (OracleConnection) del.getInnermostDelegate();
				blob = BLOB.createTemporary(conn, true, BLOB.DURATION_SESSION);
			}

			logger.info("[mntArchivoblob 02] ");
			OutputStream blob_os = blob.getBinaryOutputStream();
			logger.info("[mntArchivoblob 03] ");
			blob_os.write(param.getArbytes());
			logger.info("[mntArchivoblob 04] ");
			blob_os.flush();
			logger.info("[mntArchivoblob 05] ");
			param.setArchivoblob(blob);
			logger.info("[mntArchivoblob 06] ");

			utilDao.mntArchivoblob(conn, param);

		} catch (RuntimeException e) {
			logger.error("[mntArchivoblob] : RuntimeException-" + e);
			JdbcHelper.rollback(conn);
			throw new RuntimeException("" + e, e);

		} catch (Exception e) {
			logger.error("[mntArchivoblob] : Exception-" + e);
			JdbcHelper.rollback(conn);
			throw new RuntimeException("" + e, e);

		} finally {
			JdbcHelper.commit(conn);
			JdbcHelper.close(conn, null, null, null, null);

		}

	}

	public String viewFile(String idArchivo, String ruta) {
		java.sql.Connection conn = super.getConnection();
		String fileName = null;
		try {
			fileName = utilDao.viewFile(conn, idArchivo, ruta);

		} catch (RuntimeException e) {
			logger.error("[viewFile] : Exception-" + e);
			throw new RuntimeException("" + e, e);
		} finally {
			JdbcHelper.close(conn, null, null, null, null);
		}
		return fileName;

	}

	public void lstConstante(LstConstante param) {
		java.sql.Connection conn = super.getConnection();
		try {
			utilDao.lstConstante(conn, param);

		} catch (RuntimeException e) {
			logger.error("[lstConstante] : " + "Consulta servicio");
			JdbcHelper.rollback(conn);
			throw new RuntimeException("" + e, e);

		} finally {
			JdbcHelper.commit(conn);
			JdbcHelper.close(conn, null, null, null, null);

		}
	}

	public void mntConstante(MntConstante param) {
		java.sql.Connection conn = super.getConnection();
		try {
			utilDao.mntConstante(conn, param);

		} catch (RuntimeException e) {
			logger.error("[mntConstante] : " + "Consulta servicio");
			JdbcHelper.rollback(conn);
			throw new RuntimeException("" + e, e);

		} finally {
			JdbcHelper.commit(conn);
			JdbcHelper.close(conn, null, null, null, null);

		}

	}

	public void mntArchivoblobGes(MntArchivoblob param) {

		java.sql.Connection conn = null;
		try {
			logger.info("[mntArchivoblob 01] ");

			String server = resources.getString(Constants.SERVIDOR);
			String tipoconexion = resources.getString(Constants.TIPOCONEXION);
			BLOB blob = null;
			conn = super.getConnection();
			if (server.equals("WebSphere")) {
				blob = (BLOB) WSCallHelper.jdbcPass(BLOB.class,
						"createTemporary", new Object[] { conn,
								new Boolean(true),
								new Integer(BLOB.DURATION_SESSION) },
						new Class[] { java.sql.Connection.class, boolean.class,
								int.class }, new int[] {
								WSCallHelper.CONNECTION, WSCallHelper.IGNORE,
								WSCallHelper.IGNORE });
			} else {
				blob = BLOB.createTemporary(conn, true, BLOB.DURATION_SESSION);
			}

			logger.info("[mntArchivoblob 02] ");
			OutputStream blob_os = blob.getBinaryOutputStream();
			logger.info("[mntArchivoblob 03] ");
			blob_os.write(param.getArbytes());
			logger.info("[mntArchivoblob 04] ");
			blob_os.flush();
			logger.info("[mntArchivoblob 05] ");
			param.setArchivoblob(blob);
			logger.info("[mntArchivoblob 06] ");

			utilDao.mntArchivoblobGes(conn, param);

		} catch (RuntimeException e) {
			logger.error("[mntArchivoblob] : RuntimeException-" + e);
			JdbcHelper.rollback(conn);
			throw new RuntimeException("" + e, e);

		} catch (Exception e) {
			logger.error("[mntArchivoblob] : Exception-" + e);
			JdbcHelper.rollback(conn);
			throw new RuntimeException("" + e, e);

		} finally {
			JdbcHelper.commit(conn);
			JdbcHelper.close(conn, null, null, null, null);

		}

	}

	public void lstTipparametroGes(LstTipparametro param) {
		java.sql.Connection conn = super.getConnection();
		try {
			utilDao.lstTipparametroGes(conn, param);

		} catch (RuntimeException e) {
			logger.error("[lstTipparametro] : " + "Consulta servicio");
			JdbcHelper.rollback(conn);
			throw new RuntimeException("" + e, e);

		} finally {
			JdbcHelper.commit(conn);
			JdbcHelper.close(conn, null, null, null, null);

		}
	}

	public void mntTipparametroGes(MntTipparametro param) {
		java.sql.Connection conn = super.getConnection();
		try {
			utilDao.mntTipparametroGes(conn, param);

		} catch (RuntimeException e) {
			logger.error("[mntTipparametro] : " + "Consulta servicio");
			JdbcHelper.rollback(conn);
			throw new RuntimeException("" + e, e);

		} finally {
			JdbcHelper.commit(conn);
			JdbcHelper.close(conn, null, null, null, null);

		}

	}

	public void lstParametroGes(LstParametro param) {
		java.sql.Connection conn = super.getConnection();
		try {
			utilDao.lstParametroGes(conn, param);

		} catch (RuntimeException e) {
			logger.error("[lstParametro] : " + "Consulta servicio");
			JdbcHelper.rollback(conn);
			throw new RuntimeException("" + e, e);

		} finally {
			JdbcHelper.commit(conn);
			JdbcHelper.close(conn, null, null, null, null);

		}

	}

	public void mntParametroGes(MntParametro param) {
		java.sql.Connection conn = super.getConnection();
		try {
			utilDao.mntParametroGes(conn, param);

		} catch (RuntimeException e) {
			logger.error("[mntParametro] : " + "Consulta servicio");
			JdbcHelper.rollback(conn);
			throw new RuntimeException("" + e, e);

		} finally {
			JdbcHelper.commit(conn);
			JdbcHelper.close(conn, null, null, null, null);

		}

	}

	public void valCfgTipparGes(ValCfgTippar param) {
		java.sql.Connection conn = super.getConnection();
		try {
			utilDao.valCfgTipparGes(conn, param);

		} catch (RuntimeException e) {
			logger.error("[valCfgTippar] : " + "Consulta servicio");
			JdbcHelper.rollback(conn);
			throw new RuntimeException("" + e, e);

		} finally {
			JdbcHelper.commit(conn);
			JdbcHelper.close(conn, null, null, null, null);

		}

	}
	  public void regUsuarioxOpcion(RegUsuarioxOpcion param) {
	    java.sql.Connection conn = super.getConnection();
	    try {
	      utilDao.regUsuarioxOpcion(conn, param);

	    } catch (RuntimeException e) {
	      logger.error("[regUsuarioxOpcion] : " + "Consulta Opciones");
	      JdbcHelper.rollback(conn);
	      throw new RuntimeException("" + e, e);

	    } finally {
	      JdbcHelper.commit(conn);
	      JdbcHelper.close(conn, null, null, null, null);

	    }

	  }
	  
	  public void obtConstante(LstConstante param) {
	    java.sql.Connection conn = super.getConnection();
	    try {
	      utilDao.obtConstante(conn, param);

	    } catch (RuntimeException e) {
	      logger.error("[obtConstante] : " + "Obtener Constante");
	      JdbcHelper.rollback(conn);
	      throw new RuntimeException("" + e, e);

	    } finally {
	      JdbcHelper.commit(conn);
	      JdbcHelper.close(conn, null, null, null, null);

	    }

	  }
	  
	  public void obtAcercade(LstConstante param) {
		    java.sql.Connection conn = super.getConnection();
		    try {
		      utilDao.obtAcercade(conn, param);

		    } catch (RuntimeException e) {
		      logger.error("[obtAcercade] : " + "Obtener Constante Acerca de");
		      JdbcHelper.rollback(conn);
		      throw new RuntimeException("" + e, e);

		    } finally {
		      JdbcHelper.commit(conn);
		      JdbcHelper.close(conn, null, null, null, null);

		    }

		  }

	public void mntAcuseRecibo(MntMailLogCursor mntMailLog) {
		java.sql.Connection conn = super.getConnection();
	    try {
	      utilDao.mntAcuseRecibo(conn,mntMailLog);

	    } catch (RuntimeException e) {
	      logger.error("[mntAcuseRecibo] : " + mntMailLog.getToken());
	      JdbcHelper.rollback(conn);
	      throw new RuntimeException("" + e, e);

	    } finally {
	      JdbcHelper.commit(conn);
	      JdbcHelper.close(conn, null, null, null, null);
	    }
		
	}
	public void obtDatosCasillaMail(ListaCorreoGenerico lstCasilla) {
		java.sql.Connection conn = super.getConnection();
		try {
			utilDao.obtDatosCasillaMail(conn, lstCasilla);

		} catch (RuntimeException e) {
			logger.error("[obtDatosCasillaMail] : " + "Consulta Datos de la casilla de correos");
			JdbcHelper.rollback(conn);
			throw new RuntimeException("" + e, e);

		} finally {
			JdbcHelper.commit(conn);
			JdbcHelper.close(conn, null, null, null, null);

		}

	}  
	public void mntCasillaCorreo(MntCasillaCorreo param) {
		java.sql.Connection conn = super.getConnection();
		try {
			utilDao.mntCasillaCorreo(conn, param);

		} catch (RuntimeException e) {
			logger.error("[mntCasillaCorreo] : " + "Consulta servicio");
			JdbcHelper.rollback(conn);
			throw new RuntimeException("" + e, e);

		} finally {
			JdbcHelper.commit(conn);
			JdbcHelper.close(conn, null, null, null, null);

		}
	}
	
	public void lstCasillaCorreo(MntCasillaCorreo param) {
		java.sql.Connection conn = super.getConnection();
		try {
			utilDao.lstCasillaCorreo(conn, param);

		} catch (RuntimeException e) {
			logger.error("[lstCasillaCorreo] : " + "Consulta servicio");
			JdbcHelper.rollback(conn);
			throw new RuntimeException("" + e, e);

		} finally {
			JdbcHelper.commit(conn);
			JdbcHelper.close(conn, null, null, null, null);

		}
	}

	public List lstLog(LstLog lstlog) {
		List listaLog;
		Connection conn = null;
		
		try {
			conn = super.getConnection();
			listaLog = utilDao.lstLog(conn,lstlog);
		}
		catch (Exception e) {
				logger.error("Error al listar los log: " + e);
				throw new RuntimeException(
						"[FinanciamientoService]: Error al listar los log" +
						" de canal" + e, e);
		} finally {
				JdbcHelper.close(conn);
		}
		return listaLog;
	}
	
	public Usuario obtUsuario(Usuario usuario) {
		Connection conn = null;
		
		try {
			conn = super.getConnection();
			usuario = utilDao.obtUsuario(conn,usuario);
		}
		catch (Exception e) {
				logger.error("Error al obtUsuario: " + e);
				throw new RuntimeException(
						"[UtilService]: Error al listar los log" +
						" de canal" + e, e);
		} finally {
				JdbcHelper.close(conn);
		}
		return usuario;
	}
	
	//ini SCASTANEDM 2014.12.31
	public TipoCambio obtTipoCambio(TipoCambio tipocambio) {
		Connection conn = null;
		
		try {
			conn = super.getConnection();
			utilDao.obtTipoCambio(conn,tipocambio);

		}
		catch (Exception e) {
				logger.error("Error al obtTipoCambio: " + e);
				throw new RuntimeException(
						"[UtilService]: Error al listar los log" +
						" de canal" + e, e);
		} finally {
				JdbcHelper.close(conn);
		}
		
		return tipocambio;
	}
	//fin SCASTANEDM 2014.12.31
	
	public String getIndicadorTrabajadorRimac(String username) {
		Connection conn = null;
		String salida = "";
		try {
			conn = super.getConnection();
			salida = utilDao.getIndicadorTrabajadorRimac(conn,username);

		}
		catch (Exception e) {
				logger.error("Error al getIndicadorTrabajadorRimac: " + e);
				throw new RuntimeException(
						"[UtilService]: Error al obtener el Indicador de Trabajador Rimac" +
						" " + e, e);
		} finally {
				JdbcHelper.close(conn);
		}
		
		return salida;
	}

	// RCR 04/09/2015 GC 21989 INI
	public void listarLogAuditoria(ListaLogAuditoria param) {
		java.sql.Connection conn = super.getConnection();
		try {
			utilDao.listarLogAuditoria(conn, param);

		} catch (RuntimeException e) {
			logger.error("[listarLogAuditoria] : " + "Consulta log servicio");
			JdbcHelper.rollback(conn);
			throw new RuntimeException("" + e, e);

		} finally {
			JdbcHelper.commit(conn);
			JdbcHelper.close(conn, null, null, null, null);

		}
	}
	// RCR 04/09/2015 GC 21989 FIN
	
	// RCR 08/09/2015 GC 21989 INI
	  public Connection getConnectionOracleDS() {
			
		  Connection con = null;
		  oracle.jdbc.pool.OracleDataSource ds;
		
			try {			
				
				Context initCtx = new InitialContext();
				Context envCtx = (Context) initCtx.lookup("java:comp/env");
		        ds = (oracle.jdbc.pool.OracleDataSource) envCtx.lookup(("jdbc/DS_CNTWEB2"));
		        try {
					con = ds.getConnection();
				} catch (SQLException e) {
					//e.printStackTrace(); //SAS20151119
				}
		        
			} catch (NamingException e) {
				//e.printStackTrace(); //SAS20151119
			}
	 
	    return con;
	}
	// RCR 08/09/2015 GC 21989 FIN
	
}
