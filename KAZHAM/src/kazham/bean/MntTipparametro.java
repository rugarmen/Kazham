package kazham.bean;

import java.io.Serializable;
import java.util.Map;
import java.util.List;
import java.util.Date;
import commons.mapper.ResourceManager;

public class MntTipparametro implements Serializable {

  /**
  *
  **/
  private static final long serialVersionUID = 1L;
  private String idetippar;
  private String descripcion;
  private String codexterno;
  private String indactivo;
  private Integer longitudcampo;
  private String tipodato;
  private String usuario;
  private String fgnew;
  
  public MntTipparametro() {}

  public MntTipparametro(Map map) throws Exception {
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
	
	public Integer getLongitudcampo() {
		return longitudcampo;
	}
	
	public void setLongitudcampo(Integer longitudcampo) {
		this.longitudcampo = longitudcampo;
	}
	
	public String getTipodato() {
		return tipodato;
	}

	public void setTipodato(String tipodato) {
		this.tipodato = tipodato;
	}

	public String getUsuario() {
		return usuario;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getFgnew() {
		return fgnew;
	}

	public void setFgnew(String fgnew) {
		this.fgnew = fgnew;
	}

}

