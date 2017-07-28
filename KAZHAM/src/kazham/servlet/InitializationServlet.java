package kazham.servlet;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kazham.commons.Constants;
import kazham.controller.KazhamController;
import kazham.inisesion.bean.BsqUsuario;
import kazham.inisesion.service.InisesionService;

import org.apache.log4j.Logger;
import com.rimac.security.TripleDesUtil;

//import sas.seguridad.bean.MntSegUsuario;
//import sas.seguridad.bean.ObtUsuarioCursor;
//import sas.seguridad.commons.ConfigurationProperties;
//import sas.seguridad.commons.Constantes;
//
//import sas.seguridad.service.SeguridadService;


/**
 * Servlet implementation class Parseador
 */
public class InitializationServlet extends HttpServlet {
	private Logger logger = Logger.getLogger(KazhamController.class.getName());
	private static final long serialVersionUID = 1L;

//	ActiveDirectoryService imanager = ActiveDirectoryService.getInstance();

	BsqUsuario bsqUsuario = null;
//	private SeguridadService seguridadService = SeguridadService.getInstance();
	InisesionService inisesionService = InisesionService.getInstance();;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InitializationServlet() {
		super();
	}

	private String nombreFile;

	/**
	 * a
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		// Variable que contiene la lista de modulos concatenados '-'
//		Properties properties = ConfigurationProperties
//				.getExternalProperties("kazham");

		String codusuario = null;
		String clave = null;
		TripleDesUtil objEncripta = null;
		String url = null;
//		SeguridadService segService = null;
//		ObtUsuarioCursor usuario = null;
//		MntSegUsuario mntsegusu = null;
		String requestUri = null;
		boolean enviado = false;
		String envEjec = null;

		// Variables de validacion SAS
		String av_msgerror;
		Integer an_status;
		String an_status_msg = null;
		String av_idelog = null;

		String scheme = request.getParameter("SCHEME");
		logger.info("[scheme]: " + scheme);
		String servername = request.getParameter("SERVERNAME");
		logger.info("[servername]: " + servername);
		String serverport = request.getParameter("SERVERPORT");
		logger.info("[serverport]: " + serverport);

//		envEjec = properties.getProperty(Constants.C_AMBIENTE_EJECUCION);

		
		if (testConexionLDAP(envEjec)) {
			logger.info("[LDAP - TEST]:Paso la prueba de conexion");

			codusuario = (request.getParameter("CODUSUARIO") == null ? "" : request.getParameter("CODUSUARIO").toString().trim().toUpperCase());
			clave = (request.getParameter("CLAVE") == null ? "" : request.getParameter("CLAVE").toString().trim());
			an_status_msg = request.getParameter("STATUS");
			
			logger.info("[USUARIO]:" + codusuario);
			
			an_status = 0;
			av_msgerror = "";

			try {
				request.getSession()
				.setAttribute(
						Constants.MENSAJE_LOGIN,
						(av_msgerror == null) ? "Usuario y/o password inválidos"
								: av_msgerror);
//				request.getSession().setAttribute(Constants.CAMBIAR_CLAVE,mntsegusu.getStsusuario().toString());
				request.getSession().setAttribute(Constants.SESSION_USER,codusuario);
				request.getSession().setAttribute(Constants.SESSION_CLAVE,clave);
				
//				String indlogueo = (request.getSession().getAttribute("INDLOGUEO") == null?"N":"S");
//				System.out.println("indlogueo: " + indlogueo);
				if(request.getSession().getAttribute(Constants.SESSION_USER) == null){
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}else{
					System.out.println("Inicio del InicializationServlet");
					request.getRequestDispatcher("/index.html").forward(request, response);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public String getNombreFile() {
		return nombreFile;
	}

	public void setNombreFile(String nombreFile) {
		this.nombreFile = nombreFile;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		doGet(request, response);
	}

	/**
	 * Valida que exista conexión con el LDAP dependiendo del ambiente donde se
	 * ejecuta, para desarrollo no valida contra LDAP
	 * 
	 * @param ambiente
	 *            Ambiente donde se ejecuta la aplicación (DESA, TEST, PROD)
	 * @return true si la conexión es válida, false en caso contrario
	 */
	private boolean testConexionLDAP(String ambiente) {
		boolean existeConexion = true;

//		if (Constants.C_AMBIENTE_DESARROLLO.equalsIgnoreCase(ambiente)) {
//			existeConexion = true;
//		} else {
//			existeConexion = imanager.testLdapConnection();
//		}
		return existeConexion;
	}

	/**
	 * Método que permite validar el usuario que esta ingresando al aplicativo
	 * 
	 * @param ambiente
	 *            Ambiente donde se ejecuta la aplicación (DESA, TEST, PROD)
	 * @param status
	 * @param request
	 * @param usuario
	 * @param password
	 * @return true si el usuario tiene acceso, caso contrario false
	 */
	private boolean validarUsuarioLogueado(String ambiente) {
		boolean usuarioValido = false;

//		if (Constants.C_AMBIENTE_DESARROLLO.equalsIgnoreCase(ambiente)) {
//			usuarioValido = request.getParameter(Constantes.MODULOS) == null
//					&& (status == 1 || false);
//		} else {
//			usuarioValido = request.getParameter(Constantes.MODULOS) == null
//					&& (status == 1 || !inisesionService.validaUsuario(usuario,
//							password));
//		}

		return usuarioValido;
	}
}
