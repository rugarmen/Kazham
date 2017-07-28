package kazham.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//import sas.seguridad.commons.SeguridadUtils;

/**
 * Servlet Filter implementation class PaginaPincipalFilter
 */
public class PaginaInicioFilter implements Filter {
	
    /**
     * Default constructor. 
     */
    public PaginaInicioFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String usuarioLogeado = null;
	 	
		try {
//	 		usuarioLogeado = SeguridadUtils.getUsuarioLogueado(request);
	 	} catch(Exception e) {
	 		usuarioLogeado = null;
	 	}
		
		if (usuarioLogeado != null){
			chain.doFilter(request, response);
	  	} else {
	  		request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
