package kazham.inisesion.dao.jdbcimp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.ResourceBundle;

import kazham.inisesion.bean.BsqUsuario;
import kazham.inisesion.commons.Constants;
import kazham.inisesion.dao.InisesionDao;

import org.apache.log4j.Logger;

import oracle.jdbc.OracleTypes;
import commons.mapper.Utils;
import commons.util.JdbcHelper;


//import sas.seguridad.bean.MntSegUsuario;
//import sas.seguridad.bean.ObtUsuarioCursor;

import com.rimac.security.TripleDesUtil;

/**
 * Clase que contiene métodos para la obtención, el listado 
 * y el mantenimiento de direcciones, medios de contactos y vias. 
 * 
 * @author Rimac Seguros - Jorge Páez.
 */
@SuppressWarnings("unused")
public class InisesionDaoImp implements InisesionDao {
  private static final ResourceBundle resources = ResourceBundle.getBundle("configuracion");
  private static InisesionDaoImp instance;
  
  private static Logger logger = Logger.getLogger(InisesionDaoImp.class);
  
  public static InisesionDaoImp getInstance() {
    if (instance == null) {
	  instance = new InisesionDaoImp();
    }
    return instance;
  }

  /**
	 * Método que obtiene un listado de paises filtrado por criterios de búsqueda 
	 * @param conn  Parametro de conexion a la BD
	 * @param request Filtro(Tipo BsqPais) para la búsqueda de paises
	 * @return 
  */
  /*
  public void bsqUsuario(Connection conn, BsqUsuario param) {
    CallableStatement cs=null;
    ResultSet rs=null;
    TripleDesUtil objCrypt=null;
    
    try {
    	
      objCrypt=new TripleDesUtil();
    	
      cs=conn.prepareCall("{call " + resources.getString(Constants.OWNER_ESQUEMA_COMUNES)+"."+ "pq_iaa_seguridad.sp_val_usuario(?,?,?,?)}");
      commons.util.JdbcHelper.setString(cs, 1, param.getCodusuario());
      commons.util.JdbcHelper.setString(cs, 2,(param.getClave()!= null && param.getClave().length()>0)?objCrypt.encripta(param.getClave()):null);
      cs.registerOutParameter(3, OracleTypes.VARCHAR);
      cs.registerOutParameter(4, OracleTypes.NUMBER);
      
      cs.execute();
      
      param.setMsgerror(cs.getString(3));
      param.setStatus(new Integer(cs.getInt(4)));
      
    } catch (SQLException e) {
      //e.printStackTrace(); //SAS20151119
      throw new RuntimeException("" + e, e);

    } catch (Exception e) {
		//e.printStackTrace(); //SAS20151119
	} finally {
      JdbcHelper.close(null, cs, null, null, rs);

    }
  }
  */
  public void bsqUsuario(Connection conn, BsqUsuario param) {
    CallableStatement cs=null;
    ResultSet rs=null;
    TripleDesUtil objCrypt=null;
    
    try {
    	
      objCrypt=new TripleDesUtil();
    	
      cs=conn.prepareCall("{call " + resources.getString(Constants.OWNER_ESQUEMA_COMUNES)+"."+ "pq_iaa_seguridad.sp_val_usuario(?,?,?,?,?)}");
      commons.util.JdbcHelper.setString(cs, 1, param.getCodusuario());
      commons.util.JdbcHelper.setString(cs, 2,(param.getClave()!= null && param.getClave().length()>0)?objCrypt.encripta(param.getClave()):null);
      cs.registerOutParameter(3, OracleTypes.VARCHAR);
      cs.registerOutParameter(4, OracleTypes.NUMBER);
      cs.registerOutParameter(5, OracleTypes.NUMBER);
//      cs.registerOutParameter(6, OracleTypes.VARCHAR);
      cs.execute();
      
      param.setMsgerror(cs.getString(3));
      param.setStatus(new Integer(cs.getInt(4)));
      param.setIdelog(new Integer(cs.getInt(5))); 
//      param.setDescdominio(new String(cs.getString(6))); 
      
    } catch (SQLException e) {
      //e.printStackTrace(); //SAS20151119
      throw new RuntimeException("" + e, e);

    } catch (Exception e) {
		//e.printStackTrace(); //SAS20151119
	} finally {
      JdbcHelper.close(null, cs, null, null, rs);

    }
  }
 
