package kazham.controller;

import org.apache.commons.fileupload.FileItemIterator;

import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.json.simple.JSONObject;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.text.SimpleDateFormat;

import oracle.sql.BLOB;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kazham.bean.DatosPasarelaEC;
import kazham.bean.ListaCorreoGenerico;
import kazham.bean.ListaCorreoGenericoCursor;
import kazham.bean.LstConstante;
import kazham.bean.LstConstanteCursor;
import kazham.bean.MntArchivoblob;
import kazham.bean.TipoCambio;
import kazham.bean.Usuario;
import kazham.commons.Constants;
import kazham.inisesion.commons.CommonsHelper;
import kazham.service.UtilService;

import commons.framework.BaseController;
import commons.framework.BaseService;

//import sas.seguridad.bean.ObtUsuarioCursor;
//import sas.seguridad.commons.SeguridadUtils;
import org.apache.log4j.Logger;

import pe.beyond.mammuth.paramcrypto.SecureParameters;

import com.rimac.security.MD5;
import com.sas.trx.Procesador;
import com.sas.trx.ProcesadorDinersClubCliente;
import com.sas.trx.ProcesadorVisaCliente;
import com.sas.trx.ProcesadorMastercardCliente;
import com.sas.trx.ProcesadorVisaPuntosVidaBBVA;
import com.sas.trx.bean.BTransaccion;
import com.sas.trx.bean.DataECommerce;
import com.sas.trx.service.TransaccionService;

/**
 * Funcion: Clase que contiene métodos para exportar a excel o cargar archivos al servidor.
 * Creación: DARGUMEB - 20/03/2012
 * Modificaciones:
 * DARGUMEB			1.0			20/03/2012			Version Inicial del Componente
 * SCASTANEDM		1.1			27/11/2014			Se agregó el método: 
 * 													- obtIniciaTrxPos 
 * 													- obtFinalizaTrxPos
 * SCASTANEDM		1.2			18/12/2014			Se modifico el método: 
 * 													- obtFinalizaTrxPos
 * JLAM             2.0         20/12/2014          Se modifico los métodos:  
 *                                                  - obtIniciaTrxC 
 *                                                  - eliminarSessionPasarela
 * SCASTANEDM		3.0			31/12/2014			Se modifico el método: 
 * 													- obtIniciaTrxPos
 * SCASTANEDM		3.1			05/01/2014			Se modifico el método: 
 * 													- obtFinalizaTrxPos
 * SCASTANEDM		3.2			12/02/2015			Se modifico el método: 
 * 													- obtFinalizaTrxPos
 * 													- obtIniciaTrxPos

 * Clase que contiene métodos para exportar a excel o cargar archivos al servidor.
 * 
 * @author Rimac Seguros - David Argumé Berrocal

 */

public class UtilController extends BaseController{
  private Logger logger = Logger.getLogger(UtilController.class.getName());
  private TransaccionService transaccionService= TransaccionService.getInstance();
  
  UtilService utilService = UtilService.getInstance();
  
  public ModelAndView buscar(HttpServletRequest arg0, HttpServletResponse arg1) { return null; }
  public ModelAndView open(HttpServletRequest arg0, HttpServletResponse arg1)   { return null; }
  public ModelAndView save(HttpServletRequest arg0, HttpServletResponse arg1)   { return null; }

	/**
	 * Metodo que permite exportar a excel los listados/grids de las pantallas.
	 * @param request 
	 * @param response 
	 * @return file
	 */
  public ModelAndView exportExcel(HttpServletRequest request, HttpServletResponse response) {

	String tipoLetra = "Verdana";
	String nombreHojaExcel = "Listado";
	String separadorConcat = ";";
	String preNombreServer = "Listado-";
	String preExtensionServer = ".xls";
	String carpetaTempAbsolute = "/temp/";
	String carpetaTempRelative = "temp";
	  
    java.util.List lista = (List)request.getSession().getAttribute("EXCEL");
    String bean = request.getParameter("bean").toString();
    String dataindex = request.getParameter("dataindex").toString();
    String header = request.getParameter("header").toString();
    String width = request.getParameter("width").toString();
    String[] adataindex = dataindex.split(separadorConcat);
    String[] aheader = header.split(separadorConcat);
    String[] awidth = width.split(separadorConcat);
    int index = -1;
    int indexCol = 0;
    boolean f = false;
    Object result = null;

    HSSFWorkbook libro = new HSSFWorkbook();
    HSSFSheet hoja = libro.createSheet(nombreHojaExcel);
 
    HSSFRow fila = null;
    HSSFRichTextString texto = null;
    HSSFCell celda = null;

    HSSFCellStyle styleHead = libro.createCellStyle();
    HSSFFont fontHead = libro.createFont();

    styleHead.setFillForegroundColor(HSSFColor.GREY_50_PERCENT.index);
    styleHead.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    styleHead.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    styleHead.setBorderTop(HSSFCellStyle.BORDER_THIN);
    styleHead.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    styleHead.setBorderRight(HSSFCellStyle.BORDER_THIN);   
    styleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    fontHead.setFontName(tipoLetra);
    fontHead.setColor(HSSFColor.WHITE.index);
    fontHead.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
    styleHead.setFont(fontHead);
    
    java.lang.reflect.Method metodo = null;
    Object objetoHeader = null;
	Class claseHeader = null;
    String campo = null;
	String valor = null;
	
	try {
		objetoHeader = Class.forName(bean).newInstance();
		claseHeader = objetoHeader.getClass();
	} catch (InstantiationException e1) {
		e1.printStackTrace();
		logger.error("[exportExcel] : " + e1.getMessage());
	} catch (IllegalAccessException e1) {
		e1.printStackTrace();
		logger.error("[exportExcel] : " + e1.getMessage());
	} catch (ClassNotFoundException e1) {
		e1.printStackTrace();
		logger.error("[exportExcel] : " + e1.getMessage());
	}

	fila = hoja.createRow(0);
	int beanCount = claseHeader.getDeclaredFields().length;
    for(int j=0; j<beanCount; j++){
    	System.out.println(j);
    	campo = claseHeader.getDeclaredFields()[j].getName();

    	if (!campo.equalsIgnoreCase("serialVersionUID") && !campo.equalsIgnoreCase("indexrow")){
    		f = false;
    		index = -1;
    		for(int i=0; i<adataindex.length; i++){
    			if (adataindex[i].equalsIgnoreCase(campo)){
    				f = true;
    				index = i;
    				indexCol += 1;
    			}
    		}
    		
    		if (f==true){
            	System.out.println(claseHeader.getDeclaredFields()[j].getName());
            	hoja.setColumnWidth((int)indexCol, (int)(Integer.parseInt(awidth[index])*48));
            	texto = new HSSFRichTextString(aheader[index]);
            	celda = fila.createCell((int)(indexCol));
            	celda.setCellStyle(styleHead);
            	celda.setCellValue(texto);
    		}
    	}
	}
	
    for(int i=1; i<=lista.size(); i++){
    	fila = hoja.createRow(i);
    	
    	Object objeto = lista.get(i-1);
    	Class clase = objeto.getClass();
		
    	indexCol = 0;
        for(int j=0; j<beanCount; j++){
        	System.out.println(j);
        	campo = clase.getDeclaredFields()[j].getName();
        	System.out.println(campo);
        	if (!campo.equalsIgnoreCase("serialVersionUID") && !campo.equalsIgnoreCase("indexrow")){
        		f = false;
        		index = -1;
        		for(int ii=0; ii<adataindex.length; ii++){
        			if (adataindex[ii].equalsIgnoreCase(campo)){
        				f = true;
        				index = ii;
        				indexCol += 1;
        			}
        		}
        		
        		if (f==true){
	            	campo = campo.substring(0,1).toUpperCase() + campo.substring(1);
	            	try {
						metodo = clase.getDeclaredMethod("get"+campo, null);
					} catch (SecurityException e) {
						//e.printStackTrace(); //SAS20151119
					} catch (NoSuchMethodException e) {
						//e.printStackTrace(); //SAS20151119
					}
	            	System.out.println("get"+campo);
	    			try {
	    				result = metodo.invoke(objeto, null);
	    				if (result!=null) {
	    					valor = result.toString();
	    				} else {
	    					valor = null;
	    				}	    				
					} catch (IllegalArgumentException e) {
						//e.printStackTrace(); //SAS20151119
						logger.error("[exportExcel] : " + e.getMessage());
					} catch (IllegalAccessException e) {
						//e.printStackTrace(); //SAS20151119
						logger.error("[exportExcel] : " + e.getMessage());
					} catch (InvocationTargetException e) {
						//e.printStackTrace(); //SAS20151119
						logger.error("[exportExcel] : " + e.getMessage());
					} catch (Exception e) {
						//e.printStackTrace(); //SAS20151119
						logger.error("[exportExcel] : " + e.getMessage());
					}
	            	texto = new HSSFRichTextString(valor);
	            	celda = fila.createCell((int)(indexCol));
		        	celda.setCellValue(texto);  
        		}
        	}
    	}
    }
    
    try {
    	String ruta = request.getSession().getServletContext().getRealPath("/");
    	ruta = ruta +
        	   System.getProperty("file.separator") +
        	   carpetaTempRelative + 
        	   System.getProperty("file.separator");
        
//        ObtUsuarioCursor usuario = (ObtUsuarioCursor)request.getSession().getAttribute("USUARIO");
        
//    	FileOutputStream elFichero = new FileOutputStream(ruta + preNombreServer + usuario.getIdusuario() + preExtensionServer);
//    	libro.write(elFichero);
    	
//    	String file = carpetaTempAbsolute + preNombreServer + usuario.getIdusuario() + preExtensionServer;
//    	elFichero.close();
//    	
    	response.setHeader("JSON", ruta);
//    	this.escribirTextoSalida(response, file);
    	this.escribirTextoSalida(response, null);
    } catch (Exception e) {
        //e.printStackTrace(); //SAS20151119
		logger.error("[exportExcel] : " + e.getMessage());
    }
        
    return new ModelAndView();
  }

