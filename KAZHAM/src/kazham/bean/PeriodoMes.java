package kazham.bean;

import java.io.Serializable;
import java.util.Map;
import java.util.List;
import java.util.Date;
import commons.mapper.ResourceManager;

public class PeriodoMes implements Serializable {

  /**
  *
  **/
  private static final long serialVersionUID = 1L;
  private String idetippar;
  private String codigo;
  private String abreviatura;
  private String descripcion;
  private List cursor;

  public PeriodoMes() {}

  public PeriodoMes(Map map) throws Exception {
    ResourceManager.populateDtoFromMap(this, map);
  }

public String getIdetippar() {
	return idetippar;
}

public void setIdetippar(String idetippar) {
	this.idetippar = idetippar;
}

public String getCodigo() {
	return codigo;
}

public void setCodigo(String codigo) {
	this.codigo = codigo;
}

public String getAbreviatura() {
	return abreviatura;
}

public void setAbreviatura(String abreviatura) {
	this.abreviatura = abreviatura;
}

public String getDescripcion() {
	return descripcion;
}

public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}

public List getCursor() {
	return cursor;
}

public void setCursor(List cursor) {
	this.cursor = cursor;
}


}

