package kazham.servlet;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kazham.commons.Constants;

import org.apache.log4j.Logger; 
import org.apache.log4j.PropertyConfigurator;

//import sas.seguridad.commons.ConfigurationProperties;

import commons.framework.Service;

public class InicioAppServlet extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(InicioAppServlet.class.getName());
		
	private static ResourceBundle resources = ResourceBundle.getBundle("configuracion");
	
	public InicioAppServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); 
		
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		processRequest(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		processRequest(request,response);
	}

	public void init() throws ServletException {
		ServletContext servletContext = super.getServletContext();
		String rutaLog4j = null;
		Context context = null;

		System.out.print("[---------INICIADO APLICACION DE SAS---------]");
		
		try{
		
			System.out.print("[INICIADO PARAMETROS DE CONTEXTO]");
			context = new InitialContext();
			servletContext.setAttribute("key", "valor");	
			
//			rutaLog4j = System.getProperty(ConfigurationProperties.RUTA_ARCH_DEFAULT)+
//								Constants.RUTA_LOG4J_PROPERTIES;
//			System.out.print("[rutaLog4j] " + rutaLog4j);
			PropertyConfigurator.configure(rutaLog4j);
			
			System.out.print("[INICIO PARAMETROS DE CONTEXTO FINALIZADO]");
			
		}catch(Exception e){
			logger.error("Error en el inicio de los parametros de la aplicacion", e);
		}
			
	}
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html");
	}

	public void getUsuarioLogueado(HttpServletRequest request, HttpServletResponse response){
		System.out.println("RGARCIAM");
	}
}
