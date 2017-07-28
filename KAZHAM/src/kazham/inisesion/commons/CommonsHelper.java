package kazham.inisesion.commons;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kazham.bean.LstLog;

//import sas.seguridad.commons.SeguridadUtils;

import com.rimac.security.SeguridadXML;



public class CommonsHelper {

	/**
	 * Metodo que devuelve el usuario logueado en la aplicación
	 * @param request permite acceder a la session
	 * @return Usuario Logueado
	 */
	/*public static String getUsuarioLogueado(HttpServletRequest request) {
		//TODO
		return SeguridadUtils.getUsuarioLogueado(request);
		
	}*/
	public static String getUsuarioLogueado(HttpServletRequest request) {
		//TODO
		return "S";
		//return Constants.USUARIO_LOGUEADO;
//		return SeguridadUtils.getUsuarioLogueado(request);
	}
	
	/**
	 * Método que formatea el mensaje  que se escribira en el log con la 
	 * diferencia del tiempo actual con el que se le pasa como parámetro
	 * @param proceso Nombre del proceso del que se toma el tiempo de ejecución
	 * @param tiempoInicio Tiempo en el que se inicion el proceso
	 * @return Mensaje con el tiempo que duro el proceso
	 */
	public static String tiempoEjecucion(String proceso, Date tiempoInicio) {
		Date tiempoFin = null;
		long tiempoEjecucion;
		StringBuilder sb = null;
		
		tiempoFin = new Date();
		tiempoEjecucion = tiempoFin.getTime() - tiempoInicio.getTime();
		sb = new StringBuilder();
		
		sb.append("Tiempo de ejecucion -> ");
		sb.append(proceso);
		sb.append(": ");
		sb.append(tiempoEjecucion);
		sb.append(" milisegundos");
		
		return  sb.toString();
	}
	
	/**
	 * Retorna el parametro envidado en un request como int
	 * @param request Contiene el parametro
	 * @param parametro Nombre del parametro
	 * @return parametro convertido a entero
	 */
	public static int getParameterInt(HttpServletRequest request,
			String parametro) {
		int dato;
		
		try {
			dato = Integer.parseInt(request.getParameter(parametro));
		} catch (Exception e) {
			dato = 0;
		}
		
		return dato;
	}
	
	@SuppressWarnings("rawtypes")
	public static void setIndexRowLog(List listaLog) {
		LstLog log = null;
		
		for(int i=0; i<listaLog.size(); i++) {
			log = (LstLog) listaLog.get(i);
			log.setIndexRow(i+1);
		}
	}
	
	/**
	 * Método que formatea el error a formato JSON
	 * @param origen Origen del error (exception, warning, validacion)
	 * @param msg Mensaje para el usuario
	 * @param msgCompleto Detaller del error
	 * @return Cadena en formato JSON
	 */
	public static String formatearMensajeErrorJson(String origen,
			String msg, String msgCompleto) {
		StringBuffer mensaje = new StringBuffer();
		
		mensaje.append("{");
		mensaje.append("\'origen\':\'" + origen + "\',");
		mensaje.append("\'msg\':\'" + msg + "\',");
		mensaje.append("\'log\':\'" + msgCompleto.replaceAll("\r", "").replaceAll("\n", "<->"));
		mensaje.append("\'}");
		
		return mensaje.toString();
	}
}
