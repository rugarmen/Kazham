package kazham.servlet;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import kazham.commons.CommonUtil;
import kazham.commons.Constants;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.rimac.security.TripleDesUtil;
//import sas.seguridad.bean.Aplicacion;
//import sas.seguridad.bean.AplicacionCursor;
//import sas.seguridad.bean.ObtUsuarioCursor;
//import sas.seguridad.commons.Constantes;
//import sas.seguridad.commons.SeguridadUtils;
//import sas.seguridad.service.LoginService;
//import sas.seguridad.service.SeguridadService;

/**
 * Servlet implementation class Parseador
 */
public class CloseSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger logger = Logger.getLogger(
			CloseSessionServlet.class.getName());
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CloseSessionServlet() {
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
//		ResourceBundle resources = ResourceBundle.getBundle("configuracion");
//		String requestUri = null;
//		List<AplicacionCursor> listaAplicaciones = null;
//		Aplicacion aplicacion = new Aplicacion();
//		String modulos = null;
//		String url = null;
//		TripleDesUtil objEncripta = null;
//		boolean enviado = false;
//
//		//[INI - Nelson Cuba - 30/07/2012 - Cambio para obtener el contenedor-aplicación]
//		String idContenedor = Constants.COD_CONTENEDOR_SAS;
//		String Contenedor = Constants.APP_SAS;
//		String idUsuario = SeguridadUtils.getCookieValue(request.getCookies(), Constantes.CODUSUARIO_INIT_COOKIE,"VACIO");
//		aplicacion.setIdcontenedor(idContenedor);
//		aplicacion.setIdusuario(idUsuario);
//		//[FIN - Nelson Cuba - 30/07/2012 - Cambio para obtener el contenedor-aplicación]
//				
//		//[INI - Nelson Cuba - 13/09/2012 - Cambio para formar la URL de redirección]
//		String scheme = request.getParameter("SCHEME");
//		String servername = request.getParameter("SERVERNAME");
//		String serverport = request.getParameter("SERVERPORT");
//		//[FIN - Nelson Cuba - 13/09/2012 - Cambio para formar la URL de redirección]
//		
//		SeguridadService segService = SeguridadService.getInstance();
//
//		if (request.getSession().getAttribute(Constants.SESSION_APLICACION) == null) {
//			//[INI - Nelson Cuba - 03/08/2012 - Cambio para obtener las aplicaciones del usuario por contenedor]
//			//segService.lstAplicacion(aplicacion);
//			segService.bsqAplicacion(aplicacion);
//			//[FIN - Nelson Cuba - 03/08/2012 - Cambio para obtener las aplicaciones del usuario por contenedor]
//			
//		} else {
//			aplicacion.setCursor((List) request.getSession().getAttribute(
//					Constants.SESSION_APLICACION));
//		}
//
//		listaAplicaciones = aplicacion.getCursor();
//		Cookie[] cookies = request.getCookies();	
//		if (cookies != null) { 
//		    for (Cookie cookie : cookies) {
//		        if (cookie.getName().equals(kazham.commons.Constants.IDELOG_INIT_COOKIE)) {
//		            cookie.setMaxAge(0);
//		            response.addCookie(cookie);
//		        }
//		        //[INI - Tomas Carrillo - 14/11/2012 - Cambio para obtener los datos de referencia externa de usuario]
//		        if (cookie.getName().equals(sas.seguridad.commons.Constantes.AX_USUARIO_COOKIE)) {
//		            cookie.setMaxAge(0);
//		            response.addCookie(cookie);
//		        }
//		        //[FIN - Tomas Carrillo - 14/11/2012]
//		        if (cookie.getName().equals(Constantes.CODUSUARIO_INIT_COOKIE)) {
//		            cookie.setMaxAge(0);
//		            response.addCookie(cookie);
//		            break;
//		        }
//		    }
//		}
//
//		request.getSession().setAttribute(Constants.SESSION_APLICACION,
//				listaAplicaciones);
//
//		try {
//			objEncripta = new TripleDesUtil();
//			modulos = request.getParameter(Constantes.MODULOS);
//			//codusuario = request.getParameter(Constantes.CODUSUARIO_INIT);
//				// modulos=objEncripta.desencripta(modulos);
//				/*usuario = (ObtUsuarioCursor) request.getSession().getAttribute(
//						Constants.SESSION_USUARIO);*/
//				for (AplicacionCursor objAp : listaAplicaciones) {
//					if (!CommonUtil.existeElementoEnCadena(modulos,
//							objAp.getCodaplicacion())) {
//						modulos = (modulos == null ? "" : modulos)
//								+ Constantes.GUION + objAp.getCodaplicacion();
//	
//						//[INI - Hilmer Aliaga - 22/05/2012 - Cambio para obtener la URL]
//						
//						//url = objAp.getUrl()==null?"":objAp.getUrl();
//						requestUri = objAp.getUrl()==null?"":objAp.getUrl();
//						
//						//[INI - Nelson Cuba - 13/09/2012 - Cambio para la redirección de la URL]
//						url = SeguridadUtils.obtenerUrlAplicacion(scheme, servername, serverport,requestUri);
////						url = SeguridadUtils.obtenerUrlAplicacion(request, requestUri);
//						logger.debug("URL -> " + url);
//						
//						//[FIN - Hilmer Aliaga - 22/05/2012
//						
//						/* TODO Habilitar para desarrollo */
//						//url = url.replace("rstadihs01.rimac.com.pe", "172.25.206.185:8080");
//						//url = url.replace("rstadihs01.rimac.com.pe", "localhost:8080");
//						
//						if(CommonUtil.URLvalida(url)){
//							url = url + "?"
//									+ Constantes.ACCION + Constantes.IGUAL
//									+ Constantes.ACCION + "&" 
//									+ Constantes.MODULOS + Constantes.IGUAL
//									+ modulos + "&"
//									+ Constantes.CONTENEDOR + Constantes.IGUAL
//									+ Contenedor + "&"
//									+ Constantes.IDCONTENEDOR + Constantes.IGUAL
//									+ idContenedor + "&"
//									+ Constantes.SCHEME + Constantes.IGUAL
//									+ scheme + "&"
//									+ Constantes.SERVERNAME + Constantes.IGUAL
//									+ servername + "&"
//									+ Constantes.SERVERPORT + Constantes.IGUAL
//									+ serverport;
//							response.sendRedirect(url);
//							enviado = true;
//							break;
//						}
//					}
//				}
//
////				request.getRequestDispatcher("/logout.jsp").forward(request, response);
//				try
//				{
//					if(!enviado) {
//	//					[INI - Nelson Cuba - 13/09/2012 - Cambios para la redirección de la URL]
//						System.out.println("[USUARIO LOGUEADO BORRADO]-->"+SeguridadUtils.getCookieValue(request.getCookies(), Constantes.CODUSUARIO_INIT_COOKIE,"VACIO"));	
//						request.getSession().removeAttribute(Constants.SESSION_USUARIO);
//						request.getSession().invalidate();
//						response.sendRedirect(SeguridadUtils.obtenerUrlAplicacion(scheme,servername,serverport, "/" + Contenedor+ "/"+ "logout.jsp"));
//	//					response.sendRedirect(SeguridadUtils.obtenerUrlAplicacion(request, "/" + Contenedor+ "/"+ "logout.jsp"));
//					}
//				}
//				catch(Exception e2)
//				{
//					logger.debug("[doGet]" + e2);
//				}

//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}

	}

	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
				.getChildNodes();

		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();
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
	}

}
