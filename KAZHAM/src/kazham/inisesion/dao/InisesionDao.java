package kazham.inisesion.dao;

import java.sql.Connection;

import kazham.inisesion.bean.BsqUsuario;

//import sas.seguridad.bean.MntSegUsuario;
//import sas.seguridad.bean.ObtUsuarioCursor;

public interface InisesionDao  {
  
  void bsqUsuario(Connection conn, BsqUsuario param);
  	
  	/**
  	 * Método que pemite completar los datos de un usuario
  	 * @param conn Objeto que permite conectarce a la Base de datos
  	 * @param param Contiene el identificador y clave del usuarios
  	 */
  	public void completarDatosUsuario(Connection conn, BsqUsuario param);

  	/**
  	 * Método que permite validar el usuario/password para un usuario Externo
  	 * @param conn Conexión a la base de datos
  	 * @param mntsegusu Contiene los datos del usuario
  	 * @param clave Clave del usuario
  	 * @return "true" si las credenciales son correctas caso contrario "false"
  	 */
//	boolean validaUsuarioExt(Connection conn, MntSegUsuario mntsegusu,
//			String clave);
	
	/**
	 * Permite registrar las credenciales con las que el usuario ingresa a REL
	 * @param param Cotniene los datos del Usuario
	 * @param conn Objeto que permite conectarce a la Base de datos
	 */
//	void grabarDatosUsuarioREL(ObtUsuarioCursor param, Connection conn);
	
	//INI JLINARESP 27/08/2015
	/**
	 * Actualiza intentos fallidos de logeo de usuario
	 * @param mntsegusu Contiene los datos del Usuario
	 * @param conn Objeto que permite conectarce a la Base de datos
	 */
//	void incrementaIntentos(MntSegUsuario mntsegusu,Connection conn);
	
	/**
	 * Resetea intentos fallidos de logeo de usuario
	 * @param mntsegusu
	 * @param conn
	 */
//	void reseteaIntentos(MntSegUsuario mntsegusu,Connection conn);
	//INI JLINARESP 27/08/2015
	
	 //RTC 72229 GC 46163 INI   
	/**
	 * Graba cambio clave
	 * @param mntsegusu
	 * @param conn
	 */    	
	void mntSegUsuario(Connection conn,BsqUsuario mntsegusu);
	 //RTC72229 GC 46163 FIN   
}

  
