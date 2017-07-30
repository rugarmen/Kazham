package kazham.service;

import commons.util.JdbcHelper;
import commons.framework.BaseService;


//
//import org.apache.log4j.Logger;
//import org.json.simple.JSONObject;

import kazham.bean.ComponentePlantilla;
import kazham.bean.DetallePlantilla;
import kazham.bean.Informacion;
import kazham.bean.PeriodoMes;
import kazham.bean.Plantilla;
import kazham.bean.Usuario;
import kazham.dao.KazhamDao;
//import kazham.dao.UtilDao;
import kazham.dao.jdbcimp.KazhamDaoImp;
//import kazham.dao.jdbcimp.UtilDaoImp;
/**
 * Funcion: clase service de la clase util 
 * Creación:  - 
 * Modificaciones:
 * 					1.0								Version Inicial del Componente
 * SCASTANEDM		2.0			05/01/2015			Se creo el método: 
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
	
	public void listarPeriodo(PeriodoMes param) {
		java.sql.Connection conn = super.getConnection();
		try {
			kazhamDao.listarPeriodo(conn, param);
		} catch (RuntimeException e) {
			JdbcHelper.rollback(conn);
			throw new RuntimeException("" + e, e);

		} finally {
			JdbcHelper.commit(conn);
			JdbcHelper.close(conn, null, null, null, null);
		}
	}
	
	public void grabarInformacion(Informacion param) {
		java.sql.Connection conn = super.getConnection();
		try {
			kazhamDao.grabarInformacion(conn, param);
		} catch (RuntimeException e) {
			JdbcHelper.rollback(conn);
			throw new RuntimeException("" + e, e);

		} finally {
			JdbcHelper.commit(conn);
			JdbcHelper.close(conn, null, null, null, null);
		}
	}
	
	public void listarInformacion(Informacion param) {
		java.sql.Connection conn = super.getConnection();
		try {
			kazhamDao.listarInformacion(conn, param);
		} catch (RuntimeException e) {
			JdbcHelper.rollback(conn);
			throw new RuntimeException("" + e, e);

		} finally {
			JdbcHelper.commit(conn);
			JdbcHelper.close(conn, null, null, null, null);
		}
	}

	public void listarPlantilla(Plantilla param) {
		java.sql.Connection conn = super.getConnection();
		try {
			kazhamDao.listarPlantilla(conn, param);
		} catch (RuntimeException e) {
			JdbcHelper.rollback(conn);
			throw new RuntimeException("" + e, e);

		} finally {
			JdbcHelper.commit(conn);
			JdbcHelper.close(conn, null, null, null, null);
		}
	}
	
	public void listarComponentePlantilla(ComponentePlantilla param) {
		java.sql.Connection conn = super.getConnection();
		try {
			kazhamDao.listarComponentePlantilla(conn, param);
		} catch (RuntimeException e) {
			JdbcHelper.rollback(conn);
			throw new RuntimeException("" + e, e);

		} finally {
			JdbcHelper.commit(conn);
			JdbcHelper.close(conn, null, null, null, null);
		}
	}
	
	public void listarDetallePlantilla(DetallePlantilla param) {
		java.sql.Connection conn = super.getConnection();
		try {
			kazhamDao.listarDetallePlantilla(conn, param);
		} catch (RuntimeException e) {
			JdbcHelper.rollback(conn);
			throw new RuntimeException("" + e, e);

		} finally {
			JdbcHelper.commit(conn);
			JdbcHelper.close(conn, null, null, null, null);
		}
	}
}