	/**
	 * Metodo que permite cargar al servidor un archivo.
	 * @param request 
	 * @param response 
	 * @return Data en formato Json
	 */
  public ModelAndView uploadFile(HttpServletRequest request, HttpServletResponse response) {
    response.setContentType("text/html; charset=UTF-8");
    String[] permisos = request.getParameter("permisos").split("-");
    String descripcion = request.getParameter("descripcion");
	String fileName = null;
	String nombreArchivo = null;	//[SC54516] GDIAZJ 16/08/2016
	String fileExt = null;
    
    try {
    	logger.info("[uploadFile] : " + "String pathTarget = request.getSession().getServletContext().getRealPath('/') + System.getProperty('file.separator') + 'temp';");
    	String pathTarget = request.getSession().getServletContext().getRealPath("/") + System.getProperty("file.separator") + "temp" + System.getProperty("file.separator");
    	logger.info("[uploadFile] : pathTarget = " + pathTarget);
        File yourTempDirectory = new File(pathTarget);
        int yourMaxMemorySize  = 10000000;
        int yourMaxRequestSize = 10000000;

        // Crear una fabrica de items de archivo
        DiskFileItemFactory factory = new DiskFileItemFactory();
        logger.info("[uploadFile] : " + "DiskFileItemFactory factory = new DiskFileItemFactory();");
        factory.setSizeThreshold(yourMaxMemorySize);
        factory.setRepository(yourTempDirectory);
        logger.info("[uploadFile] : " + "factory.setRepository(yourTempDirectory);");

        // Crear un nuevo manejador de UPLOAD de archivo
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(yourMaxRequestSize);
        logger.info("[uploadFile] : " + "upload.setSizeMax(yourMaxRequestSize);");

        // Guardar los Archivos
        FileItemIterator fii = upload.getItemIterator(request);
        logger.info("[uploadFile] : " + "FileItemIterator fii = upload.getItemIterator(request);");
        
        while (fii.hasNext()) {
            FileItemStream item = fii.next();

            if (item.isFormField()) {
                //processFormField(item);
            } else {
                File file = null;
                InputStream is = null;
                FileOutputStream os = null;
                int size = 0;
                
                try {
                	
    				fileName = item.getName();
                    logger.info("[uploadFile] : while-fileName=" + fileName);
    			    fileExt = fileName.substring(fileName.lastIndexOf(".")+1);
                    logger.info("[uploadFile] : while-fileExt=" + fileExt);
    			    fileName = fileName.substring(fileName.lastIndexOf("\\")+1);  
        			//ini [SC54516] GDIAZJ 16/08/2016
                    nombreArchivo = fileName.substring(fileName.lastIndexOf("\\")+1,fileName.indexOf("."));
                    nombreArchivo = this.caracteresEspeciales(nombreArchivo);
                    nombreArchivo = nombreArchivo+"."+fileExt;
                    //fin [SC54516] GDIAZJ 16/08/2016
                    logger.info("[uploadFile] : while-fileName=" + fileName);                  
                    
                    if (permisos[0].equalsIgnoreCase("1")){
                    	/*TODO: Cambio de descarga de archivo
                    	 * RGARCIA - 27/04/2012
                    	 * JVELA - 04/05/2012
                    	 * */
                    	//file = File.createTempFile(fileName, "", yourTempDirectory);
                    	String nameFilePartial =  fileName.substring(0,fileName.indexOf("."));
	                    	nameFilePartial = nameFilePartial + System.currentTimeMillis();
	                    	nameFilePartial = nameFilePartial + "." + fileExt;
	                    	
                    	  String server = resources.getString(Constants.SERVIDOR);
                	      if (server.equals("WebSphere")){
	  	                    	file = new File(nameFilePartial);
                	      } else {
  	                    		file = new File(yourTempDirectory+"\\"+nameFilePartial);                	    	  
                	      }
                    }else{
                    	file = new File(pathTarget + fileName);
                    }
                    logger.info("[uploadFile] : while-vcnombrefile=" + fileName);
                    
                    // Guardar el archivo en el Servidor
                    byte[] b = new byte[4096];
                    int len;
                    is = item.openStream();
                    os = new FileOutputStream(file);
                    len = is.read(b);
                    logger.info("[uploadFile] : while-len=" + len);
                    while (len > 0) {
                        size += len;
                        os.write(b, 0, len);
                        len = is.read(b);
                    }

                } catch (Exception ex) {
                	file.delete();
                    JSONObject jo = new JSONObject();
                    jo.put("success", false);
                    jo.put("message", "Error al guardar el archivo.");
            		logger.error("[uploadFile] : " + ex.getMessage());
                    this.escribirTextoSalida(response, jo.toString());
                } finally {
                    if (os != null) {
                        os.flush();
                        os.close();
                    }
                    if (is != null)
                        is.close();
                }

                // Generar el registro en la Base de Datos
                //fileName = (new File(item.getName())).getName();
                logger.info("[uploadFile] : while-fileName=" + fileName);

                // Generar la Sentencia
                String idarchivo="";
                try {
                    FileInputStream fis;
                    fis = new FileInputStream(file);
                    byte[] zipped = new byte[(int)file.length()];
                    fis.read(zipped);

                    
                    if (permisos[1].equalsIgnoreCase("1")){
	                	MntArchivoblob param = new MntArchivoblob();  
	                	param.setArbytes(zipped);
	                	logger.info("[uploadFile] : while-zipped=" + zipped.toString());
	        			//ini [SC54516] GDIAZJ 16/08/2016
	                	//param.setNomarchivo(fileName);
	                	param.setNomarchivo(nombreArchivo);
	        			//fin [SC54516] GDIAZJ 16/08/2016
	                	logger.info("[uploadFile] : while-fileName=" + fileName);
	                	param.setNomarchivo2(file.getName());
	                	logger.info("[uploadFile] : while-file.getName()=" + file.getName());
	                	param.setExtension(fileExt);
	                	logger.info("[uploadFile] : while-ext=" + fileExt);
	                	param.setBytes("" + size);
	                	logger.info("[uploadFile] : while-size=" + size);
	                	param.setDescripcion(request.getParameter("descripcion"));
	                	utilService.mntArchivoblob(param);
	                    
	                	idarchivo = "" + param.getIdearchivoblob();
                    }
                    
                } catch (Exception ex) {
                    file.delete();
                    JSONObject jo = new JSONObject();
                    jo.put("success", false);
                    jo.put("message", ex.toString());
            		logger.error("[uploadFile] : " + ex.getMessage());
                    this.escribirTextoSalida(response, jo.toString());
                } finally {
                    is.close();
                }

                // Renombrar el archivo
                
                try {
                    file.renameTo(new File(pathTarget + fileName));
            		logger.error("[uploadFile] : File=" + pathTarget + fileName);
                } catch (Exception ex) {
                    file.delete();
                    JSONObject jo = new JSONObject();
                    jo.put("success", false);
                    jo.put("message", "El archivo ya existe.");
            		logger.error("[uploadFile] : " + ex.getMessage());
                    this.escribirTextoSalida(response, jo.toString());
                }
				
                JSONObject jo = new JSONObject();
                jo.put("success", true);
                jo.put("idarchivo", idarchivo);
                logger.info("[uploadFile JSONObject idarchivo] : " + idarchivo);
                jo.put("nombrearchivo", fileName);
                logger.info("[uploadFile JSONObject nombrearchivo] : " + fileName);
                jo.put("descripcion", descripcion);
                logger.info("[uploadFile JSONObject descripcion] : " + descripcion);
                //jo.put("nombreserv", file.getName());
                jo.put("nombreserv", file.getName());
                logger.info("[uploadFile JSONObject nombreserv] : " + file.getName());
                jo.put("tamano", "" + size);
                logger.info("[uploadFile JSONObject tamano] : " + size);
                jo.put("extension", fileExt);
                logger.info("[uploadFile JSONObject extension] : " + fileExt);

                logger.info("[uploadFile JSONObject] : " + jo.toString());
                this.escribirTextoSalida(response, jo.toString());

            }
        }

    } catch (Exception ex) {
		logger.error("[uploadFile] : " + ex.getMessage());
        ex.printStackTrace();
    }

    return new ModelAndView();
  }


	/**
	 * Metodo que permite visualizar el archivo.
	 * @param request 
	 * @param response 
	 * @return Data en formato Json
	 */
	public ModelAndView viewFile(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
	    String ruta = null;
	    String idArchivo = null;
	    String fileOutput = null;
	    java.util.Date tiempoInicio = new Date();
	    try {	    	
	    	//1. OBTENEMOS EL NOMBRE DEL REPORTE
	    	idArchivo = request.getParameter("idarchivo");
		    ruta = request.getSession().getServletContext().getRealPath("/")+System.getProperty("file.separator");
		      
		    fileOutput = utilService.viewFile(idArchivo, ruta);
	    	fileOutput = "temp" + "/" + fileOutput;
				    		     
			this.escribirTextoSalida(response, fileOutput);	      

	    } catch (RuntimeException e) {
	      this.escribirTextoSalida(response, commons.mapper.Utils.getMessageORA(e.getMessage()));
	      //e.printStackTrace(); //SAS20151119
	    } catch (Exception e) {
	      //e.printStackTrace(); //SAS20151119
	    }
		
		return new ModelAndView();
	}

