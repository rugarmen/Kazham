package kazham.bean;

import java.io.Serializable;
import java.util.Map;
import java.util.List;
import java.util.Date;
import commons.mapper.ResourceManager;

public class ComponentePlantilla implements Serializable {

  /**
  *
  **/
  private static final long serialVersionUID = 1L;
  
  private Double idecompplantilla;
  private String desccompplantilla;
  private Double ideunidad;
  private Double ideconcepto;
  private Double idemineral;
  private Double monto_psp;
  private Double monto_real;
  private String indcalculo;
  private String formula;
  private String annio;
  private int orden;
  private Double ideplantilla;
  private String descplantilla;
  private Double ideempresa;
  private Double idecomplantilla;
  private List cursor;
  

  public ComponentePlantilla() {}

  public ComponentePlantilla(Map map) throws Exception {
    ResourceManager.populateDtoFromMap(this, map);
  }

public Double getIdecompplantilla() {
	return idecompplantilla;
}

public void setIdecompplantilla(Double idecompplantilla) {
	this.idecompplantilla = idecompplantilla;
}

public String getDesccompplantilla() {
	return desccompplantilla;
}

public void setDesccompplantilla(String desccompplantilla) {
	this.desccompplantilla = desccompplantilla;
}

public Double getIdeunidad() {
	return ideunidad;
}

public void setIdeunidad(Double ideunidad) {
	this.ideunidad = ideunidad;
}

public Double getIdeconcepto() {
	return ideconcepto;
}

public void setIdeconcepto(Double ideconcepto) {
	this.ideconcepto = ideconcepto;
}

public Double getIdemineral() {
	return idemineral;
}

public void setIdemineral(Double idemineral) {
	this.idemineral = idemineral;
}

public Double getMonto_psp() {
	return monto_psp;
}

public void setMonto_psp(Double monto_psp) {
	this.monto_psp = monto_psp;
}

public Double getMonto_real() {
	return monto_real;
}

public void setMonto_real(Double monto_real) {
	this.monto_real = monto_real;
}

public String getIndcalculo() {
	return indcalculo;
}

public void setIndcalculo(String indcalculo) {
	this.indcalculo = indcalculo;
}

public String getFormula() {
	return formula;
}

public void setFormula(String formula) {
	this.formula = formula;
}

public String getAnnio() {
	return annio;
}

public void setAnnio(String annio) {
	this.annio = annio;
}

public int getOrden() {
	return orden;
}

public void setOrden(int orden) {
	this.orden = orden;
}

public Double getIdeplantilla() {
	return ideplantilla;
}

public void setIdeplantilla(Double ideplantilla) {
	this.ideplantilla = ideplantilla;
}

public String getDescplantilla() {
	return descplantilla;
}

public void setDescplantilla(String descplantilla) {
	this.descplantilla = descplantilla;
}

public Double getIdeempresa() {
	return ideempresa;
}

public void setIdeempresa(Double ideempresa) {
	this.ideempresa = ideempresa;
}

public Double getIdecomplantilla() {
	return idecomplantilla;
}

public void setIdecomplantilla(Double idecomplantilla) {
	this.idecomplantilla = idecomplantilla;
}

public List getCursor() {
	return cursor;
}

public void setCursor(List cursor) {
	this.cursor = cursor;
}

}

