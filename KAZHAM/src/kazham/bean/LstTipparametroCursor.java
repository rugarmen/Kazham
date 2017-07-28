package kazham.bean;

import java.io.Serializable;
import java.util.Map;
import java.util.List;
import java.util.Date;
import commons.mapper.ResourceManager;

public class LstTipparametroCursor implements Serializable {

  /**
  *
  **/
  private static final long serialVersionUID = 1L;
  private String idetippar;
  private String descripcion;
  private String codexterno;
  private String indactivo;
  private String usucreacion;
  private Date feccreacion;
  private String usumodif;
  private Date fecmodif;
  private Integer longitudcampo;
  private String tipodato;
  private String indexrow;

  public LstTipparametroCursor() {}

  public LstTipparametroCursor(Map map) throws Exception {
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

	public String getIndexrow() {
	    return indexrow;
	}
	
	public void setIndexrow(String indexrow) {
	    this.indexrow = indexrow;
	}

}

