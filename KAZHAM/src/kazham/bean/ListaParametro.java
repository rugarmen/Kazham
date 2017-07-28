package kazham.bean;

import java.io.Serializable;
import java.util.Map;
import java.util.List;
import java.util.Date;
import commons.mapper.ResourceManager;

public class ListaParametro implements Serializable {

  /**
  *
  **/
  private static final long serialVersionUID = 1L;
  private String idetippar;
  private List cursor;

  public ListaParametro() {}

  public ListaParametro(Map map) throws Exception {
    ResourceManager.populateDtoFromMap(this, map);
  }

  public String getIdetippar() {
    return idetippar;
  }

  public void setIdetippar(String idetippar) {
    this.idetippar = idetippar;
  }

  public List getCursor() {
    return cursor;
  }

  public void setCursor(List cursor) {
    this.cursor = cursor;
  }

}

