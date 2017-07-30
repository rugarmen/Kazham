package kazham.dao;

import java.sql.Connection;

import kazham.bean.Informacion;
import kazham.bean.PeriodoMes;
import kazham.bean.Usuario;



/**
 * Versi�n	Fecha			Descripci�n
 * =======	=====			=========== 
	1.0		29/07/2017		Version Inicial del Componente 
**/

public interface KazhamDao  {

    void guardarDato(Connection conn, Usuario param);
  
    void listarPeriodo(Connection conn, PeriodoMes param);
    
    void grabarInformacion(Connection conn, Informacion param);
}


