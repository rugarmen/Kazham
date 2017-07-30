package kazham.dao;

import java.sql.Connection;

import kazham.bean.PeriodoMes;
import kazham.bean.Usuario;


import org.json.simple.JSONObject;



/**
 * Funcion: clase dao de la clase util 
 * Creación:  - 
 * Modificaciones:
 * 					1.0								Version Inicial del Componente
 * SCASTANEDM		2.0			31/12/2014			Se creo el método: 
 * 													- obtTipoCambio
 */

public interface KazhamDao  {

    void guardarDato(Connection conn, Usuario param);
  
    void listarPeriodo(Connection conn, PeriodoMes param);
}


