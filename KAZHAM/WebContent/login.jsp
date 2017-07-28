<%@page import="kazham.inisesion.bean.BsqUsuario"%>
<%
	/*----------------------------------------------------------------------------------------------------------------
	 Función				:	Logeo de usuarios

	 Bitácora de cambios	:	

	 Versión				Fecha				Autor				Descripción
	 ==============		==========			================	================================================
	 1.0				26/07/2017			Kazham				Inicio del componente.
	 ----------------------------------------------------------------------------------------------------------------*/
%>
<%@ page import="kazham.controller.KazhamController" %>
<%@ page import="kazham.commons.Constants" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Kazham</title>
<link rel="stylesheet" type="text/css" href="kazham/login/css/login.css" />
<%
	String usuarioLogeado = null;
	try {
		//KazhamController k = new KazhamController(); 
		//usuarioLogeado =  KazhamController.obtenerUsuarioLogueado(request, response);
	} catch (Exception e) {
		usuarioLogeado = null;
	}
	
	String usuario = "";
	String mensaje = "";
	String cambioClave = "0";
	String blectura ="";
	String msgnodisponibilidad = "";
	//String msgnodisponibilidad = SeguridadUtils.obtMsgNoDisponibilidad(request);
	//if (usuarioLogeado != null) {
		//request.getRequestDispatcher("/index.html").forward(request,response);
	//} else {
	//}
%>

</head>
<body>
	
	
<div id="id01" class="modal">
  
  <form class="modal-content animate" method="post" id="frmInicio" name="frmInicio"
																action="inicio.do">
    
    <div class="imgcontainer">
      <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
      <img src="images/img_avatar1.png" alt="Avatar" class="avatar">
    </div>

    <div class="container">
    <input type="hidden" id="APP" name="APP" value="SAS">
	<input type="hidden" id="CAPP" name="CAPP" value="001">
	<input type="hidden" id="SCHEME" name="SCHEME"
		value="http:"> <input type="hidden"
		id="SERVERNAME" name="SERVERNAME" value="rimac.com.pe">
	<input type="hidden" id="SERVERPORT" name="SERVERPORT"
		value="80">
																	
      <label id="lblUsername"><b>Username</b></label>
      
      <input type="text" placeholder="Enter Username"  value="<%=usuario%>" name="CODUSUARIO"
		 maxlength="15" required >
     
      <label id="lblClave"><b>Password</b></label>
      <input type="password" placeholder="Enter Password" value="" name="CLAVE" maxlength="15"
	  class="cl_input" onkeypress="javascript:enterSAS(event);" required>
        
      <button type="submit" name="btn_ingresar"
																			id="btningresar" alt="Ingresar" value=" "
																			onclick="javascript:if (login()) document.frmInicio.submit();"
																			>Login</button>
      
    <tr>
																		<td colspan=2 class="td_label"><label
																			id="lblClave"><%=mensaje%></label></td>
																	</tr>
																	
    </div>

	<!-- 
    <div class="container" style="background-color:#f1f1f1">
      <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
      <span class="psw">Forgot <a href="#">password?</a></span>
    </div>
    -->
  </form>
</div>

</body>

</script>
<script>

	document.getElementById('CODUSUARIO').focus();

	function login() {
		var usuario = document.getElementById('CODUSUARIO');
		var clave = document.getElementById('CLAVE');
		var flagcambioclave = null;
		var confirmaclave = null;
		var nuevaclave = null;

		if (document.getElementById('FLAGCAMBIOCLAVE') !== null) {
			flagcambioclave = document.getElementById('FLAGCAMBIOCLAVE');
			confirmaclave = document.getElementById('CONFIRMARCLAVE');
			nuevaclave = document.getElementById('NUEVACLAVE');
		}

		var validMsj = 0;

		//[INI - DAVID ARGURME - 13/09/2012]
		document.getElementById('SCHEME').value = location.protocol;
		document.getElementById('SERVERNAME').value = location.hostname;
		document.getElementById('SERVERPORT').value = location.port;
		//[FIN - DAVID ARGURME - 13/09/2012]

		var jfLogin = {
			CODUSUARIO : usuario.value,
			CLAVE : clave.value
		};

		if ((usuario.value == null || usuario.value == '') && (validMsj == 0)) {
			alert('Debe ingresar el usuario o clave');
			username.focus();
			validMsj = 1;
			return false;
		}
		if ((clave.value == null || clave.value == '') && (validMsj == 0)) {
			alert('Debe ingresar el usuario o clave');
			clave.focus();
			validMsj = 1;
			return false;
		}
		if ((flagcambioclave != null)) {
			if (flagcambioclave.value == '2') {
				if ((nuevaclave.value == null || nuevaclave.value == '')
						&& (validMsj == 0)) {
					alert('Debe ingresar la nueva clave');
					clave.focus();
					validMsj = 1;
					return false;
				}
				if ((confirmaclave.value == null || confirmaclave.value == '')
						&& (validMsj == 0)) {
					alert('Debe ingresar la clave para confirmar');
					clave.focus();
					validMsj = 1;
					return false;

					if (nuevaclave.value !== confirmaclave.value) {
						alert('Debe ingresar la nueva clave');
						clave.focus();
						validMsj = 1;
						return false;
					}

				}
			}
		}

		// [INI - HA - 14/08/2013]
		//var userCookie = obtenerUserCookie();

		//if (userCookie != null && userCookie != ''
				//&& userCookie != usuario.value) {
			//alert('Ya se ha logueado con otro usuario. Cierre su session anterior y vuelva a intentarlo.');
			//return false;
		//}
		// [FIN - HA - 14/08/2013]

		var btningresar = document.getElementById('btningresar');

		if (btningresar.style.visibility == 'hidden') {
			return false;
		}

		btningresar.style.visibility = "hidden";

		return true;
	}
	//[INI - JCASIANOC - 28/01/2013]
	function enterSAS(e) {

		var keycode;
		if (window.event)
			keycode = window.event.keyCode;//IE
		else if (e)
			keycode = e.which;//otros
		else
			return true;
		if (keycode == 13) {
			if (login())
				document.frmInicio.submit();
		}

	}
	//[FIN - JCASIANOC - 28/01/2013]

	// [INI - HA - 14/08/2013]
	function obtenerUserCookie() {
		var nombre = 'KAZHAM_CODUSUARIO_COOKIE';
		var userCookie = '';
		var comienzo;
		var cantidad;

		var cookies = document.cookie;

		if (cookies) {
			comienzo = cookies.indexOf(nombre);
			if (comienzo != -1) {
				comienzo = comienzo + nombre.length + 1;
				cantidad = cookies.indexOf("; ", comienzo) - comienzo;
				if (cantidad <= 0)
					cantidad = cookies.length;
				userCookie = cookies.substr(comienzo, cantidad);
			}
		}

		return userCookie;
	}
	// [FIN - HA - 14/08/2013]
</script>

</html>