package kazham.bean;

import java.io.Serializable;
import java.util.Map;
import java.util.List;
import java.util.Date;
import commons.mapper.ResourceManager;

public class ListaParametroCursor implements Serializable {

  /**
  *
  **/
  private static final long serialVersionUID = 1L;
  private String codigo;
  private String abreviatura;
  private String indexrow;

  public ListaParametroCursor() {}

  public ListaParametroCursor(Map map) throws Exception {
    ResourceManager.populateDtoFromMap(this, map);
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

  public String getIndexrow() {
    return indexrow;
  }

  public void setIndexrow(String indexrow) {
    this.indexrow = indexrow;
  }

}