	/**
	 * Metodo que permite cargar al servidor un archivo.
	 * @param request 
	 * @param response 
	 * @return Data en formato Json
	 */
  public ModelAndView uploadFileGes(HttpServletRequest request, HttpServletResponse response) {
    response.setContentType("text/html; charset=UTF-8");
    String[] permisos = request.getParameter("permisos").split("-");
    String descripcion = request.getParameter("descripcion");
	String fileName = null;
	String fileExt = null;
    
    try {
    	logger.info("[uploadFile] : " + "String pathTarget = request.getSession().getServletContext().getRealPath('/') + System.getProperty('file.separator') + 'temp';");
    	String pathTarget = request.getSession().getServletContext().getRealPath("/") + System.getProperty("file.separator") + "temp" + System.getProperty("file.separator");
    	logger.info("[uploadFile] : pathTarget = " + pathTarget);
        File yourTempDirectory = new File(pathTarget);
        int yourMaxMemorySize  = 10000000;
        int yourMaxRequestSize = 10000000;

        // Crear una fabrica de items de archivo
        DiskFileItemFactory factory = new DiskFileItemFactory();
        logger.info("[uploadFile] : " + "DiskFileItemFactory factory = new DiskFileItemFactory();");
        factory.setSizeThreshold(yourMaxMemorySize);
        factory.setRepository(yourTempDirectory);
        logger.info("[uploadFile] : " + "factory.setRepository(yourTempDirectory);");

        // Crear un nuevo manejador de UPLOAD de archivo
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(yourMaxRequestSize);
        logger.info("[uploadFile] : " + "upload.setSizeMax(yourMaxRequestSize);");

        // Guardar los Archivos
        FileItemIterator fii = upload.getItemIterator(request);
        logger.info("[uploadFile] : " + "FileItemIterator fii = upload.getItemIterator(request);");
        
        while (fii.hasNext()) {
            FileItemStream item = fii.next();

            if (item.isFormField()) {
                //processFormField(item);
            } else {
                File file = null;
                InputStream is = null;
                FileOutputStream os = null;
                int size = 0;
                
                try {
                	
    				fileName = item.getName();
                    logger.info("[uploadFile] : while-fileName=" + fileName);
    			    fileExt = fileName.substring(fileName.lastIndexOf(".")+1);
                    logger.info("[uploadFile] : while-fileExt=" + fileExt);
    			    fileName = fileName.substring(fileName.lastIndexOf("\\")+1);  
                    logger.info("[uploadFile] : while-fileName=" + fileName);                  

                    if (permisos[0].equalsIgnoreCase("1")){
                    	/*TODO: Cambio de descarga de archivo
                    	 * RGARCIA - 27/04/2012
                    	 * JVELA - 04/05/2012
                    	 * */
                    	//file = File.createTempFile(fileName, "", yourTempDirectory);
                    	String nameFilePartial =  fileName.substring(0,fileName.indexOf("."));
	                    	nameFilePartial = nameFilePartial + System.currentTimeMillis();
	                    	nameFilePartial = nameFilePartial + "." + fileExt;
	                    	
                    	  String server = resources.getString(Constants.SERVIDOR);
                	      if (server.equals("WebSphere")){
	  	                    	file = new File(nameFilePartial);
                	      } else {
  	                    		file = new File(yourTempDirectory+"\\"+nameFilePartial);                	    	  
                	      }
                    }else{
                    	file = new File(pathTarget + fileName);
                    }
                    logger.info("[uploadFile] : while-vcnombrefile=" + fileName);
                    
                    // Guardar el archivo en el Servidor
                    byte[] b = new byte[4096];
                    int len;
                    is = item.openStream();
                    os = new FileOutputStream(file);
                    len = is.read(b);
                    logger.info("[uploadFile] : while-len=" + len);
                    while (len > 0) {
                        size += len;
                        os.write(b, 0, len);
                        len = is.read(b);
                    }

                } catch (Exception ex) {
                	file.delete();
                    JSONObject jo = new JSONObject();
                    jo.put("success", false);
                    jo.put("message", "Error al guardar el archivo.");
            		logger.error("[uploadFile] : " + ex.getMessage());
                    this.escribirTextoSalida(response, jo.toString());
                } finally {
                    if (os != null) {
                        os.flush();
                        os.close();
                    }
                    if (is != null)
                        is.close();
                }

                // Generar el registro en la Base de Datos
                //fileName = (new File(item.getName())).getName();
                logger.info("[uploadFile] : while-fileName=" + fileName);

                // Generar la Sentencia
                String idarchivo="";
                try {
                    FileInputStream fis;
                    fis = new FileInputStream(file);
                    byte[] zipped = new byte[(int)file.length()];
                    fis.read(zipped);

                    
                    if (permisos[1].equalsIgnoreCase("1")){
	                	MntArchivoblob param = new MntArchivoblob();  
	                	param.setArbytes(zipped);
	                	logger.info("[uploadFile] : while-zipped=" + zipped.toString());
	                	param.setNomarchivo(fileName);
	                	logger.info("[uploadFile] : while-fileName=" + fileName);
	                	param.setNomarchivo2(file.getName());
	                	logger.info("[uploadFile] : while-file.getName()=" + file.getName());
	                	param.setExtension(fileExt);
	                	logger.info("[uploadFile] : while-ext=" + fileExt);
	                	param.setBytes("" + size);
	                	logger.info("[uploadFile] : while-size=" + size);
	                	param.setDescripcion(request.getParameter("descripcion"));
	                	utilService.mntArchivoblobGes(param);
	                    
	                	idarchivo = "" + param.getIdearchivoblob();
                    }
                    
                } catch (Exception ex) {
                    file.delete();
                    JSONObject jo = new JSONObject();
                    jo.put("success", false);
                    jo.put("message", ex.toString());
            		logger.error("[uploadFile] : " + ex.getMessage());
                    this.escribirTextoSalida(response, jo.toString());
                } finally {
                    is.close();
                }

                // Renombrar el archivo
                
                try {
                    file.renameTo(new File(pathTarget + fileName));
            		logger.error("[uploadFile] : File=" + pathTarget + fileName);
                } catch (Exception ex) {
                    file.delete();
                    JSONObject jo = new JSONObject();
                    jo.put("success", false);
                    jo.put("message", "El archivo ya existe.");
            		logger.error("[uploadFile] : " + ex.getMessage());
                    this.escribirTextoSalida(response, jo.toString());
                }
				
                JSONObject jo = new JSONObject();
                jo.put("success", true);
                jo.put("idarchivo", idarchivo);
                logger.info("[uploadFile JSONObject idarchivo] : " + idarchivo);
                jo.put("nombrearchivo", fileName);
                logger.info("[uploadFile JSONObject nombrearchivo] : " + fileName);
                jo.put("descripcion", descripcion);
                logger.info("[uploadFile JSONObject descripcion] : " + descripcion);
                //jo.put("nombreserv", file.getName());
                jo.put("nombreserv", file.getName());
                logger.info("[uploadFile JSONObject nombreserv] : " + file.getName());
                jo.put("tamano", "" + size);
                logger.info("[uploadFile JSONObject tamano] : " + size);
                jo.put("extension", fileExt);
                logger.info("[uploadFile JSONObject extension] : " + fileExt);

                logger.info("[uploadFile JSONObject] : " + jo.toString());
                this.escribirTextoSalida(response, jo.toString());

            }
        }

    } catch (Exception ex) {
		logger.error("[uploadFile] : " + ex.getMessage());
        ex.printStackTrace();
    }

    return new ModelAndView();
  }
	/**
	 * Metodo que permite obtener los datos para casilla genérica para envio de mail
	 * @author jcasianoc
	 * @param request 
	 * @param response 
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView obtDatosCasillaMail(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
	    
	    try {
	    	//PENDIENTE-hacer una validación de la session
	    	ListaCorreoGenerico constantesCasilla=new ListaCorreoGenerico();
  			utilService.obtDatosCasillaMail(constantesCasilla);
  			String userAuth="";
			String passAuth="";
			String host="";
			
			List<ListaCorreoGenericoCursor> lstConstanteCasilla;
  			ListaCorreoGenericoCursor beanCasilla;
  			lstConstanteCasilla = (List<ListaCorreoGenericoCursor>)constantesCasilla.getCursor();
  			for (int i = 0; i < lstConstanteCasilla.size(); i++){
  				beanCasilla = lstConstanteCasilla.get(i);
  				userAuth=beanCasilla.getCorreo();
				passAuth=beanCasilla.getPassword();
				host=beanCasilla.getHost();
  			}
			this.escribirTextoSalida(response, userAuth+"<->"+passAuth+"<->"+host);	      

	    } catch (RuntimeException e) {
	      this.escribirTextoSalida(response, commons.mapper.Utils.getMessageORA(e.getMessage()));
	      //e.printStackTrace(); //SAS20151119
	    } catch (Exception e) {
	      //e.printStackTrace(); //SAS20151119
	    }
		
		return new ModelAndView();
	}

	/**
	 * Petición de Inicialización de TRX para la pasarela de pago
	 * @author jcasianoc
	 * @param request 
	 * @param response 
	 * @return 
	 */
	public ModelAndView obtIniciaTrx(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		
	    try {	    
	    	

		    String[] resultado = null;
		    String strResultado="";
  			
	    	Procesador procesa=new Procesador();
	    	strResultado=procesa.obtUrlVisa(request);
	    	resultado=strResultado.split("<->");
			
	    	//CODERROR<->TOKEN<->NUMORDER<->URL<->MensajeAdicional<->tipoapp
	    	request.getSession().setAttribute("TOKEN_TRX",resultado[1]);
	    	request.getSession().setAttribute("TIPO_APP",resultado[5]);
	    	if("00".equals(resultado[0]))
	    	{
	    		strResultado = "{"
                //+ "idetransaccion" + ":\"" + mntGrabarTransaccion.getIdetransaccion().toString() + "\"" +  "," 
                + "direccionurl" + ":\"" + resultado[3] + "\"" +  ","
                //+ "indresultado" + ":\"" + mntGrabarTransaccion.getIndresultado().toString() + "\"" +  ","
                + "numorden" + ":\"" + resultado[2] + "\""  +  ","
               //+ "logservicio" + ":\"" + mntGrabarTransaccion.getLogservicio().toString() + "\""  +  ","
               // + "coderror" + ":\"" + mntGrabarTransaccion.getCoderror().toString() + "\""  +  "," 
                + "mensajeerror" + ":\"" + resultado[4]  + "\"" +
                "}";
	    	}
	    	else
	    	{
	    		strResultado = "{logservicio" + ":\"" + resultado[4] + "\"" +  ","  
				  + "direccionurl" + ":\"" + ""+ "\""  +
				 "}";	
	    	}
	    	
	    	
			this.escribirTextoSalida(response, strResultado);	      

	    } catch (RuntimeException e) {
	      this.escribirTextoSalida(response, commons.mapper.Utils.getMessageORA(e.getMessage()));
	      //e.printStackTrace(); //SAS20151119
	    } catch (Exception e) {
	      //e.printStackTrace(); //SAS20151119
	    }
		
		return new ModelAndView();
	}
	
