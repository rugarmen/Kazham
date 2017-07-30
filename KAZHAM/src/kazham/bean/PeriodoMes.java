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
  private String ideperiodo;
  private String descperiodo;
  private List cursor;

  public PeriodoMes() {}

  public PeriodoMes(Map map) throws Exception {
    ResourceManager.populateDtoFromMap(this, map);
  }

public String getIdeperiodo() {
	return ideperiodo;
}

public void setIdeperiodo(String ideperiodo) {
	this.ideperiodo = ideperiodo;
}

public String getDescperiodo() {
	return descperiodo;
}

public void setDescperiodo(String descperiodo) {
	this.descperiodo = descperiodo;
}

public List getCursor() {
	return cursor;
}

public void setCursor(List cursor) {
	this.cursor = cursor;
}


}