  	/*
  	 * (non-Javadoc)
  	 * @see sas.inisesion.dao.InisesionDao#buscarUsuario(java.sql.Connection, sas.inisesion.bean.BsqUsuario)
  	 */
  	@SuppressWarnings("unchecked")
	public void completarDatosUsuario(Connection conn, BsqUsuario param) {
  		logger.info("ejecutando metodo: buscarUsuario");
  		CallableStatement cs = null;
		ResultSet rs = null;
		List<BsqUsuario> listaUsuarios = null;
		BsqUsuario usuario = null;
		TripleDesUtil objCrypt=null;
		
		try {
			cs=conn.prepareCall("{call " +
					resources.getString(Constants.OWNER_ESQUEMA_COMUNES)+"."+
					"pq_iaa_seguridad.sp_obt_usuario(?,?,?)}");
			
			objCrypt=new TripleDesUtil();
			
			commons.util.JdbcHelper.setString(cs, 1, param.getCodusuario());
			commons.util.JdbcHelper.setString(cs, 2,(param.getClave()!= null && param.getClave().length()>0)?
					objCrypt.encripta(param.getClave()):null);
			cs.registerOutParameter(3, OracleTypes.CURSOR);
			
			cs.executeQuery();
			rs = (ResultSet) cs.getObject(3);
			
			listaUsuarios = Utils.populateListFromResultSet(BsqUsuario.class.getName(),rs);
			
			if(listaUsuarios!=null && listaUsuarios.size()>0) {
				usuario = listaUsuarios.get(0);
				
				param.setIdpTipoUsuario(usuario.getIdpTipoUsuario());
				param.setTipoUsu(usuario.getIdpTipoUsuario());
				param.setIdpdominio(usuario.getIdpdominio());
				param.setUpn(usuario.getUpn());//M.23524.01
			    param.setStsusuario(usuario.getStsusuario());// RCR GC 46163
			}
		} catch(Exception e) {
			logger.error("Error al completar datos del Usuario en el DAO: " + e);
			throw new RuntimeException("Error al completar datos del Usuario" +
					" en el DAO", e);
		} finally {
			JdbcHelper.close(null, cs, null, null, rs);
		}
  	}

  	/*
  	 * (non-Javadoc)
  	 * @see sas.inisesion.dao.InisesionDao#validaUsuarioExt(java.sql.Connection, sas.seguridad.bean.MntSegUsuario, java.lang.String)
  	 */
//	public boolean validaUsuarioExt(Connection conn, MntSegUsuario mntsegusu,
//			String clave) {
//		logger.info("ejecutando metodo: validaUsuarioExt");
//		CallableStatement cs = null;
//		ResultSet rs = null;
//		TripleDesUtil objCrypt=null;
//		boolean usuarioValido = false;
//		String idUsuario = null;
//		
//		try {
//			cs=conn.prepareCall("{call " +
//					resources.getString(Constants.OWNER_ESQUEMA_COMUNES)+"."+
//					"pq_iaa_seguridad.sp_val_usuario_ext(?,?,?)}");
//			
//			objCrypt=new TripleDesUtil();
//			
//			commons.util.JdbcHelper.setString(cs, 1, mntsegusu.getIdusuario());
//			commons.util.JdbcHelper.setString(cs, 2,(clave!= null && clave.length()>0)?
//					objCrypt.encripta(clave):null);
//			cs.registerOutParameter(3, OracleTypes.VARCHAR);
//			
//			cs.executeQuery();
//			
//			idUsuario = cs.getString(3);
//		} catch(Exception e) {
//			logger.error("Error al validar usuario Externo en el DAO: " + e);
//			throw new RuntimeException("Error al validar usuario Externo" +
//					" en el DAO", e);
//		} finally {
//			JdbcHelper.close(null, cs, null, null, rs);
//		}
//		
//		if(idUsuario!=null && idUsuario.equalsIgnoreCase(
//				mntsegusu.getIdusuario())) {
//			usuarioValido = true;
//		}
//		
//		return usuarioValido;
//	}
//	
//	public void grabarDatosUsuarioREL(ObtUsuarioCursor param, Connection conn) {
//		logger.debug("ejecutando metodo: validaUsuarioExt");
//		CallableStatement cs = null;
//		TripleDesUtil objCrypt=null;
//		
//		try {
//			cs=conn.prepareCall("{call " +
//					resources.getString(Constants.OWNER_ESQUEMA_COMUNES)+"."+
//					"pq_iaa_seguridad.sp_mnt_usuario_rel(?,?,?)}");
//			
//			objCrypt=new TripleDesUtil();
//			
//			commons.util.JdbcHelper.setString(cs, 1, param.getIdusuario());
//			commons.util.JdbcHelper.setString(cs, 2,(param.getClave()!= null && param.getClave().length()>0)?
//					objCrypt.encripta(param.getClave()):null);
//			commons.util.JdbcHelper.setString(cs, 3, param.getIdusuario());
//			
//			cs.execute();
//			
//		} catch(Exception e) {
//			logger.error("Error al grabar Datos Usuario REL en el DAO: " + e);
//			throw new RuntimeException("Error al grabar Datos Usuario REL" +
//					" en el DAO", e);
//		} finally {
//			JdbcHelper.close(null, cs, null, null, null);
//		}
//	}
	
