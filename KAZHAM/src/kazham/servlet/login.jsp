<%@ page import="kazham.servlet.InicioAppServlet" %>
<%@ page import="kazham.commons.Constants" %>
  <%
 
  
	String mensaje="";
  InicioAppServlet ini = new InicioAppServlet(); 
  ini.getUsuarioLogueado(request, response);
	 //if (SeguridadUtils.getUsuarioLogueado(request) != null){
	  request.getRequestDispatcher("/index.html").forward(request, response);
  	//}

%>
<!DOCTYPE HTML>
<html manifest="" lang="en-US">
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Kazham</title>

    <!-- <link href="lib/st2/resources/css/RIMAC2.css" rel="stylesheet" type="text/css" />  -->
   
    <script src="lib/st2/sencha-touch-debug.js" type="text/javascript"></script>
    
    <script src="lib.js" type="text/javascript"></script>
    <script src="lib/st2/project/global.js" type="text/javascript"></script>
    <script src="appLogin.js" type="text/javascript"></script>
 
  </head>
  <body>
  
  </body>
  
  
</html>