	/**
	 * Petición de Inicialización de TRX para la pasarela de pago con POS
	 * @author scastanedm
	 * @param request 
	 * @param response 
	 * @return 
	 */
	public ModelAndView obtIniciaTrxPos(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		
		logger.info("INICIO [obtIniciaTrxPos]");
		
	    try {	    
	    	
	    	Map<String, String> args = new LinkedHashMap<String, String>();

		    String strResultado="";
	    	/*
			args.put("ACTION", 				"auth"); 					//CONSTANTE
			args.put("TXN_REFERENCE", 		"123456789");				//SECUENCIA DE TRANSACCION
			args.put("TXN_URL", 			"http://api.vnmpos.com/simulador-comercio/response.jsp");	//CONSTANTE
			args.put("TXN_CONCEPT", 		"Operacion de prueba de prueba");							//Cotización IDEACUERDO //SDI0004113385
			args.put("TXN_AMOUNT", 			"1.00");													//Prima Bruta
			args.put("CLI_NAME", 			"John Doe");												//Nombre del cliente. preguntar si es mas de uno
			args.put("CLI_DOCUMENT", 		"12346578");												//Doc del cliente. preguntar si es mas de uno
			args.put("CLI_MAIL", 			"some@one.com");											//Correo del cliente. preguntar si es mas de uno
			args.put("REV_AUTH_REFERENCE", 	"");
			args.put("REV_TXN_UUID", 		"");

			//CONSTANTE para rimacvisanetmpos://?
			//CONSTANTE para 4MXex7A2SUqPKfrEyiH5EZaiSVn5YomBrjFFGPWpgwBpJtsM
			String url = "rimacvisanetmpos://?" + new SecureParameters().lockParameters(args, "4MXex7A2SUqPKfrEyiH5EZaiSVn5YomBrjFFGPWpgwBpJtsM");
			*/

			logger.info("[obtIniciaTrxPos] - inicio graba transaccion");
			
	    	BTransaccion param = new BTransaccion();
						
			param.setIdptipotarjeta(request.getParameter("idptipotarjeta"));
			param.setIdetipomoneda(request.getParameter("moneda"));	
			
			//ini SCASTANEDM 2014.12.31
			BigDecimal monto = new BigDecimal(request.getParameter("montocuota"));
			
			if (request.getParameter("moneda").equals("USD"))
			{
				Calendar ahoraCal = Calendar.getInstance();
		    	SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy"); 		    	
		    	String formatted = format1.format(ahoraCal.getTime());
		    	
		    	TipoCambio tipocambio = new TipoCambio();	

				tipocambio.setMonedaini("USD");
				tipocambio.setMonedafin("SOL");
				tipocambio.setTipotasa("I");//JLINARESP 03/09/2015 (Se cambia D por I)
				tipocambio.setFechacambio(formatted);

				utilService.obtTipoCambio(tipocambio);

				System.out.println("TipoCambio");
			    System.out.println(tipocambio.getTipocambio());
				System.out.println("Respuesta");
			    System.out.println(tipocambio.getRespuesta());
			    
				if ( tipocambio.getRespuesta() == 1)
				{
		    		strResultado = "{"
		    	            + "respuesta" + ":\"" + "1" + "\"" +
		    	            "}";

					this.escribirTextoSalida(response, strResultado);

				    System.out.println("FIN [obtIniciaTrxPos] - no se encontro tipo de cambio");
				    logger.info("FIN [obtIniciaTrxPos] - no se encontro tipo de cambio");
					return new ModelAndView();	
				}

				BigDecimal tipocambiobig = new BigDecimal(tipocambio.getTipocambio());
				param.setMonto(monto);		//SCASTANEDM 2015.02.12
				monto = monto.multiply(tipocambiobig).setScale(2, BigDecimal.ROUND_HALF_UP);
				//param.setMonto(monto); 	//SCASTANEDM 2015.02.12
				param.setObstransaccion("Enviado a MPOS: S/. " + monto + " (TC: " + tipocambio.getTipocambio() + " )");	//SCASTANEDM 2015.02.12 
			}else
			{
				monto = new BigDecimal(request.getParameter("montocuota")).setScale(2, BigDecimal.ROUND_HALF_UP);
				param.setMonto(monto);
				param.setObstransaccion("Enviado a MPOS: S/. " + monto );												//SCASTANEDM 2015.02.12
			}
			//fin SCASTANEDM 2014.12.31
			
			param.setDireccionip(request.getRemoteAddr());
			param.setCoderror("0");
			param.setEstadoproceso(com.sas.trx.util.Constants.ESTADO_PASARELA_VISA_INICIO);
			param.setUsuario(CommonsHelper.getUsuarioLogueado(request));
			param.setTipo(request.getParameter("tipo"));
			param.setMedio(request.getParameter("medio"));
			
			param.setIdecampo(Integer.parseInt( request.getParameter("idecampo") ) );
			param.setIderegistrobase(Integer.parseInt( request.getParameter("ideregistrobase") ) );


			System.out.println("inicio mntTransaccion");
			logger.info("[obtIniciaTrxPos] - mntTransaccion");
			transaccionService.mntTransaccion(param);
			
			System.out.println("fin mntTransaccion");
			logger.info("[obtIniciaTrxPos] - inicio graba transaccion");
			
	    	args.put("ACTION", 				request.getParameter("action")); 					//CONSTANTE
			args.put("TXN_REFERENCE", 		param.getNumorden());								//SECUENCIA DE TRANSACCION - NUMORDEN
			args.put("TXN_URL", 			request.getParameter("urlrespuesta"));				//CONSTANTE
			args.put("TXN_CONCEPT", 		"Cotizacion " + request.getParameter("ideacuerdo"));//Cotización IDEACUERDO //SDI0004113385
			args.put("TXN_AMOUNT", 			monto.toString());				//Prima Bruta
			args.put("CLI_NAME", 			request.getParameter("nombre"));					//Nombre del cliente. preguntar si es mas de uno
			args.put("CLI_DOCUMENT", 		request.getParameter("documento"));					//Doc del cliente. preguntar si es mas de uno
			args.put("CLI_MAIL", 			request.getParameter("correo"));					//Correo del cliente. preguntar si es mas de uno
			args.put("REV_AUTH_REFERENCE", 	"");
			args.put("REV_TXN_UUID", 		"");
			
			/*ini borrar*/
			System.out.println("ACTION" + request.getParameter("action")); 					
			System.out.println("TXN_REFERENCE" + 		param.getNumorden());								//SECUENCIA DE TRANSACCION - NUMORDEN
			System.out.println("TXN_URL" + 			request.getParameter("urlrespuesta"));				//CONSTANTE
			System.out.println("TXN_CONCEPT" + 		"Cotizacion " + request.getParameter("ideacuerdo"));//Cotización IDEACUERDO //SDI0004113385
			System.out.println("TXN_AMOUNT" + 			monto.toString());				//Prima Bruta
			System.out.println("CLI_NAME" + 			request.getParameter("nombre"));					//Nombre del cliente. preguntar si es mas de uno
			System.out.println("CLI_DOCUMENT" + 		request.getParameter("documento"));					//Doc del cliente. preguntar si es mas de uno
			System.out.println("CLI_MAIL" + 			request.getParameter("correo"));					//Correo del cliente. preguntar si es mas de uno
			
			/*fin borrar*/

			System.out.println("inicio SecureParameters");
			logger.info("[obtIniciaTrxPos] - inicio encrypta parametros");
			
			String url = request.getParameter("urlapp") + new SecureParameters().lockParameters(args, request.getParameter("key"));

			System.out.println("fin SecureParameters");
			logger.info("[obtIniciaTrxPos] - fin encrypta parametros");
			
			logger.info("[obtIniciaTrxPos] - url generada: " + url);
			
  			
    		strResultado = "{"
            + "direccionurl" + ":\"" + url + "\"" +  ","
            + "respuesta" + ":\"" + "0" + "\"" +
            "}";

			System.out.println("inicio mntTransaccion");
			logger.info("[obtIniciaTrxPos] - mntTransaccion");
			param.setDireccionurl(url);
			param.setEstadoproceso(com.sas.trx.util.Constants.ESTADO_PASARELA_VISA_PROCESO);
			param.setTokentrx(MD5.parse(param.getNumorden()+com.sas.trx.util.Constants.ENCRIPT_CADENA));
			
			transaccionService.mntTransaccion(param);
			
			System.out.println("fin mntTransaccion");
			logger.info("[obtIniciaTrxPos] - inicio graba transaccion");
			
		    request.getSession().setAttribute("TOKEN_TRX",param.getTokentrx());
		    request.getSession().setAttribute("NUM_ORDEN",param.getNumorden());
		    request.getSession().setAttribute("OBS_TRANSACCION",param.getObstransaccion());	//SCASTANEDM 2015.02.12
		    
			this.escribirTextoSalida(response, strResultado);	      

	    } catch (RuntimeException e) {
	      this.escribirTextoSalida(response, commons.mapper.Utils.getMessageORA(e.getMessage()));
	      //e.printStackTrace(); //SAS20151119
	    } catch (Exception e) {
	      //e.printStackTrace(); //SAS20151119
	    }
	    logger.info("FIN [obtIniciaTrxPos]");
		return new ModelAndView();
	}
	
