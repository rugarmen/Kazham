package kazham.bean;

import java.io.Serializable;
import java.util.Map;
import java.util.List;
import java.util.Date;
import commons.mapper.ResourceManager;

public class RegUsuarioxOpcion implements Serializable {

  private static final long serialVersionUID = 1L;
  private String  codusuario;
  private Integer codaplicacion;
  private String  dscaplicacion;
  private Integer codopcion;
  private String  dscopcion;
  private Integer idelog;
  private String result;

public Integer getIdelog() {
	return idelog;
}

public void setIdelog(Integer idelog) {
	this.idelog = idelog;
}

public RegUsuarioxOpcion() {}

  public RegUsuarioxOpcion(Map map) throws Exception {
    ResourceManager.populateDtoFromMap(this, map);
  }

public Integer getCodaplicacion() {
	return codaplicacion;
}

public void setCodaplicacion(Integer codaplicacion) {
	this.codaplicacion = codaplicacion;
}

public String getDscaplicacion() {
	return dscaplicacion;
}

public void setDscaplicacion(String dscaplicacion) {
	this.dscaplicacion = dscaplicacion;
}

public Integer getCodopcion() {
	return codopcion;
}

public void setCodopcion(Integer codopcion) {
	this.codopcion = codopcion;
}

public String getDscopcion() {
	return dscopcion;
}

public void setDscopcion(String dscopcion) {
	this.dscopcion = dscopcion;
}

public String getCodusuario() {
	return codusuario;
}

public void setCodusuario(String codusuario) {
	this.codusuario = codusuario;
}

public String getResult() {
	return result;
}

public void setResult(String result) {
	this.result = result;
}
}

