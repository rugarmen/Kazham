<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Sistema de Administración de Seguros</title>
	
    <link rel="stylesheet" type="text/css" href="kazham/login/css/login.css" />
    
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
                  <table class="table_content_secundario_out">
                    <tr>
                      <td class="table_content_secundario_td">
                        <table class="table_form">
                          <tr>
                            <td>
				              <h2>Sesión Finalizada</h2>
				            </td>
				          </tr>
				          <tr>
                            <td class="table_form_td_line"></td>
				          </tr>
				          <tr>
                            <td class="table_form_td_linewhite"></td>
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
  	setTimeout(function(){
  		<%
	  		request.getRequestDispatcher("/login.jsp").forward(request, response);
  	    %>
  	}, 10000);
  </script>  
</html>