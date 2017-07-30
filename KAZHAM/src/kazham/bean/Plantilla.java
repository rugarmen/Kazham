package kazham.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;
import java.util.List;
import java.util.Date;
import commons.mapper.ResourceManager;

public class Plantilla implements Serializable {

  /**
  *
  **/
  private static final long serialVersionUID = 1L;
  
  private BigDecimal ideplantilla;
  private String descplantilla;
  private BigDecimal ideempresa;
  private List cursor;

  public Plantilla() {}

  public Plantilla(Map map) throws Exception {
    ResourceManager.populateDtoFromMap(this, map);
  }

public BigDecimal getIdeplantilla() {
	return ideplantilla;
}

public void setIdeplantilla(BigDecimal ideplantilla) {
	this.ideplantilla = ideplantilla;
}

public String getDescplantilla() {
	return descplantilla;
}

public void setDescplantilla(String descplantilla) {
	this.descplantilla = descplantilla;
}

public BigDecimal getIdeempresa() {
	return ideempresa;
}

public void setIdeempresa(BigDecimal ideempresa) {
	this.ideempresa = ideempresa;
}

public List getCursor() {
	return cursor;
}

public void setCursor(List cursor) {
	this.cursor = cursor;
}


}