	/**
	 * Petición de Finalización de TRX para la pasarela de pago
	 * @author jcasianoc
	 * @param request 
	 * @param response 
	 * @return 
	 */	
	public ModelAndView obtFinalizaTrx(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
	    java.util.Date tiempoInicio = new Date();
	    try{
	    	
		    String tokenTrx="";
		    String result="";
		    tokenTrx=request.getSession().getAttribute("TOKEN_TRX").toString();
		    
		    Procesador procesa=new Procesador();
		    //visa session cookie
		    //String token = 	SeguridadUtils.getCookieValue(request.getCookies(),com.sas.trx.util.Constants.TOKEN_VISA,"VACIO");
	    	//String comercio = 	SeguridadUtils.getCookieValue(request.getCookies(),com.sas.trx.util.Constants.COMMERCE,"VACIO");
		    
		    
//	    	result=procesa.obtFinalizaTrx(token,comercio,tokenTrx,request);
	     
		    this.escribirTextoSalida(response, result);
		      logger.info(CommonsHelper.tiempoEjecucion("mntIniciarTransaccion", tiempoInicio));

	    } catch (Exception e) {
	      this.escribirTextoSalida(response, commons.mapper.Utils.getErrorMessage(e));
	      logger.error("Exceptions mntIniciarTransaccion:" + e);
	      //e.printStackTrace(); //SAS20151119
	    }

		return new ModelAndView();
	}
	
	/**
	 * Petición de Finalización de TRX para la pasarela de pago via POS
	 * @author scastanedm
	 * @param request 
	 * @param response 
	 * @return 
	 */	
	public ModelAndView obtFinalizaTrxPos(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
	    java.util.Date tiempoInicio = new Date();

		String indresultado = new String();	 
		String logservicio = new String();	
		String coderror = new String();	
		String mensajeerror = new String();	  
		String codoperacion = new String();	  
		String mtoautorizado = new String();
		String montoctaaproxcadena = new String();
		String obsTransaccion = new String(); //SCASTANEDM 2015.02.12

		indresultado=" ";
    	logservicio=" ";
		coderror="1";
		mensajeerror=" ";
		codoperacion=" ";
		mtoautorizado=" ";
		montoctaaproxcadena=" ";
		
	    try{
/*
			param.setEstadoproceso(com.sas.trx.util.Constants.ESTADO_PASARELA_VISA_TOKEN);
			transaccionService.mntTransaccion(param);
*/	
		    String tokenTrx="";
            String numOrden="";
		    String result="";
  			tokenTrx=request.getSession().getAttribute("TOKEN_TRX").toString();
		    numOrden=request.getSession().getAttribute("NUM_ORDEN").toString();
		    obsTransaccion=request.getSession().getAttribute("OBS_TRANSACCION").toString();	//SCASTANEDM 2015.02.12
		    
		    //visa session cookie
//		    String queryString = 	SeguridadUtils.getCookieValue(request.getCookies(),com.sas.trx.util.Constants.QUERY_STRING,"VACIO");
		    		    
  			BTransaccion param = new BTransaccion();
  			
            try {
    		    System.out.println("inicio desencriptar 5.0");
    		    Map<String, String> params  = null;
//                Map<String, String> params = new SecureParameters().unlockParameters(queryString,request.getParameter("visakey"));

    		    if (params.containsKey("hostMessage"))
    		    {
    		    	param.setMensajeerror(params.get("hostMessage").replaceAll("\n", " "));
        			param.setMENSAJE(params.get("hostMessage").replaceAll("\n", " "));
        			mensajeerror=params.get("hostMessage");
    		    }
    		    else
    		    {
        			param.setMENSAJE(" ");
    		    }
    		    
    		    param.setObstransaccion(obsTransaccion); //SCASTANEDM 2015.02.12
    		    
    		    if (params.containsKey("statusType"))
    		    {
      		      	if (params.get("statusType").equals("AUTHORIZED"))
      		      	{	
      		      	System.out.println("AUTHORIZED");
      		      		param.setIndresultado("0");
      	    		    param.setEstadoproceso(com.sas.trx.util.Constants.ESTADO_PASARELA_VISA_FIN);
      	    			param.setSTSTRANSACCION(0);
      	    		    indresultado = "0";
      		      	}
      		      	else
      		      	{
      		      		System.out.println("DENIED");
      		      		param.setIndresultado("1");
      		      		param.setNumorden(numOrden);
      					param.setEstadoproceso(com.sas.trx.util.Constants.ESTADO_PASARELA_VISA_ERROR);
      	    			param.setSTSTRANSACCION(1);
      	    		    indresultado = "1";
      		      	}
    		    }

    		    if (params.containsKey("authorizationCode"))
    		    {
      		      	System.out.println("authorizationCode");
    		    	param.setCodautorizacion(params.get("authorizationCode"));
    				codoperacion=params.get("authorizationCode");
    		    }
    		    
    		    if (params.containsKey("transactionAmount"))
    		    {
      		      	System.out.println("transactionAmount");
      		      	Double division;  
        		    division = new Double( params.get("transactionAmount"));
        		    //division = division /100;
    		    	//param.setMontofinal( Double.toString(division));
    		    	
        			mtoautorizado=Double.toString(division);
        			param.setIMPORTE(new BigDecimal(mtoautorizado));
    		    }

    		    if (params.containsKey("transactionLocalDate"))
    		    {
      		      	System.out.println("transactionLocalDate");
    		    	param.setFechatrans(params.get("transactionLocalDate"));
    		    	param.setFECTRANSACCION(params.get("transactionLocalDate"));
    		    }
    		    /*
    		    if (params.containsKey("transactionLocalDate"))
    		    {
      		      	System.out.println("transactionLocalDate");
    		    	Calendar ahoraCal = Calendar.getInstance();
    		    	
    		    	ahoraCal.set( ahoraCal.get(Calendar.YEAR)  ,Integer.parseInt(params.get("transactionLocalDate").substring(0, 2))-1,Integer.parseInt(params.get("transactionLocalDate").substring(2, 4)),0,0,0);
    		        		    	
    		    	SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy"); 
    		    	
    		    	String formatted = format1.format(ahoraCal.getTime());

    		    	param.setFechatrans(formatted);

        			param.setFECTRANSACCION(formatted);
    		    }*/
    		    
    			param.setTokentrx(tokenTrx);

    			param.setCoderror("0");
    			param.setMesvenctarjeta(0);
    			param.setAniovenctarjeta(0);
    			
    			//ini SCASTANEDM 2014.12.18
    		    if (params.containsKey("bank"))
    		    {
      		      	System.out.println("banco");
    		    	param.setNOMBANCO(params.get("bank"));
    		    }

    		    if (params.containsKey("T.merchantName"))
    		    {
      		      	System.out.println("merchantName");
    		    	param.setNOMCOMERCIO(params.get("T.merchantName"));
    		    }
    		    
    		    //ini SCASTANEDM v3.1
    		    if (params.containsKey("T.merchantLocation"))
    		    {
      		      	System.out.println("T.merchantLocation");
    		    	param.setCIUDAD(params.get("T.merchantLocation"));
    		    }
    		    //fin SCASTANEDM v3.1

    		    if (params.containsKey("track2"))
    		    {
      		      	System.out.println("track2");
    		    	param.setNUMTARJETA(params.get("track2"));
    		    	param.setNumtarjeta(params.get("track2"));//SCASTANEDM 2015.02.24
    		    }

    		    if (params.containsKey("terminalId"))
    		    {
      		      	System.out.println("terminalId");
    		    	param.setNUMTERMINAL(params.get("terminalId"));
    		    }
    			
    		    if (params.containsKey("T.merchantId"))
    		    {
      		      	System.out.println("merchantId");
    		    	param.setCODCOMERCIO(params.get("T.merchantId"));
    		    }

  		      	System.out.println("PERU");
    			param.setPAIS("PERU");

    		    if (params.containsKey("batchNumber"))
    		    {
      		      	System.out.println("batchNumber");
    		    	param.setLOTE(params.get("batchNumber"));
    		    }

    		    if (params.containsKey("ticketNumber"))
    		    {
      		      	System.out.println("ticketNumber");
    		    	param.setNUMREFVISA(params.get("ticketNumber"));
    		    }

    		    if (params.containsKey("authorizationCode"))
    		    {
      		      	System.out.println("authorizationCode");
    		    	param.setNUMAUTORIZACION(params.get("authorizationCode"));
    		    }

    		    if (params.containsKey("transactionLocalTime"))
    		    {
      		      	System.out.println("transactionLocalTime");
    		    	param.setHORATRANSACCION(params.get("transactionLocalTime"));
    		    }

    		    
    		    if (params.containsKey("T.currencyLabel"))
    		    {
      		      	System.out.println("currencyLabel");
    		    	param.setMONEDA(params.get("T.currencyLabel"));
    		    	if (params.get("T.currencyLabel").equals("S/."))	
    	    		param.setIDPMONEDA("SOL");
    		    }

    		    if (params.containsKey("voucherType"))
    		    {
      		      	System.out.println("voucherType");
    		    	if (params.get("voucherType").equals("DEBITO"))
    		    	param.setIDPTIPVOUCHER("D"); //CAMBIAR A D
    		    	else
    		    	param.setIDPTIPVOUCHER("C"); //CAMBIAR A C	
    		    }
    		    else
    		    {
    		    	param.setIDPTIPVOUCHER("A");
    		    }
    		    
    			
    		    if (params.containsKey("T.app"))
    		    {
      		      	System.out.println("app");
    		    	param.setLABELAPP(params.get("T.app"));
    		    }
    		    else
    		    {
    		    	param.setLABELAPP(" ");
    		    }

    		    if (params.containsKey("T.aid"))
    		    {
      		      	System.out.println("aid");
    		    	param.setAID(params.get("T.aid"));
    		    }
    		    else
    		    {
    		    	param.setAID(" ");
    		    }


    		    if (params.containsKey("T.sign"))
    		    {
      		      	System.out.println("sign");
    		    	param.setCT(params.get("T.sign"));
    		    }
    		    else
    		    {
    		    	param.setCT(" ");
    		    }


    		    if (params.containsKey("version"))
    		    {
      		      	System.out.println("version");
    		    	param.setVERSIONUTIL(params.get("version"));
    		    }

    		    if (params.containsKey("hostMessage"))
    		    {
      		      	System.out.println("hostMessage");
    		    	param.setMENSAJEHOST(params.get("hostMessage").replaceAll("\n", " "));
    		    }
    		    else
    		    {
    		    	param.setMENSAJEHOST(" ");
    		    }

    		    if (params.containsKey("prizeMessage"))
    		    {
      		      	System.out.println("prizeMessage");
    		    	param.setMENSAJEPREMIO(params.get("prizeMessage").replaceAll("\n", " "));
    		    }
    		    //fin SCASTANEDM 2014.12.18
    			
    		    //ini SCASTANEDM 2015.02.05
    		    if (params.containsKey("voucherPaymentsNumber"))
    		    {
      		      	System.out.println("voucherPaymentsNumber");
      		      	System.out.println(params.get("voucherPaymentsNumber"));
    		    	
    		    	Integer numcuotas;
    		    	numcuotas = new Integer(params.get("voucherPaymentsNumber"));

    		    	param.setNumcuotas(numcuotas);
    		    }

    		    if (params.containsKey("voucherPaymentsAmount"))
    		    {
      		      	System.out.println("voucherPaymentsAmount");
      		      	System.out.println(params.get("voucherPaymentsAmount"));

      		      	Double montoctaaprox;  
      		        montoctaaprox = new Double( params.get("voucherPaymentsAmount"));
      		      	  		    	
      		        montoctaaproxcadena=Double.toString(montoctaaprox);
      		      	
      		      	param.setMontoctaaprox(new BigDecimal(montoctaaproxcadena));
      		      
    		    }

    		    if (params.containsKey("transactionId"))
    		    {
      		      	System.out.println("transactionId");

      		      	System.out.println(params.get("transactionId"));
      		      	param.setTransactionid(params.get("transactionId"));
    		    }
    		    
//    		    param.setUrlrespuesta(queryString);
    		    
    		    //fin SCASTANEDM 2015.02.05
    		    
    			if (params.containsKey("statusType"))
    		    {
      		      	if (params.get("statusType").equals("AUTHORIZED"))
      		      	{
      		      		transaccionService.mntTransaccionVoucher(param);
      		      	}
      		      	else
      		      	{
      		      		transaccionService.mntTransaccion(param);
      		      	}
      		    }
    			
		    } catch (Exception ex) {
		    	System.out.println("getMessage");
			    System.out.println(ex.getMessage());
			    System.out.println("tostring");
			    System.out.println(ex.toString());
			    System.out.println("ERROR");
		    }
            
			result = "{"
                     + "indresultado" + ":\"" + indresultado + "\"" +  ","		                  
                     + "logservicio" + ":\"" + logservicio + "\""  +  ","
                     + "coderror" + ":\"" + coderror + "\""  +  "," 
                     + "mensajeerror" + ":\"" + mensajeerror.replaceAll("\n", " ")  + "\"" +  "," 
                     + "codoperacion" + ":\"" + codoperacion + "\"" +  "," 
                     + "mtoautorizado" + ":\"" + mtoautorizado + "\"" 
                     + "}";

		    this.escribirTextoSalida(response, result);
		      logger.info(CommonsHelper.tiempoEjecucion("mntIniciarTransaccion", tiempoInicio));

	    } catch (Exception e) {
	      this.escribirTextoSalida(response, commons.mapper.Utils.getErrorMessage(e));
	      logger.error("Exceptions mntIniciarTransaccion:" + e);
	      //e.printStackTrace(); //SAS20151119
	    }

		return new ModelAndView();
	}
	