	//INI JLINARESP 27/08/2015
//	public void incrementaIntentos(MntSegUsuario mntsegusu,Connection conn) {
//		CallableStatement cs = null;
//		ResultSet rs = null;
//		
//		try {
//			logger.info("ejecutando metodo: incrementaIntentos: "+mntsegusu.getIdusuario());
//			
//			cs=conn.prepareCall("{call " +
//					resources.getString(Constants.OWNER_ESQUEMA_COMUNES)+"."+
//					"pq_iaa_seguridad.sp_actualiza_cantidad_intentos(?)}");
//			
//			commons.util.JdbcHelper.setString(cs, 1, mntsegusu.getIdusuario());
//			
//			cs.execute();
//			
//		} catch(Exception e) {
//			
//			logger.error("Error al incrementar intentos fallidos en el DAO: " + e);
//			
//		} finally {
//			
//			JdbcHelper.close(null, cs, null, null, rs);
//			
//		}		
//	}
//	
//	public void reseteaIntentos(MntSegUsuario mntsegusu,Connection conn) {
//		CallableStatement cs = null;
//		ResultSet rs = null;
//		
//		try {
//			logger.info("ejecutando metodo: reseteaIntentos: "+mntsegusu.getIdusuario());
//			
//			cs=conn.prepareCall("{call " +
//					resources.getString(Constants.OWNER_ESQUEMA_COMUNES)+"."+
//					"pq_iaa_seguridad.sp_actualiza_cantidad_intcero(?)}");
//			
//			commons.util.JdbcHelper.setString(cs, 1, mntsegusu.getIdusuario());
//			
//			cs.execute();
//			
//		} catch(Exception e) {
//			
//			logger.error("Error al resetear intentos fallidos en el DAO: " + e);
//			
//		} finally {
//			
//			JdbcHelper.close(null, cs, null, null, rs);
//			
//		}		
//	}

	public void mntSegUsuario(Connection conn, BsqUsuario param) {
		// TODO Auto-generated method stub
		  CallableStatement cs=null;
		    TripleDesUtil objCrypt=null;
		    try {
		    	
		      objCrypt=new TripleDesUtil();
		      cs=conn.prepareCall("{call " + resources.getString(Constants.OWNER_ESQUEMA_COMUNES)+".pq_iaa_seguridad.sp_mnt_seg_usuario_clave(?,?,?,?,?)}");

		      commons.util.JdbcHelper.setString(cs, 1, param.getCodusuario());
		      cs.registerOutParameter(1, Types.VARCHAR);
		      commons.util.JdbcHelper.setString(cs, 2, param.getUsucreacion());
		      commons.util.JdbcHelper.setString(cs, 3,(param.getNuevaclave()!= null && param.getNuevaclave().length()>0)?objCrypt.encripta(param.getNuevaclave()/*.toUpperCase()*/):null);	
		      commons.util.JdbcHelper.setString(cs, 4, param.getNuevaclave());
		      commons.util.JdbcHelper.setString(cs, 5, param.getMsgerror());
		      cs.registerOutParameter(5, Types.VARCHAR);
		      
		      cs.execute();
		      param.setCodusuario(cs.getString(1));
		      param.setMsgerror(cs.getString(5));
		   
		      

		    } catch (SQLException e) {
		      logger.error("Error  mntSegUsuario(Connection conn, MntSegUsuario param): " + e);
		      
		      throw new RuntimeException("" + e, e);

		    } catch (Exception e1) {
		    	logger.error("Error  mntSegUsuario(Connection conn, MntSegUsuario param): " + e1);
			      throw new RuntimeException("" + e1, e1);

			    } finally {
		      JdbcHelper.close(null, cs, null, null, null);

		    }
		
	}
	
	
	
}


