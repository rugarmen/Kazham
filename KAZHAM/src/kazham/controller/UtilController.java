package kazham.controller;

import org.apache.commons.fileupload.FileItemIterator;

import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
/*
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
*/
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

import kazham.bean.LstConstante;
import kazham.bean.LstConstanteCursor;
import kazham.bean.MntArchivoblob;
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
//  private TransaccionService transaccionService= TransaccionService.getInstance();
  
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
/*
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
*/
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
	                	//utilService.mntArchivoblobGes(param);
	                    
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
	
}