	/**
	 * Petición TOKEN de la TRX
	 * @author jcasianoc
	 * @param request 
	 * @param response 
	 * @return 
	 */
	public ModelAndView obtenerTokenTrx(HttpServletRequest request, HttpServletResponse response) {
	    response.setContentType("text/html; charset=UTF-8");
	    java.util.Date tiempoInicio = new Date();
	    try{
	    	

		    String result="";
		    result=request.getSession().getAttribute("TOKEN_TRX").toString();
		    this.escribirTextoSalida(response, result);
		      logger.info(CommonsHelper.tiempoEjecucion("mntIniciarTransaccion", tiempoInicio));

	    } catch (Exception e) {
	      this.escribirTextoSalida(response, commons.mapper.Utils.getErrorMessage(e));
	      logger.error("Exceptions mntIniciarTransaccion:" + e);
	      //e.printStackTrace(); //SAS20151119
	    }
	    
	    return new ModelAndView();
	  }
	
	public ModelAndView obtDatosECommerce(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
	    java.util.Date tiempoInicio = new Date();
	    try{
	    	
		    String result="";
		    DataECommerce dataECommerce = null;
		    dataECommerce = new DataECommerce(request.getParameterMap());
		    Procesador procesa=new Procesador();
	    
	    	result=procesa.obtDatosECommerce(dataECommerce);
	     
		    this.escribirTextoSalida(response, result);
		      logger.info(CommonsHelper.tiempoEjecucion("obtDatosECommerce", tiempoInicio));

	    } catch (Exception e) {
	      this.escribirTextoSalida(response, commons.mapper.Utils.getErrorMessage(e));
	      logger.error("Exceptions mntIniciarTransaccion:" + e);
	      //e.printStackTrace(); //SAS20151119
	    }

		return new ModelAndView();
	}
	
	public ModelAndView obtUrlECommerce(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		
	    try {	    
	    	

		    String[] resultado = null;
		    String strResultado="";
		    DatosPasarelaEC datosPasarela;
  			
	    	Procesador procesa=new Procesador();
	    	datosPasarela = new DatosPasarelaEC(request.getParameterMap());
	    	request.getSession().setAttribute("TOKEN_IDECOTIZACION",datosPasarela.getCotizacion());
	    	
	    	
//	    	strResultado=procesa.obtUrlVisa(request);
//	    	resultado=strResultado.split("<->");
//			
//	    	//CODERROR<->TOKEN<->NUMORDER<->URL<->MensajeAdicional<->tipoapp
//	    	request.getSession().setAttribute("TOKEN_TRX",resultado[1]);
//	    	request.getSession().setAttribute("TIPO_APP",resultado[5]);
//	    	if("00".equals(resultado[0]))
//	    	{
//	    		strResultado = "{"
//                //+ "idetransaccion" + ":\"" + mntGrabarTransaccion.getIdetransaccion().toString() + "\"" +  "," 
//                + "direccionurl" + ":\"" + resultado[3] + "\"" +  ","
//                //+ "indresultado" + ":\"" + mntGrabarTransaccion.getIndresultado().toString() + "\"" +  ","
//                + "numorden" + ":\"" + resultado[2] + "\""  +  ","
//               //+ "logservicio" + ":\"" + mntGrabarTransaccion.getLogservicio().toString() + "\""  +  ","
//               // + "coderror" + ":\"" + mntGrabarTransaccion.getCoderror().toString() + "\""  +  "," 
//                + "mensajeerror" + ":\"" + resultado[4]  + "\"" +
//                "}";
//	    	}
//	    	else
//	    	{
//	    		strResultado = "{logservicio" + ":\"" + resultado[4] + "\"" +  ","  
//				  + "direccionurl" + ":\"" + ""+ "\""  +
//				 "}";	
//	    	}
	    	
	    	
			this.escribirTextoSalida(response, strResultado);	      

	    } catch (RuntimeException e) {
	      this.escribirTextoSalida(response, commons.mapper.Utils.getMessageORA(e.getMessage()));
	      //e.printStackTrace(); //SAS20151119
	    } catch (Exception e) {
	      //e.printStackTrace(); //SAS20151119
	    }
		
		return new ModelAndView();
	}
	
////////////////////*CAMBIOS PASARELA 2*///////////////////////
	/**
	* Petición de Inicialización de TRX para la pasarela de pago
	* @author jcasianoc
	* @param request 
	* @param response 
	* @return 
	*/

