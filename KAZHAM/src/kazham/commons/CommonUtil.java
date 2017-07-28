package kazham.commons;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.sql.Clob;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kazham.bean.LstConstante;
import kazham.bean.LstConstanteCursor;
import kazham.service.UtilService;

import org.springframework.web.servlet.ModelAndView;

import com.rimac.security.TripleDesUtil;

//import sas.seguridad.bean.AplicacionCursor;
//import sas.seguridad.bean.ObtUsuarioCursor;
//import sas.seguridad.bean.OpcionCursor;
//import sas.seguridad.commons.Constantes;
//import sas.seguridad.commons.SeguridadUtils;
//import sas.seguridad.service.LoginService;

public class CommonUtil {
	
	static UtilService utilService = UtilService.getInstance();

	public static String cerrarSession(HttpServletRequest req) {
	    return "S";
	}

//	public static String getAcceso(String user, String pass, HttpServletRequest req) {
//	    LoginService objLogin = new LoginService();
//	    String idContenedor = req.getParameter("IDCONTENEDOR");
//	    ObtUsuarioCursor usuario = objLogin.getUsuario(user,idContenedor);
//	    String acceso = "N"; 
//	    
//	    if (usuario.getClave()!=null){
//		    TripleDesUtil objEncripta;
//			try {
//				objEncripta = new TripleDesUtil();
//				if (usuario.getClave().equals(objEncripta.encripta(pass))){
//					acceso = "S";
//					req.getSession().setAttribute(kazham.commons.Constants.SESSION_USUARIO, usuario);
//				}else{
//					acceso = "N";
//				}				
//			} catch (Exception e) {
//				//e.printStackTrace(); //SAS20151119
//			}
//	    }
//	    return acceso;
//	}	
//	
//	public static String getUsuario(HttpServletRequest req) {
//		//ObtUsuarioCursor usuario = (ObtUsuarioCursor)req.getSession().getAttribute(Constants.SESSION_USUARIO);
//		ObtUsuarioCursor usuario = SeguridadUtils.getLogin(req);
//		String codUsuario = "";
//		if (usuario!=null) codUsuario = usuario.getIdusuario(); 
//	    return codUsuario;
//	}
//	
//	public static String getAplicaciones(HttpServletRequest req) {
//	    //ObtUsuarioCursor usuario = (ObtUsuarioCursor)req.getSession().getAttribute(Constants.SESSION_USUARIO);/*objLogin.getUsuario(user);*/
//		ObtUsuarioCursor usuario = SeguridadUtils.getLogin(req);
//	    
//	    String result = "[]"; 
//	    if (usuario!=null){
//	    	result = commons.web.UtilWeb.listaToArrayJson(usuario.getAplicaciones(), null, "sas.seguridad.bean.AplicacionCursor");
//	    	System.out.println(result);
//	    }
//	    return result;
//	}
//
//	public static String getOpciones(String app, HttpServletRequest req) {
//	    //ObtUsuarioCursor usuario = (ObtUsuarioCursor)req.getSession().getAttribute(Constants.SESSION_USUARIO);/*objLogin.getUsuario(user);*/
//		ObtUsuarioCursor usuario = SeguridadUtils.getLogin(req);
//	    String result = getTreeSAS(app, usuario.getAplicaciones());
//	    System.out.println(result);
//	    
//	    return result;
//	}	
//
//    private static void getSubTreeSAS(java.util.List<OpcionCursor> lista, StringBuilder sb) {
//    	OpcionCursor opc = null;
//        for(int i=0; i<lista.size(); i++){
//        	opc = lista.get(i);
//        	if (!opc.getTipo().equals("0")){
//         	    sb.append(String.format("{\'id\':\'%1$s\', \'text\':\'%2$s\', \'expanded\':\'false\'", opc.getCodopcion(), opc.getDescripcion()));
//            	sb.append(", \'children\':[");
//            	
//    	        getSubTreeSAS(opc.getSubopciones(), sb);
//    	        
//    	        sb.append("]");
//    	        sb.append("}");
//    	        
//            }else{
//        		sb.append(String.format("{\'id\':\'%1$s\', \'text\':\'%2$s\', \'leaf\':\'1\', \'iconCls\':\'%3$s\', \'ruta\':\'%4$s\', \'url\':\'%5$s\', \'mod\':\'%6$s\', \'ns\':\'%7$s\', \'dsc\':\'%8$s\', \'cfg\':\'%9$s\',  \'f1\':\'%10$s\', \'panel\':\'Panel\', \'indNoDisp\':\'%11$s\'", 
//    	                (opc.getCodopcion()==null?"":opc.getCodopcion()),
//    	                (opc.getDescripcion()==null?"":opc.getDescripcion()),
//    	                (opc.getIcono()==null?"":opc.getIcono()),
//    	                (opc.getUrl()==null?"":opc.getUrl()),
//    	                (opc.getUrl()==null?"":opc.getUrl()),
//    	                (opc.getModulo()==null?"":opc.getModulo()),
//    	                (opc.getEspaciotrabajo()==null?"":opc.getEspaciotrabajo()), 
//    	                (opc.getComentario()==null?"":opc.getComentario()), 
//    	                (opc.getDatoadicional()==null?"{}":opc.getDatoadicional()), 
//    	                (opc.getAyuda()==null?"":opc.getAyuda()), 
//    	                (opc.getIndnodisponibilidad()==null?"":opc.getIndnodisponibilidad())));
//        		sb.append("}");        	
//            }
//            
//        	if(i!=lista.size()-1){
//        		sb.append(",");
//        	}
//        }    	
//    }
//
//    private static String getJSONTreeSAS(java.util.List<OpcionCursor> lista) {
//        StringBuilder sb = new StringBuilder("[");
//
//    	getSubTreeSAS(lista, sb);
//
//        sb.append("]");
//        return sb.toString();
//    }
//
//    private static String getJSONTreeSAS(AplicacionCursor app) {
//        return getJSONTreeSAS(app.getOpciones());
//    }
//
//    private static String getTreeSAS(String codigo, java.util.List<AplicacionCursor> lista) {
//    	AplicacionCursor app = null;
//    	for(int i=0; i<lista.size(); i++){
//    		if (lista.get(i) == null){
//    			app = null;
//    		}else{
//        		app = (AplicacionCursor)lista.get(i);
//        		if (app.getCodaplicacion().equals(codigo)){
//        			break;
//         	    }
//        		app = null;
//    		}
//    	}
//    	if (app==null)
//    		return "";
//    	else
//    		return getJSONTreeSAS(app);
//    }	
//
//    public static boolean isLinux() {
//        return System.getProperty("os.name").toUpperCase().indexOf("LINUX") >= 0;
//    }
//
//    public static boolean URLvalida(String urlStr){
//		boolean resultado=false;
//		try{
//			
//			URL url = new URL(urlStr);
//			url.openConnection();
//			InputStream is = url.openStream();
//			resultado=true;
//		
//		}catch (Exception e){
//			resultado=false;
//		}
//		return resultado;
//	}
//
//	public static boolean existeElementoEnCadena(String cadena, String elemento){
//		String[] elementos=(cadena==null?"":cadena).split(Constantes.GUION);
//		boolean resultado=false;
//		for(String elementoAux:elementos){
//			if(elementoAux.equals(elemento)){
//				resultado=true;
//				break;
//			}
//		}
//		return resultado;
//	} 
//	
//	public static String clobToString(Clob data) {
//  	    StringBuilder sb = new StringBuilder();
//  	    try {
//  	        Reader reader = data.getCharacterStream();
//  	        BufferedReader br = new BufferedReader(reader);
//
//  	        String line;
//  	        while(null != (line = br.readLine())) {
//  	            sb.append(line);
//  	        }
//  	        br.close();
//  	    } catch (Exception e) {
//  	        //e.printStackTrace(); //SAS20151119
//  	    }
//  	    return sb.toString();
//  	}
//	
//	/**
//	 * Permite obtener un valor como entero para una cadena
//	 * @param valor Cadena a ser convertida a entero
//	 * @return Int que representa el valor de la cadena cero en caso de error
//	 */
//	public static int getCadenaComoInt(String valor) {
//		int entero = 0;
//		
//		try {
//			entero = Integer.parseInt(valor);
//		} catch(Exception e) {
//			entero = 0;
//		}
//		
//		return entero;
//	}
//	
//	/**
//	 * Método que permite obtener el número de sessiones activas
//	 * @param request Permite obtener el contexto del aplicativo
//	 * @param usuarioLogueado Usuario logeado en el aplicativo
//	 * @return Numero de sessiones del usuario logueado
//	 */
//	public static int obtenerNumeroSessiones(HttpServletRequest request,
//			String usuarioLogueado) {
//		int numeroSessiones = 0;
//		HttpSession session = null;
//		String keySessiones = null;
//		
//		usuarioLogueado = SeguridadUtils.getUsuarioLogueado(request);
//		
//		if(usuarioLogueado!=null) {
//			session = request.getSession();
//			keySessiones = Constants.PREFIJO_NUMERO_SESSIONES + usuarioLogueado;
//			
//			numeroSessiones = CommonUtil.getCadenaComoInt(
//					(String)session.getAttribute(keySessiones));
//		}
//		
//		return numeroSessiones;
//	}
//	
//	public static String obtenerValorConstante(String ideConstante) {
//		String valor = null;
//		LstConstante param = null;
//		LstConstanteCursor constante = null;
//		
//		param = new LstConstante();
//		param.setIdeconstante(ideConstante);
//		
//		utilService.obtConstante(param);
//		
//		if(param.getCursor()!=null && param.getCursor().size()>0) {
//			constante = (LstConstanteCursor) param.getCursor().get(0);
//			
//			valor = constante.getValor();
//		}
//		
//		return valor;
//	}
//	
//	 public static String obtIndicadorTrabajadorRimac(HttpServletRequest request, HttpServletResponse response) {
//		    response.setContentType("text/html; charset=UTF-8");
//		    java.util.Date tiempoInicio = new Date();
//		    String indicadorTrabajadorRimac = "";
//		    String usuario = "";
//		    String respuesta = "";
//		    
//		    try {
//		    	
//		    	usuario = SeguridadUtils.getUsuarioLogueado(request);
//		    	indicadorTrabajadorRimac = utilService.getIndicadorTrabajadorRimac(usuario);
//		    	
//		    	respuesta = "{"
//		  	  		  + "indicadorTrabajadorRimac" + ":\"" + indicadorTrabajadorRimac+ "\"" +
//		                  "}";
//		    	
//		      
//		    } catch (RuntimeException e) {
//		    	//e.printStackTrace(); //SAS20151119
//		    } catch (Exception e) {
//		    	//e.printStackTrace(); //SAS20151119
//		    }
//		    
//		    return respuesta;
//	}
		
}
