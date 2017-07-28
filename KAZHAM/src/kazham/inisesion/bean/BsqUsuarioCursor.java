package kazham.inisesion.bean;

import java.io.Serializable;
import java.util.Map;
import java.util.List;
import java.util.Date;
import commons.mapper.ResourceManager;

public class BsqUsuarioCursor implements Serializable {

  /**
  *
  **/
  private static final long serialVersionUID = 1L;
  private Integer idregla; /**idepais;**/
  /**private Integer idpcontinente;**/
  private String nombre; /**dscpais;**/
  private String dscregla; /**dsccontinente;**/
  private String proceso; /**codpaisexterno;**/
  private String indactivo;
  private String dscactivo;
  private String indexrow;
 
  
   
  
  public BsqUsuarioCursor() {}

  public BsqUsuarioCursor(Map map) throws Exception {
    ResourceManager.populateDtoFromMap(this, map);
  }

  public Integer getIdregla() {
    return idregla;
  }

  public void setIdregla(Integer idregla) {
    this.idregla = idregla;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getIndexrow() {
    return indexrow;
  }

  public void setIndexrow(String indexrow) {
    this.indexrow = indexrow;
  }

public String getDscregla() {
	return dscregla;
}

public void setDscregla(String dscregla) {
	this.dscregla = dscregla;
}

public String getProceso() {
	return proceso;
}

public void setProceso(String proceso) {
	this.proceso = proceso;
}

public String getDscactivo() {
	return dscactivo;
}

public void setDscactivo(String dscactivo) {
	this.dscactivo = dscactivo;
}

public String getIndactivo() {
	return indactivo;
}

public void setIndactivo(String indactivo) {
	this.indactivo = indactivo;
}

}

