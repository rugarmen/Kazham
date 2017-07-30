package kazham.dao;

import java.sql.Connection;

import kazham.bean.ComponentePlantilla;
import kazham.bean.DetallePlantilla;
import kazham.bean.Informacion;
import kazham.bean.PeriodoMes;
import kazham.bean.Plantilla;
import kazham.bean.Usuario;



/**
 * Versión	Fecha			Descripción
 * =======	=====			=========== 
	1.0		29/07/2017		Version Inicial del Componente 
**/

public interface KazhamDao  {

    void guardarDato(Connection conn, Usuario param);
  
    void listarPeriodo(Connection conn, PeriodoMes param);
    
    void grabarInformacion(Connection conn, Informacion param);
    
    void listarInformacion(Connection conn, Informacion param);
    
    void listarPlantilla(Connection conn, Plantilla param);
    
    void listarComponentePlantilla(Connection conn, ComponentePlantilla param);
    
    void listarComponentePlantilla(Connection conn, DetallePlantilla param);
}


