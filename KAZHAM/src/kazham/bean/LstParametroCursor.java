package kazham.bean;

import java.io.Serializable;
import java.util.Map;
import java.util.List;
import java.util.Date;
import commons.mapper.ResourceManager;

public class LstParametroCursor implements Serializable {

  /**
  *
  **/
  private static final long serialVersionUID = 1L;
  private Integer idepar;
  private String codigo;
  private String codigoc;
  private String codigon;
  private String abreviatura;
  private String descripcion;
  private String descripcion2;
  private String indactivo;
  private String usucreacion;
  private Date feccreacion;
  private String usumodif;
  private Date fecmodif;
  private String codexterno;
  private String idetippar;
  private String idetipparpadre;
  private String refmigracion;
  private String refmigracionrs;
  private String indexrow;

  public LstParametroCursor() {}

  public LstParametroCursor(Map map) throws Exception {
    ResourceManager.populateDtoFromMap(this, map);
  }

  public Integer getIdepar() {
    return idepar;
  }

  public void setIdepar(Integer idepar) {
    this.idepar = idepar;
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

  public String getDescripcion2() {
    return descripcion2;
  }

  public void setDescripcion2(String descripcion2) {
    this.descripcion2 = descripcion2;
  }

  public String getIndactivo() {
    return indactivo;
  }

  public void setIndactivo(String indactivo) {
    this.indactivo = indactivo;
  }

  public String getUsucreacion() {
    return usucreacion;
  }

  public void setUsucreacion(String usucreacion) {
    this.usucreacion = usucreacion;
  }

  public Date getFeccreacion() {
    return feccreacion;
  }

  public void setFeccreacion(Date feccreacion) {
    this.feccreacion = feccreacion;
  }

  public String getUsumodif() {
    return usumodif;
  }

  public void setUsumodif(String usumodif) {
    this.usumodif = usumodif;
  }

  public Date getFecmodif() {
    return fecmodif;
  }

  public void setFecmodif(Date fecmodif) {
    this.fecmodif = fecmodif;
  }

  public String getIndexrow() {
    return indexrow;
  }

  public void setIndexrow(String indexrow) {
    this.indexrow = indexrow;
  }

  public String getCodigon() {
	return codigon;
  }

  public void setCodigon(String codigon) {
	this.codigon = codigon;
  }

  public String getCodigoc() {
 	return codigoc;
  }

  public void setCodigoc(String codigoc) {
	this.codigoc = codigoc;
  }

  public String getCodexterno() {
	return codexterno;
  }

  public void setCodexterno(String codexterno) {
	this.codexterno = codexterno;
  }

  public String getIdetippar() {
	return idetippar;
  }

  public void setIdetippar(String idetippar) {
	this.idetippar = idetippar;
  }

  public String getIdetipparpadre() {
	return idetipparpadre;
  }

  public void setIdetipparpadre(String idetipparpadre) {
	this.idetipparpadre = idetipparpadre;
  }

public String getRefmigracion() {
	return refmigracion;
}

public void setRefmigracion(String refmigracion) {
	this.refmigracion = refmigracion;
}

public String getRefmigracionrs() {
	return refmigracionrs;
}

public void setRefmigracionrs(String refmigracionrs) {
	this.refmigracionrs = refmigracionrs;
}

}

