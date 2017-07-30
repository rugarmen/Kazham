package kazham.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;
import java.util.List;
import java.util.Date;
import commons.mapper.ResourceManager;

public class ComponentePlantilla implements Serializable {

  /**
  *
  **/
  private static final long serialVersionUID = 1L;
  
  private BigDecimal idecomplantilla;
  private String desccomplantilla;
  private int orden;
  private BigDecimal ideunidad;
  private BigDecimal ideconcepto;
  private BigDecimal idemineral;
  private Double monto_psp;
  private Double monto_real;
  private String indcalculo;
  private String formula;
  private String annio;
  
  private BigDecimal ideplantilla;
  private String descplantilla;
  private BigDecimal ideempresa;
  private List cursor;
  

  public ComponentePlantilla() {}

  public ComponentePlantilla(Map map) throws Exception {
    ResourceManager.populateDtoFromMap(this, map);
  }

public BigDecimal getIdecomplantilla() {
	return idecomplantilla;
}

public void setIdecomplantilla(BigDecimal idecomplantilla) {
	this.idecomplantilla = idecomplantilla;
}

public String getDesccomplantilla() {
	return desccomplantilla;
}

public void setDesccomplantilla(String desccomplantilla) {
	this.desccomplantilla = desccomplantilla;
}

public int getOrden() {
	return orden;
}

public void setOrden(int orden) {
	this.orden = orden;
}

public BigDecimal getIdeunidad() {
	return ideunidad;
}

public void setIdeunidad(BigDecimal ideunidad) {
	this.ideunidad = ideunidad;
}

public BigDecimal getIdeconcepto() {
	return ideconcepto;
}

public void setIdeconcepto(BigDecimal ideconcepto) {
	this.ideconcepto = ideconcepto;
}

public BigDecimal getIdemineral() {
	return idemineral;
}

public void setIdemineral(BigDecimal idemineral) {
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

