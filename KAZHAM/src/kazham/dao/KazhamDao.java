package kazham.dao;

import java.sql.Connection;
import kazham.bean.Usuario;



/**
 * Funcion: clase dao de la clase util 
 * Creaci�n:  - 
 * Modificaciones:
 * 					1.0								Version Inicial del Componente
 * SCASTANEDM		2.0			31/12/2014			Se creo el m�todo: 
 * 													- obtTipoCambio
 */

public interface KazhamDao  {

    void guardarDato(Connection conn, Usuario param);
  
}