	private void setupCookies(HttpServletResponse response,String usuario){
        Cookie usrCookie = new Cookie(Constants.COOKIE_USUARIO_KAZHAM, usuario);
        usrCookie.setPath(Constants.WEBAPP_SAS);
        response.addCookie(usrCookie);          
	}
	
	public String getValorConstanteUnica(String ideconstante)
	{
		LstConstante cst=new LstConstante();
		cst.setIdeconstante(ideconstante);
		utilService.obtConstante(cst);
		LstConstanteCursor primerCursor = (LstConstanteCursor)cst.getCursor().get(0);
		return primerCursor.getValor();
	}


 

	public ModelAndView obtIniciaTrxC(HttpServletRequest request, HttpServletResponse response) {
	response.setContentType("text/html; charset=UTF-8");
	
	    try {	    
	
	    	//
	    	Usuario user;
	    	user =new Usuario(request.getParameterMap());
	    	user = utilService.obtUsuario(user);
	    	//
	    	
	    	//String usuario=this.getValorConstanteUnica(Constants.C_PASARELA2_USER);
	    	String usuario=user.getIdeusuario();
//	    	ObtUsuarioCursor a= new ObtUsuarioCursor();
//	    	a.setIdusuario(usuario);
//	    	request.getSession().setAttribute("USUARIO", a);
	    	setupCookies(response,usuario);
	    	
			String[] resultado = null;
			String strResultado="";
			
			//INI JLAM [NWR-PVRD][RF-02] - 20/12/2014		
			String tarjetapvb="";	
			String exclusivo="";
			exclusivo = request.getParameter("exclusivo");
			if (exclusivo==null || exclusivo=="") {exclusivo="";}
			request.getSession().setAttribute("EXCLUSIVO",exclusivo);		
			//FIN JLAM [NWR-PVRD][RF-02] - 20/12/2014
			
			if(request.getParameter("idptipotarjeta").compareTo(getValorConstanteUnica(Constants.TIP_PASARELA_VISA))==0)
			{	
			/*VISA*/
			ProcesadorVisaCliente procesa=new ProcesadorVisaCliente();
			strResultado=procesa.obtUrlVisa(request);
			resultado=strResultado.split("<->");
			
			//CODERROR<->TOKEN<->NUMORDER<->URL<->MensajeAdicional<->tipoapp<->eTicket
			request.getSession().setAttribute("TOKEN_TRX",resultado[1]);
			request.getSession().setAttribute("TIPO_APP",resultado[5]);
			if("00".equals(resultado[0]))
			{
			strResultado = "{"
			+ "\"direccionurl\"" + ":\""+ resultado[3] + "\"" +  ","
			+ "\"numorden\""+ ":\"" + resultado[2] + "\""  +  ","
			+ "\"mensajeerror\"" + ":\"" + resultado[4]  + "\""  +  ","
			+ "\"eticket\"" + ":\"" + resultado[6]  + "\"" +
			//INI - CCHANGP - 15/07/2014
			"," + "\"token\"" + ":\"" + resultado[1]  + "\"" +
			//FIN - CCHANGP - 15/07/2014
			"}";
			}
			else
			{
			strResultado = "{\"logservicio\""+ ":\"" + resultado[4] + "\"" +  ","  
			  + "\"direccionurl\"" + ":\"" + ""+ "\""  +","
			  + "\"eticket\"" + ":\"" + "null"  + "\"" +
			//INI - CCHANGP - 15/07/2014
			"," + "\"token\"" + ":\"" + resultado[1]  + "\"" +
			//FIN - CCHANGP - 15/07/2014
			 "}";	
	         }
			}
			if(request.getParameter("idptipotarjeta").compareTo(getValorConstanteUnica(Constants.TIP_PASARELA_MASTERCARD))==0)
			{
				//01/07/2014
				com.sas.trx.bean.BTransaccion bTipoApp = new com.sas.trx.bean.BTransaccion(request.getParameterMap());
				logger.info("Tipo APP para MC:"+bTipoApp.getTipoapp());
				request.getSession().setAttribute("TIPO_APP",bTipoApp.getTipoapp());
				//01/07/2014
			/*MASTERCARD*/
			ProcesadorMastercardCliente procesa=new ProcesadorMastercardCliente();
			strResultado=procesa.obtUrlMastercard(request);
			resultado=strResultado.split("<->");
			
			//CODERROR<->TOKEN<->NUMORDER<->URL<->MensajeAdicional<->tipoapp<->eTicket
			request.getSession().setAttribute("TOKEN_TRX",resultado[1]);
			//request.getSession().setAttribute("TIPO_APP",resultado[5]);
					
			if("00".equals(resultado[0]))
			{
			strResultado = "{"
			+ "\"codComercio\"" + ":\""+ resultado[3] + "\"" +  ","		
			+ "\"nuReferencia\"" + ":\""+ resultado[4] + "\"" +  ","	
			+ "\"monto\"" + ":\""+ resultado[5] + "\"" +  ","	
			+ "\"tipoMoneda\"" + ":\""+ resultado[6] + "\"" +  ","	
			+ "\"fecha\"" + ":\""+ resultado[7] + "\"" +  ","	
			+ "\"hora\"" + ":\""+ resultado[8] + "\"" +  ","	
			+ "\"autogenerado\"" + ":\""+ resultado[9] + "\"" +  ","	
			+ "\"codCliente\"" + ":\""+ resultado[10] + "\"" +  ","	
			+ "\"codPais\"" + ":\""+ resultado[11] + "\"" +  ","	
			+ "\"firma\"" + ":\""+ resultado[12] + "\"" +  ","	
					
			+ "\"direccionurl\"" + ":\""+ resultado[2] + "\"" +
			//INI - CCHANGP - 15/07/2014
			"," + "\"token\"" + ":\"" + resultado[1]  + "\"" +
			//FIN - CCHANGP - 15/07/2014
			/*"," 
			+ "\"logservicio\"" + ":\""+ resultado[13] + "\"" +*/
			
			"}";
			}
			else
			{
			strResultado = "{\"logservicio\""+ ":\"" + resultado[13] + "\"" +  ","  
			  + "\"direccionurl\"" + ":\"" + ""+ "\""  +
			//INI - CCHANGP - 15/07/2014
			"," + "\"token\"" + ":\"" + resultado[1]  + "\"" +
			//FIN - CCHANGP - 15/07/2014
			   
			 "}";	
	         }
			
			}
			
			//INI JLAM [NWR-PVRD][RF-02] - 20/12/2014		
			if (request.getParameter("idptipotarjeta").compareTo(getValorConstanteUnica(Constants.TIP_PASARELA_VISA_PVB_BBVA)) == 0) {
				/*Puntos Vida BBVA*/	
				ProcesadorVisaPuntosVidaBBVA procesa = new ProcesadorVisaPuntosVidaBBVA();
				tarjetapvb =request.getParameter("tarjetapvb");  
				
				String urlModo = getValorConstanteUnica(Constants.C_PASARELA2_MOD_PVB_BBVA); 
				String urlBaseProd = getValorConstanteUnica(Constants.C_PASARELA2_SERVER_RPTA);
				String urlBaseDesa = getValorConstanteUnica(Constants.C_PASARELA2_SERVER_RPTA_DESA);
				String formularioPVB = getValorConstanteUnica(Constants.C_PASARELA_SOAT_PVB_BBVA);
				String urlBase = "";
				String redireccion = "";
				
				if (urlModo==null || urlModo == "") { urlModo = Constants.C_PASARELA2_MOD_PVB_PROD;}
				
				if (urlModo.equalsIgnoreCase(Constants.C_PASARELA2_MOD_PVB_PROD)) {
					urlBase = urlBaseProd;					
				}else if (urlModo.equalsIgnoreCase(Constants.C_PASARELA2_MOD_PVB_DESA)) {
					urlBase = urlBaseDesa;
				}
				
				if (tarjetapvb.equals("") || tarjetapvb == null ) {
					redireccion = urlBase+formularioPVB+"?producto="+request.getParameter("producto").trim()+"&ideacuerdo="+request.getParameter("ideacuerdo").trim()+"&moneda="+request.getParameter("idetipomoneda").trim()+"&monto="+request.getParameter("monto").trim() ;
					strResultado = "00"+"<-> <-> <->"+redireccion+"<-> <-> <-> <-> <->";
					resultado = strResultado.split("<->");
				} else {
					strResultado = procesa.obtUrlVisa(request);
					resultado = strResultado.split("<->");
					request.getSession().setAttribute("TOKEN_TRX", resultado[1]);
					request.getSession().setAttribute("TIPO_APP", resultado[5]);
			    	request.getSession().setAttribute("TRAMA_TRX", resultado[7]);
			    	request.getSession().setAttribute("TRAMA_RES", resultado[8]);
				}
				logger.info("[UtilController] - obtIniciaTrxC : urlModo==>"+urlModo+", urlBase==>"+urlBase+", strResultado==>"+strResultado);
				System.out.println("[UtilController] - obtIniciaTrxC : urlModo==>"+urlModo+", urlBase==>"+urlBase+", strResultado==>"+strResultado);
				
				if ("00".equals(resultado[0])) {
					strResultado = "{" 
				            + "\"direccionurl\"" + ":\"" + resultado[3]+ "\"" + "," 
				            + "\"numorden\"" + ":\"" + resultado[2] + "\"" + ","
							+ "\"mensajeerror\"" + ":\"" + resultado[4] + "\""+ "," 
							+ "\"eticket\"" + ":\"" + resultado[6] + "\"" + "," 
							+ "\"token\"" + ":\"" + resultado[1] + "\""
							+ "}";
				} else {
					strResultado = "{"
				            + "\"logservicio\"" + ":\"" + resultado[4]+ "\"" + "," 
							+ "\"direccionurl\"" + ":\"" + ""+ "\"" + "," 
				            + "\"eticket\"" + ":\"" + "null" + "\"" + "," 
							+ "\"token\"" + ":\"" + resultado[1] + "\"" 
				            + "}";
				}
				logger.info("[UtilController] - obtIniciaTrxC : strResultado==>"+strResultado);
				System.out.println("[UtilController] - obtIniciaTrxC : strResultado==>"+strResultado);

			}
			//FIN JLAM [NWR-PVRD][RF-02] - 20/12/2014
			//INI - CCHANGP 10/03/2015 -- DINERSCLUB
			if(request.getParameter("idptipotarjeta").compareTo(getValorConstanteUnica(Constants.TIP_PASARELA_DINERSCLUB))==0)
			{
				com.sas.trx.bean.BTransaccion bTipoApp = new com.sas.trx.bean.BTransaccion(request.getParameterMap());
				logger.info("Tipo APP para DC:"+bTipoApp.getTipoapp());
				request.getSession().setAttribute("TIPO_APP",bTipoApp.getTipoapp());
			/*DINERSCLUB*/
			ProcesadorDinersClubCliente procesa=new ProcesadorDinersClubCliente();
			strResultado=procesa.obtUrlDinersClub(request);
			resultado=strResultado.split("<->");
			
			request.getSession().setAttribute("TOKEN_TRX",resultado[1]);
					
			if("00".equals(resultado[0]))
			{
			strResultado = "{"
			+ "\"codComercio\"" + ":\""+ resultado[3] + "\"" +  ","		
			+ "\"nuReferencia\"" + ":\""+ resultado[4] + "\"" +  ","	
			+ "\"monto\"" + ":\""+ resultado[5] + "\"" +  ","	
			+ "\"tipoMoneda\"" + ":\""+ resultado[6] + "\"" +  ","	
			+ "\"fecha\"" + ":\""+ resultado[7] + "\"" +  ","	
			+ "\"hora\"" + ":\""+ resultado[8] + "\"" +  ","	
			+ "\"autogenerado\"" + ":\""+ resultado[9] + "\"" +  ","	
			+ "\"codCliente\"" + ":\""+ resultado[10] + "\"" +  ","	
			+ "\"codPais\"" + ":\""+ resultado[11] + "\"" +  ","	
			+ "\"firma\"" + ":\""+ resultado[12] + "\"" +  ","	
					
			+ "\"direccionurl\"" + ":\""+ resultado[2] + "\"" +
			"," + "\"token\"" + ":\"" + resultado[1]  + "\"" +
			
			"}";
			}
			else
			{
			strResultado = "{\"logservicio\""+ ":\"" + resultado[13] + "\"" +  ","  
			  + "\"direccionurl\"" + ":\"" + ""+ "\""  +
			   
			 "}";	
	         }
			
			}
			//FIN - CCHANGP 10/03/2015

			this.escribirTextoSalida(response, strResultado);	      
			
			} catch (RuntimeException e) {
				logger.error("[UtilController] - obtIniciaTrxC Error RuntimeException: "+e);
				System.out.println("[UtilController] - obtIniciaTrxC Error RuntimeException: "+e);
				this.escribirTextoSalida(response, commons.mapper.Utils.getMessageORA(e.getMessage()));
				//e.printStackTrace(); //SAS20151119
			} catch (Exception e) {
				logger.error("[UtilController] - obtIniciaTrxC Error Exception: "+e.getMessage());
				System.out.println("[UtilController] - obtIniciaTrxC Error Exception: "+e.getMessage());				
				//e.printStackTrace(); //SAS20151119
			}
			return new ModelAndView();
	}

	
	public ModelAndView obtDatosPrimerPago(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		
	    try {	    
	    	
		    String strResultado="";
		    String idecotizacion;
		    String  moneda = "";
		    String  modpago = "";
		    String  monto = "";
		    String  tipotar = "";
		    String  indresultado = "";
		    
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	    	idecotizacion =  String.valueOf(request.getSession().getAttribute("IDEACUERDO_TRX"));
	    	
	    	moneda = String.valueOf(request.getSession().getAttribute("MONEDA_TRX"));
	    	modpago = String.valueOf(request.getSession().getAttribute("MODPAGO_TRX"));
	    	monto = String.valueOf(request.getSession().getAttribute("MONTO_TRX"));
	    	tipotar = String.valueOf(request.getSession().getAttribute("TIPOTAR_TRX"));
	    	indresultado = String.valueOf(request.getSession().getAttribute("RESULTADO_TRX"));

			Calendar cal = Calendar.getInstance();
			String today = dateFormat.format(cal.getTime());

	    	strResultado = "{"
	    			+ "\"ideacuerdo\"" + ":\""+ idecotizacion + "\"" +  ","
	    			+ "\"tipocambio\""+ ":\"" + "1" + "\""  +  ","
	    			+ "\"idpmonedacuota\""+ ":\"" + moneda + "\""  +  ","
	    			+ "\"indresultado\""+ ":\"" + indresultado + "\""  +  ","
	    			+ "\"marcatarjeta\"" + ":\"" + modpago  + "\""  +  ","
	    			+ "\"idpmarcatarjeta\"" + ":\"" + modpago  + "\""  +  ","
	    			+ "\"fecha\"" + ":\"" + today  + "\""  +  ","
	    			+ "\"idpformapago\"" + ":\"" + tipotar  + "\""  +  "," 
	    			+ "\"idpmonedavoucher\"" + ":\"" + moneda  + "\""  +  "," 
	    			+ "\"nrovoucher\"" + ":\"AAA" + ""  + "\""  +  "," 
	    			+ "\"montovoucher\"" + ":\"" + monto  + "\""  +  "," 
	    			+ "\"montocuota\"" + ":\"" + monto  + "\"" +
	    			"}";


			this.escribirTextoSalida(response, strResultado);	      

	    } catch (RuntimeException e) {
	      this.escribirTextoSalida(response, commons.mapper.Utils.getMessageORA(e.getMessage()));
	      //e.printStackTrace(); //SAS20151119
	    } catch (Exception e) {
	      //e.printStackTrace(); //SAS20151119
	    }
		
		return new ModelAndView();
	}
	
