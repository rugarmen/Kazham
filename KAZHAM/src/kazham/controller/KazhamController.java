package kazham.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kazham.bean.ComponentePlantilla;
import kazham.bean.DetallePlantilla;
import kazham.bean.Informacion;
import kazham.bean.LstConstante;
import kazham.bean.PeriodoMes;
import kazham.bean.Plantilla;

import kazham.bean.Usuario;

import kazham.commons.Constants;
import kazham.inisesion.commons.CommonsHelper;
import kazham.inisesion.service.InisesionService;
import kazham.service.KazhamService;
import kazham.service.UtilService;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	KazhamService kazhamService = KazhamService.getInstance();

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
	public void guardarDato(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
	
		String txtName = (request.getParameter("name") != null?request.getParameter("name").toString():"");
		String txtEmail = (request.getParameter("email") != null?request.getParameter("email").toString():"");
		String txtPassword = (request.getParameter("password") != null?request.getParameter("password").toString():"");
		String sesIdeusuario = (request.getSession().getAttribute(Constants.SESSION_USER) != null? request.getSession().getAttribute(Constants.SESSION_USER).toString():"");
		System.out.println(txtName);
		System.out.println(txtEmail);
		System.out.println(txtPassword);
		
		Usuario param = new Usuario();
		param.setName(txtName);
		param.setEmail(txtEmail);
		param.setPassword(txtPassword);
		param.setIdeusuario(sesIdeusuario);
		
		kazhamService.guardarDato(param);
		
		try {
			request.getRequestDispatcher("/index.html").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		return null;
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

	/**
	 * Método que permite obtener el usuario logueado a la aplicación
	 * @param request permite obtener el contexto del aplicativo
	 * @param response Permite enviar la respuesta
	 */
	@RequestMapping("/listarPeriodo")
	public ModelAndView listarPeriodo(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		PeriodoMes param;

		try {
			param = new PeriodoMes(request.getParameterMap());
			kazhamService.listarPeriodo(param);

			String listaParam = commons.web.UtilWeb.listaToArrayJavaScript(param.getCursor(), null,PeriodoMes.class.getName());
//			String listaParam = "[['01','Enero'],['02','Febrero']]";
			this.escribirTextoSalida(response, listaParam);

		} catch (RuntimeException e) {
			logger.error("[listarPeriodo] : " + e.getMessage());
			this.escribirTextoSalida(response,
					commons.mapper.Utils.getErrorMessage(e));
		} catch (Exception e) {
			logger.error("[listarPeriodo] : " + e.getMessage());
		}
		return new ModelAndView();
	}

	@RequestMapping("/grabarInformacion")
	public void grabarInformacion(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
	
		Informacion param;
		
		try {
			param = new Informacion(request.getParameterMap());
			
			kazhamService.grabarInformacion(param);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	

	/**
	 * Método que permite obtener el usuario logueado a la aplicación
	 * @param request permite obtener el contexto del aplicativo
	 * @param response Permite enviar la respuesta
	 */
	@RequestMapping("/listarInformacion")
	public ModelAndView listarInformacion(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		Informacion param;

		try {
			param = new Informacion(request.getParameterMap());
			kazhamService.listarInformacion(param);

			String listaParam = commons.web.UtilWeb.listaToArrayJavaScript(param.getCursor(), null,Informacion.class.getName());
//			String listaParam = "[['01','AAAAA'],['02','BBBBB']]";
			this.escribirTextoSalida(response, listaParam);

		} catch (RuntimeException e) {
			logger.error("[listarInformacion] : " + e.getMessage());
			this.escribirTextoSalida(response,
					commons.mapper.Utils.getErrorMessage(e));
		} catch (Exception e) {
			logger.error("[listarInformacion] : " + e.getMessage());
		}
		return new ModelAndView();
	}


	@RequestMapping("/listarPlantilla")
	public ModelAndView listarPlantilla(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		Plantilla param;

		try {
			param = new Plantilla(request.getParameterMap());
			BigDecimal ideEmpresa = new BigDecimal(1);
				
			param.setIdeempresa(ideEmpresa);
			
			kazhamService.listarPlantilla(param);

			String listaParam = commons.web.UtilWeb.listaToArrayJavaScript(param.getCursor(), null,Plantilla.class.getName());
//			String listaParam = "[['01','AAAAA'],['02','BBBBB']]";
			this.escribirTextoSalida(response, listaParam);

		} catch (RuntimeException e) {
			logger.error("[listarPlantilla] : " + e.getMessage());
			this.escribirTextoSalida(response,
					commons.mapper.Utils.getErrorMessage(e));
		} catch (Exception e) {
			logger.error("[listarPlantilla] : " + e.getMessage());
		}
		return new ModelAndView();
	}

	@RequestMapping("/listarComponentePlantilla")
	public ModelAndView listarComponentePlantilla(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		ComponentePlantilla param;

		try {
			param = new ComponentePlantilla(request.getParameterMap());
			
			BigDecimal idePlantilla = new BigDecimal(1);
			
			param.setIdeplantilla(idePlantilla);
			
			
			kazhamService.listarComponentePlantilla(param);

			String listaParam = commons.web.UtilWeb.listaToArrayJavaScript(param.getCursor(), null,ComponentePlantilla.class.getName());
//			String listaParam = "[['01','AAAAA'],['02','BBBBB']]";
			this.escribirTextoSalida(response, listaParam);

		} catch (RuntimeException e) {
			logger.error("[listarComponentePlantilla] : " + e.getMessage());
			this.escribirTextoSalida(response,
					commons.mapper.Utils.getErrorMessage(e));
		} catch (Exception e) {
			logger.error("[listarComponentePlantilla] : " + e.getMessage());
		}
		return new ModelAndView();
	}
	
	@RequestMapping("/listarDetallePlantilla")
	public ModelAndView listarDetallePlantilla(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		DetallePlantilla param;

		try {
			param = new DetallePlantilla(request.getParameterMap());
			
			BigDecimal ideComPlantilla = new BigDecimal(1);
			
			param.setIdecomplantilla(ideComPlantilla);
						
			kazhamService.listarDetallePlantilla(param);

			String listaParam = commons.web.UtilWeb.listaToArrayJavaScript(param.getCursor(), null,DetallePlantilla.class.getName());
//			String listaParam = "[['01','AAAAA'],['02','BBBBB']]";
			this.escribirTextoSalida(response, listaParam);

		} catch (RuntimeException e) {
			logger.error("[listarDetallePlantilla] : " + e.getMessage());
			this.escribirTextoSalida(response,
					commons.mapper.Utils.getErrorMessage(e));
		} catch (Exception e) {
			logger.error("[listarDetallePlantilla] : " + e.getMessage());
		}
		return new ModelAndView();
	}
}
