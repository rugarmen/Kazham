package kazham.inisesion.controller;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import kazham.inisesion.bean.BsqUsuario;
import kazham.inisesion.service.InisesionService;
import commons.framework.BaseController;


//import sas.seguridad.commons.SeguridadUtils;

/**
 * Clase que contiene métodos para la obtención, el listado 
 * y el mantenimiento de direcciones, medios de contactos y vias. 
 * 
 * @author Rimac Seguros - Jorge Páez.
 */
public class InisesionController extends BaseController{

  InisesionService inisesionService = InisesionService.getInstance();
  private Logger logger = Logger.getLogger(InisesionController.class.getName());

  public ModelAndView buscar(HttpServletRequest arg0, HttpServletResponse arg1) { return null; }
  public ModelAndView open(HttpServletRequest arg0, HttpServletResponse arg1)   { return null; }
  public ModelAndView save(HttpServletRequest arg0, HttpServletResponse arg1)   { return null; }

  /**
	 * Método que obtiene un listado de paises filtrado por criterios de búsqueda 
	 * @param request Filtros para la búsqueda de paises
	 * @param response Listado de paises
	 * @return 
  */
  public ModelAndView bsqUsuario(HttpServletRequest request, HttpServletResponse response) {
	    response.setContentType("text/html; charset=UTF-8");
	    BsqUsuario bsqUsuario;
	    
	    try {

	    	bsqUsuario = new BsqUsuario(request.getParameterMap());
	    	inisesionService.bsqUsuario(bsqUsuario);
	    		    	
	    	request.setAttribute("usuario", bsqUsuario);
	    	
	    } catch (RuntimeException e) {
	      this.escribirTextoSalida(response, commons.mapper.Utils.getMessageORA(e.getMessage()));
	      logger.error("Error al buscar Usuario");
	      //e.printStackTrace(); //SAS20151119

	    } catch (Exception e) {
	      logger.error("Error al buscar Usuario");
	      //e.printStackTrace(); //SAS20151119

	    }
	    return new ModelAndView();
	  }
 
  public ModelAndView obtenerUsuario(HttpServletRequest request, HttpServletResponse response) {
	    response.setContentType("text/html; charset=UTF-8");
	    BsqUsuario bsqUsuario;
	    String usuarioLogeado = null;
	    
	    try {
//	    	usuarioLogeado = SeguridadUtils.getUsuarioLogueado(request);
	    	bsqUsuario = new BsqUsuario(request.getParameterMap());
	    	bsqUsuario.setCodusuario(usuarioLogeado);
	    	inisesionService.obtenerUsuario(bsqUsuario);
	    	// if (bsqUsuario.getCursor().size()>0){
	    	        //this.escribirTextoSalida(response, commons.web.UtilWeb.objectToJson(obtParametro.getCursor().get(0), null, "sas.comun.bean.ObtParametroCursor"));
	    	  //    }else{
	    	     //   this.escribirTextoSalida(response, "{}");
	    	   //   }
	    	 
	    //	request.setAttribute("usuario", bsqUsuario);
	    	
	    } catch (RuntimeException e) {
	      this.escribirTextoSalida(response, commons.mapper.Utils.getMessageORA(e.getMessage()));
	      logger.error("Error al buscar Usuario");
	      //e.printStackTrace(); //SAS20151119

	    } catch (Exception e) {
	      logger.error("Error al buscar Usuario");
	      //e.printStackTrace(); //SAS20151119

	    }
	    return new ModelAndView();
	  }
  
}


