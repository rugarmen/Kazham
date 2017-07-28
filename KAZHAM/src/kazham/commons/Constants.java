/**
 * Created on 20/12/2011
 * Copyright (c) Rimac Seguros S.A.
 */
package kazham.commons;

/**
 * Clase que contiene las constantes a ser utilizadas por la aplicación.
 * @author Kazham - Admin
 */

public class Constants {
	
	//variables de sesion
	public static final String SESSION_USUARIO = "USUARIO";
	public static final String SESSION_APLICACION = "APLICACION";
	public static final String URL_KAZHAM = "url_kazham";
	public static final String URL_INDEX = "url_index";
	public static final String URL_KAZHAM_CERRAR = "url_kazham_cerrar";
	public static final String URL_AAAW = "url_aaaw";
	
	//Constantes de Logueo
	public static final String CAMBIAR_CLAVE = "cambioClave"; //RCR 
	public static final String MENSAJE_LOGIN = "mensajeLogin";
	public static final String STATUS_LOGIN = "statusLogin";
	public static final String SESSION_CLAVE = "sessionClave";
	public static final String SESSION_USER = "sessionUser";
	public static final String SESSION_PASS = "sessionPass";
	public static final String PAGINA_ORIGEN_LOGIN = "login";
	public static final String PAGINA_ORIGEN_MENSAJE = "mensaje";
	
	public static final String SERVIDOR = "server"; 
	public static final String TIPOCONEXION= "tipoconexion";
	
	public static final String IDELOG = "IDELOG";
	public static final String IDELOG_INIT_COOKIE = "SAS_IDELOG_COOKIE";
	
	//[INI - Nelson Cuba - 01/08/2012 - Cambio para obtener el contenedor-aplicación]
	public static final String URL_CERRAR = "url_cerrar";	
	public static final String COD_CONTENEDOR_SAS = "001";
	public static final String APP_SAS = "SAS";
	//[FIN - Nelson Cuba - 01/08/2012 - Cambio para obtener el contenedor-aplicación]
	
	public static final String RUTA_LOG4J_PROPERTIES = "SAS" +
			System.getProperty("file.separator")+"KAZHAM" +
			System.getProperty("file.separator")+"properties" +
			System.getProperty("file.separator")+"log4j.properties";

	//INI JCASIANOC 20032013
	public static final String PAGINACION_LOG = "PAG_LOG";
	//FIN JCASIANOC 20032013
	//[INI - JCASIANOC - 27/06/2013 - Cambio proyecto Pasarela e-Commerce]
	public static final String WEBAPP_SAS = "/" ;
	public static final String COOKIE_USUARIO_KAZHAM = "KAZHAM_CODUSUARIO_COOKIE" ;
	public static final String C_PASARELA2_USER="C_PASARELA2_USER";
	public static final String C_PASARELA2_PAG_RPTA="C_PASARELA2_PAG_RPTA";
	public static final String C_PASARELA2_SERVER_RPTA="C_PASARELA2_SERVER_RPTA";
	//[FIN - JCASIANOC - 27/06/2013 - Cambio proyecto Pasarela e-Commerce]
	public static final String TIP_PASARELA_VISA="TIP_PASARELA_VISA";
	public static final String TIP_PASARELA_MASTERCARD="TIP_PASARELA_MASTERCARD";
	
	public static final String PREFIJO_NUMERO_SESSIONES = "PRE_NUM_SES";
	public static final String TIPO_USUARIO_INTERNO = "INT";
	
	public static final String CTE_URL_APLICACION_REL = "URL_CONTENEDOR_REL";

	//INI JLAM [NWR-PVRD][RF-02] - 20/12/2014	
	public static final String TIP_PASARELA_VISA_PVB_BBVA="TIP_PASARELA_VISA_PVB_BBVA";
	public static final String C_PASARELA_SOAT_PVB_BBVA="C_PASARELA_SOAT_PVB_BBVA";
	public static final String SAS_VISA_URL_RESPUESTA="SAS_VISA_URL_RESPUESTA";
	public static final String C_PASARELA2_SERVER_RPTA_DESA="C_PASARELA2_SERVER_RPTA_DESA";	
	public static final String C_PASARELA2_OPE_PVB_BBVA="C_PASARELA2_OPE_PVB_BBVA";
	public static final String C_PASARELA2_MOD_PVB_BBVA="C_PASARELA2_MOD_PVB_BBVA";
	public static final String C_PASARELA2_MOD_PVB_DESA="DESA";
	public static final String C_PASARELA2_MOD_PVB_PROD="PROD";
	//FIN JLAM [NWR-PVRD][RF-02] - 20/12/2014
	//INI - CCHANGP 10/03/2015
	public static final String TIP_PASARELA_DINERSCLUB="TIP_PASARELA_DINERSCLUB";
	//FIN - CCHANGP 10/03/2015
	
	public static final String C_AMBIENTE_EJECUCION = "kazham.ambiente";
	public static final String C_AMBIENTE_DESARROLLO = "DESA";
	public static final String C_REL_VALIDAR_LDAP = "C_REL_VALIDAR_LDAP";
	public static final String C_VALOR_SI = "S";
	
	// [ INI - HA - 28/01/2014 ]
	public static final String AD_LDAP_FACTORY = "ad.factory";
	public static final String AD_LDAP_HOST = "ad.host";
	public static final String AD_LDAP_SUFFIX_NAME = "ad.suffixname";
	// [ FIN - HA - 28/01/2014 ]
}
