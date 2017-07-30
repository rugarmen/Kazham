<%@ page import="kazham.commons.Constants" %>
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Kazham</title>
    <link rel="stylesheet" type="text/css" href="kazham/login/css/login.css" />
    <%
   
	String mensaje="", usuario = "", clave = "";
    String status="";
	 
%>
  </head>
  <body>
	<div id="divLoading" class="loading">
		<img src="sas/login/images/cargando.gif" class="loading_img">
	</div>
	  
    <center>
      <table class="table_content">
        <tr class="table_content_tr">
          <td colspan=3 class="linea_header"></td>
        </tr>
        <tr class="table_content_tr_central">
          <td class="table_content_td">
          </td>
          <td class="table_content_td_central">
		    <table align="center">
              <tr>
                <td colspan=3 class="linea_center"></td>
              </tr>
              <tr>
                <td colspan=3>
                  <center>
                  <table class="table_content_secundario">
                    <tr>
                      <td class="table_content_secundario_td">
                        <table class="table_form">
                          <tr>
                            <td>
				              <h2>Iniciar Sesión AAA</h2>
				            </td>
				          </tr>
				          <tr>
                            <td class="table_form_td_line"></td>
				          </tr>
				          <tr>
                            <td class="table_form_td_linewhite"></td>
				          </tr>
				          <tr>
				            <td class="table_form_td_central">
				              <form method="post" id="frmInicio" name="frmInicio" action="inicio.do">
				              	<input type="hidden" id="APP" name="APP" value="SAS">
								<input type="hidden" id="CAPP" name="CAPP" value="001">
				              	<input type="hidden" id="MOBILE" name="MOBILE" value="0">
				              	<input type="hidden" id="SCHEME" name="SCHEME" value="http">
				              	<input type="hidden" id="SERVERNAME" name="SERVERNAME" value="rimac.com.pe">
				              	<input type="hidden" id="SERVERPORT" name="SERVERPORT" value="80">
				                <table class="frm_login">
				                  <tr>
				                    <td class="td_field">
				                      <input type="hidden" id="CODUSUARIO" name="CODUSUARIO" value="<%=usuario%>" />
					                  <input type="hidden" id="CLAVE" name="CLAVE" value="<%=clave%>" />
					                   <input type="hidden" id="STATUS" name="STATUS" value="<%=status%>" />
					                </td>
				                  </tr>
				                  <tr>
					                
				                    <td colspan=2 class="td_label" style="width: 600px">
				                      <label id="lblClave"><%=mensaje%></label> 
				                    </td>
				                  </tr>
				                    
				                  <tr>
					                
				                    <td class="td_field">
				                      
				                    </td>
				                  </tr>
				                  
				                  <tr> 
				                    <td colspan=2 class="td_button">
				                      <!-- <input type="submit" name="btn_regresar" alt="regresar"  value="" onclick="javascript:login();" class="btn_regresar"> -->
				                      <!--<input type="submit" name="btn_continuar" alt="continuar"  value="" onclick="javascript:login();" class="btn_continuar"> -->
				                      <input type="button" name="btn_continuar" alt="continuar"  value="" onclick="javascript:if (login()) document.frmInicio.submit();" class="btn_continuar">
				                    
				                    </td>
				                    
				                  </tr>
				                  			                  
				                </table>
				              </form>
				            </td>
                          </tr>
                        </table>
				      </td>
				    </tr>
				  </table>
				  </center>
                </td>
              </tr>
            </table>
          </td>
          <td class="table_content_td">
          </td>
        </tr>
        <tr class="table_content_tr">
          <td colspan=3 class="linea_footer"></td>
        </tr>
      </table>
    </center>	
  </body>
	  
    <script>

	  function login(){
		var usuario = document.getElementById('CODUSUARIO');
	    var clave   = document.getElementById('CLAVE');	
	    var status   = document.getElementById('STATUS');
	    var validMsj = 0;	  
		
	    //[INI - DAVID ARGURME - 13/09/2012]
		document.getElementById('SCHEME').value = location.protocol;
		document.getElementById('SERVERNAME').value = location.hostname;
	    document.getElementById('SERVERPORT').value = location.port;
	    //[FIN - DAVID ARGURME - 13/09/2012]..
	    
		var jfLogin = {
				CODUSUARIO : usuario.value,
				CLAVE      : clave.value,
				STATUS	   : status.value
		};  

	    if ((usuario.value == null || usuario.value == '') && (validMsj == 0)){
		  alert('Debe ingresar el usuario o clave');
		  username.focus();
		  validMsj = 1;
	    }
	    if ((clave.value == null || clave.value == '') && (validMsj == 0)){
		  alert('Debe ingresar el usuario o clave');
		  clave.focus();
		  validMsj = 1;
	    }
	    return true;
	  }
	  
    </script>
  
</html>