	public ModelAndView eliminarSessionPasarela(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		String strResultado ="{}";
        String TIPOTAR_TRX = "";	//JLAM [NWR-PVRD][RF-02] - 20/12/2014 	
	    try {	    
	    	request.getSession().setAttribute("TOKEN_TRX", null);
	    	request.getSession().setAttribute("IDEACUERDO_TRX", null);

	    	//INI JLAM [NWR-PVRD][RF-02] - 20/12/2014
	    	request.getSession().setAttribute("EXCLUSIVO",null);
	    	TIPOTAR_TRX = request.getSession().getAttribute("TIPOTAR_TRX").toString();
	    	if (TIPOTAR_TRX.equals(getValorConstanteUnica(Constants.TIP_PASARELA_VISA_PVB_BBVA))){
	    		request.getSession().setAttribute("TRAMA_TRX", null);
	    		request.getSession().setAttribute("TRAMA_RES", null);
	    	}
	    	//FIN JLAM [NWR-PVRD][RF-02] - 20/12/2014
	    	
			this.escribirTextoSalida(response, strResultado);	      

	    } catch (RuntimeException e) {
	      this.escribirTextoSalida(response, commons.mapper.Utils.getMessageORA(e.getMessage()));
	      //e.printStackTrace(); //SAS20151119
	    } catch (Exception e) {
	      //e.printStackTrace(); //SAS20151119
	    }
		
		return new ModelAndView();
	}
	 public ModelAndView obtIndicadorTrabajadorRimac(HttpServletRequest request, HttpServletResponse response) {
		    response.setContentType("text/html; charset=UTF-8");
		    java.util.Date tiempoInicio = new Date();
		    String indicadorTrabajadorRimac = "";
		    String usuario = "";
		    String respuesta = "";
		    
		    try {
		    	
//		    	respuesta=kazham.commons.CommonUtil.obtIndicadorTrabajadorRimac( request,  response);
		    	
		      this.escribirTextoSalida(response, respuesta);
		      logger.info(CommonsHelper.tiempoEjecucion("obtIndicadorTrabajadorRimac", tiempoInicio));
		    } catch (RuntimeException e) {
		      this.escribirTextoSalida(response, commons.mapper.Utils.getMessageORA(e.getMessage()));
		      //e.printStackTrace(); //SAS20151119

		    } catch (Exception e) {
		      //e.printStackTrace(); //SAS20151119

		    }
		    return new ModelAndView();
	}
	 
	//ini [SC54516] GDIAZJ 16/08/2016
	 @SuppressWarnings("unused")
	public String caracteresEspeciales(String nombre) {
		 
		 	String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
		    String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
		    for (int i=0; i<original.length(); i++) {
		    	nombre = nombre.replace(original.charAt(i), ascii.charAt(i));
		    }
		 
			nombre = nombre.replaceAll("/", "");
			nombre = nombre.replaceAll(":", "");
			nombre = nombre.replaceAll("<", "");
			nombre = nombre.replaceAll(">", "");
			nombre = nombre.replaceAll("|", "");
			nombre = nombre.replaceAll("\"", "");
			
			return nombre;
		}
	 //fin [SC54516] GDIAZJ 16/08/2016
}


