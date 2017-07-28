package kazham.bean;

import java.io.Serializable;
import java.util.Map;
import java.util.List;
import java.util.Date;
import commons.mapper.ResourceManager;

public class LstTipparametro implements Serializable {

  /**
  *
  **/
  private static final long serialVersionUID = 1L;
  private String idetippar;
  private String descripcion;
  private String codexterno;
  private String indactivo;
  private String usuario;
  private List cursor;

  public LstTipparametro() {}

  public LstTipparametro(Map map) throws Exception {
    ResourceManager.populateDtoFromMap(this, map);
  }
	
	public String getIdetippar() {
		return idetippar;
	}
	
	public void setIdetippar(String idetippar) {
		this.idetippar = idetippar;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getCodexterno() {
		return codexterno;
	}
	
	public void setCodexterno(String codexterno) {
		this.codexterno = codexterno;
	}
	
	public String getIndactivo() {
		return indactivo;
	}
	
	public void setIndactivo(String indactivo) {
		this.indactivo = indactivo;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public List getCursor() {
	    return cursor;
	}

    public void setCursor(List cursor) {
      this.cursor = cursor;
    }

}

