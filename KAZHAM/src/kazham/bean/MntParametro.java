package kazham.bean;

import java.io.Serializable;
import java.util.Map;
import java.util.List;
import java.util.Date;
import commons.mapper.ResourceManager;

public class MntParametro implements Serializable {

  /**
  *
  **/
  private static final long serialVersionUID = 1L;
  private Integer idepar;
  private String idetippar;
  private Integer codigon;
  private String codigoc;
  private String abreviatura;
  private String descripcion;
  private String descripcion2;
  private String indactivo;
  private String usucreacion;
  private String usumodif;
  private String codexterno;
  private String idetipparpadre;
  private String refmigracion;
  private String refmigracionrs;

  public MntParametro() {}

  public MntParametro(Map map) throws Exception {
    ResourceManager.populateDtoFromMap(this, map);
  }

  public Integer getIdepar() {
    return idepar;
  }

  public void setIdepar(Integer idepar) {
    this.idepar = idepar;
  }

  public String getIdetippar() {
    return idetippar;
  }

  public void setIdetippar(String idetippar) {
    this.idetippar = idetippar;
  }

  public Integer getCodigon() {
    return codigon;
  }

  public void setCodigon(Integer codigon) {
    this.codigon = codigon;
  }

  public String getCodigoc() {
    return codigoc;
  }

  public void setCodigoc(String codigoc) {
    this.codigoc = codigoc;
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

  public String getUsumodif() {
    return usumodif;
  }

  public void setUsumodif(String usumodif) {
    this.usumodif = usumodif;
  }

  public String getCodexterno() {
    return codexterno;
  }

  public void setCodexterno(String codexterno) {
    this.codexterno = codexterno;
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

