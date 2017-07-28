package kazham.bean;

import java.io.Serializable;
import java.util.Map;
import java.util.List;
import java.util.Date;
import commons.mapper.ResourceManager;

public class LstParametro implements Serializable {

  /**
  *
  **/
  private static final long serialVersionUID = 1L;
  private String idetippar;
  private String idetipparpadre;
  private String codigo;
  private String abreviatura;
  private String descripcion;
  private String descripcion2;
  private String indactivo;
  private String masterdetail;
  private List cursor;

  public LstParametro() {}

  public LstParametro(Map map) throws Exception {
    ResourceManager.populateDtoFromMap(this, map);
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

  public String getMasterdetail() {
    return masterdetail;
  }

  public void setMasterdetail(String masterdetail) {
    this.masterdetail = masterdetail;
  }

  public List getCursor() {
    return cursor;
  }

  public void setCursor(List cursor) {
    this.cursor = cursor;
  }

}

