package kazham.service;

import commons.util.JdbcHelper;
import commons.framework.BaseService;


//
//import org.apache.log4j.Logger;
//import org.json.simple.JSONObject;

import kazham.bean.Usuario;
import kazham.dao.KazhamDao;
//import kazham.dao.UtilDao;
import kazham.dao.jdbcimp.KazhamDaoImp;
//import kazham.dao.jdbcimp.UtilDaoImp;
/**
 * Funcion: clase service de la clase util 
 * Creaci�n:  - 
 * Modificaciones:
 * 					1.0								Version Inicial del Componente
 * SCASTANEDM		2.0			05/01/2015			Se creo el m�todo: 
 * 													- obtTipoCambio
 */
public class KazhamService extends BaseService {

	/**
  *
  **/
//	private Logger logger = Logger.getLogger(KazhamService.class.getName());
	private static KazhamService instance;
//	UtilDao utilDao = UtilDaoImp.getInstance();
	KazhamDao kazhamDao = KazhamDaoImp.getInstance();
	
//	ResourceBundle resources = ResourceBundle.getBundle("configuracion");

	private KazhamService() {
	}

	public static KazhamService getInstance() {
		if (instance == null) {
			instance = new KazhamService();
		}
		return instance;
	}

	public void guardarDato(Usuario param) {
		java.sql.Connection conn = super.getConnection();
		try {
			kazhamDao.guardarDato(conn, param);
		} catch (RuntimeException e) {
//			logger.error("[initUser] : " + "Init User");
			JdbcHelper.rollback(conn);
			throw new RuntimeException("" + e, e);

		} finally {
			JdbcHelper.commit(conn);
			JdbcHelper.close(conn, null, null, null, null);

		}
	}
		
}