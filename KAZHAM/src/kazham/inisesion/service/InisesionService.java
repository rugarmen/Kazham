package kazham.inisesion.service;

import java.sql.Connection;

import kazham.commons.Constants;
import kazham.inisesion.bean.BsqUsuario;
import kazham.inisesion.dao.InisesionDao;
import kazham.inisesion.dao.jdbcimp.InisesionDaoImp;

import org.apache.log4j.Logger;

import commons.util.JdbcHelper;
import commons.framework.BaseService;
//import sas.seguridad.bean.MntSegUsuario;
//import sas.seguridad.bean.ObtUsuarioCursor;
//import sas.seguridad.dao.IdentificacionWebDAO;


/**
 * Clase que contiene métodos para la obtención, el listado 
 * y el mantenimiento de direcciones, medios de contactos y vias. 
 * 
 * @author Rimac Seguros - Jorge Páez.
 */
public class InisesionService extends BaseService {

  private static InisesionService instance;
  InisesionDao inisesionDao = InisesionDaoImp.getInstance();
  private Logger logger = Logger.getLogger(InisesionService.class.getName());

//  ActiveDirectoryService imanager = ActiveDirectoryService.getInstance();

  private InisesionService() {}

  public static InisesionService getInstance() {
    if (instance == null) {
      instance = new InisesionService();
    }
    return instance;
  }

	  /**
		 * Método que obtiene un listado de Usuarios filtrado por criterios de búsqueda 
		 * @param request Filtro(Tipo bsqUsuario) para la búsqueda de usuarios
		 * @return 
	  */
	  public void bsqUsuario(BsqUsuario param) {
	    java.sql.Connection conn = super.getConnection();
	    try {
//	    	inisesionDao.bsqUsuario(conn, param);
//	    	inisesionDao.completarDatosUsuario(conn, param);
	    	
	    	if (param.getMsgerror()!= null)
	    	{
//	    		JdbcHelper.rollback(conn);
	    	}
	    		    	
	    } catch (RuntimeException e) {
	      JdbcHelper.rollback(conn);
	      logger.error("Error al buscar Usuario:"+ e);
	      throw new RuntimeException("" + e, e);

	    } finally {
	      JdbcHelper.commit(conn);
	      JdbcHelper.close(conn, null, null, null, null);
	    }
	  }
	
	  /**
		 * Método que obtiene un listado de paises filtrado por criterios de búsqueda 
		 * @param request Filtro(Tipo BsqPais) para la búsqueda de paises
		 * @return 
	  */
	  public void obtenerUsuario(BsqUsuario param) {
	    java.sql.Connection conn = super.getConnection();
	    try {
	    	
//	    	inisesionDao.completarDatosUsuario(conn, param);

	    } catch (RuntimeException e) {
	      JdbcHelper.rollback(conn);
	      logger.error("Error al buscar Usuario:"+ e);
	      throw new RuntimeException("" + e, e);

	    } finally {
	      JdbcHelper.commit(conn);
	      JdbcHelper.close(conn, null, null, null, null);
	    }
	  }
	  
	/**
	 * Método que permite validar que el usuario password de un usuario sea
	 * el correcto
	 * @param mntsegusu Contiene los datos del usuarios
	 * @param clave Clave del usuarios
	 * @return true si el usuario/clave es válida, false caso contrario
	 */
//	public boolean validaUsuario(MntSegUsuario mntsegusu, String clave) {
//		boolean usuarioValido = false;
//		Connection conn = null;
//		
//		if(Constants.TIPO_USUARIO_INTERNO.equalsIgnoreCase(mntsegusu.getIndinterno())) {
//			//usuarioValido = imanager.validaUsuarioLDAP(mntsegusu,clave);
//		} else {
//			try {
//				conn = super.getConnection();
//				
//				usuarioValido = inisesionDao.validaUsuarioExt(conn,
//						mntsegusu, clave);
//			} catch(RuntimeException e) {
//				JdbcHelper.rollback(conn);
//				logger.error("Error al buscar Usuario:"+ e);
//				usuarioValido = false;
//			} finally {
//				JdbcHelper.commit(conn);
//				JdbcHelper.close(conn, null, null, null, null);
//			}
//		}
//		
//		//INI JLINARESP 27/08/2015
//		try {
//			conn = super.getConnection();
//			if(!usuarioValido){
//				inisesionDao.incrementaIntentos(mntsegusu, conn);	
//			}else{
//				inisesionDao.reseteaIntentos(mntsegusu, conn);	
//			}
//		} catch(RuntimeException e) {
//			JdbcHelper.rollback(conn);
//			logger.error("Error al modificar intentos:"+ e);
//		} finally {
//			JdbcHelper.commit(conn);
//			JdbcHelper.close(conn, null, null, null, null);
//		}	
//		//FIN JLINARESP 27/08/2015
//		
//		return usuarioValido;
//	}
	
	/**
	 * Permite registrar la clave de Usuario con que se conetará a REL
	 * @param param Datos del usuario y la clave
	 */
//	public void grabarDatosUsuarioREL(ObtUsuarioCursor param) {
//		Connection conn = null;
//		
//		try {
//			conn = super.getConnection();
//			inisesionDao.grabarDatosUsuarioREL(param, conn);
//		} catch(RuntimeException e) {
//			JdbcHelper.rollback(conn);
//			logger.error("Error al buscar Usuario:"+ e);
//		} finally {
//			JdbcHelper.commit(conn);
//			JdbcHelper.close(conn, null, null, null, null);
//		}
//	}
	
	public void mntSegUsuario(BsqUsuario  param) {

		java.sql.Connection conn = super.getConnection();
		
			try
			{
//		inisesionDao.mntSegUsuario(conn, param);
			 if (param.getMsgerror() !=null)
			 {
				 JdbcHelper.rollback(conn);
			 }

		} catch (RuntimeException e) {
			JdbcHelper.rollback(conn);
			logger.error("Error mntSegUsuario: " + e);
			throw new RuntimeException("" + e, e);

		} finally {
			JdbcHelper.commit(conn);			
			JdbcHelper.close(conn, null, null, null, null);

		}

	}
//	public boolean validaUsuarioExterno(MntSegUsuario mntsegusu, String clave) {
//		boolean usuarioValido = false;
//		Connection conn = null;
//		
//
//			try {
//				conn = super.getConnection();
//				
//				usuarioValido = inisesionDao.validaUsuarioExt(conn,
//						mntsegusu, clave);
//			} catch(RuntimeException e) {
//				JdbcHelper.rollback(conn);
//				logger.error("Error al buscar Usuario:"+ e);
//				usuarioValido = false;
//			} finally {
//				JdbcHelper.commit(conn);
//				JdbcHelper.close(conn, null, null, null, null);
//			}
//		
//		
//		return usuarioValido;
//	}
	
}


