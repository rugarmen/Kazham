package kazham.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kazham.bean.ListaLogAuditoria;
import kazham.bean.ListaParametro;
import kazham.bean.LstConstante;
import kazham.bean.LstLog;
import kazham.bean.LstParametro;
import kazham.bean.LstTipparametro;
import kazham.bean.MntCasillaCorreo;
import kazham.bean.MntConstante;
import kazham.bean.MntParametro;
import kazham.bean.MntTipparametro;
import kazham.bean.RegUsuarioxOpcion;
import kazham.bean.ValCfgTippar;
import kazham.commons.CommonUtil;
import kazham.commons.Constants;
import kazham.inisesion.bean.BsqUsuario;
import kazham.inisesion.commons.CommonsHelper;
import kazham.inisesion.service.InisesionService;
import kazham.service.UtilService;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import commons.framework.BaseController;

/**
 * Clase que contiene métodos que manipula información relacionada a carga de sesiones, 
 * aplicaciones, opciones y mantenimiento de parametros.
 * 
 * @author Rimac Seguros - David Argumé Berrocal
 */

@Controller
public class KazhamController extends BaseController {

	private Logger logger = Logger.getLogger(KazhamController.class.getName());
//	UtilService utilService = UtilService.getInstance();
//	ResourceBundle resources = ResourceBundle.getBundle("configuracion");
//	IdentificacionWebDAO imanager = new IdentificacionWebDAO();
//	private InisesionService inisesionService = InisesionService.getInstance();

	public ModelAndView buscar(HttpServletRequest arg0, HttpServletResponse arg1) {
		return null;
	}

	public ModelAndView open(HttpServletRequest arg0, HttpServletResponse arg1) {
		return null;
	}

	public ModelAndView save(HttpServletRequest arg0, HttpServletResponse arg1) {
		return null;
	}
	
	@RequestMapping("/guardarDato")
	public String guardarDato(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
	
		String txtName = (request.getParameter("name") != null?request.getParameter("name").toString():"");
		String txtEmail = (request.getParameter("email") != null?request.getParameter("email").toString():"");
		String txtPassword = (request.getParameter("password") != null?request.getParameter("password").toString():"");
		
		System.out.println(txtName);
		System.out.println(txtEmail);
		System.out.println(txtPassword);
		return "Llegó al controlador!!!";
	}
	
	/**
	 * Método que permite obtener el usuario logueado a la aplicación
	 * @param request permite obtener el contexto del aplicativo
	 * @param response Permite enviar la respuesta
	 */
	@RequestMapping("/obtenerUsuarioLogueado")
	public String obtenerUsuarioLogueado(HttpServletRequest request,
			HttpServletResponse response) {
		String usuarioLogueado = null;
		
		//usuarioLogueado = SeguridadUtils.getUsuarioLogueado(request);
		usuarioLogueado = usuarioLogueado!=null?usuarioLogueado:"S";
		
		return usuarioLogueado; 
//		this.escribirTextoSalida(response, usuarioLogueado);
	}
	// [FIN - HA - 09/08/2013]

}
